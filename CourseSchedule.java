package BreadthFirstSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule {
	
	public static boolean canFinish1(int numCourses, int[][] prerequisites) {
	    int[] indegree = new int[numCourses];
	    Queue<Integer> queue = new LinkedList<Integer>();
	    
	    for(int[] pair: prerequisites) {
	    	System.out.println("pair: "+Arrays.toString(pair)+"pair[1]: "+pair[1]+" indegree[pair[1]]: "+indegree[pair[1]]);
	        indegree[pair[1]]++;
	    }
	    
	    System.out.println(Arrays.toString(indegree));
	    
	    for(int i=0;i<indegree.length;i++) {
	    	System.out.println("i: "+i+" indegree[i]: "+indegree[i]);
	        
	    	if(indegree[i] == 0) {
	            queue.add(i);
	        }
	    }
	    
	    while(!queue.isEmpty()) {
	        System.out.println("queue: "+queue+" numCourses: "+numCourses);
	    	
	        numCourses--;
	        int course = queue.poll();
	        System.out.println("course: "+course);
	        
	        for(int[] pair: prerequisites) {
	        	System.out.println("pair: "+Arrays.toString(pair));
	            
	        	System.out.println("pair[0]: "+pair[0]+" course: "+course);
	        	
	        	if(pair[0] == course) {
	        		System.out.println("pair[1]: "+pair[1]+" indegree[pair[1]]: "+indegree[pair[1]]);
	                indegree[pair[1]]--;
	                
	                System.out.println("indegree[pair[1]]: "+indegree[pair[1]]);
	                
	                if(indegree[pair[1]] == 0) {
	                    queue.add(pair[1]);
	                }
	            }
	        }
	    }
	    
	    System.out.println("numCourses: "+numCourses);
	    
	    return numCourses == 0;
	}

	public static boolean canFinish(int num, int[][] preReqs) {
        List<Integer>[] adj = new List[num];
        
        for (int i = 0; i < adj.length; i++) 
        	adj[i] = new ArrayList<>();
        
        for (int[] edge : preReqs) {
            int from = edge[1], to = edge[0];
            adj[from].add(to);
        }
        
        int[] state = new int[num];
        for (int i = 0; i < num; i++) {
            if (!dfs(adj, state, i)) 
            	return false;
        }
        
        return true;
    }
    
    public static boolean dfs(List<Integer>[] adj, int[] state, int e) {
        if (state[e] == 2) 
        	return true;
        
        if (state[e] == 1) 
        	return false;
        
        state[e] = 1;
        
        for (int next : adj[e]) {
            if (!dfs(adj, state, next)) 
            	return false;
        }
        state[e] = 2;
        
        return true;
    }
	
	public static void main(String[] args) {
		int num = 2;
		int[][] preReqs = {{1,0}};
		System.out.println(canFinish1(num, preReqs));
	}

}

