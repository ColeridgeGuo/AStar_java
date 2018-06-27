public class Node{

  private final String nodeID;
  private double g; // cost so far
  private double h; // heuristic cost to destination
  private double f; // estimated total cost of path
  // TODO: take out the initial values
  private final double latitude = 0;
  private final double longitude = 0;
  public Edge[] adjacencies;
  private Node parent;

  // TODO: take out the heuristic in the constructor
  // TODO: add the lat/long doubles back in
  public Node(String nodeID, int heuristic){
    this.nodeID = nodeID;
//    this.latitude = latitude;
//    this.longitude = longitude;
    this.h = heuristic;
    this.g = 0;
    this.f = 0;
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
    this.h = findDistance(d);
  }

  public double findDistance(Node n){
    return Math.sqrt(Math.pow(this.latitude - n.latitude, 2) + Math.pow(this
            .longitude - n.longitude, 2));
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