package pers.caijx.java8.tutorial;

import java.util.function.Consumer;

/**
 * Created by Administrator on 2017/8/21/021.
 */
//Consumer代表了在一个输入参数上需要进行的操作。
public class ConsumersTest {

    public static void main(String[] args){
        Consumer<Person> greeter = p -> System.out.println("Hello," + p.firstName);
        greeter.accept(new Person("Luke","Skywalker"));
    }
}
