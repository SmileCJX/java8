package pers.caijx.java8.tutorial;

import java.lang.annotation.Repeatable;

/**
 * Created by Administrator on 2017/8/21/021.
 */
//只要在前面加上注解名：@Repeatable，Java 8 允许我们对同一类型使用多重注解
@Repeatable(Hints.class)
@interface Hint {
    String value();
}
