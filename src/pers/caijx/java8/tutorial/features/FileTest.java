package pers.caijx.java8.tutorial.features;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2017/8/29/029.
 */
public class FileTest {

    public static void main(String[] args){
//        fileTest1();

//        fileTest2();

//        fileTest3();

//        fileTest4();

//        fileTest5();

//        fileTest6();

//        fileTest7();

        fileTest8();
    }

    public static void fileTest8() {
        Path path = Paths.get("src/pers/caijx/java8/tutorial/js/nashorn1.js");
        try (BufferedReader reader = Files.newBufferedReader(path)){
            long countPrints = reader
                    .lines()
                    .filter(line -> line.contains("print"))
                    .count();
            System.out.println(countPrints);
        } catch (IOException e){
            e.getMessage();
        }
    }

    public static void fileTest7() {
        Path path = Paths.get("src/pers/caijx/java8/tutorial/js/nashorn1.js");
        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            writer.write("print('Hello world');");
        } catch (IOException e){
            e.getMessage();
        }
    }

    public static void fileTest6() {
        Path path = Paths.get("src/pers/caijx/java8/tutorial/js/nashorn1.js");
        try (BufferedReader reader = Files.newBufferedReader(path)){
            System.out.println(reader.readLine());
        } catch (IOException e){
            e.getMessage();
        }
    }

    //    使用 Files.lines 方法来作为内存高效的替代。这个方法读取每一行，并
//    使用函数式数据流来对其流式处理，而不是一次性把所有行都读进内存。
    public static void fileTest5() {
        try (Stream<String> stream = Files.lines(Paths.get("src/pers/caijx/java8/tutorial/js/nashorn1.js"))){
            stream
                    .filter(line -> line.contains("Created"))
                    .map(String::trim)
                    .forEach(System.out::println);
        } catch (IOException e){
            e.getMessage();
        }
    }

    //    读写文件
//    将文本文件读到内存，以及向文本文件写入字符串在Java 8 中是简单的任务。不需
//    要再去摆弄读写器了。 Files.readAllLines 从指定的文件把所有行读进字符串
//    列表中。你可以简单地修改这个列表，并且将它通过 Files.write 写到另一个文
//    件中：
    public static void fileTest4() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/pers/caijx/java8/tutorial/js/nashorn1.js"));
            lines.add("print('foobar);");
            Files.write(Paths.get("src/pers/caijx/java8/tutorial/js/nashorn1-modified.js"),lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    使用 Files.walk 方法来完成相同的行为。这个方法会遍历每个文件，
//    而不需要传递搜索谓词。
    public static void fileTest3() {
        Path start = Paths.get("");
        int maxDepth = 5;
        try (Stream<Path> stream = Files.walk(start,maxDepth)){
            String joined = stream
                    .map(String::valueOf)
                    .filter(path -> path.endsWith(".iml"))
                    .sorted()
                    .collect(Collectors.joining("; "));
            System.out.println("walk(): " + joined);
        } catch (IOException e){
            e.getMessage();
        }
    }

    //    查找在目录及其子目录下的文件
//find 方法接受三个参数：目录路径 start 是起始点， maxDepth 定义了最大搜
//    索深度。第三个参数是一个匹配谓词，定义了搜索的逻辑。上面的例子中，我们搜
//    索了所有JavaScirpt文件（ 以 .js 结尾的文件名） 。
    public static void fileTest2() {
        Path start = Paths.get("");
        int maxDepth = 5;
        try (Stream<Path> stream = Files.find(start,maxDepth,(path, attr) ->
        String.valueOf(path).endsWith(".iml"))){
            String joined = stream
                    .sorted()
                    .map(String::valueOf)
                    .collect(Collectors.joining("; "));
            System.out.println("Found: " + joined);
        } catch (IOException e){
            e.getMessage();
        }
    }

    //    列出文件
//    上面的例子列出了当前工作目录的所有文件，之后将每个路径都映射为它的字符串
//    表示。之后结果被过滤、排序，最后连接为一个字符串。
    public static void fileTest1() {
        try (Stream<Path> stream = Files.list(Paths.get(""))){
            String joined = stream
                    .map(String::valueOf)
                    .filter(Path -> !Path.startsWith("."))
                    .sorted()
                    .collect(Collectors.joining("; "));
            System.out.println("List: " + joined);
        } catch (IOException e){
            e.getMessage();
        }
    }
}
