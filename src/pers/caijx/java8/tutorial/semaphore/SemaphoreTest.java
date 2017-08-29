package pers.caijx.java8.tutorial.semaphore;

import pers.caijx.java8.tutorial.concurrent.ConcurrentUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created by Administrator on 2017/8/29/029.
 */
public class SemaphoreTest {

    public static void main(String[] args){
//        执行器可能同时运行10个任务，但是我们使用了大小为5的信号量，所以将并发访
//        问限制为5。使用 try-finally 代码块在异常情况中合理释放信号量十分重要。
//        信号量限制对通过 sleep(5) 模拟的长时间运行任务的访问，最大5个线程。每个
//        随后的 tryAcquire() 调用在经过最大为一秒的等待超时之后，会向控制台打印
//                不能获取信号量的结果
        ExecutorService executor = Executors.newFixedThreadPool(10);

        Semaphore semaphore = new Semaphore(5);

        Runnable longRunningTask = () -> {
          boolean permit = false;
            try {
                permit = semaphore.tryAcquire(1, TimeUnit.SECONDS);
                if(permit){
                    System.out.println("Semaphore acquired");
                    ConcurrentUtils.sleep(5);
                } else {
                    System.out.println("Could not acquire semaphore");
                }
            } catch (InterruptedException e){
                throw new IllegalStateException();
            } finally {
                if(permit){
                    semaphore.release();
                }
            }
        };

        IntStream.range(0,10)
                .forEach(i -> executor.submit(longRunningTask));

        ConcurrentUtils.stop(executor);
    }
}
