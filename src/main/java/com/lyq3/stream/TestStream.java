package com.lyq3.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * JAVA8新增的：
 *  Stream是元素的集合，这点让Stream看起来用些类似Iterator；
 *  可以支持顺序和并行的对原Stream进行汇聚的操作；
 *  @author ${USER}
 */
public class TestStream {
    private static  final int NUM = 10;
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0 ; i< NUM ; i++){
            list.add(i);
        }
        long cout = list.stream().filter(m -> m > 5).count();
        System.out.println(cout);

        //2创建Stream
        //2.1 使用Stream静态方法来创建Stream
        //of方法：有两个overload方法，一个接受变长参数，一个接口单一值
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 5);
        Stream<String> stringStream = Stream.of("taobao");
        //2. generator方法：生成一个无限长度的Stream，其元素的生成是通过给定的
        // Supplier（这个接口可以看成一个对象的工厂，每次调用返回一个给定类型的对象）
         Stream.generate(Math::random);

        //3. iterate方法：也是生成无限长度的Stream，和generator不同的是，其元素的生成是重复对给定的种子值(seed)调用用户指定函数来生成的。其中包含的元素可以认为是：seed，f(seed),f(f(seed))无限循环
        Stream.iterate(1, item -> item + 1).limit(10).forEach(System.out::println);






    }
}
