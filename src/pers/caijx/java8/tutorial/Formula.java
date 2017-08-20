package pers.caijx.java8.tutorial;

/**
 * Created by Administrator on 2017/8/21/021.
 */
public interface Formula {

    double calculate(int a);

//    Java 8 允许我们使用default关键字，为接口声明添加非抽象的方法实现。这个特性
//    又被称为扩展方法。
    default double sqrt(int a){
        return Math.sqrt(a);
    }
}
