/**
 * Created by Genevieve on 11/27/2016.
 */
import java.util.*;

public class LeachGp3 {

  private static int[][] adj; //adjacency matrix
  private static String[] nameLookup; //

  public static void main(String[] args) {
    //part 1: implement directed graph from image
    partOne();
    System.out.println();

    //part 2: add 1 vertex, delete 1 vertex, add 2 edges
    System.out.println("Part 2:");
    Scanner kb = new Scanner(System.in);
    System.out.print("Vertex to delete: ");
    deleteVertex(kb.nextLine().toUpperCase());
    System.out.print("Vertex to add: ");
    addVertex(kb.nextLine().toUpperCase());
    System.out.print("Edge to add: ");
    addEdge(kb.nextLine().toUpperCase());
    System.out.print("Edge to add: ");
    addEdge(kb.nextLine().toUpperCase());
    System.out.println();

    //part 3: print list of all edges and weights with one line for each vertex. list all edges from that vertex along with their weights.
    System.out.println("Part 3:");
    printList();
    System.out.println();

    //part 4: print a list of all vertices which can be reached from A. this includes all vertices, not just adjacent vertices.
    System.out.println("Part 4:");
    printListA();
    System.out.println();

    //part 5: find shortest paths from A to every other vertex (for which there is a path)
    System.out.println("Part 5:");
    printShortestPaths();
    System.out.println();
  }

  private static void partOne() {
    adj = new int[13][13];
    nameLookup = new String[13];
    addVertex("A");
    addVertex("B");
    addVertex("C");
    addVertex("D");
    addVertex("E");
    addVertex("F");
    addVertex("G");
    addVertex("H");
    addVertex("K");
    addVertex("L");
    addVertex("M");
    addVertex("N");
    addEdge("A 2 B");
    addEdge("B 3 C");
    addEdge("B 1 F");
    addEdge("C 2 D");
    addEdge("C 2 G");
    addEdge("E 3 A");
    addEdge("E 2 F");
    addEdge("F 1 L");
    addEdge("G 3 H");
    addEdge("G 2 F");
    addEdge("H 2 N");
    addEdge("K 1 E");
    addEdge("K 3 L");
    addEdge("L 1 M");
    addEdge("M 1 N");
  }

  private static void deleteVertex(String s) {
    s.trim();
    int index = Arrays.asList(nameLookup).indexOf(s);
    for (int i = 0; i < nameLookup.length; i++) {
      adj[i][index] = 0;
    } // deletes edges
    for (int i = 0; i < nameLookup.length; i++) {
      adj[index][i] = 0;
    } // deletes edges
    nameLookup[index] = null; //deletes vertex
  }

  private static void addVertex(String s) {
    s.trim();
    for(int i = 0; i < nameLookup.length; i++) {
      if(nameLookup[i] != null) {
        continue; //already have a vertex there, keep going until an unfilled vertex
      } else {
        nameLookup[i] = s;
        break;
      }
    }
  }

  private static void addEdge(String s) {
    s.trim();
    String[] parts = s.split(" "); // [vertex, weight, nextVertex]
    adj[findIndex(parts[0])][findIndex(parts[2])] = Integer.parseInt(parts[1]);
  }

  private static void printList() {
    for (int i = 0; i < nameLookup.length - 1 ; i++) {
      for (int j = 0; j < nameLookup.length - 1 ; j++) {
        if (adj[i][j] != 0) {
          System.out.print(nameLookup[i] + " " + adj[i][j] + " " + nameLookup[j] + ", ");
        } else {
          continue;
        }
      }
      System.out.println(); // separates vertices for each line, if no edges it will just print a blank line
    }
  }

  static void dijkstra(int graph[][], int src, boolean part4, boolean part5)
  {
    int dist[] = new int[nameLookup.length]; // hold the shortest distance from src to i

    // vstd[i] will true if vertex i is included in shortest
    // path tree or shortest distance from src to i is finalized
    Boolean vstd[] = new Boolean[nameLookup.length];

    // Initialize all distances as INFINITE and vstd[] as false
    for (int i = 0; i < nameLookup.length; i++)
    {
      dist[i] = Integer.MAX_VALUE;
      vstd[i] = false;
    }

    // Distance of source vertex from itself is always 0
    dist[src] = 0;

    // Find shortest path for all vertices
    for (int count = 0; count < nameLookup.length-1; count++)
    {
      // Pick the minimum distance vertex from the set of vertices not yet visited. u is always equal to src in first iteration.
      int u = minDistance(dist, vstd);

      // Mark the picked vertex as visited
      vstd[u] = true;

      // Update dist value of the adjacent vertices of the
      // picked vertex.
      for (int v = 0; v < nameLookup.length; v++)

        // Update dist[v] only if is not in vstd, there is an
        // edge from u to v, and total weight of path from src to
        // v through u is smaller than current value of dist[v]
        if (!vstd[v] && graph[u][v]!=0 &&
                dist[u] != Integer.MAX_VALUE &&
                dist[u]+graph[u][v] < dist[v])
          dist[v] = dist[u] + graph[u][v];
    }

    if(part4) {
      printVertices(dist, nameLookup.length);
    }
    if(part5) {
      printSolution(dist, nameLookup.length);
    }
  }

  private static void printVertices(int[] dist, int length) {
    for (int i = 0; i < nameLookup.length-1; i++) {
      if (dist[i] == 0) {
        continue;
      } else if (dist[i] == Integer.MAX_VALUE) {
        continue;
      } else {
        System.out.print(findVertexName(i) + ", ");
      }
    }
    System.out.println();
  }

  static int minDistance(int dist[], Boolean vstd[])
  {
    // Initialize min value
    int min = Integer.MAX_VALUE, min_index=-1;

    for (int v = 0; v < nameLookup.length; v++)
      if (vstd[v] == false && dist[v] <= min)
      {
        min = dist[v];
        min_index = v;
      }

    return min_index;
  }

  static void printSolution(int dist[], int n)
  {
    System.out.println("Paths from A:");
    for (int i = 1; i < nameLookup.length-1; i++) {
      if (dist[i] == 0) {
        System.out.println("To " + findVertexName(i) + ": no path");
      } else if (dist[i] == Integer.MAX_VALUE) {
        System.out.println("To " + findVertexName(i) + ": no path");
      } else {
        System.out.println("To " + findVertexName(i) + ": length " + dist[i]);
      }
    }
  }

  private static void printListA() {
    dijkstra(adj,findIndex("A"),true,false);
  }

  private static void printShortestPaths() {
    dijkstra(adj,findIndex("A"),false,true);
  }

  private static int findIndex (String s) {
    s.trim();
    for (int i = 0; i < nameLookup.length - 1; i++) {
      if(nameLookup[i].equals(s)){
        return i;
      } else {
        continue;
      }
    }
    return -1; // not found
  }

  private static String findVertexName (int index) {
    return nameLookup[index];
  }
}