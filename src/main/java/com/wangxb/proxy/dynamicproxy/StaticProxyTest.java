package com.wangxb.proxy.dynamicproxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 静态代理示例
 * @author wangxb
 * @version 1.0
 * @date 2019-9-10 16:07
 **/
public class StaticProxyTest {
    @Test
    public void testStaticProxy() {
        //声明一个被代理类
        //UserManager userManager = new UserManagerImpl();
        UserManager userManagerProxy = (UserManager) new DynamicProxyFactory().getProxyInstance(new UserManagerImpl());
        userManagerProxy.addUser("1000", "张珊");
        userManagerProxy.delUser("1000");
        userManagerProxy.findUser("1000");
        userManagerProxy.modifyUser("1000", "张三");


    }
}

interface UserManager {
    //添加用户
    void addUser(String userId,String username);
    //删除用户
    void delUser(String userId);
    //查找用户
    String findUser(String userId);
    //修改用户
    void modifyUser(String userId, String username);
}

//声明原始类（被代理类）
class UserManagerImpl implements UserManager {

    public void addUser(String userId, String username) {
        System.out.println(this.getClass().getSimpleName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    public void delUser(String userId) {
        System.out.println(this.getClass().getSimpleName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    public String findUser(String userId) {
        System.out.println(this.getClass().getSimpleName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName());
        return this.getClass().getSimpleName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName();
    }

    public void modifyUser(String userId, String username) {
        System.out.println(this.getClass().getSimpleName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}

//声明一个创建代理对象的类,需要实现InvocationHandler接口，被代理对象在invoke方法中执行
class DynamicProxyFactory implements InvocationHandler {

    //声明原始类（被代理的类）
    private Object targetObject;

    //获取动态代理生成的代理类
    public Object getProxyInstance(Object object) {
        // 绑定被代理类
        // 动态代理生成的类和原始类结构相同
        this.targetObject = object;
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),
                object.getClass().getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //对原方法调用前处理日志信息
        System.out.println("执行方法前");

        //调用被代理类方法
        Object obj = method.invoke(targetObject, args);

        //调用原方法后处理日志信息
        System.out.println("执行方法后");
        return obj;
    }
}

