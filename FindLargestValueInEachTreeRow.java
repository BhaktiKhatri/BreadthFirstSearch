package BreadthFirstSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Leetcode 515. Find Largest Value in Each Tree Row
 * https://leetcode.com/problems/find-largest-value-in-each-tree-row/description/
 * You need to find the largest value in each row of a binary tree. 
 * Example: Input: 
 *          1
 *         / \
 *        3   2
 *       / \   \  
 *      5   3   9 
 * Output: [1, 3, 9]
 * Explanation and Code from: https://leetcode.com/problems/find-largest-value-in-each-tree-row/discuss/98971
 * @author NisuBhakti
 * Medium
 * LinkedIn
 */

public class FindLargestValueInEachTreeRow {

	TreeNode root;
		
	public static List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        helper(root, result, 0);
        return result;
    }
	
	/*
	 *          1
	 *         / \
	 *        3   2
	 *       / \   \  
	 *      5   3   9 
	 */
    public static void helper(TreeNode root, List<Integer> result, int depth) {
        if(root == null) 
            return;
        
        System.out.println("root: "+root.val+" depth: "+depth+" result: "+result);
        					
        if(depth == result.size()) {										//expand list size
            result.add(root.val);
        }
        else {
        	System.out.println("depth: "+depth+" result.get(depth): "+result.get(depth)+" root: "+root.val);
            result.set(depth, Math.max(result.get(depth), root.val));		//or set value
        }
        
        helper(root.left, result, depth + 1);
        helper(root.right, result, depth + 1);
    }
	
	public static void main(String[] args) {
		FindLargestValueInEachTreeRow tree = new FindLargestValueInEachTreeRow();
		
		tree.root = new TreeNode(1);
		tree.root.left = new TreeNode(3);
		tree.root.left.left = new TreeNode(5);
		tree.root.left.right = new TreeNode(3);
		tree.root.right = new TreeNode(2);
		tree.root.right.right = new TreeNode(9);
		
		System.out.println(largestValues(tree.root));
	}

}
