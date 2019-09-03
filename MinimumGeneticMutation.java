package BreadthFirstSearch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
 * 433. Minimum Genetic Mutation
 * https://leetcode.com/problems/minimum-genetic-mutation/description/
 * A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".
 * Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation is defined as ONE single character changed in the gene string.
 * For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.
 * Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in the bank to make it a valid gene string.
 * Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations needed to mutate from "start" to "end". If there is no such a mutation, return -1.
 * Note: Starting point is assumed to be valid, so it might not be included in the bank.
 * If multiple mutations are needed, all mutations during in the sequence must be valid. You may assume start and end string is not the same.
 * Note:
 * Starting point is assumed to be valid, so it might not be included in the bank.
 * If multiple mutations are needed, all mutations during in the sequence must be valid. You may assume start and end string is not the same.
 * Example 1: start: "AACCGGTT", end:   "AACCGGTA", bank: ["AACCGGTA"], return: 1
 * Example 2: start: "AACCGGTT", end:   "AAACGGTA", bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"], return: 2
 * Example 3: start: "AAAAACCC", end:   "AACCCCCC", bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"], return: 3
 * Explanation and Code from: https://gist.github.com/mymxyx/55e25ac6bcb38551adc12073afff2e45
 * Twitter
 * Medium
 */

public class MinimumGeneticMutation {

	//final https://gist.github.com/mymxyx/55e25ac6bcb38551adc12073afff2e45 https://medium.com/@terracotta_ko/leetcode-433-minimum-genetic-mutation-f2aeec28731f
	public static int minMutation(String start, String end, String[] bank) {
        int count = 0;
		
        System.out.println("start: "+start+" end: "+end+" bank: "+Arrays.toString(bank));
        
		if(start.length() != end.length()) 
			return -1;
		else if(start.equals(end))
			return 0;
		
		Queue<String> q = new LinkedList<String>();		
		q.add(start);
		
		while(!q.isEmpty()) {
			System.out.println("queue: "+q+" count: "+count);
			
			Queue<String> temp = new LinkedList<String>();
		
			while(!q.isEmpty()) {
				String tmp = q.poll();
				System.out.println("tmp: "+tmp+" end: "+end);
				
				if(tmp.equals(end))
					return count;
				
				for(String s:bank) {
					System.out.println("s: "+s);
					if(mutation(tmp,s) == 1) {
						System.out.println("temp: "+temp);
						temp.add(s);
					}
				}
			}
			q = temp;
			count++;
		}
		return -1;
    }
    
	public static int mutation(String s1,String s2) {
		if(s1.equals(s2))
			return 0;
		else if(s1.length() != s2.length())
            return -1;
		
		int count = 0;
		for(int i=0; i<s1.length(); i++) {
			System.out.println("s1.charAt(i): "+s1.charAt(i)+" s2.charAt(i): "+s2.charAt(i));
			if(s1.charAt(i) != s2.charAt(i)) {
				count++;
			}
		}
		System.out.println("count: "+count);
		
		return count;
	}
	
	//https://leetcode.com/problems/minimum-genetic-mutation/discuss/91484/Java-Solution-using-BFS
	public static int minMutation1(String start, String end, String[] bank) {
        if(start.equals(end)) 
        	return 0;
        
        System.out.println("start: "+start+" end: "+end+" bank: "+Arrays.toString(bank));
        
        Set<String> bankSet = new HashSet<>();
        for(String b: bank) 
        	bankSet.add(b);
        
        char[] charSet = new char[]{'A', 'C', 'G', 'T'};
        
        int level = 0;
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                String curr = queue.poll();
                if(curr.equals(end)) 
                	return level;
                
                char[] currArray = curr.toCharArray();
                for(int i = 0; i < currArray.length; i++) {
                    char old = currArray[i];
                    for(char c: charSet) {
                        currArray[i] = c;
                        String next = new String(currArray);
                        if(!visited.contains(next) && bankSet.contains(next)) {
                            visited.add(next);
                            queue.offer(next);
                        }
                    }
                    currArray[i] = old;
                }
            }
            level++;
        }
        return -1;
    }
	
	public static void main(String[] args) {
		String start = "AACCGGTT";
		String end = "AAACGGTA";
		String[] bank = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};
		
		System.out.println(minMutation1(start, end, bank));
	}

}
