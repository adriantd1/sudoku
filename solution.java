import java.util.ArrayList;

public class solution{
	ArrayList<Node> list = new ArrayList<Node>();
	
	public void add(Node a){
		list.add(a);
	}
	
	public void remove(Node a){
		list.remove(a);
	}
	
	public Node[] toArray(){
		return list.toArray(new Node[list.size()]);
	}
}