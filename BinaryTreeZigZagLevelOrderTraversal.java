package BreadthFirstSearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Leetcode 103. Binary Tree Zigzag Level Order Traversal
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
 * For example: Given binary tree [3,9,20,null,null,15,7],
 *    3
 *   / \
 *  9  20
 *    /  \
 *   15   7
 * return its zigzag level order traversal as:
 * [
 *  [3],
 *  [20,9],
 *  [15,7]
 * ]
 * Explanation and Code from: @marcusgao https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/discuss/33815
 * Microsoft, Bloomberg, LinkedIn
 * Medium
 */

public class BinaryTreeZigZagLevelOrderTraversal {

	TreeNode root;
	
	/*
	 	Just a little change from the Binary Tree Level Order Traversal.
	 	I use a queue to implement BFS. Each time when I poll a node, I add this node value to level.
	 	I use a variable zigzag to indicate whether add from left to right or right to left. If zigzag == false, it is from left to right; 
	 	if zigzag == true, it is from right to left. And from right to left just need to use ArrayList.add(0, value) method
	 */
	public static List<List<Integer>> zigZagLevelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		
		if(root == null) {
			return result;
		}
		
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		
		System.out.println("root: "+root.val);
		boolean zigzag = false;
		
		while(!queue.isEmpty()) {
		    List<Integer> level = new ArrayList<>();
		    int cnt = queue.size();
		    
		    System.out.println("level: "+level+" cnt: "+cnt);
		    
		    for (int i=0; i<cnt; i++) {
		        TreeNode node = queue.poll();
		        System.out.println("node: "+node.val+" zigzag: "+zigzag);
		        
		        if(zigzag) {
		            level.add(0, node.val);
		        }
		        else {
		            level.add(node.val);
		        }
		        
		        if(node.left != null) {
		        	System.out.println("node.left: "+node.left.val);
		            queue.add(node.left);
		        }
		        
		        if(node.right != null) {
		        	System.out.println("node.right: "+node.right.val);
		            queue.add(node.right);
		        }
		    }
		    result.add(level);
		    zigzag = !zigzag;
		    System.out.println("result: "+result+" zigzag: "+zigzag);
		}
		return result;
	}
	
	public static void main(String[] args) {
		BinaryTreeZigZagLevelOrderTraversal tree = new BinaryTreeZigZagLevelOrderTraversal();
		
		tree.root = new TreeNode(3);
		tree.root.left = new TreeNode(9);
		tree.root.right = new TreeNode(20);
		tree.root.right.left = new TreeNode(15);
		tree.root.right.right = new TreeNode(7);
		
		System.out.println(zigZagLevelOrder(tree.root));
	}

}
