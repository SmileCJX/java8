package pers.caijx.java8.tutorial;

/**
 * Created by Administrator on 2017/8/21/021.
 */
public class Lambda4 {

    static int outerStaticNum;

    int outerNum;

//    在lambda表达式的内部能获取到对成员变量或静态变量的读写权
    void testScopers(){
       Converter<Integer,String> stringConverter1 = from -> {
           outerNum = 23;
           return String.valueOf(from);
       };

        Converter<Integer,String> stringConverter2 = from -> {
          outerStaticNum = 72;
            return String.valueOf(from);
        };
    }

}
