import javax.swing.*;

public class PopUp {

  public void infoBox(String infoMessage, String titleBar) {
    JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
  }
}
