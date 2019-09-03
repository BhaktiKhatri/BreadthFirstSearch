package BreadthFirstSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Leetcode 301. Remove Invalid Parentheses
 * https://leetcode.com/problems/remove-invalid-parentheses/description/
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 * Note: The input string may contain letters other than the parentheses ( and ).
 * Examples: "()())()" -> ["()()()", "(())()"]; "(a)())()" -> ["(a)()()", "(a())()"]; ")(" -> [""]
 * Explanation and Code from: @dietpepsi https://leetcode.com/problems/remove-invalid-parentheses/discuss/75027/
 * @author NisuBhakti
 * Facebook
 */

public class RemoveInvalidParenthesis {

	public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        remove(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }
    
    public void remove(String s, List<String> ans, int last_i, int last_j,  char[] par) {
	    System.out.println("s: "+s+" ans: "+ans+" last_i: "+last_i+" last_j: "+last_j+" par: "+Arrays.toString(par));
	    
    	for (int stack = 0, i = last_i; i < s.length(); ++i) {
    		System.out.println("stack: "+stack+" i: "+i+" s.charAt(i): "+s.charAt(i)+" par[0]: "+par[0]+" par[1]: "+par[1]);
    		
    		if (s.charAt(i) == par[0]) {
	        	stack++;
	        }
	        else if (s.charAt(i) == par[1]) {
	        	stack--;
	        }
    		
	        if (stack >= 0) 
	        	continue;
	        
	        for (int j = last_j; j <= i; ++j) {
	        	if(j >= 1)
	        	System.out.println("i: "+i+" j: "+j+" last_j: "+last_j+" s.charAt(j): "+s.charAt(j)+" s.charAt(j - 1): "+s.charAt(j - 1));
	            
	        	if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1])) {
	            	System.out.println("substr1: "+s.substring(0, j)+" substr2: "+s.substring(j + 1, s.length())+" ans: "+ans);
	            
	            	remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
	            }
	        }
	        return;
	    }
	    
	    String reversed = new StringBuilder(s).reverse().toString();
	    System.out.println("reversed: "+reversed);
	    
	    if (par[0] == '(') {// finished left to right
	        remove(reversed, ans, 0, 0, new char[]{')', '('});
	    }
	    else { // finished right to left
	        ans.add(reversed);
	    }
    }

    public static void main(String args[]) {
    	RemoveInvalidParenthesis remove = new RemoveInvalidParenthesis();
    	String expression = "()())()";
    	System.out.println(expression);
        System.out.println(remove.removeInvalidParentheses(expression));
    }
}
