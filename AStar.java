import java.util.*;

public class AStar {
  //h is the straight-line distance from the pq city to destination
  public static void main(String[] args){

    //initialize the graph
    Node nodeA = new Node("A", 32, -82);
    Node nodeB = new Node("B", 32, -82);
    Node nodeC = new Node("C", 32, -82);
    Node nodeD = new Node("D", 32, -82);
    Node nodeE = new Node("E", 32, -82);

    //initialize the edges
    //A
    nodeA.adjacencies = new Edge[]{
            new Edge(nodeB,nodeA.findDistance(nodeB)),
            new Edge(nodeD,nodeA.findDistance(nodeD))
    };
    //B
    nodeB.adjacencies = new Edge[]{
            new Edge(nodeA,nodeB.findDistance(nodeA)),
            new Edge(nodeC,nodeB.findDistance(nodeC))
    };
    //C
    nodeC.adjacencies = new Edge[]{
            new Edge(nodeB,nodeC.findDistance(nodeB)),
            new Edge(nodeD,nodeC.findDistance(nodeD)),
            new Edge(nodeE, nodeC.findDistance(nodeE))
    };
    //D
    nodeD.adjacencies = new Edge[]{
            new Edge(nodeA,nodeD.findDistance(nodeA)),
            new Edge(nodeE,nodeD.findDistance(nodeE)),
            new Edge(nodeC,nodeD.findDistance(nodeC)),
    };
    //E
    nodeE.adjacencies = new Edge[]{
            new Edge(nodeD,nodeE.findDistance(nodeD)),
            new Edge(nodeC,nodeE.findDistance(nodeC))
    };

    AStarSearch(nodeA,nodeE);
    List<Node> path = printPath(nodeE);
    System.out.println("Path: " + path);
  }

  private static List<Node> printPath(Node target){
    List<Node> path = new ArrayList<Node>();

    for(Node node = target; node!=null; node = node.getParent()){
      path.add(node);
    }
    Collections.reverse(path);
    return path;
  }

  private static void AStarSearch(Node source, Node goal){

    //Initialize the closedList
    Set<Node> closedList = new HashSet<Node>();

    // Initialize the openList and put the starting node on the list
    PriorityQueue<Node> openList = new PriorityQueue<Node>(20, new
            Comparator<Node>(){
      //override compare method
      public int compare(Node i, Node j){
        if (i.getF() > j.getF()) return 1;
        if (j.getF() > i.getF()) return -1;
        return 0;
      }
    });
    //source.setG(0);
    //source.setF(0);
    openList.add(source);

    // While the openList is not empty and not GOOOOOOOOOAL
    boolean found = false;
    while((!openList.isEmpty()) && (!found)){
      // Remove the node with the minimum f on the openList
      Node pq = openList.poll();

      // Put pq on the closedList
      closedList.add(pq);

      // If goal found
      if(pq.getNodeID().equals(goal.getNodeID())){
        found = true;
      }

      // Check every child of pq node
      for(Edge e : pq.adjacencies){
        Node child = e.getTarget();
        double temp_g = pq.getG() + e.getCost();
        double temp_f = temp_g + child.getH();

        // If successor is in the openList and the newer f is higher, skip
        if((closedList.contains(child)) && (temp_f >= child.getF())){
          continue;
        }

        // Else if successor is in the closedList or newer f is lower
        else if((!openList.contains(child)) || (temp_f < child.getF())){
          child.setParent(pq);
          child.setG(temp_g);
          child.setF(temp_f);

          if(openList.contains(child)){
            openList.remove(child);
          }
          openList.add(child);
        }
      }
    }
  }
}