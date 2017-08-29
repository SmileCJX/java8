package pers.caijx.java8.tutorial.lock;

import pers.caijx.java8.tutorial.concurrent.ConcurrentUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/8/29/029.
 */
//ReentrantLock 类是互斥锁，与通过 synchronized 访问的隐式监视器具有相
//        同行为，但是具有扩展功能。就像它的名称一样，这个锁实现了重入特性，就像隐
//        式监视器一样。
public class ReentrantLockTest {

    public static void main(String[] args){
        ReentrantLock lock = new ReentrantLock();
        ExecutorService executor = Executors.newFixedThreadPool(2);
//        锁可以通过 lock() 来获取，通过 unlock() 来释放。把你的代码包装
//        在 try-finally 代码块中来确保异常情况下的解锁非常重要。这个方法是线程安
//        全的，就像同步副本那样。如果另一个线程已经拿到锁了，再次调用 lock() 会阻
//        塞当前线程，直到锁被释放。在任意给定的时间内，只有一个线程可以拿到锁。
        executor.submit( () -> {
           lock.lock();
            try {
                ConcurrentUtils.sleep(1);
            } finally {
                lock.unlock();
            }
        });

        executor.submit( () -> {
           System.out.println("Locked: " + lock.isLocked());
            System.out.println("Held by me: " + lock.isHeldByCurrentThread());
//            tryLock() 方法是 lock() 方法的替代，它尝试拿锁而不阻塞当前线程。在访问
//            任何共享可变变量之前，必须使用布尔值结果来检查锁是否已经被获取。
            boolean locked = lock.tryLock();
            System.out.println("Lock acquired: " + locked);
        });

        ConcurrentUtils.stop(executor);
    }

}
