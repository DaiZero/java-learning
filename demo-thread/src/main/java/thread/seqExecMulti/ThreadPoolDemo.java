package thread.seqExecMulti;

import java.util.concurrent.*;

/**
 * @author dzero
 */
public class ThreadPoolDemo {
  static ExecutorService singleThreadPool =new ThreadPoolExecutor(1,1,0,
          TimeUnit.SECONDS,new LinkedBlockingQueue<>());

    static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> System.out.println("001"));
        Thread thread2 = new Thread(() -> System.out.println("002"));
        Thread thread3 = new Thread(() -> System.out.println("003"));
        singleThreadPool.submit(thread1);
        singleThreadPool.submit(thread2);
        singleThreadPool.submit(thread3);
        singleThreadPool.shutdown();
    }


}
