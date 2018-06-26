public class Edge{
  public final double cost;
  public final Node target;

  Edge(Node target, double cost){
    this.target = target;
    this.cost = cost;
  }
}