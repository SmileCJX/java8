package pers.caijx.java8.tutorial.scheduledExecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/8/28/028.
 */
public class ScheduledExecutorTest {

    public static void main(String[] args){

//        scheduledExecutorTest1();

//        scheduledExecutorTest2();

        scheduledExecutorTest3();
    }

//    等待时间 period 的应用是在一次任务的结束和下一个任务的开始之间
    public static void scheduledExecutorTest3() {
//        这个例子调度了一个任务，并在一次执行的结束和下一次执行的开始之间设置了一
//        个1分钟的固定延迟。初始化延迟为0，任务执行时间为0。所以我们分别在
//        0s,3s,6s,9s等间隔处结束一次执行。
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Scheduling: " + System.nanoTime());
            } catch (InterruptedException e) {
                System.out.println("task interrupted");
            }
        };

        executor.scheduleWithFixedDelay(task,0,1,TimeUnit.SECONDS);
    }

    //    为了调度任务持续的执行，executors 提供了两个方
//    法 scheduleAtFixedRate() 和 scheduleWithFixedDelay() 。第一个方法用来
//    以固定频率来执行一个任务，比如，下面这个示例中，每分钟一次：
    public static void scheduledExecutorTest2() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());

        int initialDelay = 0;
        int period = 1;
//        scheduleAtFixedRate() 并不考虑任务的实际用时。所以，如果你指定
//        了一个period为1分钟而任务需要执行2分钟，那么线程池为了性能会更快的执行。
        executor.scheduleAtFixedRate(task,initialDelay,period, TimeUnit.SECONDS);
    }

    public static void scheduledExecutorTest1() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());
        ScheduledFuture<?> future = executor.schedule(task,3, TimeUnit.SECONDS);

//        调度一个任务将会产生一个专门的future类型—— ScheduleFuture ，它除了提供
//        了Future的所有方法之外，他还提供了 getDelay() 方法来获得剩余的延迟。在延
//        迟消逝后，任务将会并发执行。

        try {
            TimeUnit.MILLISECONDS.sleep(1337);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);
        System.out.printf("Remaing Delay: %sms",remainingDelay);
    }
}
