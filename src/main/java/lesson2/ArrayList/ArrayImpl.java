package lesson2.ArrayList;

import java.util.Arrays;

public class ArrayImpl<E extends Comparable<? super E>> implements Array<E> {
    protected static final int DEFAULT_CAPACITY = 8;
    protected E[] data;
    protected int size;

    public ArrayImpl() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayImpl(int initialCapacity) {
        this.data = (E[]) new Comparable[initialCapacity];
    }

    @Override
    public void add(E value) {
        checkAndGrow();
        data[size++] = value;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return data[index];
    }

    @Override
    public boolean remove(E value) {
        return remove(indexOf(value)) != null;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E removedValue = data[index];
        if ((size - index - 1) >= 0) {
            System.arraycopy(data, index + 1, data, index, size - index - 1);
        }
        data[--size] = null;
        return removedValue;
    }

    @Override
    public boolean contains(E value) {
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(E value) {
        for (int i = 0; i < size; i++) {
            if (value.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void display() {
        System.out.println(this);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            sb.append(data[i]);
            sb.append(", ");
        }
        if (size > 0) {
            sb.append(data[size - 1]);
        }
        sb.append("]");
        return sb.toString();
    }

    protected void checkAndGrow() {
        if (data.length == size) {
            data = Arrays.copyOf(data, calculateNewLength());
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            String errMsg = String.format("Incorrect 'index' value: %d; max value is %d", index, size - 1);
            throw new IndexOutOfBoundsException(errMsg);
        }
    }

    private int calculateNewLength() {
        return size == 0 ? 1 : size * 2;
    }

}
