package lesson3.Counter;

public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();

        Thread firstThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) counter.inc();
            }
        });
        Thread secondThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) counter.dec();
            }
        });

        firstThread.start();
        secondThread.start();

        try {
            firstThread.join();
            secondThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter.getCounter());

    }
}
