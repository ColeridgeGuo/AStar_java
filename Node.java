package Astar;

public class Node{

	private final String nodeID;
	private double g; // cost so far
	private double h; // heuristic cost to destination
	private double f; // estimated total cost of path
	
	private final double latitude;
	private final double longitude;
	public Edge[] adjacencies;
	private Node parent;

	// TODO: take out the heuristic in the constructor
	// TODO: add the lat/long doubles back in
	public Node(String nodeID, double lat, double lon){
		this.nodeID = nodeID;
		this.latitude = lat;
		this.longitude = lon;
		this.h = -1;
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

	//gets the anticipated cost to go to the other node
	public double getH(Node other) {
		if (h < 0) {
			h = Math.sqrt(Math.pow(this.latitude - other.latitude,2)+(Math.pow(this.longitude-other.longitude, 2)));
		}
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