package pers.caijx.java8.tutorial.atomAndConcurrentMap;

import pers.caijx.java8.tutorial.concurrent.ConcurrentUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;
import java.util.stream.IntStream;

/**
 * Created by Administrator on 2017/8/29/029.
 */
public class LongAccumulatorTest {

    public static void main(String[] args){
//        使用函数 2 * x + y 创建了 LongAccumulator ，初始值为1。每次调
//        用 accumulate(i) 的时候，当前结果和值 i 都会作为参数传入lambda表达式。
        LongBinaryOperator op = (x,y) -> 2 * x + y;
        LongAccumulator accumulator = new LongAccumulator(op,1L);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0,10)
                .forEach(i -> executor.submit( () -> accumulator.accumulate(i)));

        ConcurrentUtils.stop(executor);

        System.out.println(accumulator.getThenReset());
    }
}
