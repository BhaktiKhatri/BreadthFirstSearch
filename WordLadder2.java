package BreadthFirstSearch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadder2 {
	
	public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
		System.out.println("beginWord: "+beginWord+" endWord: "+endWord+" wordList: "+wordList);
		
		Set<String> dict = new HashSet<>(wordList);
		Set<String> visited = new HashSet<>();
	    Queue<String> queue = new LinkedList<>();
	    queue.offer(beginWord);
	    
	    List<List<String>> results = new ArrayList<>();
	    
	    for (int len = 1; !queue.isEmpty(); len++) {
	    	System.out.println("len: "+len+" queue: "+queue);
	        for (int i = queue.size(); i > 0; i--) {
	            String w = queue.poll();
	            System.out.println("w: "+w);
	            if (w.equals(endWord)) 
	            	return len;
	
	            for (int j = 0; j < w.length(); j++) {
	                char[] ch = w.toCharArray();
	                for (char c = 'a'; c <= 'z'; c++) {
	                    if (c == w.charAt(j)) 
	                    	continue;
	                    ch[j] = c;
	                    String nb = String.valueOf(ch);
	                    if (dict.contains(nb) && visited.add(nb)) 
	                    	queue.offer(nb);
	                }
	            }
	        }
	    }
	    return 0;
	}
	
	public static void main(String[] args) {

	}

}
