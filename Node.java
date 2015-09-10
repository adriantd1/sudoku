public class Node{
	private columnNode column;
	private Node up;
	private Node down;
	private Node left;
	private Node right;
	private gridCoord coord;
	
	// getters and setters
	public Node getLeft(){return this.left;}
	
	public Node getRight(){return this.right;}
	
	public Node getUp(){return this.up;}
	
	public Node getDown(){return this.down;}
	
	public columnNode getColumnNode(){return this.column;}
	
	public gridCoord getGridCoord(){return this.coord;}
	
	public void setLeft(Node a){this.left = a;}
	
	public void setRight(Node a){this.right = a;}
	
	public void setUp(Node a){this.up = a;}
	
	public void setDown(Node a){this.down = a;}
	
	public void setColumnNode(columnNode a){this.column = a;}
	
	public void setGridCoord(gridCoord coord){this.coord = coord;}
	
	//constructors
	public Node(Node left,Node right,Node up,Node down,columnNode column){
		this.left = left;
		this.right = right;
		this.up = up;
		this.down = down;
		this.column = column;
	}
	
	public Node(){
		this.left = null;
		this.right = null;
		this.up = null;
		this.down = null;
		this.column = null;
	}

}