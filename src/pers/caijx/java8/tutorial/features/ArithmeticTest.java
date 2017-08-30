package pers.caijx.java8.tutorial.features;

/**
 * Created by Administrator on 2017/8/29/029.
 */
public class ArithmeticTest {

    public static void main(String[] args){
//        Java8添加了严格数学运算的支持来解决这个问题。 Math 扩展了一些方法，它们
//        全部以 exact 结尾，例如 addExact 。当运算结果不能被数值类型装下时，这些
//        方法通过抛出 ArithmeticException 异常来合理地处理溢出。
        try {
            Math.addExact(Integer.MAX_VALUE,1);
        } catch (ArithmeticException e){
            System.err.println(e.getMessage());
        }

//        当尝试通过 toIntExact 将长整数转换为整数时，可能会抛出同样的异常
        try {
            Math.toIntExact(Long.MAX_VALUE);
        } catch (ArithmeticException e){
            System.err.println(e.getMessage());
        }
    }
}
