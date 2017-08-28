package pers.caijx.java8.tutorial.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/8/28/028.
 */
public class ExecutorTest {

    public static void main(String[] args){

//        Executors 类提供了便利的工厂方法来创建不同类型的 executor services。在这
//        个示例中我们使用了一个单线程线程池的 executor。
//        代码运行的结果类似于上一个示例，但是当运行代码时，你会注意到一个很大的差
//        别：Java进程从没有停止！Executors必须显式的停止-否则它们将持续监听新的任
//        务。
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit( () -> {
           String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        });

//        ExecutorService 提供了两个方法来达到这个目的—— shutdwon() 会等待正
//        在执行的任务执行完而 shutdownNow() 会终止所有正在执行的任务并立即关闭
//        execuotr。
//        executor通过等待指定的时间让当前执行的任务终止来“温柔的”关闭executor。在等
//        待最长5分钟的时间后，execuote最终会通过中断所有的正在执行的任务关闭。
        try {
            System.out.println("attempt to shutdown exector");
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if( ! executor.isTerminated() ){
                System.out.println("cancal non-finished tasks");
            }
            executor.shutdownNow();
            System.out.println("shutdown finished");
        }
    }
}
