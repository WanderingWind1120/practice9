package org.example;

public class ReverseNodesInKGroups1 {
    public ListNode reverseKGroup(ListNode head, int k) { // tham số pass in vào là node đầu tiên của LinkedList, và int K
        ListNode current = head; // Gán Node current vs giá trị của tham số Node head pass in vào
        ListNode resultHead = null; // Declare node resulthead
        ListNode prev = null; // declare node previous

        while(current != null) {
            // Tỉm node đầu tiên sau k nodes
            ListNode next = getNextAfterCount(current, k);

            // reverse chuỗi k node hiện tại và tìm node cuối cùng của chuỗi
            ListNode lastNode = reverseList(current, 0, k);

            // Nếu chuỗi k đủ k nodes thì ngừng while loop
            if(lastNode == null) break;

            // Cập nhật head mới của LinkedList
            if(resultHead == null) { // Chỉ có trong vòng lặp while loop đầu tiên thì resultHead mới Null
                // => Ta gán resultHead với node cuối của chuỗi k node đầu tiên khi chưa reverse hay chính là
                // head mới của LinkedList
                resultHead = lastNode;
            }

            //  Nối các chuỗi k node sau khi đã reverse
            if(prev != null) { // Chỉ có từ vòng while loop thứ 2 thì prev ( previous ) mới không còn null
                prev.next = lastNode; // Tại dòng này khi chưa thực hiện các đoạn code dưới thì
                // prev đang là current của chuỗi k phía trước đã reverse hay chính là node cuối của chuỗi k node phía trước
            }

            // and connect next nodes with new sequence
            current.next = next; // current của chuỗi k hiện tại và là phần tử đứng cuối cùng của chuỗi k hiện tại, đang thực hiện nối
            // với phần tử đầu tiên của chuỗi k node tiếp theo ( chưa reverse )

            prev = current; // Gán prev = current khi đang chạy cho while loop hiện tại trước khi cập nhật thành node đầu
            // của chuỗi k Node tiếp theo, current hiện tại
            current = current.next; // Cập nhật current thành node đầu tiên của chuỗi k node tiếp theo để tiếp tục while loop
        }

        return resultHead;
    }

    public ListNode reverseList(ListNode node, int currentCount, int maxCount) {
        currentCount++; // mỗi lần recursive sẽ tăng currentcount thêm 1 đơn vị

        if(node == null) { // trước khi tính tới possible case để reverse nếu node chạm node của linked list trả về null
            return null;// if statement này chỉ cho kết quả khi ở chuỗi k node cuối cùng và k > số lượng node còn lại
        } else if(currentCount == maxCount) { // Khi currrent count chạm max count trả về node
            return node; // if statement này chỉ return kết quả khi đi đến node thứ k trong chuỗi cần reverse
        }

        ListNode lastNode = reverseList(node.next, currentCount, maxCount);
        // Gán last node bằng recursive của method và nếu method trả ra possible case thì sẽ bằng
        // chính tham số pass in vào thuộc class ListNode cụ thể ở đây là lastNode = node.next
        // Nếu đến case khi mà node pass in là node tiếp theo của node cuối hay chính là node cuối .next = null thì lastNode = Null
        // lastNode sẽ được gán giá trị là node trong chuỗi k node đầy đủ, và sẽ nhận giá trị là null khi trong chuỗi node cuối
        // và không có đủ k node
        // Khi chạy tới node cuối của chỗi k node thì nó sẽ dừng  ngay ở if statement return node và gán giá trị cho gán lastnode
        // statement ngay trước đó và không call thêm recursive hay statement reverse chuỗi node.next.next = node
        // Đặc thù của recursive là sẽ gọi đủ method chồng lên nhanh thành 1 stack rồi xử lí từ base case ( trong trường hợp này là
        // node cuối cho nên cái reverse chuỗi statement sẽ không bị kiểu vì gán node sau node.next ( chính là node.next.next = node )
        // thì node.next.next.next sẽ bị sai vì thực thi từ cuối lên sau khi đã call đủ method
        if(lastNode == null) {
            return null;
        }

        node.next.next = node;

        return lastNode;
    }

    // Tìm node đầu tiên của chuỗi k node tiếp theo sau chuối k node hiện tại
    // Với tham số pass in là node đầu của k node hiện tại và k
    private ListNode getNextAfterCount(ListNode node, int count) {
        while(count > 0) { // Khi count vẫn thỏa mãn lớn hơn 0 nhưng đã chạy tới node của của Linked List
            // thì trả về null do là node cuối thì node tiếp theo trong declare của nó sẽ là null
            if(node == null) {
                return null;
            }
            node = node.next;
            count--;
        }

        return node;
    }
}
