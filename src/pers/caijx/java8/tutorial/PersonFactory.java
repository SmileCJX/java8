package pers.caijx.java8.tutorial;

/**
 * Created by Administrator on 2017/8/21/021.
 */
//person工厂接口
interface PersonFactory<P extends Person> {

    P create(String firstName,String lastName);
}
