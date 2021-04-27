package threadlocal;

import java.util.ArrayList;
import java.util.List;

public class ThreadLocalTest {
    private List<String> messages = new ArrayList<>();

    public static final ThreadLocal<ThreadLocalTest> holder = ThreadLocal.withInitial(ThreadLocalTest::new);

    public static void add(String message) {
        holder.get().messages.add(message);
    }

    public static List<String> clear() {
        System.out.println("before size: " + holder.get().messages.size());
        List<String> messages = holder.get().messages;
        holder.remove();

        System.out.println("size: " + holder.get().messages.size());
        return messages;
    }

    public static void main(String[] args) {
        ThreadLocalTest.add("一枝花算不算浪漫");
        System.out.println(holder.get().messages);

        new Thread(() -> System.out.println("子线程获取父类`ThreadLocal`数据：" + holder.get().messages)).start();
//        threadlocal.ThreadLocalTest.clear();
    }
}