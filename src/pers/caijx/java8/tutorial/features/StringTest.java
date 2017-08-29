package pers.caijx.java8.tutorial.features;

import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2017/8/29/029.
 */
public class StringTest {

    public static void main(String[] args){
//        两个新的方法可在字符串类上使用： join 和 chars 。第一个方法使用指定的分
//        隔符，将任何数量的字符串连接为一个字符串
        String result = String.join(":","foobar","foo","bar");
        System.out.println(result);

        String charSort = result
                .chars()
                .distinct()
                .mapToObj(c -> String.valueOf((char)c))
                .sorted()
                .collect(Collectors.joining());

        System.out.println(charSort);

        String pattStr =
        Pattern.compile(":")
                .splitAsStream("footbar:foo:bar")
                .filter(s -> s.contains("bar"))
                .sorted()
                .collect(Collectors.joining(":"));
        System.out.println(pattStr);

//        式串接受任何以 @gmail.com 结尾的字符串，并且之后用作Java8
//        的 Predicate 来过滤电子邮件地址流
        Pattern pattern = Pattern.compile(".*@gmail\\.com");
        long count = Stream.of("bob@gmail.com", "alice@hotmail.com")
                .filter(pattern.asPredicate())
                .count();
        System.out.println(count);
    }
}
