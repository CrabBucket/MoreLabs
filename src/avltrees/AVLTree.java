package avltrees;

import java.util.LinkedList;
import java.util.Scanner;

import com.rits.cloning.Cloner;

public class AVLTree {
	public Node root;
	
	public AVLTree(int num) {
		root = new Node(num);
		root.balance = 0;
	}
	public AVLTree(Node head) {
		root = head;
	}
	public void add(int num) {
		add_(num, root);
	}
	public void ll(Node node) {
		boolean left = true;
		boolean lull = false;
		if(node.parent == null) {
			lull = true;
		}else if(node.parent.right!= null && node.parent.right != null) {
			left = false;
		}
		if(lull) {
			
		}else if(left) {
			node.parent.left=node.left;
			
		}else {
			node.parent.right=node.left;
		}
		Node temp = node.left;
		node.left = node.left.right;
		temp.right = node;
		temp.parent = node.parent;
		node.parent = temp;
		if(node.left != null)node.left.parent=node;
		if(temp.parent == null) root = temp;
		update(node);
		update(temp);
	}
	public void rr(Node node) {
		boolean left = true;
		boolean lull = false;
		if(node.parent == null) {
			lull = true;
		}else if(node.parent.right!= null && node.parent.right != null) {
			left = false;
		}
		if(lull) {
			
		}else if(left) {
			node.parent.left=node.right;
			
		}else {
			node.parent.right=node.right;
		}
		Node temp = node.right;
		node.right = node.right.left;
		temp.left = node;
		temp.parent = node.parent;
		node.parent = temp;
		if(node.right != null)node.right.parent=node;
		if(temp.parent == null) root = temp;
		update(node);
		update(temp);
	}
	public void lr(Node node) {
		boolean left = true;
		boolean lull = false;
		if(node.parent == null) {
			lull = true;
		} else if(node.parent.right!= null && node.parent.right.equals(node)) {
			left = false;
		}
		Cloner c = new Cloner();
		
		Node primary = c.deepClone(node);
		Node child = c.deepClone(node.left);
		
		Node gChild = c.deepClone(child.right);
		if(lull) {
			System.out.println("lull");
		}else if(left) {
			node.parent.left=gChild;
		}else{
			node.parent.right = gChild;
		}
		
		
		
		child.right = gChild.left;
		primary.left= gChild.right;
		gChild.left = child;
		gChild.right = primary;
		gChild.left.parent = gChild.right.parent = gChild;
		primary = child = gChild;
		gChild.parent = node.parent;
		
		primary.left.parent = primary;
		child.right.parent = child;
		node = primary;
		if(gChild.parent == null)root = gChild;
		update(node.left);
		update(node.right);
		update(node);
		

		
		
	}
	
	public void left(Node node) {
		if(node.left != null && node.left.left != null) {
			ll(node);
		}else {
			lr(node);
		}
		
	};
	public void rl(Node node) {
		boolean left = true;
		boolean lull = false;
		if(node.parent == null) {
			lull = true;
		} else if(node.parent.right!= null && node.parent.right.equals(node)) {
			left = false;
		}
		Cloner c = new Cloner();
		
		Node primary = c.deepClone(node);
		Node child = c.deepClone(node.right);
		
		Node gChild = c.deepClone(child.left);
		if(lull) {
			System.out.println("lull");
		}else if(left) {
			node.parent.left=gChild;
		}else{
			node.parent.right = gChild;
		}
		
		
		
		child.left = gChild.right;
		primary.right= gChild.left;
		gChild.right = child;
		gChild.left = primary;
		gChild.left.parent = gChild.right.parent = gChild;
		primary = child = gChild;
		gChild.parent = node.parent;
		
		primary.right.parent = primary;
		child.left.parent = child;
		node = primary;
		if(gChild.parent == null)root = gChild;
		update(child);
		update(primary);
		update(gChild);
		
	}
	
	public void right (Node node) {
		if(node.right != null && node.right.right != null) {
			rr(node);
		}else {
			rl(node);
		}
	}
	public void rotate(Node node) {
		if(node.balance<-1) {
			left(node);
		}else if (node.balance > 1) {
			right(node);
		}
	}
	public void update(Node node) {
		int left = -1;
		int right = -1;
		if(node.left == null && node.right == null) {
			node.height = 0;
		}
		else if(node.right == null) {
			node.height = node.left.height+1;
			
		}else if(node.left == null) {
			node.height = node.right.height+1;
			
		}else {
			node.height = Math.max(node.left.height,node.right.height)+1;
		}
		if(node.right != null) {
			right = node.right.height;
		}
		if(node.left != null) {
			left = node.left.height;
		}
//		if(node.left != null && node.left.balance<0) //left*=-1;
		node.balance = right - left;
		if(node.balance>1 || node.balance <-1) {
			rotate(node);
		}
	}
	void walkUp(Node node) {
		update(node);
		if(node.parent == null) {
			return;
		}else {
			walkUp(node.parent);
		}
	}
	private void add_(int num, Node cur) {
		if(num<cur.value) {
			if(cur.left == null) {
				cur.left = new Node(num,cur);
				cur.height = 1;
				walkUp(cur);
			}else {
				add_(num,cur.left);
				
			}
		}else {
			if(cur.right == null) {
				cur.right = new Node(num,cur);
				cur.height = 1;
				walkUp(cur);
			}else {
				add_(num,cur.right);
			}
		}
	}
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		AVLTree tree = new AVLTree(100);
		while(true) {
			tree.add(in.nextInt());
			tree.root.print2D(10);
		}
	}
	
}
