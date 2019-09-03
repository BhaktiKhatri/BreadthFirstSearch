package BreadthFirstSearch;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Leetcode 127. Word Ladder
 * https://leetcode.com/problems/word-ladder/description/
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
 * Only one letter can be changed at a time. Each transformed word must exist in the word list. Note that beginWord is not a transformed word
 * For example, Given: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", return its length 5
 * Note: Return 0 if there is no such transformation sequence. All words have the same length. All words contain only lowercase alphabetic characters. You may assume no duplicates in the word list. You may assume beginWord and endWord are non-empty and are not the same.
 * Explanation and Coode from: @cdai https://leetcode.com/problems/word-ladder/discuss/40707 http://javabypatel.blogspot.in/2015/10/word-ladder-doublets-word-links-word-golf.html
 * @author NisuBhakti
 * Medium
 * Facebook, Amazon, LinkedIn, Snapchat, Yelp
 * I think the original solution is O(nL), n is the number of the words in the wordList, and L is the length of the each word.
 * Because, the worst case is that each word needs to enqueue and dequeue. And each time the we need to loop of L times, to check if it exits in the wordList.
 * And the find function is O(1) and to change number is O(1). so the finally time complexity is O(nL).
 */

public class WordLadder {

	// w = hit, hot, dot, lot, dog, log, cog
	//len = 1    2    3    3    4    4    5
	public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
		System.out.println("beginWord: "+beginWord+" endWord: "+endWord+" wordList: "+wordList);
		
		Set<String> dict = new HashSet<>(wordList);
		Set<String> visited = new HashSet<>();
	    Queue<String> queue = new LinkedList<>();
	    
	    queue.offer(beginWord);
	    
	    for(int len = 1; !queue.isEmpty(); len++) {
	    	System.out.println("len: "+len+" queue=========================================================================: "+queue);
	        
	    	for(int i = queue.size(); i > 0; i--) {
	            String w = queue.poll();
	            System.out.println("w==================================================: "+w);
	        
	            if(w.equals(endWord)) { 
	            	System.out.println(queue);
	            	return len;
	            }
	
	            for(int j = 0; j < w.length(); j++) {
	                char[] ch = w.toCharArray();
	                System.out.println("ch: "+Arrays.toString(ch));
	                
	                for(char c = 'a'; c <= 'z'; c++) {
	                    if(c == w.charAt(j)) { 
	                    	continue;
	                    }
	                    
	                    ch[j] = c;
	                    String nb = String.valueOf(ch);
	                    System.out.println("nb: "+nb+" dict: "+dict+" visited: "+visited);
	                    
	                    if (dict.contains(nb) && visited.add(nb)) { 
	                    	queue.offer(nb);
	                    }
	                }
	            }
	        }
	    }
	    return 0;
	}
	
	public static void main(String[] args) {
		String beginWord = "hit";
		String endWord = "cog";
		List<String> wordList = new ArrayList<>();//["hot","dot","dog","lot","log","cog"];
		wordList.add("hot");
		wordList.add("dot");
		wordList.add("dog");
		wordList.add("lot");
		wordList.add("log");
		wordList.add("cog");
		System.out.println(ladderLength(beginWord, endWord, wordList));
	}
}