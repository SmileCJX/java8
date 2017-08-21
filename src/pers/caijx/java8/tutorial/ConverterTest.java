package pers.caijx.java8.tutorial;

/**
 * Created by Administrator on 2017/8/21/021.
 */
public class ConverterTest {
    public static void main(String[] args){
        method4();
    }

    private static void method1() {
        Converter<String,Integer> converter = (from) -> Integer.valueOf(from);
        Integer converted = converter.convert("123");
        System.out.println(converted);
    }

//    静态方法引用
    private static void method2() {
        Converter<String,Integer> converter = Integer::valueOf;
        Integer converted = converter.convert("321");
        System.out.println(converted);
    }

//    对于lambdab表达式外部的变量，其访问权限的粒度与匿名对象的方式非常类似。
//    你能够访问局部对应的外部区域的局部final变量，以及成员变量和静态变量。
    private static void method3() {
        final int num = 1;
        Converter<Integer,String> stringConverter = from -> String.valueOf(from + num);
        System.out.println( stringConverter.convert(2) );
    }

//    与匿名对象不同的是，变量num并不需要一定是final
    private static void method4() {
        int num = 1;
        Converter<Integer,String> stringConverter = from -> String.valueOf(from + num);
        System.out.println( stringConverter.convert(3) );
//        加num = 4代码会报错，num在编译的时候被隐式地当做final变量来处理
//        num = 4;
    }
}
