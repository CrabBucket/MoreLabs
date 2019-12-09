import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;
import java.util.List;

public class DFS {
	public static List<Node> sick = new Stack<Node>();
	private static int timer = 0;
	private static boolean cycle = false;
	public static void reset() {
		timer = 0;
		cycle = false;
	}
	public static void clean(LinkedList<Node> graph) {
		for(Node node : graph) {
			node.parent = null;
			node.start = null;
			node.stop = null;
			node.printed = false;
			
		}
	}
	static Node getHead(int num,LinkedList<Node> graph) {
		Node head = null;
		for(Node node : graph) {
			if(node.num==num) {
				head=node;
				break;
			}
		}
		if(head.equals(null)) {
			System.out.println("error in finding user node");
			System.exit(0);
		}
		return head;
	}
	static List<Node> DFS(LinkedList<Node> graph){
		sick.clear();
		for(Node node: graph ) {
			if(node.parent == null) {
				node.parent = new Node();
				visit(node);
			}
		}
		if(cycle) {
			return (List)new Stack<Node>();
		}else {
			return sick;
		}
	}
	public static void print(Node temp) {
		System.out.println("Node:"+temp.num+" Start/Finish Timer:"+temp.start+"/"+temp.stop);
	}
	public static void printGraph(LinkedList<Node> graph) {
		HashSet<Node> toPrint = new HashSet<Node>();
		graph.forEach((node)->toPrint.add(node));
		for(Node temp : toPrint) {
			System.out.println("Node:"+temp.num+" Start/Finish Timer:"+temp.start+"/"+temp.stop);
		}
	}
	public static void visit(Node node) {
		node.start = ++timer;
		
		for(Node neighbor : node.adj) {
			if(neighbor.parent == null) {
				neighbor.parent = node;
				visit(neighbor);
			}else if(neighbor.stop == null) {
				cycle = true;
			}
			
		}
		
		
		node.stop = ++timer;
		((Stack<Node>) sick).push(node);
	}
	public static void printNode(Node node) {
		HashSet<Node> set = BFS.addAll(node, new HashSet<Node>());
		for(Node temp : set) {
			System.out.println("Node:"+temp.num+" Start/Finish Timer:"+temp.start+"/"+temp.stop);
		}
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		RandomDirectedGraph temp = new RandomDirectedGraph(10,11);
		temp.printGraph();
		LinkedList<Node> graph = temp.getGraph();
		System.out.print("Please enter a node from 1-"+graph.size()+":");
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int userin = Integer.parseInt(read.readLine());
		Node head = getHead(userin,temp.getGraph());
		BFS.run(head);
		BFS.print(head);
		
		clean(temp.getGraph());
		head.parent = new Node();
		visit(head);
		printNode(head);
		clean(temp.getGraph());
		reset();
		Stack<Node> graph_ = (Stack<Node>) DFS(temp.getGraph());
		if(graph_.size()==0) {
			System.out.println("Not a DAG");
		}else {
			System.out.println("Topological Sort");
			System.out.println("___________________");
			while(graph_.size()>0) {
				graph_.pop().printDFS();
			}
			
			
			
			//printGraph(graph);
			//graph.forEach((node)->node.printed=false);
//			HashSet<Node> unique = new HashSet<Node>();
//			LinkedList<Node> level = new LinkedList<Node>();
//			
//			for(Node node : graph) {
//				if(node.start==(node.stop-1)) {
//					
//					node.printed = true;
//					level.add(node);
//				}
//			}
//			int count = 0;
//			while(level.size()>0) {
//				Node current = level.remove();
//				System.out.print(++count + ":");
//				print(current);
//				if(count == 10) {
//					break;
//				}
//				if(current.parent.printed == false & current.parent.parent != null) {
//					current.parent.printed = true;
//					level.add(current.parent);
//				}
//			}
//			class temp implements Comparable {
//				public Node n;
//				public int start;
//				public temp(Node n,int start) {
//					this.n = n;
//					start = n.start;
//				}
//				@Override
//				public int compareTo(Object o) {
//					Integer t = start;
//					return start-((temp)o).start;
//				}
//			}
//			ArrayList<temp> sort = new ArrayList<temp>();
//			for(Node node : graph) {
//				sort.add(new temp(node,node.start));
//			}
//			int count = 0;
//			Collections.sort(sort);
//			for(temp t : sort) {
//				System.out.print(++count+":");
//				print(t.n);
//			}
		}
		
		
	}

}
