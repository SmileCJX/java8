package pers.caijx.java8.tutorial;

/**
 * Created by Administrator on 2017/8/21/021.
 */
public class AnnotationsTest {

    public static void main(String[] args){
        Hint hint = Person2.class.getAnnotation(Hint.class);
        System.out.println(hint);

        Hints hint1 = Person2.class.getAnnotation(Hints.class);
        System.out.println(hint1.value().length);

        Hint[] hint2 = Person2.class.getAnnotationsByType(Hint.class);
        System.out.println(hint2.length);
    }
}
