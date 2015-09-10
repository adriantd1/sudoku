import java.util.*;

public class test{
	public static void main(String args[]){
		int[][] z = new int[2][2];
		columnNode a = methods.build_linked_list(4,z);
		int size = 1;
		columnNode pointer = a.getRight();
		while(pointer.getRight() != a){
			size++;
			pointer = pointer.getRight();
		}
		columnNode x = a.getRight();
		System.out.println(Arrays.toString(a.getRight().getFirst().getRight().getGridCoord().getPosition()));
		methods.cover(x);
		methods.uncover(x);
		System.out.println(Arrays.toString(a.getRight().getFirst().getRight().getGridCoord().getPosition()));
	}
}