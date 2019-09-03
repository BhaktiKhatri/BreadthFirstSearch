package BreadthFirstSearch;
import java.util.LinkedList;
import java.util.Queue;

import BreadthFirstSearch.TreeNode;

/**
 * Leetcode 101. Symmetric Tree
 * https://leetcode.com/problems/symmetric-tree/
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center)
 * A tree is symmetric if the left subtree is a mirror reflection of the right subtree.
 * Two trees are a mirror reflection of each other if:
 * Their two roots have the same value. 
 * The right subtree of each tree is a mirror reflection of the left subtree of the other tree.
 * @author NisuBhakti
 * Because we traverse the entire input tree once, the total run time is O(n), where n is the total number of nodes in the tree.
 * The number of recursive calls is bound by the height of the tree. In the worst case, the tree is linear and the height is in O(n)O(n).
 * Therefore, space complexity due to recursive calls on the stack is O(n) in the worst case.
 * Microsoft, Bloomberg, LinkedIn
 * Easy
 */
public class SymmetricTree {

	TreeNode root;

	//Recursive approach
	/**
	 * Two trees are a mirror reflection of each other if:
	 * Their two roots have the same value. The right subtree of each tree is a mirror reflection of the left subtree of the other tree.
	 * Method to check if a binary tree is symmetric or not (Recursively)
	 * @param root
	 * @return
	 */
	public boolean isSymmetric(TreeNode root) {
		return isMirror(root,root);
	}
	
	/*
			    1
			   / \
			  2   2
			 / \ / \
			3  4 4  3
	 */
	public boolean isMirror(TreeNode t1, TreeNode t2) {
		if(t1 == null && t2 == null)
			return true;
		
		if(t1 == null || t2 == null)
			return false;
		
		System.out.println("t1: "+t1.val+" t2: "+t2.val);
		
		if(t1.val == t2.val) {
			return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
		}
		return false;
	}
	//
	
	//Iterative approach
	public boolean isSymmetricIterative(TreeNode root) {
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		q.add(root);
		
		while(!q.isEmpty()) {
			TreeNode t1 = q.poll();
			TreeNode t2 = q.poll();
			
			if(t1 == null && t2 == null)	continue;
			if(t1 == null || t2 == null)	return false;
			if(t1.val != t2.val)	return false;
			q.add(t1.left);
			q.add(t2.right);
			q.add(t1.right);
			q.add(t2.left);
		}
		return true;
	}
	//
	
	public static void main(String args[]) 
    {
        SymmetricTree tree = new SymmetricTree();
        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(2);
        tree.root.left.left = new TreeNode(3);
        tree.root.left.right = new TreeNode(4);
        tree.root.right.left = new TreeNode(4);
        tree.root.right.right = new TreeNode(3);
        boolean output = tree.isSymmetric(tree.root);
        if (output == true)
            System.out.println("Binary tree is symmetric");
        else
            System.out.println("Binary tree is not symmetric");
    }

}
