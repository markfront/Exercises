/*

*/

using System.Collections.Generic;

public class MergeKSortedLists {
    public class ListNode {
        public int val;
        public ListNode next;
        public ListNode(int val=0, ListNode next=null) {
            this.val = val;
            this.next = next;
        }
    }

    // use a PriorityQueue
    public ListNode MergeKLists(ListNode[] lists) {
        if (lists.Length == 0) {
            return null;
        }
        PriorityQueue<ListNode, int> pq = new PriorityQueue<ListNode, int>(); // use node value as priority.
        foreach (ListNode node in lists) {
            if (node != null) {
                pq.Enqueue(node, node.val);
            }
        }
        ListNode dummy = new ListNode(0); // use a dummy head
        ListNode tail = dummy;
        while (pq.Count > 0) {
            ListNode node = pq.Dequeue();
            tail.next = node;
            tail = tail.next;
            if (node.next != null) {
                pq.Enqueue(node.next, node.next.val);
            }
        }
        return dummy.next;
    }

    // use recursion
    public ListNode MergeKLists2(ListNode[] lists) {
        if (lists.Length == 0) {
            return null;
        }
        return MergeLists(lists, 0, lists.Length - 1);
    }

    // Merge the lists from start index to end index recursively
    private ListNode MergeLists(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        int mid = start + (end - start) / 2;
        ListNode left = MergeLists(lists, start, mid);
        ListNode right = MergeLists(lists, mid + 1, end);
        return Merge(left, right);
    }

    // Merge two sorted linked lists
    private ListNode Merge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = Merge(l1.next, l2);
            return l1;
        }
        else {
            l2.next = Merge(l1, l2.next);
            return l2;
        }
    }
}
