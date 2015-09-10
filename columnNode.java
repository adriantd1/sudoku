public class columnNode{
	private columnNode left;
	private columnNode right;
	private columnNode head;
	private Node first;
	private Node last;
	
	public columnNode getLeft(){return this.left;}
	public columnNode getRight(){return this.right;}
	public columnNode getHead(){return this.head;}
	public Node getFirst(){return this.first;}
	public Node getLast(){return this.last;}
	public void setLeft(columnNode left){this.left = left;}
	public void setRight(columnNode right){this.right = right;}
	public void setHead(columnNode head){this.head = head;}
	public void setFirst(Node first){this.first = first;}
	public void setLast(Node last){this.last = last;}
}