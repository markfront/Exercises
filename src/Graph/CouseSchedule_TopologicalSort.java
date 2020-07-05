/*
https://leetcode.com/problems/course-schedule/

There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.

Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.

Constraints:

    The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
    You may assume that there are no duplicate edges in the input prerequisites.
    1 <= numCourses <= 10^5
*/

public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        // prerequisites[i][j] means course j is prerequisite of course i.
        // In topological order, j is before i. In graphic, j-->i
        
        int[] incomingEdges = new int[numCourses]; // for each course, track how many prerequisites it has.
        List<Integer>[] outgoingCourses = new List[numCourses]; // for each course, track what other courses depend on it.
        for(int i=0; i<outgoingCourses.length; i++) {
            outgoingCourses[i] = new ArrayList<Integer>();
        }
        // [i, j] means course j is prerequisite of course i.
        for(int[] pair: prerequisites) {        
            incomingEdges[pair[0]]++; // the course pair[0] has one more prerequisite
            outgoingCourses[pair[1]].add(pair[0]); // course pair[1] is prerequisite of course pair[0]
        }
        
        Deque<Integer> queue = new ArrayDeque<>();
        for(int i=0; i<incomingEdges.length; i++) {
            if (incomingEdges[i]==0) { // the course i has NO prerequisite
                queue.add(i);
            }
        }
        
        /* Topological sorting:
        L ← Empty list that will contain the sorted elements
        S ← Set of all nodes with no incoming edges
        while S is non-empty do
            remove a node n from S
            add n to tail of L
            // get outgoing edge of node n
            for each node m with an incoming edge e from n to m do
                remove edge e from the graph

                if m has no other incoming edges then
                    insert m into S

        if graph has edges then
            return error (graph has at least one cycle)
        else
            return L (a topologically sorted order)
        */
        
        int edgeCnt = prerequisites.length; // total edges in the graph
        while(!queue.isEmpty()) {
            int curr = queue.poll();
            for(int x: outgoingCourses[curr]) { // go over all courses x that depend on curr
                edgeCnt--; // indicate this edge has been checked
                --incomingEdges[x]; // for course x, reduce 1 prerequisite course, which is curr
                if (incomingEdges[x]==0) // the course x has NO more prerequisite
                    queue.add(x);
            }
        }
        return edgeCnt==0; // all prerequisites are satisfied
    }
}
