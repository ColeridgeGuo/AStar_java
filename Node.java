public class Node{

  private final String nodeID;
  private double g; // cost so far
  private double h; // heuristic cost to destination
  private double f; // estimated total cost of path
  private double latitude;
  private double longitude;
  public Edge[] adjacencies;
  private Node parent;

  public Node(String nodeID, double latitude, double longitude){
    this.nodeID = nodeID;
    this.latitude = latitude;
    this.longitude = longitude;
    this.h = -1;
  }

  public String getNodeID() {
    return nodeID;
  }

  public double getG() {
    return g;
  }
  public void setG(double g) {
    this.g = g;
  }

  public double getH() {
    return h;
  }
  public void setH(Node d) {
    this.h = Math.sqrt(Math.pow(this.latitude - d.latitude, 2) + Math.pow
            (this.longitude - d.longitude, 2));
  }

  public double getF() {
    return f;
  }
  public void setF(double f) {
    this.f = f;
  }

  public double getLatitude() {
    return latitude;
  }
  public double getLongitude() {
    return longitude;
  }

  public Node getParent() {
    return parent;
  }
  public void setParent(Node parent) {
    this.parent = parent;
  }

  @Override public String toString(){
    return nodeID;
  }
}