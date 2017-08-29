package pers.caijx.java8.tutorial.atomAndConcurrentMap;

import pers.caijx.java8.tutorial.concurrent.ConcurrentUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

/**
 * Created by Administrator on 2017/8/29/029.
 */
//LongAdder 是 AtomicLong 的替代，用于向某个数值连续添加值。
public class LongAdderTest {

    public static void main(String[] args){
//        LongAdder 提供了 add() 和 increment() 方法，就像原子数值类一样，同样
//        是线程安全的。但是这个类在内部维护一系列变量来减少线程之间的争用，而不是
//        求和计算单一结果。实际的结果可以通过调用 sum() 或 sumThenReset() 来获
//        取。
//        当多线程的更新比读取更频繁时，这个类通常比原子数值类性能更好。这种情况在
//        抓取统计数据时经常出现，例如，你希望统计Web服务器上请求的数
//        量。 LongAdder 缺点是较高的内存开销，因为它在内存中储存了一系列变量。

        LongAdder adder = new LongAdder();

        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0,1000)
                .forEach(i -> executor.submit(adder::increment));

        ConcurrentUtils.stop(executor);

        System.out.println(adder.sumThenReset());
    }
}
