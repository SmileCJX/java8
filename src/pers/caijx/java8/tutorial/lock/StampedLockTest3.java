package pers.caijx.java8.tutorial.lock;

import pers.caijx.java8.tutorial.concurrent.ConcurrentUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

/**
 * Created by Administrator on 2017/8/29/029.
 */
//第一个任务获取读锁，并向控制台打印 count 字段的当前值。但是如果当前值是
//        零，我们希望将其赋值为 23 。我们首先需要将读锁转换为写锁，来避免打破其它
//        线程潜在的并发访问。 tryConvertToWriteLock() 的调用不会阻塞，但是可能会
//        返回为零的标记，表示当前没有可用的写锁。这种情况下，我们调
//        用 writeLock() 来阻塞当前线程，直到有可用的写锁。
public class StampedLockTest3 {

    public static void main(String[] args){
        ExecutorService executor = Executors.newFixedThreadPool(2);
        StampedLock lock = new StampedLock();


        executor.submit( () -> {
            int count = 0;
           long stamp = lock.readLock();
            try {
                if(count == 0){
                    stamp = lock.tryConvertToWriteLock(stamp);
                    if(stamp == 0L){
                        System.out.println("Could not cover to write lock");
                        stamp = lock.writeLock();
                    }
                    count = 23;
                }
                System.out.println(count);
            } finally {
                lock.unlock(stamp);
            }
        });

        ConcurrentUtils.stop(executor);
    }
}
