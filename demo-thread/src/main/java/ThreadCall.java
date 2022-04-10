import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable接口，call方法返回值，通过FutureTask类
 */
public class ThreadCall implements Callable {
    @Override
    public Object call() throws Exception {
        for (int i = 0; i < 5 ; i++) {
            //线程随机时长休眠
//            Thread.sleep(Math.round(Math.random()*1000));
            System.out.println(Thread.currentThread().getName()+" "+i);
        }
        // 返回值
        return Thread.currentThread().getName()+" is completed.";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1. 创建实现类实例
        ThreadCall callableTest1 = new ThreadCall();
        ThreadCall callableTest2 = new ThreadCall();

        // 2. FutureTask类使用Callable实现类的实例通过FutureTask的单参数构造器创建FutureTask的实例；
        FutureTask<String> futureTask1 = new FutureTask<String>(callableTest1);
        FutureTask<String> futureTask2 = new FutureTask<String>(callableTest2);

        // 3. 通过Thread类的构造器创建Thread类实例，并执行start方法，启动线程
        new Thread(futureTask1,"Thread-1: ").start();
        new Thread(futureTask2,"Thread-2: ").start();

        // 4. 通过FutureTask实例的get()方法阻塞拿到Callable实现类的call方法的返回值
        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());

        StringBuilder sb=new StringBuilder();
        sb.append(1);
        sb.append(2);
        System.out.println(sb.toString());
        Set<Integer> sb2=new HashSet<>();
        sb2.add(1);
        sb2.add(2);
        System.out.println(sb2.toString());



    }
}
