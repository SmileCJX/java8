package pers.caijx.java8.tutorial.nashorn;

import pers.caijx.java8.tutorial.Person;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/25/025.
 */
public class nashornTest {

    public static void main(String[] args){
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        try {
            engine.eval(new FileReader("D://IdeaProjects/java8/src/pers/caijx/java8/tutorial/nashorn/script.js"));
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//        为了调用函数，你首先需要将脚本引擎转换为 Invocable 。 Invocable 接口
//        由 NashornScriptEngine 实现，并且定义了 invokeFunction 方法来调用指定
//        名称的JavaScript函数。
        Invocable invocable = (Invocable) engine;
        try {
            Object result = invocable.invokeFunction("fun1","Peter Parker");
            System.out.println(result);
            System.out.println(result.getClass());
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

//        执行这段代码会在控制台产生三行结果。调用函数 print 将结果输出
//        到 System.out ，所以我们会首先看到JavaScript输出。
        try {
            invocable.invokeFunction("fun2",new Date());
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        try {
            invocable.invokeFunction("fun2", LocalDateTime.now());
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        try {
            invocable.invokeFunction("fun2",new Person());
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
