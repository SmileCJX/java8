package pers.caijx.java8.tutorial;

/**
 * Created by Administrator on 2017/8/21/021.
 */
public class SomethingTest {

    public static void main(String[] args){
        Something something = new Something();
//        对一个对象的方法进行引用
        Converter<String,String> converter = something::startWith;
        String converted = converter.convert("Java");
        System.out.println(converted);
    }
}
