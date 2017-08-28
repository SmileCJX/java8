package pers.caijx.java8.tutorial.callableAndFuture;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/8/28/028.
 */
public class callableAndFutureTest {

    public static void main(String[] args){

//        callableAndFutureTest1();

//        callableAndFutureTest2();


    }

    public static void callableAndFutureTest2() {
        //        指定的最长等待时间为1分钟，而这个callable在返回结果之前实际需要两分钟
        ExecutorService executor = Executors.newFixedThreadPool(1);

        Future<Integer> future = executor.submit( () -> {
            TimeUnit.SECONDS.sleep(2);
            return 123;
        });
        try {
            future.get(1,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static void callableAndFutureTest1() {
        //        Callables也是类似于runnables的函数接口，不同之处在于，Callable返回一个值。
        Callable<Integer> task = () -> {
            TimeUnit.SECONDS.sleep(1);
            return 123;
        };

        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(task);

        System.out.println("future done? " + future.isDone());

//        在将callable提交给exector之后，我们先通过调用 isDone() 来检查这个future是
//        否已经完成执行。我十分确定这会发生什么，因为在返回那个整数之前callable会休
//                眠一分钟
        try {
            Integer result =future.get();
            System.out.println("future done? " + future.isDone());
            System.out.println("result: " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
