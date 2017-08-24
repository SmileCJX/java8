package pers.caijx.java8.tutorial.stream;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by Administrator on 2017/8/24/024.
 */
public class parallelStreamTest {

    public static void main(String[] args){

//        parallelStreamTest1();

//        parallelStreamTest2();

//        parallelStreamTest3();

    }

//    sort 看起来只在主线程上串行执行。实际上，并行流上的 sort 在背后使用了
//    Java8中新的方法 Arrays.parallelSort() 。如javadoc所说，这个方法会参照数
//            据长度来决定以串行或并行来执行
    public static void parallelStreamTest3() {
        Arrays.asList("a1","a2","b1","c2","c1")
                .parallelStream()
                .filter(s -> {
                    System.out.format("filter: %s [%s] \n",
                            s,Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    System.out.format("map: %s [%s]\n",
                            s,Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .sorted((s1,s2) -> {
                    System.out.format("sort: %s <> %s [%s]\n",
                            s1,s2,Thread.currentThread().getName());
                    return s1.compareTo(s2);
                })
                .forEach(s -> System.out.format("forEach: %s [%s]\n",
                        s,Thread.currentThread().getName()));
    }

    //    打印了当前线程的信息
    public static void parallelStreamTest2() {
        Arrays.asList("a1","a2","b1","c2")
                .parallelStream()
                .filter(s -> {
                    System.out.format("filter:%s [%s]\n",
                            s,Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    System.out.format("map:%s [%s]\n",
                            s,Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .forEach(s -> {
                    System.out.format("forEach:%s [%s]\n",
                            s,Thread.currentThread().getName());
                });
    }

    //        流可以并行执行，在大量输入元素上可以提升运行时的性能。并行流使用公共
//        的 ForkJoinPool ，由 ForkJoinPool.commonPool() 方法提供。底层线程池的
//        大小最大为五个线程 -- 取决于CPU的物理核数。
//        这个值可以通过设置下列JVM参数来增减：
//        -Djava.util.concurrent.ForkJoinPool.common.parallelism=5
    public static void parallelStreamTest1() {
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        System.out.println(commonPool.getParallelism()); //3
    }
}
