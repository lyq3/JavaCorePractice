package com.lyq3.lambda;

public class LambdaClass {
    /**
     * 以函数式接口为参数的方法
     * @param a
     * @param b
     * @param lam
     */
    public void parmWhthLambdaInterface(String a, String b,LambdaInterface lam){
        String str= lam.one(a,b);
        System.out.println(str);
    }
}
