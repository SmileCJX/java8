package pers.caijx.java8.tutorial.stream;

import java.util.Arrays;
import java.util.List;
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

        List<Person> filtered =
                persons
                .stream()
                .filter(p -> p.name.startsWith("P"))
                .collect(Collectors.toList());

        System.out.println(filtered);
    }
}
