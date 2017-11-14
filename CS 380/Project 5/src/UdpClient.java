import java.io.InputStream;
import java.net.Socket;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Random;

public class UdpClient {
  public static void main(String[] args) throws Exception {
    try (Socket socket = new Socket("18.221.102.182", 38005)) {
      OutputStream os = socket.getOutputStream();
      InputStream is = socket.getInputStream();
      Random random = new Random();
      byte[] deadBeef = {(byte) 0xDE, (byte) 0xAD, (byte) 0xBE, (byte) 0xEF};
      byte[] handshake = createIpv4(24, deadBeef);
      os.write(handshake);
      int response = is.read() << 24 | is.read() << 16 | is.read() << 8 | is.read();
      System.out.print("Handshake response: ");
      System.out.printf("0x%X\n", response);
      if( response != 0xCAFEBABE ){
        throw new Exception( "bad response" );
      }
      int byteOne = is.read();
      int byteTwo = is.read();
      int combinedBytes = byteOne << 8 | byteTwo;
      System.out.printf("Port number received: %d\n", combinedBytes);

      long meanRTT = 0;

      for (int i = 2; i <= Math.pow(2,12); i*=2) {
        System.out.printf("Sending packet with %d bytes of data\n", i);
        byte[] data = new byte[i];
        random.nextBytes(data);
        os.write(createUDP(i, data, combinedBytes));
        long begin = System.currentTimeMillis();
        response = is.read() << 24 | is.read() << 16 | is.read() << 8 | is.read();
        System.out.print("Response: ");
        System.out.printf("0x%X\n", response);
        long end = System.currentTimeMillis();
        long elapsedTime = end-begin;
        System.out.printf( "RTT: %dms\n\n", elapsedTime );
        meanRTT += elapsedTime;
        if( response != 0xCAFEBABE ){
          throw new Exception( "bad response" );
        }
      }

      System.out.println("Average RTT: " + meanRTT/12 + "ms");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static short checksum(byte[] b) {
    //if the array length is odd
    if((b.length % 2) != 0) {
      byte[] bOdd = new byte[b.length+1];
      System.arraycopy(b, 0, bOdd, 0, b.length);
      bOdd[bOdd.length-1] = 0;
      b = bOdd;
    }
    int sum = 0;
    for (int i = 0; (i + 1) < b.length; i += 2) {
      int first = b[i];
      if (first < 0) {
        first ^= 0xFFFFFF00;
      }
      int second = b[i+1];
      if (second < 0) {
        second^= 0xFFFFFF00;
      }
      first <<= 8;
      sum += (first ^ second);
      // overflow detection
      if ((sum & 0xFFFF0000) != 0) {
        /*carry occurred, so wrap around */
        sum &= 0xFFFF;
        sum++;
      }
    }
    return (short)(~(sum & 0xFFFF));
  }

  private static byte[] createUDP(int dataLength, byte[] data, int portNumber) throws Exception {
    String protocol = "00010001"; //8
    String emptyChecksum = "0000000000000000"; //16
    String sourceAddr = "10000110010001111111100110000111"; //32
    String destinationAddr = "00010010110111010110011010110110"; //32
    String sourcePort = "0101010110101010";
    String destPort = String.format( "%16s", Integer.toBinaryString( portNumber ) ).replace( ' ', '0' );
    String udpLength = String.format( "%16s", Integer.toBinaryString( (int)dataLength + 8 ) ).replace(' ', '0');
    String pseudoHeaderString = sourceAddr + destinationAddr + "00000000" + protocol + udpLength + sourcePort + destPort + udpLength + emptyChecksum;
    byte[] pseudoHeader = new byte[20 + data.length];
    for( int i = 0; i < 20; ++i ){
      pseudoHeader[i] = (byte)Integer.parseInt( pseudoHeaderString.substring( i * 8, ( i + 1 ) * 8 ), 2 );
    }
    System.arraycopy( data, 0, pseudoHeader, 20, data.length );
    byte[] checksum = ByteBuffer.allocate( 2 ).putShort( checksum( pseudoHeader) ).array();
    String udpPacketString = sourcePort + destPort + udpLength;
    byte[] udpPacket = new byte[8 + data.length];
    for( int i = 0; i < 6; ++i ){
      udpPacket[i] = (byte)Integer.parseInt( udpPacketString.substring( i * 8, ( i + 1 ) * 8 ), 2 );
    }
    System.arraycopy( checksum, 0, udpPacket, 6, checksum.length );
    System.arraycopy( data, 0, udpPacket, 8, data.length );
    byte[] output = createIpv4(20+udpPacket.length, udpPacket);
    return output;
  }

  private static byte[] createIpv4(int dataLength, byte[] data) throws Exception {
    String version = "0100"; //4
    String hLen = "0101"; //4
    String tos = "00000000"; //8
    //length
    String ident = "0000000000000000"; //16
    String flags = "010"; //3
    String offset = "0000000000000"; //13
    String ttl = "00110010"; //8
    String protocol = "00010001"; //8
    String emptyChecksum = "0000000000000000"; //16
    String sourceAddr = "10000110010001111111100110000111"; //32
    String destinationAddr = "00010010110111010110011010110110"; //32
    //data

    String length = Integer.toBinaryString(dataLength);
    while (length.length() != 16) {
      length = "0" + length;
    }
    String headerString = (version + hLen + tos + length + ident + flags + offset + ttl + protocol + emptyChecksum + sourceAddr + destinationAddr);
    byte[] header = new byte[20];
    for (int i = 0; i < 20; ++i) {
      header[i] = (byte) Integer.parseInt(headerString.substring(i * 8, (i + 1) * 8), 2);
    }
    byte[] checksum = ByteBuffer.allocate(2).putShort(checksum(header)).array();
    header[10] = checksum[0];
    header[11] = checksum[1];
    //Add header and data
    byte[] output = new byte[header.length + data.length];
    System.arraycopy(header, 0, output, 0, header.length);
    System.arraycopy(data, 0, output, header.length, data.length);
    return output;
  }
}