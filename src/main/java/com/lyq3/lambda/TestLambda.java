package com.lyq3.lambda;

public class TestLambda {
    public static void main(String[] args) {
        LambdaClass lc = new LambdaClass();
        lc.parmWhthLambdaInterface("参数1","参数2",(a,b) -> {
            System.out.println(a+b);
            return a;
        });
    }
}
