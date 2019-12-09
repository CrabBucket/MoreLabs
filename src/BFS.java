import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;



public class BFS {
	public static void run(Node node) {
		LinkedList<Node> neighbors = new LinkedList<Node>();
		node.dist = -1;
		node.parent = new Node();
		neighbors.push(node);
		while(neighbors.size()>0) {
			Node neighbor = neighbors.pop();
			for(Node temp: neighbor.adj) {
				if(temp.parent ==null) {
					temp.parent = neighbor;
					temp.dist = neighbor.dist+1;
					neighbors.push(temp);
					
				}
			}
		}
		
	}
	public static void print(Node node) {
		Set<Node> near = addAll(node,new HashSet<Node>());
		for(Node temp: near) {
			System.out.print("Node:"+temp.num+" Distance:"+(temp.dist+1)+" Path to root:");
			Node cur = temp;
			if(cur.dist==-1) {
				System.out.println("This is the root");
			}else {
				if(temp.parent==null) {
					System.out.println("Node is not connected to the root");
					break;
				}
				while(!(cur.parent.parent==null)) {
					System.out.print(cur.num+"->");
					cur = cur.parent;
				}
				System.out.println("root");
			}
		}
		
		
	}
	
	static HashSet<Node> addAll(Node node,HashSet<Node> set){
		set.add(node);
		node.printed=true;
		for(Node neighbor : node.adj) {
			if(!neighbor.printed) {
				addAll(neighbor,set);
			}
		}
		return set;
	}
	public static void main(String args[]) throws NumberFormatException, IOException {
		RandomDirectedGraph temp = new RandomDirectedGraph(10,20);
		temp.printGraph();
		LinkedList<Node> graph = temp.getGraph();
		System.out.print("Please enter a node from 1-"+graph.size()+":");
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int userin = Integer.parseInt(read.readLine());
		Node head = null;
		for(Node node : graph) {
			if(node.num==userin) {
				head=node;
				break;
			}
		}
		if(head.equals(null)) {
			System.out.println("error in finding user node");
			System.exit(0);
		}
		BFS.run(head);
		BFS.print(head);
		
		
	}
}
