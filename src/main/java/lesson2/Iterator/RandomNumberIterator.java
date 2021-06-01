package lesson2.Iterator;

import java.util.Iterator;

public class RandomNumberIterator implements Iterator {
    private static final int MIN_NUMBER = 0;
    private static final int MAX_NUMBER = 10;
    private int maxElements;
    private int count;

    public RandomNumberIterator(int maxElements) {
        this.maxElements = maxElements;
    }

    @Override
    public boolean hasNext() {
        return count < maxElements;
    }

    @Override
    public Object next() {
        count++;
        return MIN_NUMBER + (int) (Math.random() * MAX_NUMBER);
    }

    public static int getMinNumber() {
        return MIN_NUMBER;
    }

    public static int getMaxNumber() {
        return MAX_NUMBER;
    }
}
