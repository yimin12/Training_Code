package src.template.algorithm.two_pointers;

import src.template.algorithm.data_structure.list.ListNode;

/**
 *  Given a linked list, determine if it has a cycle in it.
 *  Typical fast, slow pointers question
 */

public class CycleLinkedList {

    /**
     * Use two pointers to traverse the linked list, if it is cycled, two pointers should meet somewhere
     * Time: O(n) Space: O(1)
     * @param head
     * @return
     */
    public boolean hasCycle (ListNode head) {
        if (head == null) return false;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    public ListNode detect_node_cycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                ListNode newSlow = head;
                while (newSlow != slow) {
                    slow = slow.next;
                    newSlow = newSlow.next;
                }
                return newSlow;
            }
        }
        return null;
    }
}
