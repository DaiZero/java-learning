package thread;

/**
 * @author dzero
 */
public class ThreadBlockedStateTest {
    public static void main(String[] args){
        Thread threadA = new Thread(() -> method01(), "A-Thread");
        Thread threadB = new Thread(() -> method01(), "B-Thread");
        threadA.start();
        threadB.start();
        System.out.println("线程A的状态为："+threadA.getState());
        System.out.println("线程B的状态为："+ threadB.getState());
    }

    /**
     * 停顿10毫秒、模拟方法执行耗时
     */
    public static synchronized void method01() {
        System.out.println(Thread.currentThread().getName()+":开始执行主线程的方法");
        try {
            Thread.sleep(10);
            System.out.println(Thread.currentThread().getName()+"的状态为："+Thread.currentThread().getState());
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+":主线程的方法执行完毕");
    }
}
