public class Test02 {
    public static void main(String[] args) {
        int results = new Test02().test();
        System.out.println("func results:" + results);
//        in try.....
//        results = 1
//        in catch......
//        results = 2
//        in finally
//        results = 3
//        func results:2
    }

    public int test() {
        int results = 0;
        try {
            System.out.println("in try.....");
            results += 1;
            System.out.println("results = " + results);
            double d = 1 / 0;
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