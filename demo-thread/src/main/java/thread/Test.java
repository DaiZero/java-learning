package thread;

public class Test {
    public static void main(String[] args){
        System.out.println("======线程的六种状态======");
        System.out.println("线程-初始状态：" + Thread.State.NEW+",值为"+Thread.State.NEW.toString());
        System.out.println("线程-就绪状态：" + Thread.State.RUNNABLE);
        System.out.println("线程-阻塞状态：" + Thread.State.BLOCKED);
        System.out.println("线程-等待状态：" + Thread.State.WAITING);
        System.out.println("线程-限时等待状态：" + Thread.State.TIMED_WAITING);
        System.out.println("线程-终止状态：" + Thread.State.TERMINATED + "\n");
        System.out.println("======结束======");

        Thread t = new Thread();
        System.out.println("t线程-当前状态："+ t.getState());
        t.start();
//        Thread.sleep(1000);
//        try {
//            t.wait();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("t线程-当前状态："+ t.getState());
    }
}
