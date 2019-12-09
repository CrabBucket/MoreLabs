package avltrees;

public class Node implements Cloneable {
	
	public int value;
	public int height;
	public Node parent,left,right;
	public int balance = -1;
	public Node (int num) {
		value = num;
		
	}
	public Node(int num, Node parent) {
		value = num;
		height = 0;
		balance = 0;
		this.parent = parent;
	}
	public void print2DUtil(Node root, int space,int COUNT)  
	{  
	      
	    if (root == null)  
	        return;  
	  
	    
	    space += COUNT;  
	  
	     
	    print2DUtil(root.right, space,COUNT);  
	  
	    
	    System.out.print("\n");  
	    for (int i = COUNT; i < space; i++)  
	        System.out.print(" ");  
	    System.out.print(root.value + "\n");  
	  
	    
	    print2DUtil(root.left, space,COUNT);  
	}  
	  
	  
	public void print2D(int count)  
	{  
	    
	    print2DUtil(this, 0,count);  
	}  
	
	
}
