package pers.caijx.java8.tutorial;

import java.util.function.Supplier;

/**
 * Created by Administrator on 2017/8/21/021.
 */
//Supplier接口产生一个给定类型的结果。
// 与Function不同的是，Supplier没有输入参数。
public class SuppliersTest {

    public static void main(String[] args){

        Supplier<Person> personSupplier = Person::new;
        System.out.println(personSupplier.get());
    }
}
