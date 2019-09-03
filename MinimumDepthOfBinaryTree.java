package BreadthFirstSearch;

/**
 * Leetcode 111. Minimum Depth of Binary Tree
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/discuss/
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * Explanation and Code from: @caiqi8877 https://leetcode.com/problems/minimum-depth-of-binary-tree/discuss/36045
 * @author NisuBhakti
 * Time complexity of above solution is O(n) as it traverses the tree only once.
 * Easy
 */

public class MinimumDepthOfBinaryTree {

	TreeNode root;
	
	public int minDepth(TreeNode root) {
		if(root == null)
			return 0;
		
		System.out.println("root: "+root.val);
		
		int left = minDepth(root.left);
		int right = minDepth(root.right);
		
		System.out.println("left: "+left+" right: "+right);
		
		if(left == 0 || right == 0) 
			return left + right + 1;
		else 
			return Math.min(left, right) + 1;
	}
	
	public static void main(String[] args) 
    {
		MinimumDepthOfBinaryTree tree = new MinimumDepthOfBinaryTree();
  
        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(5);
  
        System.out.println("Minimum Depth of tree is : " + tree.minDepth(tree.root));
    }

}
