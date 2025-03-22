package Matrix;

public class Matrix {
	public int m; // row size
	public int n; // column size
	public double[][] grid; // values
	
	// Constructs the matrix as a zero matrix.
	Matrix(int m, int n) {
		this.m = m; 
		this.n = n;
		this.grid = new double[m][n]; 
	}
	
	// Constructs the matrix with a given grid.
	Matrix(double[][] grid) {
		this.m = grid.length; 
		this.n = grid[0].length;
		
		// Deep copy the given grid into the new matrix grid.
		this.grid = new double[m][n];
		
		for(int i=0; i<m; ++i) {
			for(int j=0; j<n; ++j) {
				this.grid[i][j] = grid[i][j];
			}
		}
	}
	
	// Displays the matrix.
	public void print() {
		for(int i=0; i<m; ++i) {
			for(int j=0; j<n; ++j) {
				System.out.printf("%.2f ", grid[i][j]);
			}
			System.out.println();
		}
	}
}
