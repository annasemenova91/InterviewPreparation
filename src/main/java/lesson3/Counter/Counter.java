package lesson3.Counter;

import java.util.concurrent.locks.ReentrantLock;

public class Counter extends ReentrantLock {
    private int counter = 0;

    public int getCounter() {
        return counter;
    }

    public void inc() {
        this.lock();
        counter++;
        this.unlock();
    }

    public void dec(){
        this.lock();
        counter--;
        this.unlock();
    }
}
