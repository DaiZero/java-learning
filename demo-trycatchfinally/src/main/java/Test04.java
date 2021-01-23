public class Test04 {
    public static void main(String[] args) {
        int results = new Test04().test();
        System.out.println("func results:" + results);
//        do try
//        results = 1
//        do catch
//        results = 2
//        do finally
//        results = 3
//        func results:3
    }

    public int test() {
        int results = 0;
        try {
            System.out.println("do try");
            results += 1;
            System.out.println("results = " + results);
            double d = 1 / 0;
            return results;
        } catch (Exception e) {
            System.out.println("do catch");
            results += 1;
            System.out.println("results = " + results);
            return results;
        } finally {
            System.out.println("do finally");
            results += 1;
            System.out.println("results = " + results);
            return results;
        }
    }
}