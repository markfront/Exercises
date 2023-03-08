// Binary trees are already defined with this interface:

class Tree<T> {
  Tree(T x) {
    value = x;
  }
  T value;
  Tree<T> left;
  Tree<T> right;
}

public class SymmetricTree_Recursion {
    boolean solution(Tree<Integer> t) {
    
        if (t==null) return true;
        
        return isSymmetric(t.left, t.right);
    }
    
    boolean isSymmetric(Tree<Integer> left, Tree<Integer> right) {
        
        if (left==null && right==null) return true;
        
        if ((left!=null && right==null) || 
            (left==null && right!=null)) return false;
        
        if (left.value != right.value) return false;
        
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }
}
