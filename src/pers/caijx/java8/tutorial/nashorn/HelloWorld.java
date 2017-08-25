package pers.caijx.java8.tutorial.nashorn;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by Administrator on 2017/8/25/025.
 */
public class HelloWorld {

    public static void main(String[] args){
//        JavaScript代码既可以通过传递JavaScript代码字符串，也可以传递指向你的JS脚
//        本文件的 FileReader 来执行：
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        try {
            engine.eval("print('hello world!');");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        try {
            engine.eval(new FileReader("D://IdeaProjects/java8/src/pers/caijx/java8/tutorial/nashorn/test.js"));
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
