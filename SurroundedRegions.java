package BreadthFirstSearch;

import java.util.Arrays;

/**
 * Leetcode 130. Surrounded Regions
 * https://leetcode.com/problems/surrounded-regions/description/
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'. A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * For example, X X X X
 *				X O O X
 *				X X O X
 *				X O X X
 * After running your function, the board should be: X X X X
 * 													 X X X X
 * 													 X X X X
 * 													 X O X X
 * Explanation and Code from: https://www.programcreek.com/2014/04/leetcode-surrounded-regions-java/
 * @author NisuBhakti
 * O(mn) time; O(mn) space
 * Medium
 */

public class SurroundedRegions {

	/*
	 * 	First, check the four border of the matrix. If there is a element is
		'O', alter it and all its neighbor 'O' elements to '1' or #.
		
		Then ,alter all the 'O' to 'X'
		
		At last,alter all the '1' to 'O'
	 */
	public static void solve(char[][] board) {
	    if(board == null || board.length==0) 
	        return;
	 
	    int m = board.length;
	    int n = board[0].length;
	 
	    //merge O's on left & right border
	    for(int i=0;i<m;i++) {
	    	System.out.println("i: "+i+" board[i][0]: "+board[i][0]+" board[i][n-1]: "+board[i][n-1]);
	        
	    	if(board[i][0] == 'O') {
	            merge(board, i, 0);
	        }
	 
	        if(board[i][n-1] == 'O') {
	            merge(board, i,n-1);
	        }
	    }
	 
	    //merge O's on top & bottom border
	    for(int j=0; j<n; j++) {
	    	System.out.println("j: "+j+" board[0][j]: "+board[0][j]+" board[m-1][j]: "+board[m-1][j]);
	         
	    	if(board[0][j] == 'O') {
	            merge(board, 0,j);
	        }
	 
	        if(board[m-1][j] == 'O') {
	            merge(board, m-1,j);
	        }
	    }
	 
	    //process the board
	    for(int i=0;i<m;i++) {
	        for(int j=0; j<n; j++) {
	        	System.out.println("i: "+i+" j: "+j+" board[i][j]: "+board[i][j]);
	            
	        	if(board[i][j] == 'O') {
	                board[i][j] = 'X';
	            }
	            else if(board[i][j] == '#') {
	                board[i][j] = 'O';
	            }
	        }
	    }
	}
	 
	public static void merge(char[][] board, int i, int j) {
	    if(i<0 || i>=board.length || j<0 || j>=board[0].length) 
	        return;
	 
	    System.out.println("i: "+i+" j: "+j+" board[i][j]: "+board[i][j]);
	    if(board[i][j] != 'O')
	        return;
	 
	    board[i][j] = '#';
	 
	    merge(board, i-1, j);
	    merge(board, i+1, j);
	    merge(board, i, j-1);
	    merge(board, i, j+1);
	}
	
	public static void main(String[] args) {
		char[][] board = {  {'X', 'X', 'X', 'X'},
							{'X', 'O', 'O', 'X'},
							{'X', 'X', 'O', 'X'},
							{'X', 'O', 'X', 'X'},
							};
		solve(board);
		
		for(int i=0; i<board.length; i++)
			System.out.println(Arrays.toString(board[i]));
	}

}
