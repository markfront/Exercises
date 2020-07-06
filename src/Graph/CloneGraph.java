/*
https://leetcode.com/problems/clone-graph/

// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        if (node==null) return null;
        
        Deque<Node> queue = new ArrayDeque<>(); // keep nodes already cloned but yet to fill all neighbors
        Map<Node, Node> map = new HashMap<>(); // keep mapping from a node to its cloned copy!
        
        Node head = node;
        Node newHead = new Node(head.val);
        map.put(head, newHead);
        queue.offer(node);
        
        // Breadth-First traverse, clone the neighbor nodes on the go
        
        while(!queue.isEmpty()) {
            Node curr = queue.poll();
            Node copy = map.get(curr);
            List<Node> neighbors = curr.neighbors;
            for(Node neighbor: neighbors) {
                if (!map.containsKey(neighbor)) { 
                    // this neighbor not cloned yet, now clone it
                    Node copyNeighbor = new Node(neighbor.val);
                    copy.neighbors.add(copyNeighbor);
                    map.put(neighbor, copyNeighbor);
                    // add to queue to fill the cloned node's neighbors
                    queue.offer(neighbor);
                } else {
                    copy.neighbors.add(map.get(neighbor)); // neighbor already cloned
                }
            }
        }
        
        return newHead;
    }
}
