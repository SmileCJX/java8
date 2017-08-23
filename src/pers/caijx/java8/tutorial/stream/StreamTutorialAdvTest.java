package pers.caijx.java8.tutorial.stream;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/8/22/022.
 */
public class StreamTutorialAdvTest {

    public static void main(String [] args){
        List<Person> persons =
                Arrays.asList(
                        new Person("Max",18),
                        new Person("Peter",23),
                        new Person("Pamela",23),
                        new Person("David",12)
                );

//        collectTest(persons);

//        collectTest2(persons);

//        collectTest3(persons);

//        collectTest4(persons);

//        collectTest5(persons);

//        collectTest6(persons);

    }

//    由于Java中的字符串是不可变的，我们需要一个助手类 StringJointer 。让收集
//    器构造我们的字符串。供应器最开始使用相应的分隔符构造了这样一
//    个 StringJointer 。累加器用于将每个人的大写名称加到 StringJointer 中。
//    组合器知道如何把两个 StringJointer 合并为一个。最后一步，终结器
//    从 StringJointer 构造出预期的字符串。
    public static void collectTest6(List<Person> persons) {
        Collector<Person, StringJoiner, String> personNameCollector =
                Collector.of(
                        () -> new StringJoiner(" | "),   //  supplier 供应器
                        (j, p) -> j.add(p.name.toUpperCase()),  //accumulator  累加器
                        (j1, j2) -> j1.merge(j2), // combiner  组合器
                        StringJoiner::toString);  // finisher  终止器

        String names = persons
                .stream()
                .collect(personNameCollector);

        System.out.println(names);
    }

    //    连接收集器接受分隔符，以及可选的前缀和后缀。
    public static void collectTest5(List<Person> persons) {
        String phrase = persons
                .stream()
                .filter(p -> p.age >= 18)
                .map(p -> p.name)
                .collect(Collectors.joining(" and ","In Germany"," are of legal age."));

        System.out.println(phrase);
    }

    public static void collectTest4(List<Person> persons) {
        IntSummaryStatistics ageSummary =
                persons
                .stream()
                .collect(Collectors.summarizingInt(p -> p.age));
        System.out.println(ageSummary);
    }

    public static void collectTest3(List<Person> persons) {
        Double averageAge = persons
                .stream()
                .collect(Collectors.averagingInt(p -> p.age));
        System.out.println(averageAge);
    }

    //    按照年龄对所有人进行分组
    public static void collectTest2(List<Person> persons) {
        Map<Integer,List<Person>> personsByAge = persons
                .stream()
                .collect(Collectors.groupingBy(p -> p.age));

        personsByAge
                .forEach((age,p) -> System.out.format("age %s: %s\n",age,p));
    }

    public static void collectTest(List<Person> persons) {
        List<Person> filtered =
                persons
                .stream()
                .filter(p -> p.name.startsWith("P"))
                .collect(Collectors.toList());

        System.out.println(filtered);
    }
}
