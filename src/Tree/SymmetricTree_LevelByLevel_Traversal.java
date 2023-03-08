
/*
 * O(N) solution -- level by level traversal
 * 
 */
//Binary trees are already defined with this interface:

import java.util.*;

class Tree<T> {
  Tree(T x) {
    value = x;
  }
  T value;
  Tree<T> left;
  Tree<T> right;
}

public class SymmetricTree_LevelByLevel_Traversal {
    class Node<T> extends Tree<T> {
        int level;
        Node(Tree<T> t, int l) {
            super(t.value);
            this.left = t.left;
            this.right = t.right;
            level = l;
        }
    }
    
    boolean solution(Tree<Integer> t) {
        if (t==null) return false;
        
        Deque<Node<Integer>> queue = new ArrayDeque<>();
        queue.offer(new Node<Integer>(t, 0));
        
        List<Integer> valuesInLevel = new ArrayList<>();
        valuesInLevel.add(t.value);
        
        int prev_level = -1;
        while(!queue.isEmpty()) {
            Node<Integer> node = queue.poll();
            
            if (node.level != prev_level) {
                if (!checkSymmetric(valuesInLevel)) {
                    return false;
                } else {
                    valuesInLevel = new ArrayList<>();
                }
            } else {
                valuesInLevel.add(node.value);
            }
            
            if (t.left!=null)  { queue.offer(new Node<Integer>(t.left, node.level+1)); }
            if (t.right!=null) { queue.offer(new Node<Integer>(t.right, node.level+1)); }
            
            prev_level = node.level;
        }
        
        return checkSymmetric(valuesInLevel);
    }
    
    boolean checkSymmetric(List<Integer> values) {
        int n = values.size();
        for(int i=0, j=n-1; i<j; i++, j--) {
            if (values.get(i) != values.get(j)) return false;
        }
        return true;
    }
    
}
