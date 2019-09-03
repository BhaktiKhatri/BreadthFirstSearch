package BreadthFirstSearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Leetcode 107. Binary Tree Level Order Traversal II
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/description/
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 * For example: Given binary tree [3,9,20,null,null,15,7],
 *    3
 *   / \
 *  9  20
 *    /  \
 *   15   7
 * return its bottom-up level order traversal as:
 * [
 *  [15,7],
 *  [9,20],
 *  [3]
 * ]
 * Explanation and Code from: https://leetcode.com/problems/binary-tree-level-order-traversal-ii/discuss/34981 Leetcode 102. Binary Tree Level Order Traversal
 * Easy
 */

public class BinaryTreeLevelOrderTraversal2 {
	
	TreeNode root;

	public static List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		
		if(root == null)
			return result;
		
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		
		while(!queue.isEmpty()) {
			List<Integer> level = new ArrayList<>();
			int cnt = queue.size();
			
			for(int i=0; i<cnt; i++) {
				TreeNode node = queue.poll();
				level.add(node.val);
				
				if(node.left != null)
					queue.add(node.left);
				
				if(node.right != null)
					queue.add(node.right);
			}
			result.add(0,level);
		}
		return result;
	}
	
	public static void main(String[] args) {
		BinaryTreeLevelOrderTraversal2 tree = new BinaryTreeLevelOrderTraversal2();
		
		tree.root = new TreeNode(3);
		tree.root.left = new TreeNode(9);
		tree.root.right = new TreeNode(20);
		tree.root.right.left = new TreeNode(15);
		tree.root.right.right = new TreeNode(7);
		
		System.out.println(levelOrderBottom(tree.root));
	}

}
