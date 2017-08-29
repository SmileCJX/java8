package pers.caijx.java8.tutorial.synchronization;

import pers.caijx.java8.tutorial.concurrent.ConcurrentUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * Created by Administrator on 2017/8/29/029.
 */
public class synchronizationTest {

    public  void increment(){
        int count = 0;

        count = count + 1;

        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0,10)
                .forEach(i -> executor.submit(this::increment));

        ConcurrentUtils.stop(executor);

        System.out.println(count);
    }

    public static void main(String[] args){

        synchronizationTest synchronizationTest = new synchronizationTest();
        synchronizationTest.increment();
    }
}
