package org.example;

public class ReverseNodeInKGroup {
    public ListNode solution (ListNode head, int k){
        ListNode resultHead = null;
        ListNode current = head;
        ListNode prev = null;

        while ( current != null){
            ListNode next = getNextAfterCount(current,k);

            ListNode lastnode = reverseList(current,0,k);

            if (lastnode == null){
                break;
            }
            if (resultHead == null){
                resultHead = lastnode;
            }

            if (prev != null){
                prev.next = lastnode;
            }

            current.next = next;
            prev = current;
            current = current.next;
        }
        return resultHead;
    }
    public ListNode reverseList (ListNode node, int currentCount, int maxCount){
        currentCount++;

        if (node == null){
            return null;
        }
        else if (currentCount == maxCount){
            return node;
        }

        ListNode lastNode = reverseList(node.next, currentCount, maxCount);

        if (lastNode == null){
            return null;
        }

        node.next.next = node;

        return lastNode;
    }
    public ListNode getNextAfterCount (ListNode node, int count){
        while (count > 0){
            if (node == null){
                return null;
            }
            node = node.next;
            count --;
        }
        return node;
    }
}
