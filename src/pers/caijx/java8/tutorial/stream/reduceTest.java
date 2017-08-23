package pers.caijx.java8.tutorial.stream;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/8/23/023.
 */
public class reduceTest {

    public static void main(String [] args) {
        List<Person> persons =
                Arrays.asList(
                        new Person("Max", 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 23),
                        new Person("David", 12)
                );

//        reduceTest1(persons);

//        reduceTest2(persons);

//        reduceTest3(persons);

//        reduceTest4(persons);

//        redecuTest5(persons);
    }

//    这个流的并行执行行为会完全不同。现在实际上调用了组合器。由于累加器被并行
//    调用，组合器需要用于计算部分累加值的总和。
    public static void redecuTest5(List<Person> persons) {
        Integer ageSum = persons
                .parallelStream()
                .reduce(0,
                        (sum,p) -> {
                            System.out.format("accumulator: sum=%s; person=%s\n"
                                    , sum, p);
                            return sum += p.age;
                        },
                        (sum1,sum2) -> {
                            System.out.format("combiner: sum1=%s; sum2=%s\n",
                                    sum1, sum2);
                            return sum1 + sum2;
                        });
    }

    //    累加器函数做了所有工作。它首先使用初始值 0 和第一个人Max来
//    调用累加器。接下来的三步中 sum 会持续增加，直到76
    public static void reduceTest4(List<Person> persons) {
        Integer ageSum = persons
                .stream()
                .reduce(0,
                        (sum,p) -> {
                            System.out.format("accumulator: sum=%s; person=%s\n"
                                    , sum, p);
                            return sum += p.age;
                        },
                        (sum1,sum2) -> {
                            System.out.format("combiner: sum1=%s; sum2=%s\n",
                                    sum1, sum2);
                            return sum1 + sum2;
                        });
    }

    //    归约函数来计算所有人的年龄总和
    public static void reduceTest3(List<Person> persons) {
        Integer ageSum = persons
                .stream()
                .reduce(0,(sum,p) -> sum += p.age,(sum1,sum2) -> sum1 + sum2);

        System.out.println(ageSum);
    }

    //    第二个 reduce 方法接受一个初始值，和一个 BinaryOperator 累加器。这个方
//    法可以用于从流中的其它 Person 对象中构造带有聚合后名称和年龄的
//    新 Person 对象
    public static void reduceTest2(List<Person> persons) {
        Person result =
                persons.stream()
                .reduce(new Person("",0),(p1,p2) -> {
                    p1.age += p2.age;
                    p1.name += p2.name;
                    return p1;
                });

        System.out.format("name=%s; age=%s",result.name,result.age);
    }

    //    reduce 方法接受 BinaryOperator 积累函数。它实际上是两个操作数类型相同
//    的 BiFunction 。 BiFunction 就像是 Function ，但是接受两个参数。示例中
//    的函数比较两个人的年龄，来返回年龄较大的人。
    public static void reduceTest1(List<Person> persons) {
        persons
                .stream()
                .reduce((p1,p2) -> p1.age > p2.age ?  p1 : p2)
                .ifPresent(System.out::println);
    }
}
