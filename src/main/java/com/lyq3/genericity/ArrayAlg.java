package com.lyq3.genericity;

/**
 * 泛型类测试
 *
 *
 */
public class ArrayAlg {
    public static  <T> T getMiddle(T... a){
        return a[a.length / 2];
    }

    public static void main(String[] args) {
        ArrayAlg.<String>getMiddle("12","s","23");
    }
}
