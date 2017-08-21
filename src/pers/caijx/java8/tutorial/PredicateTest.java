package pers.caijx.java8.tutorial;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Created by Administrator on 2017/8/21/021.
 */
//Predicate是一个布尔类型的函数，该函数只有一个输入参数。Predicate接口包含
//        了多种默认方法，用于处理复杂的逻辑动词（ and, or，negate）
public class PredicateTest {

    public static void main(String[] args){
        Predicate<String> predicate = s -> s.length() > 0;

        System.out.println(predicate.test("foo"));
        System.out.println(predicate.negate().test("foo"));  //neate 相反

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
    }
}
