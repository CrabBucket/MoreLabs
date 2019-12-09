package weighted;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Djikstra {
	public static void DJK(Node root) {
		root.inside = true;
		root.dist = 0;
		root.parent = new Node();
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		q.add(root);
		while(q.size()>0) {
			Node node = q.remove();
			node.inside = false;
			for(Node nbor: node.adj) {
				if(nbor.dist>(node.dist + node.weights.get(nbor.num))) {
					nbor.dist = node.dist + node.weights.get(nbor.num);
					nbor.parent = node;
					if(!nbor.inside) {
						q.add(nbor);
						nbor.inside = true;
					}else {
						
					}
				}
			}
		} 
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph graph = new Graph(5,5);
		graph.printGraph();
		Scanner in = new Scanner(System.in);
		int from = in.nextInt();
		int to = in.nextInt();
		System.out.println("Node from:"+graph.getNode(from-1).num);
		System.out.println("Node to:"+graph.getNode(to-1).num);
		DJK(graph.getNode(from-1));
		System.out.println(graph.getNode(to-1).dist);
		
		
	}

}
