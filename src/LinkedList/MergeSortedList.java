package LinkedList;

public class MergeSortedList {
    // Singly-linked lists are already defined with this interface:
    class ListNode<T> {
      ListNode(T x) {
        value = x;
      }
      T value;
      ListNode<T> next;
    }
    
    ListNode<Integer> solution(ListNode<Integer> l1, ListNode<Integer> l2) {
        ListNode<Integer> dummyHead = new ListNode<>(0);
        
        ListNode<Integer> curr = dummyHead;
        while(l1!=null && l2!=null) {
            if (l1.value <= l2.value) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        
        if (l1!=null) {
            curr.next = l1;
        }
        
        if (l2!=null) {
            curr.next = l2;
        }
        
        return dummyHead.next;
    }
}
