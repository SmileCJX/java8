package pers.caijx.java8.tutorial;

/**
 * Created by Administrator on 2017/8/21/021.
 */
//通过Person::new来创建一个Person类构造函数的引用。Java编译器会自动地
//        选择合适的构造函数来匹配PersonFactory.create函数的签名，并选择正确的构造
//        函数形式。
public class PersonFactoryTest {

    public static void main(String[] args){
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter","Parker");
    }
}
