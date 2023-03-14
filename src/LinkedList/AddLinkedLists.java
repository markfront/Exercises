/*
You're given 2 huge integers represented by linked lists. Each linked list element is a number from 0 to 9999 that represents a number with exactly 4 digits. The represented number might have leading zeros. Your task is to add up these huge integers and return the result in the same format.

Example

    For a = [9876, 5432, 1999] and b = [1, 8001], the output should be
    solution(a, b) = [9876, 5434, 0].

    Explanation: 987654321999 + 18001 = 987654340000.

    For a = [123, 4, 5] and b = [100, 100, 100], the output should be
    solution(a, b) = [223, 104, 105].

    Explanation: 12300040005 + 10001000100 = 22301040105.

Input/Output

    [execution time limit] 3 seconds (java)

    [input] linkedlist.integer a

    The first number, without its leading zeros.

    Guaranteed constraints:
    0 ≤ a size ≤ 104,
    0 ≤ element value ≤ 9999.

    [input] linkedlist.integer b

    The second number, without its leading zeros.

    Guaranteed constraints:
    0 ≤ b size ≤ 104,
    0 ≤ element value ≤ 9999.

    [output] linkedlist.integer

    The result of adding a and b together, returned without leading zeros in the same format.
 */

package LinkedList;

public class AddLinkedLists {
    // Singly-linked lists are already defined with this interface:
    class ListNode<T> {
    ListNode(T x) {
        value = x;
    }
    T value;
    ListNode<T> next;
    }

    ListNode<Integer> solution(ListNode<Integer> a, ListNode<Integer> b) {
        if (a==null) return a;
        if (b==null) return b;
        
        a = reverseInPlace(a);
        b = reverseInPlace(b);
        
        int[] carryover = new int[] {0};
        ListNode<Integer> curr = null, prev=null;
        do {
            curr = addTwoNodes(a, b, carryover); 
            curr.next = prev;
            prev = curr;
            if (a!=null) a = a.next;
            if (b!=null) b = b.next;
        } while(a!=null || b!=null);
        
        ListNode<Integer> newHead = curr;
        if (carryover[0] > 0) {
            newHead = new ListNode<Integer>(carryover[0]);
            newHead.next = curr;
        }
            
        return newHead;
    }

    ListNode<Integer> reverseInPlace(ListNode<Integer> head) {
        if (head==null || head.next==null) return head;
        
        ListNode<Integer> curr=head, prev=null, next=null;
        while(curr!=null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    ListNode<Integer> addTwoNodes(ListNode<Integer> a, ListNode<Integer> b, int[] carryover) {
        int sum = 0;
        if (a!=null) sum += a.value;
        if (b!=null) sum += b.value;
        sum += carryover[0];
        carryover[0] = sum / 10000;
        return new ListNode<>(sum % 10000);
    }
}
