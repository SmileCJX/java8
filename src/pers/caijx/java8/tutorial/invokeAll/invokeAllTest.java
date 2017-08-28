package pers.caijx.java8.tutorial.invokeAll;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/8/28/028.
 */
public class invokeAllTest {

    public static void main(String[] args) throws InterruptedException {
//        Executors支持通过 invokeAll() 一次批量提交多个callable。这个方法结果一个
//        callable的集合，然后返回一个future的列表。
        ExecutorService executor = Executors.newWorkStealingPool();

//        在这个例子中，我们利用Java8中的函数流（ stream） 来处理 invokeAll() 调用
//        返回的所有future。我们首先将每一个future映射到它的返回值，然后将每个值打印
//        到控制台。
        List<Callable<String>> callables = Arrays.asList(
                () -> "task1",
                () -> "task2",
                () -> "task3"
        );

        executor.invokeAll(callables)
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    }catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                })
                .forEach(System.out::println);
    }
}
