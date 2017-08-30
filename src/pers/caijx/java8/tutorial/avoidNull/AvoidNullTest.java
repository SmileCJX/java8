package pers.caijx.java8.tutorial.avoidNull;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Created by Administrator on 2017/8/30/030.
 */
public class AvoidNullTest {

    public static void main(String[] args){
        Outer outer = new Outer();
        //老的解决方案
        if(outer != null && outer.nested != null && outer.nested.inner != null){
            System.out.println(outer.nested.inner.foo);
        }

//        我们可以通过利用 Java 8 的 Optional 类型来摆脱所有这些 null 检查。map 方法接
//        收一个 Function 类型的 lambda 表达式，并自动将每个 function 的结果包装成一个
//        Optional 对象。这使我们能够在一行中进行多个 map 操作。Null 检查是在底层自
//        动处理的。
        Optional.of(new Outer())
                .map(Outer::getNested)
                .map(Nested::getInner)
                .map(Inner::getFoo)
                .ifPresent(System.out::println);


    }

    public static <T> Optional<T> resolve(Supplier<T> resolver) {
        try {
            T result = resolver.get();
            return Optional.ofNullable(result);
        } catch (NullPointerException e) {
            return Optional.empty();
        }
    }
}
