package pers.caijx.java8.tutorial;

/**
 * Created by Administrator on 2017/8/21/021.
 */
//函数式接口必须要有且仅有一个抽象方法声明
//每个与之对应的lambda表达式必须要与抽象方法的声明相匹配。
//由于默认方法不是抽象的，因此你可以在你的函数式接口里任意添加默认方法
@FunctionalInterface
interface Converter<F,T> {

    T convert(F from);
}
