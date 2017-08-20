package pers.caijx.java8.tutorial;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/8/21/021.
 */
public class LambdaTest {

    public static void main(String args[]){
        List<String> names = Arrays.asList("peter","anna","mike","xenia");

//        java8之前的方法
//        Collections.sort(names, new Comparator<String>() {
//            @Override
//            public int compare(String a, String b) {
//                return b.compareTo(a);
//            }
//        });

//        Lambda表达式
//        Collections.sort(names,(String a,String b) ->{
//            return b.compareTo(a);
//        });

//        简短的Lambda表达式
//        Collections.sort(names,(String a,String b) -> b.compareTo(a));

//        简短的Lambda表达式
        Collections.sort(names,(a,b) -> b.compareTo(a));
        for (int i = 0; i<names.size(); i++){
            System.out.println(names.get(i));
        }
    }
}
