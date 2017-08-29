package pers.caijx.java8.tutorial.concurrentMap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Administrator on 2017/8/29/029.
 */
public class ConcurrentMapTest {

    public static void main(String[] args){
        ConcurrentMap<String,String> map = new ConcurrentHashMap<>();
        map.put("foo","bar");
        map.put("han","solo");
        map.put("r2","d2");
        map.put("c3","p0");

//        forEach() 方法接受类型为 BiConsumer 的lambda表达式，以映射的键和值作
//        为参数传递。它可以作为 for-each 循环的替代，来遍历并发映射中的元素。迭代
//        在当前线程上串行执行。
//        map.forEach((key,value) -> System.out.printf("%s = %s\n",key,value));


//        新方法 putIfAbsent() 只在提供的键不存在时，将新的值添加到映射中。至少
//        在 ConcurrentHashMap 的实现中，这一方法像 put() 一样是线程安全的，所以
//        你在不同线程中并发访问映射时，不需要任何同步机制。
//        String value = map.putIfAbsent("c3","p1");
//        System.out.println(value);

//        getOrDefault() 方法返回指定键的值。在传入的键不存在时，会返回默认值
//        String value = map.getOrDefault("hi","there");
//        System.out.println(value);

//        replaceAll() 接受类型为 BiFunction 的lambda表达式。 BiFunction 接受
//        两个参数并返回一个值。函数在这里以每个元素的键和值调用，并返回要映射到当
//        前键的新值。
//        map.replaceAll((key,value) -> "r2".equals(key) ? "d3":value);
//        System.out.println(map.get("r2"));

//        compute() 允许我们转换单个元素，而不是替换映射中的所有值。这个方法接受
//        需要处理的键，和用于指定值的转换的 BiFunction
//        map.compute("foo",(key,value) -> value + value + value);
//        System.out.println(map.get("foo"));

//        最后， merge() 方法可以用于以映射中的现有值来统一新的值。这个方法接受
//        键、需要并入现有元素的新值，以及指定两个值的合并行为的 BiFunction 。
//        map.merge("foo","boo",(oldVal,newVal) -> newVal + " was " +  oldVal);
//        System.out.println(map.get("foo"));
    }
}
