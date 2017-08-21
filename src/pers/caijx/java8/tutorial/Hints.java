package pers.caijx.java8.tutorial;

/**
 * Created by Administrator on 2017/8/21/021.
 */
//定义一个包装注解，它包括了一个实际注解的数组
@interface Hints {
    Hint[] value();
}
