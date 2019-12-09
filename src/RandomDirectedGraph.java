
import java.util.LinkedList;
import java.util.Random;

public class RandomDirectedGraph {
	
	
	private LinkedList<Node> graph;
	
	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	public Node getNode(int index) {
		 int x = 0;
		 for(Node c:graph) {
			 if(x==index) {
				 return c;
				 
			 }
			 x++;
		 }
		 return null;
	}
	
	public LinkedList<Node> getGraph(){
		return (LinkedList<Node>) graph.clone();
	}
	
	public RandomDirectedGraph(int size,int edges) {
		graph = new LinkedList<Node>();
		for(int i = 0;i<size;i++) {
			graph.add(new Node());
		}
		for(int i = 0;i<edges;i++) {
			Node tempNode = getNode(getRandomNumberInRange(0,graph.size()-1));
			graph.remove(tempNode);
			Node ranTry = getNode(getRandomNumberInRange(0,graph.size()-1));
			while(tempNode.adj.contains(ranTry)) {
				ranTry = getNode(getRandomNumberInRange(0,graph.size()-1));
			}
			tempNode.adj.add(ranTry);
			graph.add(tempNode);
			
		}
	}
	public void printGraph() {
		for(Node node:graph) {
			System.out.print(node.num+": ");
			for(Node child:node.adj) {
				child.printEdge(node); 
				System.out.print(" ");
				
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		RandomDirectedGraph graph = new RandomDirectedGraph(100,300);
		graph.printGraph();
	}

}
