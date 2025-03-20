package Matrix;

public class Main {

	public static void main(String[] args) {
		double[][] matrix = {
			    {2, 4, -2},
			    {1, 3, 4},
			    {3, 7, 2}
			};
		
		double[][] matrix1 = {
			    {1, 0, 0, 0},
			    {2, 1, 0, 0},
			    {3, 4, 1, 0},
			    {6, 6, 7, 1}
			};
		
		double[][] matrix2 = {
			    {1, 0, 3, 0},
			    {2, 1, 4, 0},
			    {3, 4, 1, 0},
			    {5, 6, 7, 1}
			};
		
		double[][] matrix3 = {
				{1, 2, -1, 3},
			    {2, 4, 0, 8},
			    {3, 6, 1, 12}
		};
		
		double[][] matrix4 = {
			    {1, 2, 3, 4, 5, 6},
			    {1, 3, 4, 5, 6, 7},
			    {2, 3, 4, 5, 6, 8},
			    {1, 2, 3, 4, 5, 9}
			};
		
		double[][] matrix5 = {
			    {2, 0, -2, 1, -1},
			    {-1, 1, 3, 0, 1},
			    {0, -2, -4, -3, 0}, 
			    {1, 0, -1, 2, 1}
			};
		
		double[][] matrix6 = {
				{1, 2, 3, 4},
				{4, 5, 6, 7},
				{6, 7, 8, 9}
			};
		
		
		double[][] matrix7 = {
	            {1, 3, 1, 5},
	            {2, 4, -2, 6},
	            {3, 7, -1, 9},
	            {2, 6, 0, 8}
	        };
		
		double[][] matrix8 = {
				{-1, 1, 1, 9},
				{2, 1, -1, -10},
				{3, 0, -2, -19},
				{-1, 2, 3, -10},
		};
		
		reduceMatrix(matrix8, false);	
	}
	
	// Helper function to swap two rows.
	public static void swapRows(int r1, int r2, double[][] matrix) {
		double[] temp = matrix[r1];
		matrix[r1] = matrix[r2];
		matrix[r2] = temp;
	}
	
	// @param rref : Set to false for row echelon form (REF), true for reduced row echelon form (RREF).
	public static void reduceMatrix(double[][] matrix, boolean rref) {
	    int m = matrix.length;
	    int n = matrix[0].length;

	    System.out.println("=== Row Operations ===");

	    for (int row = 0; row < m; ++row) {
	        boolean isEntry = true;

	        for (int col = 0; col < n; col++) {
	        	
	            // If pivot is zero, find another row with a non-zero pivot and swap them.
	            if (row == col && matrix[row][col] == 0) {
	                for (int r = row + 1; r < m; ++r) {
	                    if (matrix[r][col] != 0) {
	                        System.out.printf("Swap: Row %d <-> Row %d%n", row + 1, r + 1);
	                        swapRows(row, r, matrix);
	                        break;
	                    }
	                }
	            }

	            if (matrix[row][col] != 0 && isEntry) {
	                isEntry = false;
	                double pivot = matrix[row][col];

	                // If pivot is not 1, divide the row by pivot to make the pivot 1.
	                if (pivot != 1) {
	                    System.out.printf("Row %d -> Row %d / %.2f%n", row + 1, row + 1, pivot);
	                    for (int c = col; c < n; ++c) {
	                        matrix[row][c] /= pivot;
	                    }
	                }

	                // Eliminate above and below the pivot using the pivot.
	                int i = rref ? 0 : row; // false: starts reducing from r=row, true: starts reducing from r=0.
	                for (int r = i; r < m; ++r) {
	                    if (r == row) continue;
	                    double target = matrix[r][col];
	                    if (matrix[r][col] != 0) {
	                        System.out.printf("Row %d -> Row %d - (%.2f * Row %d)%n", r + 1, r + 1, target, row + 1);
	                        for (int c = 0; c < n; ++c) {
	                            matrix[r][c] -= target * matrix[row][c];
	                        }
	                    }
	                }
	            }
	        }
	    }

	    // Display the final matrix
	    System.out.println("\n=== Reduced Matrix ===");
	    for (int i = 0; i < matrix.length; ++i) {
	        for (int j = 0; j < matrix[0].length; ++j) {
	            System.out.printf("%5.2f ", matrix[i][j]);
	        }
	        System.out.println();
	    }
	    System.out.println();
	}

}
