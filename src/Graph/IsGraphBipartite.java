/*
https://leetcode.com/problems/is-graph-bipartite/

Given an undirected graph, return true if and only if it is bipartite.

Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.

The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.

Example 1:
Input: [[1,3], [0,2], [1,3], [0,2]]
Output: true
Explanation: 
The graph looks like this:
0----1
|    |
|    |
3----2
We can divide the vertices into two groups: {0, 2} and {1, 3}.

Example 2:
Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
Output: false
Explanation: 
The graph looks like this:
0----1
| \  |
|  \ |
3----2
We cannot find a way to divide the set of nodes into two independent subsets.

*/
/*
Our goal is trying to use two colors to color the graph and see if there are any adjacent nodes having the same color.
Initialize a color[] array for each node. Here are three states for colors[] array:
0: Haven't been colored yet.
1: Blue.
-1: Red.
For each node,

    If it hasn't been colored, use a color to color it. Then use the other color to color all its adjacent nodes (DFS).
    If it has been colored, check if the current color is the same as the color that is going to be used to color it. (Please forgive my english... Hope you can understand it.)

The proof is the following: 

A bipartite graph can be divided into two sets of vertices which are disjoint and exhaustive such that there are no edges between the two sets.

Assign one colour each to the set. This is possible because there are no edges between vertices in the same set in a bipartite graph.

Hence, if we arrive at an edge which is between two different colours, we return false, else return true.
*/

class Solution {
    public boolean isBipartite(int[][] graph) {
        int len = graph.length;
        int[] colors = new int[len];
        
        for (int i = 0; i < len; i++) {
            if (colors[i] != 0) continue;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            colors[i] = 1;   // Blue: 1; Red: -1.
            
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                for (int next : graph[curr]) { // go over all nodes having an edge with node curr
                    if (colors[next] == 0) {          // If this node hasn't been colored;
                        colors[next] = -colors[curr];  // Color it with a different color;
                        queue.offer(next);
                    } else if (colors[next] != -colors[curr]) {   // If it is colored and its color is different, return false;
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
}
