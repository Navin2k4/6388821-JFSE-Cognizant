class MyThread extends Thread {
    String message;

    MyThread(String msg) {
        this.message = msg;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(message + " " + i);
        }
    }
}

public class ThreadDemo {
    public static void main(String[] args) {
        Thread t1 = new MyThread("Thread A");
        Thread t2 = new MyThread("Thread B");

        t1.start();
        t2.start();
    }
}
