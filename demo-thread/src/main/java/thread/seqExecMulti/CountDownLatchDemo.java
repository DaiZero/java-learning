package thread.seqExecMulti;

import java.util.concurrent.CountDownLatch;

/**
 * @author dzero
 */
public class CountDownLatchDemo {
    private static CountDownLatch latch1 = new CountDownLatch(1);
    private static CountDownLatch latch2 = new CountDownLatch(1);

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            System.out.println("001");
            latch1.countDown();
        });

        Thread thread2 = new Thread(() -> {
            try {
                latch1.await();
                System.out.println("002");
                latch2.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread3 = new Thread(() -> {
            try {
                latch2.await();
                System.out.println("003");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread3.start();
        thread1.start();
        thread2.start();
    }
}
