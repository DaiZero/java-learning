package thread.seqExecMulti;

/**
 * 顺序执行线程
 * 实现：子线程内部调用join
 * @author dzero
 */
public class ThreadJoinDemo {
    public static void main(String[] args) {
        Thread thread1=new Thread(() -> System.out.println("001"));

        Thread thread2=new Thread(() -> {
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("002");
        });

        Thread thread3=new Thread(() -> {
            try {
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("003");
        });

        // start的顺序就算乱了，线程也是按照同样的顺序执行
        thread3.start();
        thread1.start();
        thread2.start();
    }
}
