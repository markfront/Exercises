class ReorderList {

    //Class definition of ListNode
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public void ReorderList(ListNode head) {
        if (head == null || head.next == null) return;

        ListNode slow = head;
        ListNode fast = head;

        //use a fast and slow pointer to break the link to two parts.
        while (fast != null && fast.next != null && fast.next.next!= null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode second = slow.next;
        slow.next = null;// need to close first part

        // now should have two lists: head and fast

        // reverse order for second part
        second = ReverseOrder(second);

        ListNode p1 = head;
        ListNode p2 = second;

        //merge two lists here
        while (p2 != null) {
            ListNode temp1 = p1.next;
            ListNode temp2 = p2.next;

            p1.next = p2;
            p2.next = temp1;

            p1 = temp1;
            p2 = temp2;
        }
	}

    public ListNode ReverseOrder(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode p1 = head;
		ListNode p2 = head.next;

		while (p2 != null) {
			ListNode temp = p2.next;
			p2.next = p1;
			p1 = p2;
			p2 = temp;
		}

		// set head node's next
		head.next = null;

		return p1;
	}
}

