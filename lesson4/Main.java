package lesson4;

public class Main {

    static Object o = new Object();
    private static String a = "A";
    private static String b = "B";
    private static String c = "C";


    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            letterOutput(a, b);
        });

        Thread thread2 = new Thread(() -> {
            letterOutput(b, c);
        });

        Thread thread3 = new Thread(() -> {
            letterOutput(c, a);
        });

        thread1.start();
        thread2.start();
        thread3.start();

    }

    private static void letterOutput(String letter, String nextLetter) {
        for (int i = 0; i < 5; i++) {
            synchronized (o) {
                try {
                    while (a != letter)
                        o.wait();
                    System.out.print(letter);
                    a = nextLetter;
                    o.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}


