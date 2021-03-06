import java.util.*;
import java.io.*;

class Sudoku
{
    //Size of the sudoku. Classic sudoku have SIZE = 3. N is the square of SIZE
    int SIZE, N;
	int y=0;

    // A 2D array used to represent the sudoku
    int Grid[][];
	
	Stack<Node> solution_set = new Stack<Node>();
	int j = 0;

    // Solves the sudoku by removing all x and replacing them by the according number
    public void solve()
    {	
		columnNode a = methods.build_linked_list(N,Grid);
		getSolution(a);
		
		
		
	}
	
	public void getSolution(columnNode h){
		
		if(h.getRight() == h){
			printSolution();
		}else{
			
			columnNode column = h.getRight();
			methods.cover(column);
			solution_set.push(column.getFirst());
			
			Node pointer = column.getFirst();
			Node row_ptr = pointer.getRight();
			
			do{
				do{
					methods.cover(row_ptr.getColumnNode());
					row_ptr = row_ptr.getRight();
				}while(row_ptr != pointer);
				
				getSolution(h);
				
				System.out.println(j);
				
				pointer = column.getLast();
				row_ptr = pointer.getLeft();
				
				do{
					methods.uncover(row_ptr.getColumnNode());
					row_ptr = row_ptr.getLeft();
				}while(row_ptr != pointer);
			}while(pointer != column.getFirst());
			
			column = solution_set.pop().getColumnNode();
			methods.uncover(column);
			
		}
	}
	
	public void printSolution(){
		int[] cur;
		while(!solution_set.empty()){
			cur = solution_set.pop().getGridCoord().getPosition();
			Grid[cur[0]][cur[1]] = cur[2];
		}
	}

    // Constructor initializing all position to 0
    public Sudoku( int size )
    {
        SIZE = size;
        N = size*size;

        Grid = new int[N][N];
        for( int i = 0; i < N; i++ ) 
            for( int j = 0; j < N; j++ ) 
                Grid[i][j] = 0;
    }


    /* readInteger is a helper function for the reading of the input file.  It reads
     * words until it finds one that represents an integer. For convenience, it will also
     * recognize the string "x" as equivalent to "0". */
    static int readInteger( InputStream in ) throws Exception
    {
        int result = 0;
        boolean success = false;

        while( !success ) {
            String word = readWord( in );

            try {
                result = Integer.parseInt( word );
                success = true;
            } catch( Exception e ) {
                // Convert 'x' words into 0's
                if( word.compareTo("x") == 0 ) {
                    result = 0;
                    success = true;
                }
            }
        }

        return result;
    }


    // readWord is a helper function that reads a word separated by white space
    static String readWord( InputStream in ) throws Exception
    {
        StringBuffer result = new StringBuffer();
        int currentChar = in.read();
	String whiteSpace = " \t\r\n";
        // Ignore any leading white space
        while( whiteSpace.indexOf(currentChar) > -1 ) {
            currentChar = in.read();
        }

        // Read all characters until you reach white space
        while( whiteSpace.indexOf(currentChar) == -1 ) {
            result.append( (char) currentChar );
            currentChar = in.read();
        }
        return result.toString();
    }

    // Reads the sudoku from the input stream while ignoring all non valid characters
    public void read( InputStream in ) throws Exception
    {
        for( int i = 0; i < N; i++ ) {
            for( int j = 0; j < N; j++ ) {
                Grid[i][j] = readInteger( in );
            }
        }
    }


    /* Helper function for the printing of Sudoku puzzle.  This function will print
     * out text, preceded by enough ' ' characters to make sure that the printint out
     * takes at least width characters.  */

    // Helper function for the printing of the sudoku that ensure a fixed width
    void printFixedWidth( String text, int width )
    {
        for( int i = 0; i < width - text.length(); i++ )
            System.out.print( " " );
        System.out.print( text );
    }


    public void print()
    {
        // Compute the number of digits necessary to print out each number in the Sudoku puzzle
        int digits = (int) Math.floor(Math.log(N) / Math.log(10)) + 1;

        // Create a dashed line to separate the boxes 
        int lineLength = (digits + 1) * N + 2 * SIZE - 3;
        StringBuffer line = new StringBuffer();
        for( int lineInit = 0; lineInit < lineLength; lineInit++ )
            line.append('-');

        // Go through the Grid, printing out its values separated by spaces
        for( int i = 0; i < N; i++ ) {
            for( int j = 0; j < N; j++ ) {
                printFixedWidth( String.valueOf( Grid[i][j] ), digits );
                // Print the vertical lines between boxes 
                if( (j < N-1) && ((j+1) % SIZE == 0) )
                    System.out.print( " |" );
                System.out.print( " " );
            }
            System.out.println();

            // Print the horizontal line between boxes
            if( (i < N-1) && ((i+1) % SIZE == 0) )
                System.out.println( line.toString() );
        }
    }




    /* the main function reads a sudoku puzzle from the standard input unless a 
     * file name is provided as an argument */
    public static void main( String args[] ) throws Exception
    {
    	
        InputStream in;
        if( args.length > 0 ) 
            in = new FileInputStream( args[0] );
        else
            in = System.in;

        // The first number in all Sudoku files must represent the size of the puzzle.
        int puzzleSize = readInteger( in );
        if( puzzleSize > 100 || puzzleSize < 1 ) {
            System.out.println("Error: The Sudoku puzzle size must be between 1 and 100.");
            System.exit(-1);
        }

        Sudoku s = new Sudoku( puzzleSize );

        // read the rest of the Sudoku puzzle
        s.read( in );

        // Solve the puzzle.  
        s.solve();

        // Print out the puzzle
        s.print();
    }
}
