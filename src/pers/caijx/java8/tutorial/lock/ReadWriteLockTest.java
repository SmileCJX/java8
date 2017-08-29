package pers.caijx.java8.tutorial.lock;

import pers.caijx.java8.tutorial.concurrent.ConcurrentUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Administrator on 2017/8/29/029.
 */
//ReadWriteLock 接口规定了锁的另一种类型，包含用于读写访问的一对锁。读写
//        锁的理念是，只要没有任何线程写入变量，并发读取可变变量通常是安全的。所以
//        读锁可以同时被多个线程持有，只要没有线程持有写锁。这样可以提升性能和吞吐
//        量，因为读取比写入更加频繁
public class ReadWriteLockTest {

//    在释放了写锁之后，两个读任务会同时执行，并同时打印结果。它们不需要相互等待完成，
//    因为读锁可以安全同步获取，只要没有其它线程获取了写锁。
    public static void main(String[] args){
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Map<String,String> map = new HashMap<>();
        ReadWriteLock lock = new ReentrantReadWriteLock();

        executor.submit( () -> {
           lock.writeLock().lock();
            try {
                ConcurrentUtils.sleep(1);
                map.put("foo","bar");
            } finally {
                lock.writeLock().unlock();
            }
        });

//        上面的例子在暂停一秒之后，首先获取写锁来向映射添加新的值。在这个任务完成
//        之前，两个其它的任务被启动，尝试读取映射中的元素，并暂停一秒：
        Runnable readTask = () -> {
          lock.readLock().lock();
            try {
                System.out.println(map.get("foo"));
                ConcurrentUtils.sleep(1);
            } finally {
                lock.readLock().unlock();
            }
        };

        executor.submit(readTask);
        executor.submit(readTask);

        ConcurrentUtils.stop(executor);
    }
}
