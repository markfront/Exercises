/*
 * 
Note: Try to solve this task in O(n) time using O(1) additional space, where n is the number of elements in l, since this is what you'll be asked to do during an interview.

Given a singly linked list of integers, determine whether or not it's a palindrome.

Note: in examples below and tests preview linked lists are presented as arrays just for simplicity of visualization: in real data you will be given a head node l of the linked list

Example

    For l = [0, 1, 0], the output should be
    solution(l) = true;

    For l = [1, 2, 2, 3], the output should be
    solution(l) = false.

Input/Output

    [execution time limit] 3 seconds (java)

    [input] linkedlist.integer l

    A singly linked list of integers.

    Guaranteed constraints:
    0 ≤ list size ≤ 5 · 105,
    -109 ≤ element value ≤ 109.

    [output] boolean

    Return true if l is a palindrome, otherwise return false.
 */

package LinkedList;

public class PalindromeList {
    // Singly-linked lists are already defined with this interface:
    class ListNode<T> {
      ListNode(T x) {
        value = x;
      }
      T value;
      ListNode<T> next;
    }

    boolean solution(ListNode<Integer> l) {
        if (l==null || l.next==null) return true;
        
        ListNode<Integer> slow = l; // move 1 node per step
        ListNode<Integer> fast = l; // move 2 nodes per step
            
        while(fast!=null && fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
        }
            
        ListNode<Integer> right = reverseListInPlace(slow);
        
        ListNode<Integer> left = l;
        while(left!=null && right!=null) {
            if ((long)left.value != (long)right.value) return false;
            left = left.next;
            right = right.next;
        }
        
        return true;
    }

    ListNode<Integer> reverseListInPlace(ListNode<Integer> head) {
        if (head==null || head.next==null) return head;
        
        ListNode<Integer> curr = head;
        ListNode<Integer> prev=null, next=null;
        while(curr!=null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
