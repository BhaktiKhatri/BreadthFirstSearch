package BreadthFirstSearch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Leetcode 690. Employee Importance
 * https://leetcode.com/problems/employee-importance/description/
 * You are given a data structure of employee information, which includes the employee's unique id, his importance value and his direct subordinates' id.
 * For example, employee 1 is the leader of employee 2, and employee 2 is the leader of employee 3. They have importance value 15, 10 and 5, respectively. Then employee 1 has a data structure like [1, 15, [2]], and employee 2 has [2, 10, [3]], and employee 3 has [3, 5, []]. Note that although employee 3 is also a subordinate of employee 1, the relationship is not direct.
 * Now given the employee information of a company, and an employee id, you need to return the total importance value of this employee and all his subordinates.
 * Example 1: Input: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1; Output: 11
 * Explanation: Employee 1 has importance value 5, and he has two direct subordinates: employee 2 and employee 3. They both have importance value 3. So the total importance value of employee 1 is 5 + 3 + 3 = 11.
 * Explanation and Code from: https://leetcode.com/problems/employee-importance/solution/
 * @author NisuBhakti
 * Time Complexity: O(N), where N is the number of employees. We might query each employee in dfs.
 * Space Complexity: O(N), the size of the implicit call stack when evaluating dfs.
 * Uber
 * Easy
 */


//Employee info
class Employee {
	 // It's the unique id of each node;
	 // unique id of this employee
	 public int id;
	 // the importance value of this employee
	 public int importance;
	 // the id of direct subordinates
	 public List<Integer> subordinates;
};

public class EmployeeImportance {

	 	public static Map<Integer, Employee> emap;
	 	
	    public static int getImportance(List<Employee> employees, int id) {
	        emap = new HashMap<Integer, Employee>();
	        for(Employee e: employees) 
	        	emap.put(e.id, e);
	        return dfs(id);
	    }
	    
	    public static int dfs(int eid) {
	        Employee employee = emap.get(eid);
	        int ans = employee.importance;
	        for(Integer subid: employee.subordinates)
	            ans = ans + dfs(subid);
	        return ans;
	    }
	
	public static void main(String[] args) {
		//[[1, 5, [2, 3]], [2, 3, []], [3, 3, []]]
	}

}
