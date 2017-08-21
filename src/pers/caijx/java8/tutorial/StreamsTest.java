package pers.caijx.java8.tutorial;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/8/21/021.
 */
public class StreamsTest {

    //    Filter接受一个predicate接口类型的变量，并将所有流对象中的元素进行过滤。该操
//    作是一个中间操作，因此它允许我们在返回结果的基础上再进行其他的流操作
//    （ forEach） 。ForEach接受一个function接口类型的变量，用来执行对每一个元素
//    的操作。ForEach是一个中止操作。它不返回流，所以我们不能再调用其他的流操
//    作。
    private static void filterTest(List<String> stringCollection) {
        stringCollection
                .stream()
                .filter(s -> s.startsWith("a"))
                .forEach(System.out::println);
    }

//    Sorted是一个中间操作，能够返回一个排过序的流对象的视图。流对象中的元素会
//    默认按照自然顺序进行排序，除非你自己指定一个Comparator接口来改变排序规
//    则。
    private static void sortedTest(List<String> stringCollection) {
        stringCollection
                .stream()
                .sorted()
                .filter(s -> s.startsWith("a"))
                .forEach(System.out::println);

//        一定要记住，sorted只是创建一个流对象排序的视图，而不会改变原来集合中元素
//        的顺序。原来string集合中的元素顺序是没有改变的。
//        System.out.println(stringCollection);
    }

//    map是一个对于流对象的中间操作，通过给定的方法，它能够把流对象中的每一个
//    元素对应到另外一个对象上。下面的例子就演示了如何把每个string都转换成大写
//    的string. 不但如此，你还可以把每一种对象映射成为其他类型。对于带泛型结果的
//    流对象，具体的类型还要由传递给map的泛型方法来决定。
    private static void mapTest(List<String> stringCollection) {
        stringCollection
                .stream()
                .map(String::toUpperCase)
                .sorted((a,b) -> b.compareTo(a) )
                .forEach(System.out::println);
    }

//    匹配操作有多种不同的类型，都是用来判断某一种规则是否与流对象相互吻合的。
//    所有的匹配操作都是终结操作，只返回一个boolean类型的结果。
    private static void matchTest(List<String> stringCollection) {
        boolean anyStarWithA =
                stringCollection
                        .stream()
                        .anyMatch(s -> s.startsWith("a"));
        System.out.println(anyStarWithA);

        boolean allStarWithA =
                stringCollection
                        .stream()
                        .allMatch(s -> s.startsWith("a"));
        System.out.println(allStarWithA);

        boolean noneStartWithZ =
                stringCollection
                        .stream()
                        .noneMatch(s -> s.startsWith("z"));
        System.out.println(noneStartWithZ);
    }

    private static void ReduceTest(List<String> stringCollection) {
//        该操作是一个终结操作，它能够通过某一个方法，对元素进行削减操作。该操作的
//        结果会放在一个Optional变量里返回。
        Optional<String> reduced =
                stringCollection
                        .stream()
                        .sorted()
                        .reduce((s1,s2) -> s1 + "#" + s2);
        reduced.ifPresent(System.out::println);
    }


    //    Count是一个终结操作，它的作用是返回一个数值，用来标识当前流对象中包含的
//    元素数量。
    private static void countTest(List<String> stringCollection) {
        long startWithB =
                stringCollection
                        .stream()
                        .filter(s -> s.startsWith("b"))
                        .count();
        System.out.println(startWithB);
    }

//    顺序排序
    public static void sequentialParallelStreamsTest(){
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i<max; i++){
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
        long t0 = System.nanoTime();
        long count = values.stream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();
        long miles = TimeUnit.NANOSECONDS.toMillis( t1 - t0 );
        System.out.println(String.format("sequential sort took: %d ms",miles));
    }

//    并行排序，效率更高
    public static void parallelParallelStreamsTest(){
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i<max; i++){
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
        long t0 = System.nanoTime();
        long count = values.parallelStream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();
        long miles = TimeUnit.NANOSECONDS.toMillis( t1 - t0 );
        System.out.println(String.format("parallel sort took: %d ms",miles));
    }

    private static void mapMethodTest() {
        Map<Integer,String> map = new HashMap<>();

        for(int i=0; i<10; i++){
            map.putIfAbsent(i,"val" + i);
        }

        map.forEach((id,val) -> System.out.println(val));

//        使用函数来计算map的编码
        map.computeIfPresent(3,(num,val) -> val + num);
        System.out.println(map.get(3));

        map.computeIfPresent(9,(num,val) -> null);
        System.out.println(map.containsKey(9));

        map.computeIfAbsent(23,num -> "val" + num);
        System.out.println(map.containsKey(23));

        map.computeIfAbsent(3,num -> "bam");
        System.out.println(map.get(3));

//        当给定一个key值时，如何把一个实例从对应的key中移除
        map.remove(3,"val3");
        System.out.println(map.get(3));

        map.remove(3,"val33");
        System.out.println(map.get(3));

        System.out.println(map.getOrDefault(42,"not found"));

        map.merge(9,"val9",(value,newValue) -> value.concat(newValue));
        System.out.println(map.get(9));

//        合并操作先看map中是否没有特定的key/value存在，如果是，则把key/value存入
//        map，否则merging函数就会被调用，对现有的数值进行修改。
        map.merge(9,"concat",(value,newValue) -> value.concat(newValue));
        System.out.println(map.get(9));
    }

    public static void main(String[] args){
        List<String> stringCollection = new ArrayList<>();

        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

//        filterTest(stringCollection);

//        sortedTest(stringCollection);

//        mapTest(stringCollection);

//        matchTest(stringCollection);

//        countTest(stringCollection);

//        ReduceTest(stringCollection);

//        sequentialParallelStreamsTest();

//        parallelParallelStreamsTest();

        mapMethodTest();
    }

}
