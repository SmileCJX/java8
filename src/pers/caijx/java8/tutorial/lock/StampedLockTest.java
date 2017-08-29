package pers.caijx.java8.tutorial.lock;

import pers.caijx.java8.tutorial.concurrent.ConcurrentUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

/**
 * Created by Administrator on 2017/8/29/029.
 */

//Java 8 自带了一种新的锁，叫做 StampedLock ，它同样支持读写锁，就像上面的
//        例子那样。与 ReadWriteLock 不同的是， StampedLock 的锁方法会返回表示
//        为 long 的标记。你可以使用这些标记来释放锁，或者检查锁是否有效。
public class StampedLockTest {

//    两个读任务都需要等待写锁释放。之后两个读任务同时向控制台打印信息，因为多个读操作不会相互阻塞，只要没有线程拿
//    到写锁。
    public static void main(String[] args){
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Map<String,String > map = new HashMap<>();
        StampedLock lock = new StampedLock();

        executor.submit( () -> {
            long stamp = lock.readLock();
            try {
                ConcurrentUtils.sleep(1);
                map.put("foo","bar");
            } finally {
                lock.unlockWrite(stamp);
            }
        });

        Runnable readTask = () -> {
          long stamp = lock.readLock();
            try {
                System.out.println(map.get("foo"));
                ConcurrentUtils.sleep(1);
            } finally {
                lock.unlockRead(stamp);
            }
        };

        executor.submit(readTask);
        executor.submit(readTask);

        ConcurrentUtils.stop(executor);
    }
}
