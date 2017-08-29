package pers.caijx.java8.tutorial.atomAndConcurrentMap;

import pers.caijx.java8.tutorial.concurrent.ConcurrentUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Created by Administrator on 2017/8/29/029.
 */
public class AtomicIntegerTest {

    public static void main(String[] args){

//        atomicIntegerTest1();

//        atomicIntegerTest2();

//        atomicIntegerTest3();
    }

    public static void atomicIntegerTest3() {
        AtomicInteger atomicInt = new AtomicInteger(0);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0,1000)
                .forEach(i -> {
                    Runnable task = () ->
                            atomicInt.accumulateAndGet(i,(n,m) -> n+m);
                    executor.submit(task);
                });

        ConcurrentUtils.stop(executor);
        System.out.println(atomicInt.get());
    }

    //    通过使用 AtomicInteger 代替 Integer ，我们就能线程安全地并发增加数值，
//    而不需要同步访问变量。 incrementAndGet() 方法是原子操作，所以我们可以在
//    多个线程中安全调用它。
//AtomicInteger 支持多种原子操作。 updateAndGet() 接受lambda表达式
    public static void atomicIntegerTest2() {
        AtomicInteger atomicInt = new AtomicInteger(0);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0,1000)
                .forEach(i -> {
                    Runnable task = () ->
                            atomicInt.updateAndGet(n -> n+2);
                    executor.submit(task);
                });

        ConcurrentUtils.stop(executor);
        System.out.println(atomicInt.get());
    }

    //        通过使用 AtomicInteger 代替 Integer ，我们就能线程安全地并发增加数值，
//        而不需要同步访问变量。 incrementAndGet() 方法是原子操作，所以我们可以在
//        多个线程中安全调用它。
    public static void atomicIntegerTest1() {
        AtomicInteger atomicInt = new AtomicInteger(0);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0,1000)
                .forEach(i -> executor.submit(atomicInt::incrementAndGet));

        ConcurrentUtils.stop(executor);

        System.out.println(atomicInt.get());
    }
}
