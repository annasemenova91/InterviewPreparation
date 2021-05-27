package lesson2.LinkedList;

public class Main {
    public static void main(String[] args) {
        TwoSideLinkedList<Integer> linkedList = new TwoSideLinkedListImpl<>();
        linkedList.insertFirst(1);
        linkedList.insertFirst(2);
        linkedList.insertFirst(3);
        linkedList.insertFirst(4);
        linkedList.insertLast(5);
        linkedList.display();
        System.out.println("Find 2: " + linkedList.contains(2));
        System.out.println("Find 1: " + linkedList.contains(1));
        System.out.println("Find 4: " + linkedList.contains(4));
        System.out.println("Find 0: " + linkedList.contains(0));
        linkedList.removeFirst();
        linkedList.remove(2);
        linkedList.display();
    }
}
