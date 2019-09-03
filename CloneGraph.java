package BreadthFirstSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Leetcode 133. Clone Graph
 * https://leetcode.com/problems/clone-graph/description/
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 * @author NisuBhakti
 *
 */
public class CloneGraph {

	public HashMap<Integer, UndirectedGraphNode> map = new HashMap<Integer, UndirectedGraphNode>();
	
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		return clone(node);
	}
	
	public UndirectedGraphNode clone(UndirectedGraphNode node) {
		if(node == null)
			return null;
		
		if(map.containsKey(node.label))
			return map.get(node.label);
		
		UndirectedGraphNode cloneNode = new UndirectedGraphNode(node.label);
		map.put(cloneNode.label, cloneNode);
		for(UndirectedGraphNode neighborNode: node.neighbors) {
			cloneNode.neighbors.add(clone(neighborNode));
		}
		
		return cloneNode;
	}
}
