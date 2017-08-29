package pers.caijx.java8.tutorial.lock;

import pers.caijx.java8.tutorial.concurrent.ConcurrentUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

/**
 * Created by Administrator on 2017/8/29/029.
 */
//乐观锁在刚刚拿到锁之后是有效的。和普通的读锁不同的是，乐观锁不阻止其他线
//        程同时获取写锁。在第一个线程暂停一秒之后，第二个线程拿到写锁而无需等待乐
//        观的读锁被释放。此时，乐观的读锁就不再有效了。甚至当写锁释放时，乐观的读
//        锁还处于无效状态。
public class StampedLockTest2 {

    public static void main(String[] args){
        ExecutorService executor = Executors.newFixedThreadPool(2);
        StampedLock lock = new StampedLock();

//        乐观的读锁通过调用 tryOptimisticRead() 获取，它总是返回一个标记而不阻塞
//        当前线程，无论锁是否真正可用。如果已经有写锁被拿到，返回的标记等于0。你
//        需要总是通过 lock.validate(stamp) 检查标记是否有效
        executor.submit( () -> {
            long stamp = lock.tryOptimisticRead();
            try {
                System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
                ConcurrentUtils.sleep(1);
                System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
                ConcurrentUtils.sleep(2);
                System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
            } finally {
                lock.unlock(stamp);
            }
        });

//        tip:所以在使用乐观锁时，你需要每次在访问任何共享可变变量之后都要检查锁，
//        来确保读锁仍然有效
        executor.submit( () -> {
           long stamp = lock.writeLock();
            try {
                System.out.println("Write Lock acquired");
                ConcurrentUtils.sleep(2);
            } finally {
                lock.unlock(stamp);
                System.out.println("Write done");
            }
        });

        ConcurrentUtils.stop(executor);
    }
}
