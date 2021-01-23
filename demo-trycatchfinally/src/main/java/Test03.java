public class Test03 {
    public static void main(String[] args) {
        int results = new Test03().test();
        System.out.println("func results:" + results);
//        in try.....
//        results = 1
//        in finally
//        results = 2
//        func results:2

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
            return results;
        }
    }
}