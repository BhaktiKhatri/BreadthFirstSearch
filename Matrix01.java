package BreadthFirstSearch;

import java.util.Arrays;

/**
 * Leetcode 542. 01 Matrix
 * https://leetcode.com/problems/01-matrix/description/
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell. The distance between two adjacent cells is 1.
 * Example 1: Input:
 * 					0 0 0
 * 					0 1 0
 * 					0 0 0
 * Output:
 * 			0 0 0
 * 			0 1 0
 * 			0 0 0
 * Example 2: Input:
 * 					0 0 0
 * 					0 1 0
 * 					1 1 1
 * Output:
 * 			0 0 0
 * 			0 1 0
 * 			1 2 1
 * Note: The number of elements of the given matrix will not exceed 10,000. There are at least one 0 in the given matrix. The cells are adjacent in only four directions: up, down, left and right.
 * Explanation and Code from: http://massivealgorithms.blogspot.com/2017/04/leetcode-542-01-matrix.html https://leetcode.com/problems/01-matrix/solution/
 * @author NisuBhakti
 * Time complexity: O(r⋅c); 2 passes of r*c each; Space Complexity: O(1)
 * Google
 * Medium
 */


public class Matrix01 {
	
	public static int[][] updateMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return matrix;
        int M = matrix.length, N = matrix[0].length;
        
      //First pass: check for left and top
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++) {
            	System.out.println("i: "+i+" j: "+j+" matrix[i][j]: "+matrix[i][j]);
                if (matrix[i][j] == 0)
                    continue;
                int val = Integer.MAX_VALUE - 1;
                if (i > 0) {
                	System.out.println("val: "+val+" matrix[i - 1][j]: "+matrix[i - 1][j]);
                    val = Math.min(val, matrix[i-1][j] + 1);	//top
                }
                if (j > 0) {
                	System.out.println("val: "+val+" matrix[i][j-1]: "+matrix[i][j-1]);
                    val = Math.min(val, matrix[i][j-1] + 1);	//left
                }
                matrix[i][j] = val;
            }

      //Second pass: check for bottom and right
        for (int i = M - 1; i >= 0; i--)
            for (int j = N - 1; j >= 0; j--) {
            	System.out.println("i: "+i+" j: "+j+" matrix[i][j]: "+matrix[i][j]);
                if (matrix[i][j] == 0)
                    continue;
                int val = matrix[i][j];
                if (i < M - 1) {
                	System.out.println("val: "+val+" matrix[i + 1][j]: "+matrix[i + 1][j]);
                	val = Math.min(val, matrix[i+1][j] + 1);	//bottom
                }
                if (j < N - 1) {
                	System.out.println("val: "+val+" matrix[i][j + 1]: "+matrix[i][j + 1]);
                    val = Math.min(val, matrix[i][j+1] + 1);	//right
                }
                matrix[i][j] = val;
            }
        return matrix;
    }

	public static void main(String[] args) {
		int[][] matrix = {  {0, 0, 0},
							{0, 1, 0},
							{1, 1, 1}
						 };
		
		matrix = updateMatrix(matrix);
		for(int i=0; i<matrix.length; i++)
			System.out.println(Arrays.toString(matrix[i]));
	}
}