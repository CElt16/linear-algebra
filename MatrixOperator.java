package Matrix;

public class MatrixOperator {

	// 1 2 7    2 4
	// 0 5 4    1 2
	//		    9 0	

	public Matrix product(Matrix a, Matrix b) {

		// Multiplication result, set as zero matrix initially.
		Matrix result = new Matrix(a.m, b.n);

		// Dimension check for multiplicity.
		if (a.n != b.m) {
			System.out.println("Matrix multiplication cannot be done with given dimensions: " + "(columns of A): " + a.n
					+ ", (rows of B): " + b.m);
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
	
	// Helper function to swap two rows of a given matrix.
	public static void swapRows(int r1, int r2, Matrix matrix) {
		double[] temp = matrix.grid[r1];
		matrix.grid[r1] = matrix.grid[r2];
		matrix.grid[r2] = temp;
	}

	// @param rref : Set to false for row echelon form (REF), true for reduced row echelon form (RREF).
	// @returns a new reduced matrix 
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
						if (r == row) continue;
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

}
