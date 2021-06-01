package lesson2.Iterator;

public class Main {
    public static void main(String[] args) {
        RandomNumberIterator randomNumberIterator = new RandomNumberIterator(10);
        while (randomNumberIterator.hasNext()) {
            System.out.println(randomNumberIterator.next());
        }
    }
}
