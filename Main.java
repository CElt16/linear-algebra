package Matrix;

public class Main {

	public static void main(String[] args) {
		double[][] grid0 = {
			    {2, 4, -2},
			    {1, 3, 4},
			    {3, 7, 2}
			};
		
		double[][] grid1 = {
			    {1, 0, 0},
			    {2, 1, 0},
			    {3, 4, 1},

			};
		
		double[][] grid2 = {
			    {1, 0, 3, 0},
			    {2, 1, 4, 0},
			    {3, 4, 1, 0},
			    {5, 6, 7, 1}
			};
		
		double[][] grid3 = {
				{1, 2, -1, 3},
			    {2, 4, 0, 8},
			    {3, 6, 1, 12}
		};
		
		double[][] grid4 = {
			    {1, 2, 3, 4, 5, 6},
			    {1, 3, 4, 5, 6, 7},
			    {2, 3, 4, 5, 6, 8},
			    {1, 2, 3, 4, 5, 9}
			};
		
		double[][] grid5 = {
			    {2, 0, -2, 1, -1},
			    {-1, 1, 3, 0, 1},
			    {0, -2, -4, -3, 0}, 
			    {1, 0, -1, 2, 1}
			};
		
		double[][] grid6 = {
				{1, 2, 3, 4},
				{4, 5, 6, 7},
				{6, 7, 8, 9}
			};
		
		
		double[][] grid7 = {
	            {1, 3, 1, 5},
	            {2, 4, -2, 6},
	            {3, 7, -1, 9},
	            {2, 6, 0, 8}
	        };
		
		double[][] grid8 = {
				{-1, 1, 1, 9},
				{2, 1, -1, -10},
				{3, 0, -2, -19},
				{-1, 2, 3, -10},
		};
		
		Matrix a = new Matrix(grid1);
		
		Matrix b = new Matrix(grid2);
		
		Matrix c = new Matrix(grid8);
				
		MatrixOperator op = new MatrixOperator();
		
		Matrix d = op.product(a, b);
		
		d.print();
		c.print();
		op.reduceMatrix(c, true).print();
	}
}
