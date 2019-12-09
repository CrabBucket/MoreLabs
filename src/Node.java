

import java.util.LinkedList;

public class Node {
	public LinkedList<Node> tree = new LinkedList<>();
	public LinkedList<Node> adj;
	public int num;
	public int dist;
	public Integer start;
	public Integer stop;
	public boolean printed = false;
	public Node parent;
	public static int count = 0;
	public Node() {
		adj = new LinkedList<Node>();
		count++;
		num = count;
	}
	public void printEdge(Node parent) {
		System.out.print(parent.num + "->"+num);
	}
	public void printDFS() {
		System.out.println("Node:" + num + "Start/Finish Timer:" + start + "/" + stop);
	}

}
