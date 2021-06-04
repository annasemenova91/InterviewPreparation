package lesson3.PingPong;

public class Main {
    public static void main(String[] args) {

        Thread firstPingPongThread = new Thread(new Runnable() {
            @Override
            public void run() {
                play(PingPongTurn.PING);
            }
        });

        Thread secondPingPongThread = new Thread(new Runnable() {
            @Override
            public void run() {
                play(PingPongTurn.PONG);
            }
        });

        while (true) {
            firstPingPongThread.run();
            secondPingPongThread.run();
        }
    }

    public static synchronized void play(PingPongTurn pingPongTurn) {
        System.out.println(pingPongTurn);
    }
}
