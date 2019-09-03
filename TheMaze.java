package BreadthFirstSearch;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 490. The Maze
 * https://leetcode.com/problems/the-maze/
 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, 
 * but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
 * Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze
 * are all walls. The start and destination coordinates are represented by row and column indexes.
 * Example 1: Input 1: a maze represented by a 2D array
	0 0 1 0 0
	0 0 0 0 0
	0 0 0 1 0
	1 1 0 1 1
	0 0 0 0 0

	Input 2: start coordinate (rowStart, colStart) = (0, 4)
	Input 3: destination coordinate (rowDest, colDest) = (4, 4)
	
	Output: true
	Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.

	Input 1: a maze represented by a 2D array

	0 0 1 0 0
	0 0 0 0 0
	0 0 0 1 0
	1 1 0 1 1
	0 0 0 0 0
	
	Input 2: start coordinate (rowStart, colStart) = (0, 4)
	Input 3: destination coordinate (rowDest, colDest) = (3, 2)
	
	Output: false
	
	Explanation: There is no way for the ball to stop at the destination.

 *	Explanation and Code from: @selim https://leetcode.com/problems/the-maze/discuss/97067/Simple-Java-DFS-with-comments
 *
 */

public class TheMaze {

	static int[][] dirs = new int[][]{ {0, -1}, {1, 0}, {0, 1}, {-1, 0} };
    
	/*
	 * Search in the four possible directions when coming to a stopping point (i.e. a new starting point).
	 * Keep track of places that you already started at in case you roll back to that point.
	 */
	public static boolean hasPath(int[][] maze, int[] start, int[] destination) {
		printMatrix(maze);
    	System.out.println("start: "+Arrays.toString(start));
    	System.out.println("destination: "+Arrays.toString(destination));
    	
	    boolean[][] visited = new boolean[maze.length][maze[0].length];
	    return dfs(maze, start, destination, visited);
	}

	private static boolean dfs(int[][] maze, int[] start, int[] destination, boolean[][] visited) {
		System.out.println("start: "+Arrays.toString(start)+" visited[start[0]][start[1]]: "+visited[start[0]][start[1]]);
		
		if (visited[start[0]][start[1]]) {
	        return false;
	    }
	    
		if (start[0] == destination[0] && start[1] == destination[1]) {
	        return true;
	    }
	    
		visited[start[0]][start[1]] = true;
	    System.out.println("=======================================dfs: ["+start[0]+"]["+start[1]+"]");
		
		for (int i = 0; i < dirs.length; i++) {
			int[] d = dirs[i];
			
			System.out.println("i: "+i+" d: "+Arrays.toString(d));
			
	        int row = start[0];
	        int col = start[1];
	        System.out.println("row: "+row+" col: "+col+" d[0]: "+d[0]+" d[1]: "+d[1]);
	        
	        while(isValid(maze, row + d[0], col + d[1])) {
	            row = row + d[0];
	            col = col + d[1];
	        }
	        System.out.println("row: "+row+" col: "+col);
	        
	        if (dfs(maze, new int[]{ row, col }, destination, visited)) {
	            return true;
	        }
	    }
	    return false;
	}

	private static boolean isValid(int[][] maze, int row, int col) {
		System.out.println("row: "+row+" col: "+col);
        if (row >= maze.length || row < 0 || col >= maze[0].length || col < 0) 
        	return false;
        
        System.out.println("maze[row][col]: "+maze[row][col]);
        return maze[row][col] != 1; // 1 is a wall
    }
	
    public static void printMatrix(int[][] matrix) {
		for(int i=0; i<matrix.length; i++) {
			for(int j=0; j<matrix[0].length; j++) {
				System.out.print(" "+matrix[i][j]);
			}
			System.out.println();
		}
	}
    
	public static void main(String[] args) {
		int[][] maze = {{0, 0, 1, 0, 0},
						{0, 0, 0, 0, 0},
						{0, 0, 0, 1, 0},
						{1, 1, 0, 1, 1},
						{0, 0, 0, 0, 0}};
		int[] start = {0, 4};
		int[] destination = {4, 4};
		System.out.println(hasPath(maze, start, destination));
		
	}

}
