package weighted;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


public class Node implements Comparable{
	public LinkedList<Node> tree = new LinkedList<>();
	public LinkedList<Node> adj;
	public HashMap<Integer,Integer> weights;
	public int num;
	public int dist = Integer.MAX_VALUE;
	public Integer start;
	public Integer stop;
	public boolean printed = false;
	public Node parent;
	public static int count = 0;
	public boolean inside = false;
	public Node() {
		adj = new LinkedList<Node>();
		weights = new HashMap<>();
		num = ++count;
	}
	public Node(Node node) {
		adj = new LinkedList<Node>();
		num = node.num;
		
	}
	public void printEdge(Node parent) {
		System.out.print(parent.num + "->"+num);
	}
	public void printDFS() {
		System.out.println("Node:" + num + "Start/Finish Timer:" + start + "/" + stop);
	}
	public void add(Node node, int weight) {
		adj.add(node);
		node.adj.add(this); 
		weights.put(node.num, weight);
		node.weights.put(num, weight);
		
	}
	
		
	public int weight(Node node) {
		return weights.get(node.num);
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return dist-((Node)o).dist;
	}
}
