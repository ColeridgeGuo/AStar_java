package Astar;

/**
 * 
 * @author bcatron
 *
 */
public class Edge{
	private double cost;
	private final Node endpointA;
	private final Node endpointB;

	Edge(Node n1, Node n2){
		this.endpointA = n1;
		this.endpointB = n2;
		if (n1 == n2)
			this.cost = 0;
		else { //since edges are, by our definition straight, the cost is the euclidean + hazards
			this.cost = Math.sqrt(Math.pow(n1.getLatitude() - n2.getLatitude(),2)
					+(Math.pow(n1.getLongitude()-n2.getLongitude(), 2)));
			
			this.cost = this.cost + 0.55;  //some hazard value  TBD
		}
	}

	public double getCost(){
		return cost;
	}
	
	//useful only for hand-tweaking the values
	public void setCost(double c){
		cost = c;
	}
	
	public Node getOtherEnd(Node oneEnd){
		if (oneEnd == this.endpointA)
			return this.endpointB;
		if (oneEnd == this.endpointB)
			return this.endpointA;
		return null;  //this edge doesn't include oneEnd
	}
}