/**
 * @author dzero
 */
public class Test01 {
    public static void main(String[] args) {
        int results = new Test01().test();
        System.out.println("func results:" + results);
//        运行结果
//        in try.....
//        results = 1
//        in finally
//        results = 2
//        func results:1
    }

    public int test() {
        int results = 0;
        try {
            System.out.println("in try.....");
            results += 1;
            System.out.println("results = " + results);
            return results;
        } catch (Exception e) {
            System.out.println("in catch......");
            results += 1;
            System.out.println("results = " + results);
            return results;
        } finally {
            System.out.println("in finally");
            results += 1;
            System.out.println("results = " + results);
        }
    }
}