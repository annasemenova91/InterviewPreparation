package lesson2.ArrayList;

public class Main {
    static final int NUMBER_OF_ELEMENTS = 10;
    static final int MIN_NUMBER = 0;
    static final int MAX_NUMBER = 10;

    public static void main(String[] args) {

        ArrayImpl array = new ArrayImpl(NUMBER_OF_ELEMENTS);
        for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            int randomNumber = MIN_NUMBER + (int) (Math.random() * MAX_NUMBER);
            array.add(randomNumber);
        }
        array.display();
    }
}
