public class gridCoord{
	private int[] position = new int[3];
	
	public gridCoord(int row, int col, int num){
		position[0]=row; position[1]=col; position[2]=num;
	}
	
	public int[] getPosition(){return position;}
	public void setPosition(int row, int col, int num){position[0]=row; position[1]=col; position[2]=num;}
}