public class Edge{
  private final double cost;
  private final Node otherEndpoint;

  Edge(Node target, double cost){
    this.otherEndpoint = target;
    this.cost = cost;
  }

  public double getCost(){
    return cost;
  }

  public Node getTarget(){
    return otherEndpoint;
  }
}