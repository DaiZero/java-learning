package thread.seqExecMulti;

/**
 * 在主线程中通过join()方法指定顺序
 *
 * @author dzero
 */
public class ThreadMainJoinDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> System.out.println("001"));
        Thread thread2 = new Thread(() -> System.out.println("002"));
        Thread thread3 = new Thread(() -> System.out.println("003"));

        thread1.start();
        thread1.join();

        thread2.start();
        thread2.join();

        thread3.start();

    }
}
