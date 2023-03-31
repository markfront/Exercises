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

import java.util.*;

public class CourseSchedule_TopoSort {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        // prerequisites[i][j] means course j is prerequisite of course i.
        // In topological order, j is before i. In graphic, j-->i
        
        // for each course, track how many prerequisites it has.
        int[] incomingEdges = new int[numCourses]; 

        // for each course, track what courses depend on it.
        Map<Integer, List<Integer>> outgoingCourses = new HashMap<>();

        // [i, j] means course j is prerequisite of course i.
        for(int[] pair: prerequisites) {
            int courseId = pair[0];
            int prereqId = pair[1];

            incomingEdges[courseId]++; // the course pair[0] has one more prerequisite

            // course pair[1] is prerequisite of course pair[0]
            // outgoing is from prereqId to courseId
            if (!outgoingCourses.containsKey(prereqId)) {
                List<Integer> outgoingList = new ArrayList<>();
                outgoingList.add(courseId);
                outgoingCourses.put(prereqId, outgoingList);
            } else {
                outgoingCourses.get(prereqId).add(courseId);
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
        
        // track the courses that have NO prerequisite
        Deque<Integer> queue = new ArrayDeque<>();
        for(int i=0; i<incomingEdges.length; i++) {
            if (incomingEdges[i]==0) { 
                queue.add(i);
            }
        }

        int edgeCnt = prerequisites.length; // track total edges not satisfied
        while(!queue.isEmpty()) {
            int curr = queue.poll();

            // for all courses depend on this curr course
            // flag that this prerequisite has been satisfied
            List<Integer> outgoingList = outgoingCourses.get(curr);
            for(int x: outgoingList) { 
                edgeCnt--; // remove 1 from total unsatisfied
                --incomingEdges[x]; // for course x, reduce 1 prerequisite course, which is curr
                if (incomingEdges[x]==0) {
                    // the course x has NO more prerequisite
                    // means other courses depending on it could become unblocked
                    queue.add(x);
                }
            }
        }
        return edgeCnt==0; // all prerequisites are satisfied
    }
}
