package weighted;


import java.util.LinkedList;
import java.util.Stack;
import java.util.HashSet;

public class Shahmeer {
	
	public static void printTree(Node head) {
		Stack<Node> stack = new Stack<Node>();
		System.out.println("Start:" + head.num);
		stack.push(head);
		while(stack.size()>0) {
			Node temp = stack.pop();
			for(Node child : temp.tree) {
				System.out.print(child.num + " ");
				stack.push(child);
			}
			System.out.println();
		}
			
		
		
	}
	public static void prim(Graph graph,HashSet<Node> tree) {
		Node head = graph.graph.peek();
		while(head.adj.size()==0) {
			head = graph.getNode(graph.getRandomNumberInRange(0, graph.graph.size()));
		}
		tree.add(head);
		while(tree.size()!=graph.graph.size()) {
			int min = Integer.MAX_VALUE;
			Node nMin = null;
			Node parent = null;
			for(Node temp : tree) {
				
				for(Node nbor : temp.adj) {
					if(!tree.contains(nbor)) {
						if(min>nbor.weights.get(temp.num)) {
							nMin = nbor;
							min = nMin.weights.get(temp.num);
							parent = temp;
						}
					}
				}
				
				
				
			}
			tree.add(nMin);
			parent.tree.add(nMin);
		}
		
//		Initialize a tree with a single vertex, chosen arbitrarily from the graph.
//		Grow the tree by one edge: of the edges that connect the tree to vertices not yet in the tree, find the minimum-weight edge, and transfer it to the tree.
//		Repeat step 2 (until all vertices are in the tree).
		
	}
	public static void main (String args[]) {
		Graph graph = new Graph(5,8);
		graph.printGraph();
		prim(graph,new HashSet<Node>());
		System.out.println("Starting Node:" + graph.graph.peek().num);
		System.out.println("Tree:");
		System.out.println("_________________________");
		graph.printTree();
		
	}
}
//(Java) Trying to finish all of my algorithms labs like a madman Streaming All Night
