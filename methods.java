import java.lang.Math.*;
import java.util.*;

public class methods{
	
	//return the head of the 2D doubly linked Node list
	public static columnNode build_linked_list(int size,int[][] array){
		int no_of_columns = (int)Math.pow(size,2.0)*4;
		columnNode[] column_list = new columnNode[no_of_columns + 1]; //allows to access each columnNode in O(1);
		columnNode head = new columnNode();
		column_list[0] = head;
		columnNode pointer = head;
		
		//Create the "columnNodes"
		for(int i = 0;i < no_of_columns; i++){
			pointer.setRight(new columnNode());
			pointer.getRight().setLeft(pointer);
			pointer = pointer.getRight();
			column_list[i+1] = pointer;
		}

		pointer.setRight(head);
		head.setLeft(pointer);
		
		//build the constraint nodes
		int one,two,three,four;
		Node ptr;
		for(int ROW = 1; ROW <= size; ROW++){
			for(int COL = 1; COL <= size; COL++){
				for(int NUM = 1; NUM <= size; NUM++){
					for(int i = 0; i < 4; i++){
						one = (ROW-1)*size+COL;
						two = size*size+(ROW-1)*size+NUM;
						three = 2*size*size+(COL-1)*size+NUM;
						four = 3*size*size+((COL-1)/(int)(Math.sqrt(size)))*size+((ROW-1)/(int)(Math.sqrt(size)))*size+NUM;
						gridCoord pos = new gridCoord(ROW,COL,NUM);
						switch (i){
							//position constraint
							case 0:	ptr = new Node(null,null,column_list[one].getLast(),column_list[one].getFirst(), column_list[one]);
								if(column_list[one].getFirst() == null){
									column_list[one].setFirst(ptr);
									column_list[one].setLast(ptr);
									}else{
										column_list[one].getLast().setDown(ptr);
									}
								column_list[one].getFirst().setUp(ptr);
								column_list[one].setLast(ptr);
								ptr.setGridCoord(pos);
								continue;
							//Row constraint
							case 1: ptr = new Node(column_list[one].getLast(),null,column_list[two].getLast(),column_list[two].getFirst(), column_list[two]);
								if(column_list[two].getFirst() == null){
									column_list[two].setFirst(ptr);
									column_list[two].setLast(ptr);
									}else{
										column_list[two].getLast().setDown(ptr);
									}
								column_list[two].getFirst().setUp(ptr);
								column_list[one].getLast().setRight(ptr);
								column_list[two].setLast(ptr);
								ptr.setGridCoord(pos);
								continue;
							case 2: ptr = new Node(column_list[two].getLast(),null,column_list[three].getLast(),column_list[three].getFirst(), column_list[three]);
								if(column_list[three].getFirst() == null){
									column_list[three].setFirst(ptr);
									column_list[three].setLast(ptr);
									}else{
										column_list[three].getLast().setDown(ptr);
									}
								column_list[three].getFirst().setUp(ptr);
								column_list[two].getLast().setRight(ptr);
								column_list[three].setLast(ptr);
								ptr.setGridCoord(pos);
								continue;
							case 3: ptr = new Node(column_list[three].getLast(),column_list[one].getLast(),column_list[four].getLast(),column_list[four].getFirst(), column_list[four]);
								if(column_list[four].getFirst() == null){
									column_list[four].setFirst(ptr);
									column_list[four].setLast(ptr);
									}else{
										column_list[four].getLast().setDown(ptr);
									}
								column_list[four].getFirst().setUp(ptr);
								column_list[three].getLast().setRight(ptr);
								column_list[one].getLast().setLeft(ptr);
								column_list[four].setLast(ptr);
								ptr.setGridCoord(pos);
						}
					}
				}
			}
		}

		//cover the constraints filled by the initial hints
		int number;
		for(int i = 0; i<array.length; i++){
			for(int j = 0; j<array[i].length; j++){
				number = array[i][j];
				if(number != 0){
					cover(column_list[i*size+j+1]);
					cover(column_list[size*size+i*4+number]);
					cover(column_list[2*size*size+(j+1)*size+number]);
					cover(column_list[3*size*size+((j)/(int)(Math.sqrt(size)))*size+((i)/(int)(Math.sqrt(size)))*size+number]);
				}
			}
		}
		return head;
	}
	
	public static void cover(columnNode c){
		c.getLeft().setRight(c.getRight());
		c.getRight().setLeft(c.getLeft());
		
		Node ptr = c.getFirst();
		Node index = ptr;
		
		do{
			System.out.println("1");
			do{
				//System.out.println("4");
				index = index.getRight();
				index.getDown().setUp(index.getUp());
				index.getUp().setDown(index.getDown());
				
				//update first Node after covering
				if(index.getColumnNode().getFirst() == index){
					index.getColumnNode().setFirst(index.getDown());
				}
				
				//update last Node after covering
				if(index.getColumnNode().getLast() == index){
					index.getColumnNode().setLast(index.getUp());
				}
			}while(index.getRight() != ptr);
			
			ptr = ptr.getDown();
			index = ptr;
		}while(ptr != c.getFirst());
		
	}
	
	public static void uncover(columnNode c){
		Node ptr = c.getLast();
		Node index = ptr;
		
		do{
			//System.out.println("1");
			do{
				//System.out.println("2");
				index = index.getLeft();
				//update first Node before uncovering
				if(index.getDown() == index.getColumnNode().getFirst()){
					index.getColumnNode().setFirst(index);
				}
				
				//update last Node before uncovering
				if(index.getUp() == index.getColumnNode().getLast()){
					index.getColumnNode().setLast(index);
				}
				
				index.getUp().setDown(index);
				index.getDown().setUp(index);
				
			}while(index.getLeft() != ptr);
			
			ptr = ptr.getUp();
			index = ptr;
		}while(ptr != c.getLast());
		
		c.getLeft().setRight(c);
		c.getRight().setLeft(c);
	}
	
	
	
	
}