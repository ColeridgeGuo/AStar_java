package Astar;

import java.util.*;

public class AStar {
	//count of explored nodes as crude measurement of algorithmic effort
	static int count = 0;
	
	//h is the straight-line distance from the pq city to destination
	public static void main(String[] args){

		//initialize the graph with heuristic TO THE GOAL node
		Node nodeA = new Node("A", 2,3);
		Node nodeB = new Node("B", 8,3);
		Node nodeC = new Node("C", 2,7);
		Node nodeD = new Node("D", 6,6);
		Node nodeE = new Node("E", 8,7);
		Node nodeF = new Node("F", 12,4);
		Node nodeG = new Node("G", 12,9);
		Node nodeX = new Node("X", -2,0);  //nowhere close
		Node nodeCX = new Node("CX", 0,30); //nowhere close

		//initialize the edges
		nodeA.adjacencies = new Edge[]{
				new Edge(nodeA, nodeB),
				new Edge(nodeA, nodeC),
				new Edge(nodeA, nodeX)
		};
		nodeB.adjacencies = new Edge[]{
				new Edge(nodeB, nodeA),
				new Edge(nodeB, nodeD),
				new Edge(nodeB, nodeE),
				new Edge(nodeB, nodeX)
		};
		nodeC.adjacencies = new Edge[]{
				new Edge(nodeC, nodeA),
				new Edge(nodeC, nodeD),
				new Edge(nodeC, nodeG),
				new Edge(nodeC, nodeCX)
		};
		nodeD.adjacencies = new Edge[]{
				new Edge(nodeD, nodeB),
				new Edge(nodeD, nodeC),
				new Edge(nodeD, nodeE),

		};
		nodeE.adjacencies = new Edge[]{
				new Edge(nodeE, nodeB),
				new Edge(nodeE, nodeD),
				new Edge(nodeE, nodeF),

		};
		nodeF.adjacencies = new Edge[]{
				new Edge(nodeF, nodeE),
				new Edge(nodeF, nodeG),
		};
		nodeG.adjacencies = new Edge[]{
				new Edge(nodeG, nodeF),
				new Edge(nodeG, nodeC),
		};
		nodeX.adjacencies = new Edge[]{
				new Edge(nodeX, nodeA),
				new Edge(nodeX, nodeB),
		};
		nodeCX.adjacencies = new Edge[]{
				new Edge(nodeCX, nodeC)
		};

		
		//Mess with edge costs here
		//nodeB.adjacencies[2].setCost(8);  //stairs cost??
		//nodeE.adjacencies[0].setCost(8);

		Node startNode = nodeA;
		Node targetNode = nodeF;

		AStarSearch(startNode,targetNode);
		List<Node> path = printPath(targetNode);
		System.out.println("Path: " + path);
		System.out.println("Cost: " + targetNode.getG() + " : "+count);
	}

	//follow path from end, via parent's back to start
	private static List<Node> printPath(Node target){
		List<Node> path = new ArrayList<Node>();

		for(Node node = target; node != null; node = node.getParent()){
			path.add(node);
		}
		Collections.reverse(path);
		return path;
	}

	/**
	 * implements A* path finding algorithm
	 * @param source -starting node
	 * @param goal -target node
	 */
	private static void AStarSearch(Node source, Node goal){
		//Initialize the closedList - list of nodes that have been explored
		Set<Node> closedList = new HashSet<Node>();

		// Initialize the openList of yet-to-be explored nodes
		PriorityQueue<Node> openList = new PriorityQueue<Node>(20, new
				Comparator<Node>(){
					//Setup the compare method
					public int compare(Node i, Node j){
						if (i.getF() > j.getF()) return 1;
						if (j.getF() > i.getF()) return -1;
						return 0;
						}
				});
	
		openList.add(source);    //search starts at beginning

		// While connected nodes have remain to be explored
		while((!openList.isEmpty()) ){
			// Remove the node with the minimum f on the openList
			Node pq = openList.poll();

			// If goal found
			if(pq.getNodeID().equals(goal.getNodeID())){
				break;
			}

			// Check every successor of pq node
			for(Edge e : pq.adjacencies){
				Node successor = e.getOtherEnd(pq);
	
				// Calculate g, f
				double temp_g = pq.getG() + e.getCost();       //actual cost so far
				double temp_f = temp_g + successor.getH(goal); //estimated cost to target

				if (openList.contains(successor)) {  //two routes to this node exist
					if (temp_f <= successor.getF()) {
						//update cheaper route on the openList with new routing
						successor.setParent(pq);
						successor.setG(temp_g);
						successor.setF(temp_f);
						System.out.println("New route to "+successor+ "is via "+pq);
					}
				} else if (!closedList.contains(successor)) {  //b/c closedList is guaranteed cheapest route
					///update this before putting on openlist
					successor.setParent(pq);
					successor.setG(temp_g);
					successor.setF(temp_f);
					System.out.println("OL add:"+successor+" @ "+successor.getF());
					openList.add(successor);
				}
			}
			// Put pq on the closedList
			closedList.add(pq);
			count++;
		}
	}
}