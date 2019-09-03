package BreadthFirstSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Leetcode 279. Perfect Squares
 * https://leetcode.com/problems/perfect-squares/description/
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 * Explanation and Code from: @zhukov 2.Static Dynamic Programming: 12ms @edickcoding https://leetcode.com/problems/perfect-squares/discuss/71488?page=4
 * @author NisuBhakti
 * Google
 * Medium
 */

public class PerfectSquares {

	public static List<Integer> result = new ArrayList<>();
	
    public static int numSquares(int n) {
    	System.out.println("n: "+n);
        if (result.size() == 0) {
            result.add(0);
        }
        
        while (result.size() <= n) {
        	System.out.println("result: "+result+" result.size(): "+result.size());
        	
            int m = result.size();
            int tempMin = Integer.MAX_VALUE;
            System.out.println("m: "+m+" tempMin: "+tempMin);
            
            for(int j = 1; j * j <= m; j++) {
            	System.out.println("j: "+j+" (m - j * j): "+(m - j * j));
            	
                tempMin = Math.min(tempMin, result.get(m - j * j) + 1);		 //For each i, it must be the sum of some number (i - j*j) & a perfect square number (j*j)
                System.out.println("tempMin: "+tempMin);
            }
            result.add(tempMin);
            System.out.println("result: "+result);
        }
        return result.get(n);
    }
    
    //Refer this: https://www.youtube.com/watch?v=KaXeidsmvVQ
    public static int numSquares2(int n) {
        if(n <= 0) {
            return 0;
        }
        
        System.out.println("n: "+n);
        
        int[] dp = new int[n+1];
        Arrays.fill(dp, n);
        dp[0] = 0;
        
        System.out.println("dp: "+Arrays.toString(dp));
        //12 = 4 + dp[12 - 4] = 4 + 4 + dp[8 - 4] = 4 + 4 + 4
        for(int i=1; i<=n; i++) {
        	System.out.println("i: "+i);
            
        	for(int j=1; j*j<=i; j++) {
        		System.out.println("i: "+i+" dp[i]: "+dp[i]+" j: "+j+" i-j*j: "+(i-j*j)+" dp[i-j*j]: "+dp[i-j*j]);
        		
                dp[i] = Math.min(dp[i], dp[i-j*j]+1);
                System.out.println("dp[i]: "+dp[i]);
            }
        }
        System.out.println("dp: "+Arrays.toString(dp));
        
        return dp[n];
    }
    
    public static int numSquares1(int n) {
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.offer(0);
        visited.add(0);
        int depth = 0;
        
        System.out.println("n: "+n);
        
        while(!q.isEmpty()) {
        	System.out.println("q: "+q+" visited: "+visited);
            
        	int size = q.size();
            depth++;
        
            System.out.println("size: "+size+" depth: "+depth);
            
            while(size > 0) {
            	System.out.println("q: "+q+" visited: "+visited);
            	
            	size--;
                int u = q.poll();
                System.out.println("u: "+u);
                
                for(int i = 1; i*i <= n; i++) {
                	System.out.println("u: "+u+" i: "+i);
                	
                    int v = u+i*i;
                    System.out.println("v: "+v+" n: "+n+" depth: "+depth);
                    
                    if(v == n) {
                        return depth;
                    }
                    
                    if(v > n) {
                        break;
                    }
                    System.out.println("visited: "+visited+" v: "+v+" q: "+q);
                    
                    if(!visited.contains(v)) {
                        q.offer(v);
                        visited.add(v);
                    }
                }
            }
        }
        System.out.println("depth: "+depth+" q: "+q+" visited: "+visited);
        return depth;
    }

	
	public static void main(String[] args) {
		int n = 12;
		System.out.println(numSquares2(n));
	}

}
