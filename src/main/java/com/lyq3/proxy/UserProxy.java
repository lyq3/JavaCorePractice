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
        System.err.println("我准备调用了");
        if (Object.class.equals(method.getDeclaringClass())) {
            try {
                // 诸如hashCode()、toString()、equals()等方法，将target指向当前对象this
//                return method.invoke(this, args);
            } catch (Throwable t) {
            }
        }
        System.out.println(this.getClass().getName());
//        result=method.invoke(this, args);
        //反射方法后调用.
        System.err.println("调用完了");
        return new UserMapperImpl();
    }

    public static void main(String[] args) {
        UserMapper user = new UserProxy().newInstance(UserMapper.class);
        System.out.println("===" + user.toString() + "123112");
        System.out.println("↑↑↑↑"+user.getClass().getName());
        user.findUserById(1);
    }
}
