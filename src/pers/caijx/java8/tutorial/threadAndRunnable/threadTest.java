package pers.caijx.java8.tutorial.threadAndRunnable;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/8/28/028.
 */
public class threadTest {

    public static void main(String[] args){

//        threadTest1();

//        threadTest2();
    }

    public static void threadTest2() {
        Runnable runnable = () -> {
            try {
                String name = Thread.currentThread().getName();
                System.out.println("Foo " + name);
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Bar " + name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    //    当你运行上面的代码时，你会注意到在第一条打印语句和第二条打印语句之间存在
//    一分钟的延迟。 TimeUnit 在处理单位时间时一个有用的枚举类。你可以通过调
//    用 Thread.sleep(1000) 来达到同样的目的。
    public static void threadTest1() {
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        };

        task.run();

        Thread thread = new Thread(task);
        thread.start();

        System.out.println("Done!");
    }
}
