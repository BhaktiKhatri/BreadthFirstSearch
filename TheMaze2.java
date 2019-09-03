package BreadthFirstSearch;

import java.util.Arrays;

/*
 * 505. The Maze II
 * https://leetcode.com/problems/the-maze-ii/
 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, 
 * but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
 * Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. 
 * The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). 
 * If the ball cannot stop at the destination, return -1.
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls.
 * The start and destination coordinates are represented by row and column indexes.
 * Example 1:

	Input 1: a maze represented by a 2D array
	
	0 0 1 0 0
	0 0 0 0 0
	0 0 0 1 0
	1 1 0 1 1
	0 0 0 0 0
	
	Input 2: start coordinate (rowStart, colStart) = (0, 4)
	Input 3: destination coordinate (rowDest, colDest) = (4, 4)
	
	Output: 12
	
	Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
	             The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.
 
 * Code from: https://leetcode.com/problems/the-maze-ii/discuss/98401/JAVA-Accepted-DFS and Maze-1
 *   
 *  
 */
public class TheMaze2 {

	static int[][] dirs = new int[][]{ {0, -1}, {1, 0}, {0, 1}, {-1, 0} };
    
	/*
	 * Search in the four possible directions when coming to a stopping point (i.e. a new starting point).
	 * Keep track of places that you already started at in case you roll back to that point.
	 */
	public static int shortestDistance(int[][] maze, int[] start, int[] destination) {
		printMatrix(maze);
    	System.out.println("start: "+Arrays.toString(start));
    	System.out.println("destination: "+Arrays.toString(destination));
    	
	    int[][] visited = new int[maze.length][maze[0].length];
	    visited[start[0]][start[1]] = 1;
	    dfs(maze, start, destination, visited);
	    return visited[destination[0]][destination[1]] - 1;
	}

	private static void dfs(int[][] maze, int[] start, int[] destination, int[][] visited) {
		System.out.println("start: "+Arrays.toString(start)+" visited[start[0]][start[1]]: "+visited[start[0]][start[1]]);
	    
		if (start[0] == destination[0] && start[1] == destination[1]) {
	        return;
	    }
	    
	    System.out.println("=======================================dfs: ["+start[0]+"]["+start[1]+"]");
		
		for (int i = 0; i < dirs.length; i++) {
			int[] d = dirs[i];
			
			System.out.println("i: "+i+" d: "+Arrays.toString(d));
			
	        int row = start[0];
	        int col = start[1];
	        int len = visited[row][col];
	        System.out.println("row: "+row+" col: "+col+" d[0]: "+d[0]+" d[1]: "+d[1]+" len: "+len);
	        
	        while(isValid(maze, row + d[0], col + d[1])) {
	            row = row + d[0];
	            col = col + d[1];
	            len++;
	        }
	        System.out.println("row: "+row+" col: "+col+" len: "+len+" visited[row][col]: "+visited[row][col]);
	        /*
	         * visited[row][col] > 0 means it is visited so don't do dfs on visited instead iterate for loop
	         * len >= visited[row][col] means visited[row][col] already has a shorter length so don't put visited[row][col] = len and iterate for loop
	         */
	        if(visited[row][col] > 0 && len >= visited[row][col]) {	
	        	continue;
	        }
	        visited[row][col] = len;
	        
	        System.out.println("row: "+row+" col: "+col+" len: "+len+" visited[row][col]: "+visited[row][col]);
	        dfs(maze, new int[]{ row, col }, destination, visited);
	    }
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
		System.out.println(shortestDistance(maze, start, destination));
		
	}


}
