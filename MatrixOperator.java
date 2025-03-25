package Matrix;

public class MatrixOperator {

	// Takes the dot product of two given matrices. (a x b) then @returns it as a new matrix.
	public Matrix product(Matrix a, Matrix b) {

		// Multiplication result, set as zero matrix initially.
		Matrix result = new Matrix(a.m, b.n);

		// Dimension check for multiplicity.
		if (a.n != b.m) {
			System.out.println("Error: Matrix multiplication cannot be done with given dimensions: "
					+ "(columns of A): " + a.n + ", (rows of B): " + b.m);
			return result; // Returns a null (zero) matrix if multiplication cannot be done.
		}

		// Perform the multiplication.
		for (int i = 0; i < a.m; ++i) {
			for (int j = 0; j < b.n; ++j) {
				double sum = 0;
				for (int k = 0; k < a.n; ++k) {
					sum += a.grid[i][k] * b.grid[k][j];
				}
				result.grid[i][j] = sum;
			}
		}

		return result;
	}

	// Takes the transpose of the given matrix and @returns a new transposed matrix.
	public Matrix transpose(Matrix a) {
		
		// Create a new matrix with swapped dimensions.
		Matrix result = new Matrix(a.n, a.m);

		// Take the transpose. (i,j <=> j,i)
		for (int i = 0; i < a.m; ++i) {
			for (int j = 0; j < a.n; ++j) {
				result.grid[j][i] = a.grid[i][j];
			}
		}

		return result;
	}

	// Calculates the determinant of a square matrix using cofactor expansion (Laplace expansion).
	public double findDeterminant(Matrix a) {
		
		// Check if matrix is a square.
		if (a.m != a.n) {
			System.out.println("Matrix should be square to find it's determinant.");
			return Double.NaN;
		}

		double[][] grid = a.grid;

		// Base case (we have a 1x1 matrix).
		if (a.m == 1)
			return grid[0][0];

		// Base case (we have a 2x2 matrix).
		if (a.m == 2) {
			double diagonal = grid[0][0] * grid[1][1];
			double antiDiagonal = grid[0][1] * grid[1][0];
			return diagonal - antiDiagonal;
		}

		double determinant = 0;
		
		// Choose 0'th column for finding the determinant. (Any row or column is possible, but i chose 0)
		for (int i = 0; i < a.m; ++i) {
			
			double curr = grid[i][0];
			
			// No need to compute if term is zero.
			if(curr == 0) continue;
			
			// Apply the cofactor sign.
			curr *= (i % 2 == 0) ? 1 : -1;

			// Create a new matrix by removing the current row (i) and first column (0).
			Matrix b = new Matrix(a.grid);
			b = deleteRow(b, i);
			b = deleteColumn(b, 0);
			
			// Find the determinant recursively for the smaller matrix, multiply by current cell and add it to the total determinant.
			determinant += curr * findDeterminant(b);
		}

		return determinant;
	}

	// Transforms the given matrix A into its row echelon form or reduced row echelon form (without modifying A).
	// @returns A new matrix representing the reduced form of A.
	// @param rref : Set to false for row echelon form (REF), true for reduced row echelon form (RREF).
	public Matrix reduceMatrix(Matrix matrix, boolean rref) {

		// Create a deep copy of the given matrix.
		Matrix result = new Matrix(matrix.grid);
		double[][] grid = result.grid;
		int m = result.m;
		int n = result.n;

		System.out.println("= Row Operations =");

		// Start reducing
		for (int row = 0; row < m; ++row) {
			boolean isEntry = true;

			for (int col = 0; col < n; col++) {

				// If pivot is zero, find another row with a non-zero pivot and swap them.
				if (row == col && grid[row][col] == 0) {
					for (int r = row + 1; r < m; ++r) {
						if (grid[r][col] != 0) {
							System.out.printf("Swap: Row %d <-> Row %d%n", row + 1, r + 1);
							swapRows(row, r, matrix);
							break;
						}
					}
				}

				if (grid[row][col] != 0 && isEntry) {
					isEntry = false;
					double pivot = grid[row][col];

					// If pivot is not 1, divide the row by pivot to make pivot 1.
					if (pivot != 1) {
						System.out.printf("Row %d -> Row %d / %.2f%n", row + 1, row + 1, pivot);
						for (int c = col; c < n; ++c) {
							grid[row][c] /= pivot;
						}
					}

					// Eliminate the column using the pivot.
					int i = rref ? 0 : row; // false: starts reducing from r=row, true: starts reducing from r=0.
					for (int r = i; r < m; ++r) {
						if (r == row)
							continue;
						double target = grid[r][col];
						if (grid[r][col] != 0) {
							System.out.printf("Row %d -> Row %d - (%.2f * Row %d)%n", r + 1, r + 1, target, row + 1);
							for (int c = 0; c < n; ++c) {
								grid[r][c] -= target * grid[row][c];
							}
						}
					}
				}
			}
		}
		System.out.println("================");
		return result;
	}

	// Helper Functions

	// Helper function to swap two rows of a given matrix.
	private void swapRows(int r1, int r2, Matrix matrix) {
		double[] temp = matrix.grid[r1];
		matrix.grid[r1] = matrix.grid[r2];
		matrix.grid[r2] = temp;
	}
	
	// Helper function to delete n'th row of a matrix.
	private Matrix deleteRow(Matrix a, int row) {
		Matrix newA = new Matrix(a.m - 1, a.n);

		int newRow = 0;
		for (int i = 0; i < a.m; ++i) {
			if (i == row)
				continue;
			newA.grid[newRow++] = a.grid[i].clone();
		}

		return newA;
	}

	// Helper function to delete n'th column of a matrix.
	private Matrix deleteColumn(Matrix a, int col) {
		Matrix newA = new Matrix(a.m, a.n - 1);

		for (int i = 0; i < a.m; i++) {
			int newCol = 0;
			for (int j = 0; j < a.n; j++) {
				if (j == col)
					continue;
				newA.grid[i][newCol++] = a.grid[i][j];
			}
		}

		return newA;
	}
}
