package BreadthFirstSearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Leetcode 102. Binary Tree Level Order Traversal
 * https://leetcode.com/problems/binary-tree-level-order-traversal/description/
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 * For example: Given binary tree [3,9,20,null,null,15,7],
 *   3
 *   / \
 *  9  20
 *    /  \
 *   15   7
 * return its level order traversal as:
 *	[
 *	  [3],
 *	  [9,20],
 *	  [15,7]
 *	]
 * Explanation and Code from: @marcusgao https://leetcode.com/problems/binary-tree-level-order-traversal/discuss/33450
 * Facebook, Microsoft, Amazon, Bloomberg, LinkedIn, Apple
 * Medium
 */

public class BinaryTreeLevelOrderTraversal {

	TreeNode root;
	
	/*
	    3
	   / \
	  9  20
	    /  \
	   15   7
   */
    public static List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();  
		
		if(root == null) { 
			return result;
		}
		
		System.out.println("root: "+root.val);
		
		Queue<TreeNode> queue = new LinkedList<>();  
		queue.add(root);  
		
		while(!queue.isEmpty()) {
			System.out.println("queue: "+queue+" root: "+root.val);
			
			List<Integer> level = new ArrayList<>();  
			int cnt = queue.size();
			System.out.println("cnt: "+cnt);
			
			for(int i=0; i<cnt; i++) {  
				
				TreeNode node = queue.poll();  
				level.add(node.val); 
				
				System.out.println("node: "+node.val+" level: "+level+" cnt: "+cnt);
				
				if(node.left != null) {  
					System.out.println("node.left.val: "+node.left.val);
					queue.add(node.left);  
				}
				
				if(node.right != null) {  
					System.out.println("node.right.val: "+node.right.val);
					queue.add(node.right);  
				}
			}
			result.add(level);   
		}  
		return result;
    }
    
	public static void main(String[] args) {
		BinaryTreeLevelOrderTraversal tree = new BinaryTreeLevelOrderTraversal();
		
		/*
		 	    3
			   / \
			  9  20
			    /  \
			   15   7
		 */
		
		tree.root = new TreeNode(3);
		tree.root.left = new TreeNode(9);
		tree.root.right = new TreeNode(20);
		tree.root.right.left = new TreeNode(15);
		tree.root.right.right = new TreeNode(7);
		
		System.out.println(levelOrder(tree.root));
	}

}
