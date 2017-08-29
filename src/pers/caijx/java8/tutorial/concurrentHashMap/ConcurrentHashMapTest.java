package pers.caijx.java8.tutorial.concurrentHashMap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by Administrator on 2017/8/29/029.
 */
public class ConcurrentHashMapTest {

    public static void main(String[] args){

//        这个值可以通过设置下列JVM参数来增减
//        -Djava.util.concurrent.ForkJoinPool.common.parallelism=5
        System.out.println(ForkJoinPool.getCommonPoolParallelism());

        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();
        map.put("foo","bar");
        map.put("han","solo");
        map.put("r2","d2");
        map.put("c3","p0");

//        forEach() 方法可以并行迭代映射中的键值对。 BiConsumer 以当前迭代元素的
//        键和值调用。为了将并行执行可视化，我们向控制台打印了当前线程的名称。
//        map.forEach(1,(key,value) ->
//                System.out.printf("key: %s; value: %s; thread: %s\n",
//                        key,value,Thread.currentThread().getName())
//        );

//        search() 方法接受 BiFunction 并为当前的键值对返回一个非空的搜索结果，
//        或者在当前迭代不匹配任何搜索条件时返回 null 。只要返回了非空的结果，就不
//        会往下搜索了。要记住 ConcurrentHashMap 是无序的。搜索函数应该不依赖于映
//        射实际的处理顺序。如果映射的多个元素都满足指定搜索函数，结果是非确定的。
//        String result  = map.search(1,(key,value) ->{
//                System.out.println(Thread.currentThread().getName());
//                if("foo".equals(key)){
//                    return value;
//                }
//                return null;
//        });
//        System.out.println("Result: " + result);

//        仅搜索映射中的值
//        String result = map.searchValues(1,value -> {
//           System.out.println(Thread.currentThread().getName());
//            if(value.length()>3){
//                return value;
//            }
//            return null;
//        });
//        System.out.println("Result: " + result);

//        reduce() 方法已经在Java 8 的数据流之中用过了，它接受两个 BiFunction 类
//        型的lambda表达式。第一个函数将每个键值对转换为任意类型的单一值。第二个函
//        数将所有这些转换后的值组合为单一结果，并忽略所有可能的 null 值
        String result = map.reduce(1,(key,value) -> {
           System.out.println("Transform: " + Thread.currentThread().getName());
            return key + "=" + value;
        },
        (s1,s2) ->{
            System.out.println("Reduce: " + Thread.currentThread().getName());
            return s1 + ", " + s2;
        }
        );
        System.out.println("Result: " + result);
    }
}
