package pers.caijx.java8.tutorial.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2017/8/22/022.
 */

public class StreamTutorialTest {

    public static void main(String[] args){
//        streamTest1();

//        streamTest2();

//        streamTest3();

//        streamTest4();

//        streamTest5();

//        streamTest6();

//        streamTest7();

//        streamTest8();

//        streamTest9();

//        streamTest10();

//        streamTest11();

//        streamTest12();

//        streamTest13();

//        streamTest14();

//        streamTest15();

//        streamTest16();

//        streamTest17();
    }

//    克服这个限制，我们需要为每个我们想要执行的终止操作创建新的数据流调用链
//    每次对 get() 的调用都构造了一个新的数据流，我们将其保存来调用终止操作。
    public static void streamTest17() {
        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));
        streamSupplier.get().anyMatch(s -> true);
        streamSupplier.get().noneMatch(s -> true);
    }

    //    Java8的数据流不能被复用。一旦你调用了任何终止操作，数据流就关闭了
    public static void streamTest16() {
        Stream<String> stream =
                Stream.of("d2","a2","b1","b3","c")
                .filter(s -> s.startsWith("a"));
        stream.anyMatch(s -> true);  // ok
        stream.noneMatch(s -> true); // exception
    }

    //    例子中 sorted 永远不会调用，因为 filter 把输入集合减少至只有一个元
//    素。所以对于更大的输入集合会极大提升性能。
    public static void streamTest15() {
        Stream.of("d2","a2","b1","b3","c")
                .filter(s -> {
                    System.out.println("filter:" + s);
                    return s.startsWith("a");
                })
                .sorted((s1,s2) -> {
                    System.out.printf("sort: %s; %s\n",s1,s2);
                    return s1.compareTo(s2);
                })
                .map(s -> {
                    System.out.println("map:" + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach:" + s));
    }

    public static void streamTest14() {
        Stream.of("d2","a2","b1","b3","c")
                .sorted((s1,s2) -> {
                    System.out.printf("sort:%s,%s\n",s1,s2);
                    return s1.compareTo(s2);
                })
                .filter(s -> {
                    System.out.println("filter:" + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map:" + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach:" + s));
    }

    //    调整操作顺序，将 filter 移动到调用链的顶端，就可以极大减少操作的执行次数
    public static void streamTest13() {
        Stream.of("d2","a2","b1","b3","c")
                .filter(s -> {
                    System.out.println("filter:" + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map:" + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach:" + s));
    }

    //    就像你可能猜到的那样， map 和 filter 会对底层集合的每个字符串调用五次，
//    而 forEach 只会调用一次。
    public static void streamTest12() {
        Stream.of("d2","a2","b1","b3","c")
                .map(s -> {
                    System.out.println("map:" + s);
                    return s.toUpperCase();
                })
                .filter(s -> {
                    System.out.println("filter:" + s);
                    return s.startsWith("A");
                })
                .forEach(s -> System.out.println("foreach:" + s));
    }

    //        只要提供的数据元素满足了谓词， anyMatch 操作就会返回 true 。对于第二个
//        传递 "A2" 的元素，它的结果为真。由于数据流的链式调用是垂直执行
//        的， map 这里只需要执行两次。所以 map 会执行尽可能少的次数，而不是把所有
//        元素都映射一遍。
    public static void streamTest11() {

        Stream.of("d2","a2","b1","b3","c")
                .map(s -> {
                    System.out.println("map:" + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch:" + s);
                    return s.startsWith("A");
                });
    }

    private static void streamTest10() {
        //        原始的方法会在数据流的所有元素上，一个接一个地水
//        平执行所有操作。但是每个元素在调用链上垂直移动。第一个字符串 "d2" 首先经
//        过 filter 然后是 forEach ，执行完后才开始处理第二个字符串 "a2
        Stream.of("d2","a2","b1","b3","c")
                .filter(s -> {
                    System.out.println("filter:" + s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach:" + s));
    }

    //    执行这段代码时，不向控制台打印任何东西。这是因为衔接操作只在终止操作调用时被执行。
    private static void streamTest9() {
        Stream.of("d2","a2","b1","b3","c")
                .filter(s -> {
                   System.out.println("filter:" + s);
                    return true;
                });
    }

    //    浮点数据流首先映射为整数数据流，之后映射为字符串的对象数据流
    private static void streamTest8() {
        Stream.of(1.0,2.0,3.0)
                .mapToInt(Double::intValue)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);
    }

    //    基本数据流可以通过 mapToObj() 转换为对象数据流：
    private static void streamTest7() {
        IntStream.range(1,4)
                .mapToObj( i -> "a" + i)
                .forEach(System.out::println);
    }

    private static void streamTest6() {
        Stream.of("a1","a2","a3")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(System.out::println);
    }

    //    基本数据流支持额外的聚合终止操作 sum() 和 average()
    private static void streamTest5() {
        Arrays.stream(new int[]{1,2,3})
                .map(n -> 2 * n + 1)
                .average()
                .ifPresent(System.out::println);
    }

    private static void streamTest4() {
        IntStream.range(1,4)
                .forEach(System.out::println);
    }

    //    使用 Stream.of() ，就可以从一系列对象引用中创建数据流。
    private static void streamTest3() {
        Stream.of("a1","a2","a3")
                .findFirst()
                .ifPresent(System.out::println);
    }

    private static void streamTest2() {
        Arrays.asList("a1","a2","a3")
                .stream()
                .findFirst()
                .ifPresent(System.out::println);
    }

    private static void streamTest1() {
        List<String> myList =
                Arrays.asList("a1", "a2", "b1", "c2", "c1");

        myList
                .stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
    }
}
