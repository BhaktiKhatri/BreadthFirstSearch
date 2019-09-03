package BreadthFirstSearch;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Leetcode 210. Course Schedule II
 * https://leetcode.com/problems/course-schedule-ii/description/
 * There are a total of n courses you have to take, labeled from 0 to n - 1. 
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
 * For example: 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]
 * 4, [[1,0],[2,0],[3,1],[3,2]] 
 * There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken 
 * after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].
 * Note: The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 * Explanation and Code from: https://discuss.leetcode.com/topic/13848/standard-bfs-method-for-course-schedule-ii
 * @author NisuBhakti
 * Medium
 * Facebook, Zenefits
 */

public class CourseSchedule2 {

	/*
	 	The idea is to start from courses upon which no other courses will depend (These courses all have the least priority and should come at the last 
	 	in the order list). Then do standard BFS on these courses and try to find courses that have next level of priority(These courses will come
	 	right before the least priority courses in the order list). And continue the process until we have no more courses to deal with. If there is
	 	no cycle in the prerequisites graph, then in this way we should be able to deal with all the courses. Otherwise there will be remaining courses
	 	left untended. See the comments in the code for more information.
	 */
	public static int[] findOrder(int numCourses, int[][] prerequisites) {
		System.out.println("numCourses: "+numCourses);
		for(int i=0; i<prerequisites.length; i++)
			System.out.println(Arrays.toString(prerequisites[i]));
		
		// for each course in the map nextVertex, the corresponding set contains prerequisite courses for this course 
	    Map<Integer, Set<Integer>> nextVertex = new HashMap<>();		//key-course; value-prereq
	    // preVertex[i] indicates the number of courses that depend on course i
	    int[] preVertex = new int[numCourses];
	    
	    // set up nextVertex and preVertex
	    for (int i = 0; i < prerequisites.length; i++) {
	    	System.out.println("i: "+i+" prerequisites[i][0]: "+prerequisites[i][0]+" prerequisites[i][1]: "+prerequisites[i][1]);
	    	if (!nextVertex.containsKey(prerequisites[i][0])) {
	    		nextVertex.put(prerequisites[i][0], new HashSet<>());
	    	}
	    	
	    	if (nextVertex.get(prerequisites[i][0]).add(prerequisites[i][1])) {
	    		preVertex[prerequisites[i][1]]++;
	    	}
	    }
	    System.out.println("nextVertex: "+nextVertex+" preVertex: "+Arrays.toString(preVertex));
	    
	    // queue for BFS, which will only hold courses currently upon which no other courses depend
	    Deque<Integer> queue = new ArrayDeque<>();
	    
	    for (int i = 0; i < preVertex.length; i++) {
	    	System.out.println("i: "+i+" preVertex[i]: "+preVertex[i]);
	    	// start from courses upon which no other courses depend. These courses should come last in the order list
	    	if(preVertex[i] == 0) {
	    		queue.offerLast(i);
	    	}
	    }
	    
	    // array for the result, which will be filled up from the end by index
	    int[] res = new int[numCourses];
	    int index = res.length - 1;
	    
	    while(!queue.isEmpty()) {
	    	
	    	int key = queue.pollFirst(); // this is a course that no other courses will depend upon
	    	System.out.println("queue: "+queue+" key: "+key);
	    	res[index--] = key;          // so we put it at the end of the order list
	    	
	    	// since we are done with course "key", for any other course that course "key" is dependent on, we can decrease
	    	// the corrresponding preVertex by one and check if it is qualified to be added to the queue.
	    	if(nextVertex.containsKey(key)) {
	    		System.out.println("nextVertex: "+nextVertex+" key: "+key);
	    		
	    		for(int i: nextVertex.get(key)) {
	    			System.out.println("i: "+i+" preVertex[i]: "+preVertex[i]);
	    		
	    			if(--preVertex[i] == 0) {
	    				queue.offerLast(i);
	    			}
	    		}
	    	}
	    	
	    	--numCourses; // we are done with course "key", so reduce the remaining number of courses by 1
	    }
	    
	    // if the remaining number of courses is not zero, then we cannot complete all the courses; otherwise return the result
	    return numCourses == 0 ? res : new int[0];
	}
	
	public static void main(String[] args) {
		int numCourses = 4;
		int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
		System.out.println(Arrays.toString(findOrder(numCourses, prerequisites)));
	}
}