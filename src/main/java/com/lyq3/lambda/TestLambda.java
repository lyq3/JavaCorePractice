package com.lyq3.lambda;

import com.sun.deploy.util.StringUtils;

public class TestLambda {
    public static void main(String[] args) {
        LambdaClass lc = new LambdaClass();
        lc.parmWhthLambdaInterface("参数1","参数2",(a,b) -> {
            System.out.println(a+b);
            return a;
        });

        lc.testFunctionQuote("测试方法引用",System.out :: println);//测试推测：使用方法引用，引用的方法参数和返回值因和原接口方法一致
    }
}
