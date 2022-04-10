import java.util.HashMap;

public class main {
    public static void main(String[] args) {
        HashMap<Integer, String> Sites = new HashMap<>();
        Sites.put(1,"eeeee");
        System.out.println(reval());
        System.out.println(reval2());
    }


    private static int reval(){
        try{
            return 1;
        } finally {
            return 2;
        }
    }

    private static int reval2(){
        try{
            return 1;
        } finally {
            System.out.println("111111111111");
        }
    }
}
