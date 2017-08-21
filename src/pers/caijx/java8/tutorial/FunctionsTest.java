package pers.caijx.java8.tutorial;

import java.util.function.Function;

/**
 * Created by Administrator on 2017/8/21/021.
 */
//Function接口接收一个参数
//        起（ compse, andThen）
public class FunctionsTest {

    public static void main(String[] args){

        Function<String,Integer> toInteger = Integer::valueOf;
        Function<String,String> backToString = toInteger.andThen(String::valueOf);

        System.out.println(backToString.apply("123"));
    }
}
