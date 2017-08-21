package pers.caijx.java8.tutorial;

import java.util.Comparator;

/**
 * Created by Administrator on 2017/8/21/021.
 */
public class ComparatorsTest {

    public static void main(String[] args){
        Comparator<Person> comparator = (p1,p2) -> p1.firstName.compareTo(p2.firstName);

        Person p1 = new Person("John","Doe");
        Person p2 = new Person("Alice","wonderland");

        System.out.println(comparator.compare(p1, p2));
        System.out.println(comparator.reversed().compare(p1,p2));
    }
}
