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
        
        Deque<Node> queue = new ArrayDeque<>();
        Map<Node, Node> map = new HashMap<>(); // keep nodes already cloned: node --> its cloned copy
        
        Node head = node;
        Node newHead = new Node(head.val);
        map.put(head, newHead);
        
        queue.offer(node);
        
        while(!queue.isEmpty()) {
            Node curr = queue.poll();
            Node copy = map.get(curr);
            
            for(Node nbr: curr.neighbors) {
                if (!map.containsKey(nbr)) {
                    // node nbr has not been cloned                    
                    Node copy_nbr = new Node(nbr.val);
                    
                    // nbr is curr's neighbor
                    // copy_nbr is copy's neighbor
                    // clone the edge: curr --> nbr, copy --> copy_nbr
                    copy.neighbors.add(copy_nbr);
                    
                    map.put(nbr, copy_nbr); // indicate nbr has been cloned
                    
                    queue.offer(nbr); // add nbr to queue to traverse its neighbors
                } else {
                    // clone the edge: curr-->nbr
                    // copy-->nbr's clone
                    copy.neighbors.add(map.get(nbr));
                }
            }
        }
        
        return newHead;
    }
}

