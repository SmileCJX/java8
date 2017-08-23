package pers.caijx.java8.tutorial.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * Created by Administrator on 2017/8/23/023.
 */
public class flapMapTest {

    public static void main(String[] args){
//        flatMapTest1();

//        flatMapTest2();

//        flapMapTest3();

        flapMapTest4();
    }

//    如果存在的话，每个 flatMap 的调用都会返回预期对象的 Optional 包装，否则
//    为 null 的 Optional 包装。
    public static void flapMapTest4() {
        Optional.of(new Outer())
                .flatMap(o -> Optional.ofNullable(o.nested))
                .flatMap(n -> Optional.ofNullable(n.inner))
                .flatMap(i -> Optional.ofNullable(i.foo))
                .ifPresent(System.out::println);
    }

    //    为了处理外层示例上的内层字符串 foo ，你需要添加多个 null 检查来避免潜在
//    的 NullPointerException ：
    public static void flapMapTest3() {
        Outer outer = new Outer();
        if(outer != null
                && outer.nested != null
                && outer.nested.inner != null){
            System.out.println(outer.nested.inner.foo);
        }
    }

    public static void flatMapTest2() {
        IntStream.range(1,4)
                .mapToObj(i -> new Foo("Foo" + i))
                .peek(f -> IntStream.range(1,4)
                .mapToObj(i -> new Bar("Bar" + i + " <- " + f.name))
                .forEach(f.bars::add))
                .flatMap(f -> f.bars.stream())
                .forEach(b -> System.out.println(b.name));
    }

    public static void flatMapTest1() {
        List<Foo> foos = new ArrayList<>();

        //create foos
        IntStream
                .range(1,4)
                .forEach(i -> foos.add(new Foo("Foo" + i)));

        //create bars
        foos.forEach(f ->
            IntStream
                .range(1,4)
                .forEach(i -> f.bars.add(new Bar("Bar" + i + "<-" + f.name))));

        foos.stream()
                .flatMap(f -> f.bars.stream())
                .forEach(b -> System.out.println(b.name));
    }
}
