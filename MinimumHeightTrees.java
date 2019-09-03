package BreadthFirstSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinimumHeightTrees {

	public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
		for(int i=0; i<edges.length; i++)
			System.out.println(Arrays.toString(edges[i]));
		
	    if (n == 1) 
	    	return Collections.singletonList(0);

	    List<Set<Integer>> adj = new ArrayList<>(n);
	    
	    for (int i = 0; i < n; ++i)
	    	adj.add(new HashSet<>());
	    
	    for (int[] edge : edges) {
	    	System.out.println("edge: "+Arrays.toString(edge));
	        adj.get(edge[0]).add(edge[1]);
	        adj.get(edge[1]).add(edge[0]);
	    }

	    List<Integer> leaves = new ArrayList<>();
	    for (int i = 0; i < n; ++i) {
	     System.out.println("adj.get(i): "+adj.get(i));
	    	if (adj.get(i).size() == 1) 
	        	leaves.add(i);
	    }

	    while (n > 2) {
	    	System.out.println("n: "+n+" leaves.size(): "+leaves.size());
	        n = n - leaves.size();
	        List<Integer> newLeaves = new ArrayList<>();
	        for (int i : leaves) {
	        	System.out.println("i: "+i+" adj.get(i): "+adj.get(i));
	            int j = adj.get(i).iterator().next();
	            System.out.println("j: "+j+" adj.get(j): "+adj.get(j));
	            adj.get(j).remove(i);
	            if (adj.get(j).size() == 1) 
	            	newLeaves.add(j);
	        }
	        System.out.println("newLeaves: "+newLeaves);
	        leaves = newLeaves;
	    }
	    return leaves;
	}
	
	public static void main(String[] args) {
		int n = 6;
		int[][] edges = {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
		//int[][] edges = {{1, 0}, {1, 2}, {1, 3}};
		System.out.println(findMinHeightTrees(n, edges));
	}

}
