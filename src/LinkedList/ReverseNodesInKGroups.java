/*
 * 
Note: Your solution should have O(n) time complexity, where n is the number of elements in l, and O(1) additional space complexity, since this is what you would be asked to accomplish in an interview.

Given a linked list l, reverse its nodes k at a time and return the modified list. k is a positive integer that is less than or equal to the length of l. If the number of nodes in the linked list is not a multiple of k, then the nodes that are left out at the end should remain as-is.

You may not alter the values in the nodes - only the nodes themselves can be changed.

Example

    For l = [1, 2, 3, 4, 5] and k = 2, the output should be
    solution(l, k) = [2, 1, 4, 3, 5];
    For l = [1, 2, 3, 4, 5] and k = 1, the output should be
    solution(l, k) = [1, 2, 3, 4, 5];
    For l = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11] and k = 3, the output should be
    solution(l, k) = [3, 2, 1, 6, 5, 4, 9, 8, 7, 10, 11].

Input/Output

    [execution time limit] 3 seconds (java)

    [input] linkedlist.integer l

    A singly linked list of integers.

    Guaranteed constraints:
    1 ≤ list size ≤ 104,
    -109 ≤ element value ≤ 109.

    [input] integer k

    The size of the groups of nodes that need to be reversed.

    Guaranteed constraints:
    1 ≤ k ≤ l size.

    [output] linkedlist.integer

    The initial list, with reversed groups of k elements.

 */
package LinkedList;

public class ReverseNodesInKGroups {
    // Definition for singly-linked list:
    class ListNode<T> {
      ListNode(T x) {
        value = x;
      }
      T value;
      ListNode<T> next;
    }
    
    // Iterative approach
    ListNode<Integer> iterativeSolution(ListNode<Integer> head, int k) {
        ListNode<Integer> dummy = new ListNode<>(-1);
        dummy.next = head;
        ListNode<Integer> begin = dummy;
        int count = 0;
        while(head != null){
            count++;
            if(count % k == 0){
                begin = reverseNode(begin, head.next);
                head = begin.next;
            }else{
                head = head.next;
            }
        }
        return dummy.next;
    }

    private ListNode<Integer> reverseNode(ListNode<Integer> begin, ListNode<Integer> end){
        ListNode<Integer> prev = begin;
        ListNode<Integer> curr = begin.next;
        ListNode<Integer> first = curr;
        while(curr != end){
            ListNode<Integer> next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        begin.next = prev;
        first.next = curr;
        return first;
    }

    // Recursive approach
    ListNode<Integer> recurriveSolution(ListNode<Integer> l, int k) {
        ListNode<Integer> p = l;
        int count = 0;
        while(p!=null && count!=k) {
            p=p.next;
            count++;
        }

        if (count==k) {
            p=recurriveSolution(p,k);
            while(count > 0) {
                ListNode<Integer> temp = l.next;
                l.next = p;
                p = l;
                l = temp;
                count--;
            }
            l = p;
        }
        return l;
    }
    
}
