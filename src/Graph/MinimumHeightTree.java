// https://leetcode.com/problems/minimum-height-trees/

/*
For an undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example 1 :

Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3 

Output: [1]

Example 2 :

Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5 

Output: [3, 4]

Note:

    According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
    The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
*/

class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 0) return new ArrayList<>();
        else if (n == 1) {
            List<Integer> ret = new ArrayList<>();
            ret.add(0);
            return ret;
        }
        // represent the graph using list
        // each vertex has a list of neighbor vertices
        List<Integer>[] lists = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            lists[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            int v1 = edges[i][0];
            int v2 = edges[i][1];
            lists[v1].add(v2);
            lists[v2].add(v1);
        }
        // if a vertex has only 1 neighbor vertex, then it's leaf
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (lists[i].size() == 1) {
                leaves.add(i);
            }
        }
        
        // traverse from the leaves level by level toward the center
        int count = n; // count the levels
        while (count > 2) { // the vertices remaining in the last 2 level are the roots!
            int size = leaves.size();
            count -= size;
            List<Integer> newLeaves = new ArrayList<>();
            // for each leaf, remove it from it's neighbor's list
            for (int i = 0; i < size; i++) {
                int leaf = leaves.get(i);
                // go over the leaf's neighbor
                for (int j = 0; j < lists[leaf].size(); j++) {
                    int toRemove = lists[leaf].get(j);
                    lists[toRemove].remove(Integer.valueOf(leaf));
                    
                    // now the neighbor might become a new leaf after removing an edge
                    if (lists[toRemove].size() == 1) {
                        newLeaves.add(toRemove);
                    }
                }
            }
            leaves = newLeaves;
        }
        return leaves;
    }
}
