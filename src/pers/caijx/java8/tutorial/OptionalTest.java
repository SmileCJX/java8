package pers.caijx.java8.tutorial;

import java.util.Optional;

/**
 * Created by Administrator on 2017/8/21/021.
 */
//Optional是一个简单的值容器，这个值可以是null，也可以是non-null。考虑到一个
//        方法可能会返回一个non-null的值，也可能返回一个空值。为了不直接返回null，我
//        们在Java 8中就返回一个Optional.
public class OptionalTest {

    public static void main(String[] args){

        Optional<String> optional = Optional.of("bam");
        System.out.println( optional.isPresent() );
        System.out.println( optional.get() );
        System.out.println( optional.orElse("falback") );
        optional.ifPresent( (s) -> System.out.println(s.charAt(0)) );

    }
}
