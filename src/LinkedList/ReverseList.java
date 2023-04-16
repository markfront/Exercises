package LinkedList;

public class ReverseList {
    class ListNode<T> {
        ListNode(T x) {
          value = x;
        }
        T value;
        ListNode<T> next;
    }

    public ListNode<Integer> reverseList(ListNode<Integer> head) {
        if (head==null || head.next==null) return head;

        ListNode<Integer> p1 = head;
        ListNode<Integer> p2 = head.next;

        head.next = null;

        while(p1!=null && p2!=null) {
            ListNode<Integer> t = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = t;
        }

        return p1;
    }
}
