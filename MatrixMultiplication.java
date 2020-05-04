/***********************************************************
Author:        Zach Seiss
Written:       May 3, 2020
Last Update:   May 4, 2020

Compile:       javac MatrixMultiplication.java
Execute:       java MatrixMultiplication

$ java MatrixMultiplication
Enter the first matrix row by row : (Enter blank line to terminate)

3 4 2

Enter the second matrix row by row : (Enter blank line to terminate)

4 6 5 8
4 5 7 9
3 5 1 7

Output:
[34.0] [48.0] [45.0] [74.0] 
 **********************************************************/
    
import java.util.*;

public class MatrixMultiplication{

    public static void main( String[] args){

	Scanner input = new Scanner(System.in);
	double[][] A = null,
	           B = null,
	           C = null;
	do{
	    A = getInputMatrix(input, 1);
	    B = getInputMatrix(input, 2);
	    if( A[0].length != B.length)
		System.out.println("Your input matricies were invalid: \nThe number of columns " +
				   "in matrix_1 must equal number of rows in matrix_2.\n");
	}while( A[0].length != B.length);
		
	input.close();

	C = multiplyMatrix( A, B);
	printMatrix( C);
    }

    private static double[][] getInputMatrix( Scanner sc, int arrIndex){
	String[] numArr = {"", "first", "second"};
	System.out.println("Enter the " + numArr[arrIndex] +
			   " matrix row by row : (Enter blank line to terminate)\n");
	int rows = 0;
	Integer cols = null;
	double[][] newArr = null;
	while( true){
	    String[] strArr = sc.nextLine().replaceAll("(\\s+)", " ").split(" ");
	    if( !strArr[0].isEmpty() ){
		if( cols == null)
		    cols = strArr.length;
		
		double[] dblArr = new double[cols];
		for( int i = 0; i < cols; i++ ){
		    dblArr[i] = Double.parseDouble( strArr[i]);
		}
		
		double[][] tempArray = new double[++rows][cols];
		
		if( newArr != null)
		    for( int i = 0; i < rows-1; i++)
			System.arraycopy( newArr[i], 0, tempArray[i], 0, cols);
		
		//copy dblArr into tempArray[rows]
		System.arraycopy( dblArr, 0, tempArray[rows-1], 0, cols);

		//remake newArr with a new row
		newArr = new double[rows][cols];
		
		//copy tempArray into newArr
		for( int i = 0; i < rows; i++)
		    System.arraycopy( tempArray[i], 0, newArr[i], 0, cols);		
	    }
	    else
		break;
	}
	return newArr;
    }
    
    private static void printMatrix( double[][] matrix){
	int fieldWidth = maxWidth( matrix);
	String format = "[%" + fieldWidth + ".1f] ";

	for( int i = 0; i < matrix.length; i++){
	    for( int j = 0; j < matrix[0].length; j++){
		System.out.printf(format, matrix[i][j]);
	    }
	    System.out.println();
	}
	System.out.println();
    }

    private static int maxWidth( double[][] arr){
	double max = 0;
	for( int i = 0; i < arr.length; i++)
	    for( int j = 0; j < arr[0].length; j++)
		max = (arr[i][j] > max) ? arr[i][j] : max;
	
	int width  = 0;
	while( (int)max > 0){
	    max /= 10;
	    width++;
	}

	return width + 2;  //  +2 accounts for the space taken by decimal portion of the double.
    }

    static double[][] multiplyMatrix( double[][] a, double[][] b){
	int numProducts = a[0].length,
	           rows = a.length,
	           cols = b[0].length;
	double[][]    c = new double [rows][cols];	

	for( int i = 0; i < rows; i++){
	    for( int j = 0; j < cols; j++){
		double sumCell = 0;
		for( int l = 0; l < numProducts; l++){
		    sumCell += a[i][l] * b[l][j];
		}
		c[i][j] = sumCell;
	    }
	}
	return c;
    }
}
