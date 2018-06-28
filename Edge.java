public class Edge{
  private final double cost;
  private final Node otherEndpoint;

  Edge(Node otherEndpoint, double cost){
    this.otherEndpoint = otherEndpoint;
    this.cost = cost;
  }

  public double getCost(){
    return cost;
  }

  public Node getOtherEndpoint(){
    return otherEndpoint;
  }
}