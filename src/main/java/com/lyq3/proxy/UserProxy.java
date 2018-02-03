package com.lyq3.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理类
 * @author 卡卢比
 */
public class UserProxy implements InvocationHandler {
    public <T> T newInstance(Class<T> clz) {
        return (T) Proxy.newProxyInstance(clz.getClassLoader(), new Class[] { clz }, this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.err.println("############我是JDK动态代理################");
        Object result = null;
        //反射方法前调用
        System.err.println("开始调用");
        if (Object.class.equals(method.getDeclaringClass())) {//判断是不是继承自Object的3个方法（toString、equals、hashcode）
                // 诸如hashCode()、toString()、equals()等方法，将target指向当前对象this
                result=method.invoke(this, args);//根据实际情况确定调用谁的方法,这里暂时调用当前类的tostring，
                System.out.println("根据实际情况确定调用谁的方法");
        } else {

            result = method.invoke(new UserMapperImpl(), args);
            //反射方法后调用.
            System.err.println("调用完了");
        }
        return result;
    }

    public static void main(String[] args) {
        UserMapper user = new UserProxy().newInstance(UserMapper.class);
        User u = user.findUserById(1);
        System.out.println(u.getId());
    }
}
