package pers.caijx.java8.tutorial.invokeAny;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/8/28/028.
 */
public class invokeAnyTest {

    public static void main(String[] args){


//        批量提交callable的另一种方式就是 invokeAny() ，它的工作方式
//        与 invokeAll() 稍有不同。在等待future对象的过程中，这个方法将会阻塞直到
//                第一个callable中止然后返回这一个callable的结果

//        调用 newWorkStealingPool() 。这个工厂方法是Java8引入的，返回一
//        个 ForkJoinPool 类型的 executor，它的工作方法与其他常见的execuotr稍有不
//        同。与使用一个固定大小的线程池不同， ForkJoinPools 使用一个并行因子数来
//        创建，默认值为主机CPU的可用核心数
        ExecutorService executor  = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
                callable("task1",2),
                callable("task2",1),
                callable("task3",3)
        );

        try {
            String result = executor.invokeAny(callables);
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    public static Callable<String> callable(String result,long sleepSecond){
        return () -> {
            TimeUnit.SECONDS.sleep(sleepSecond);
            return result;
        };
    }
}
