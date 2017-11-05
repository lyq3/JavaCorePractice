package com.lyq3.lambda;


import javax.swing.*;
import java.io.IOException;

public class TestLambda {
    public static void main(String[] args) throws IOException {
        LambdaClass lc = new LambdaClass();
        lc.parmWhthLambdaInterface("参数1","参数2",(a,b) -> {
            System.out.println(a+b);
            return a;
        });
        lc.testFunctionQuote("测试方法引用",System.out :: println);//测试推测：使用方法引用，引用的方法参数和返回值因和原接口方法一致

        Timer t = new Timer(1000,System.out :: println);
        t.start();
        System.in.read();
    }
}
