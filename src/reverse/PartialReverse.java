package reverse;

public class PartialReverse {

    public static Node Reverse(Node head, int left, int right) {

        if (head == null || left == right) {
            return head;
        }

        Node dummy = new Node(0);
        dummy.next = head;
        Node prev = dummy;

        // Перемещаемся к узлу перед left
        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

        Node start = prev.next;
        Node then = start.next;

        // Разворачиваем подсписок
        for (int i = left; i < right; i++) {
            start.next = then.next;
            then.next = prev.next;
            prev.next = then;
            then = start.next;
        }

        return dummy.next;
    }
}
