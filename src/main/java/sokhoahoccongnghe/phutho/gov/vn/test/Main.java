package sokhoahoccongnghe.phutho.gov.vn.test;

import java.util.Optional;

public class Main {
    public static Object get(Object j){
        System.out.println(j.getClass().getName());
        return j;
    }

    public static void main(String[] args) {
        System.out.println(get(true));


    }
}
