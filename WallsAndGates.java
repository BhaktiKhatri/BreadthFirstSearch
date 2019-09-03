package BreadthFirstSearch;

import java.util.Arrays;

/**
 * Leetcode 286. Walls and Gates
 * https://leetcode.com/problems/walls-and-gates/description/
 * You are given a m x n 2D grid initialized with these three possible values.
 * -1 - A wall or an obstacle.
 * 0 - A gate.
 * INF - Infinity means an empty room. 
 * We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
 * For example, given the 2D grid:
 * INF  -1  0  INF
 * INF INF INF  -1
 * INF  -1 INF  -1
 * 0  -1 INF INF
 * After running your function, the 2D grid should be:
 *  3  -1   0   1
 *  2   2   1  -1
 *  1  -1   2  -1
 *  0  -1   3   4
 *  Explanation and Code from: @hwy_2015 https://leetcode.com/problems/walls-and-gates/discuss/72746/
 * @author NisuBhakti
 * Google, Facebook
 * Medium
 */

public class WallsAndGates {

	public static int INF = (int) Double.POSITIVE_INFINITY;
	
	public static void wallsAndGates(int[][] rooms) {
	    for(int i=0; i < rooms.length; i++) {
	       for(int j=0; j < rooms[0].length; j++) {
	        	System.out.println("i: "+i+" j: "+j+" rooms[i][j]: "+rooms[i][j]);
	            
	        	if(rooms[i][j] == 0) {
	            	dfs(rooms, i, j, 0);
	            }
	        }
	    }
	}

	public static void dfs(int[][] rooms, int i, int j, int d) {
		System.out.println("i: "+i+" j: "+j+" d: "+d);
	    if (i < 0 || i >= rooms.length || j < 0 || j >= rooms[0].length || rooms[i][j] < d) 
	    	return;
	    else
	    	System.out.println("dfs i: "+i+" j: "+j+" rooms[i][j]: "+rooms[i][j]+" d: "+d);
	    
	    rooms[i][j] = d;
	    
	    dfs(rooms, i - 1, j, d + 1);	//up
	    dfs(rooms, i + 1, j, d + 1);	//down
	    dfs(rooms, i, j - 1, d + 1);	//left
	    dfs(rooms, i, j + 1, d + 1);	//right
	}
	
	public static void main(String[] args) {
		int[][] rooms = { {INF, -1, 0, INF},
						  {INF, INF, INF, -1},
						  {INF, -1, INF, -1},
						  {0, -1, INF, INF} };
		
		wallsAndGates(rooms);
		
		for(int i=0; i<rooms.length; i++) 
			System.out.println(Arrays.toString(rooms[i]));
	}
		
}