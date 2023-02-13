package org.example;

public class ReverseNodeInKGroups2 {
    public ListNode solution (ListNode head, int k){
        ListNode resultHead = null;
        ListNode prev = null;
        ListNode current = head;
        while (current != null){
            ListNode next = getNextAfterCount(current, k);

            ListNode lastNode = reverseList(current,0, k);

            if (lastNode == null){
                break;
            }
            if (resultHead == null){
                resultHead = lastNode;
            }
            if (prev != null){
                prev.next = lastNode;
            }
            current.next = next;
            prev = current;
            current = current.next;
        }
        return resultHead;
    }
    public ListNode reverseList (ListNode node , int currentCount, int maxCount){
        currentCount++;
        if (node == null){
            return null;
        }
        else if (currentCount == maxCount) {
            return node;
        }
        ListNode lastNode = reverseList(node.next, currentCount, maxCount);
        if (lastNode == null){
            return null;
        }
        node.next.next = node;
        return lastNode;
    }
    public ListNode getNextAfterCount(ListNode node, int count){
        while (count > 0){
            if (node == null){
                return null;
            }
            node = node.next;
            count--;
        }
        return node;
    }
}
