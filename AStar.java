import java.util.*;

public class AStar {
  //h is the straight-line distance from the pq city to destination
  public static void main(String[] args){

    //initialize the graph
    Node nodeS = new Node("S", 0, 0, 7);
    Node nodeA = new Node("A", 0, 0, 6);
    Node nodeB = new Node("B", 0 ,0, 2);
    Node nodeC = new Node("C", 0, 0, 1);
    Node nodeG = new Node("G", 0 ,0, 0);

    //initialize the edges
    nodeS.adjacencies = new Edge[]{
            new Edge(nodeA, 1),
            new Edge(nodeB, 4)
    };
    nodeA.adjacencies = new Edge[]{
            new Edge(nodeB, 2),
            new Edge(nodeC, 5),
            new Edge(nodeG, 12)
    };
    nodeB.adjacencies = new Edge[]{
            new Edge(nodeC, 2)
    };
    nodeC.adjacencies = new Edge[]{
            new Edge(nodeG, 3)
    };

    Node target = nodeG;

    AStarSearch(nodeS,target);
    List<Node> path = printPath(target);
    System.out.println("Path: " + path);
    System.out.println("Cost: " + target.getG());
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
    PriorityQueue<Node> openList = new PriorityQueue<Node>(11, new
            Comparator<Node>(){
      //override compare method
      public int compare(Node i, Node j){
        if (i.getG() > j.getG()) return 1;
        if (j.getG() > i.getG()) return -1;
        return 0;
      }
    }
    );
    openList.add(source);

    // While the openList is not empty and not GOOOOOOOOOAL
    boolean found = false;
    while((!openList.isEmpty()) && (!found)){

      // Remove the node with the minimum f on the openList
      Node pq = openList.poll();

      // If goal found
      if(pq.getNodeID().equals(goal.getNodeID())){
        found = true;
        break;
      }

      // Check every successor of pq node
      for(Edge e : pq.adjacencies){
        Node successor = e.getTarget();
        successor.setParent(pq);

        // Calculate g, h, f
        double temp_g = pq.getG() + e.getCost();
        successor.setG(temp_g);
//        if (successor.getH() == -1){
//          successor.setH(goal);
//        }
        double temp_f = temp_g + successor.getH();
        successor.setF(temp_f);

        // If successor is in the openList and that node's f < successor.f, skip
        if((openList.contains(successor)) && (temp_f <= successor.getF())){
          continue;
        }

        // If successor is in the closedList or newer f is lower
        if((closedList.contains(successor)) && (temp_f > successor.getF())) {
          successor.setParent(pq);
          successor.setG(temp_g);
          successor.setF(temp_f);
        }
        else{
          openList.add(successor);
        }
      }
      // Put pq on the closedList
      closedList.add(pq);
    }
  }
}