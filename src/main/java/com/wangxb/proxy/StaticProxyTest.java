package com.wangxb.proxy;

import org.junit.Test;

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
        UserManager userManagerProxy = new UserManagerImplProxy(new UserManagerImpl());
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

//声明代理类
class UserManagerImplProxy implements UserManager {

    private UserManager userManager;

    //注入被代理类
    public UserManagerImplProxy(UserManager userManager) {
        this.userManager = userManager;
    }

    public void addUser(String userId, String username) {
        //添加日志打印的功能
        System.out.println("开始添加用户");
        System.out.println(this.getClass().getSimpleName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("添加用户成功");
    }

    public void delUser(String userId) {
        System.out.println("开始删除用户");
        System.out.println(this.getClass().getSimpleName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("删除用户成功");
    }

    public String findUser(String userId) {
        System.out.println("开始查找用户");
        System.out.println(this.getClass().getSimpleName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("查找用户成功");
        return this.getClass().getSimpleName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName();
    }

    public void modifyUser(String userId, String username) {
        System.out.println("开始修改用户");
        System.out.println(this.getClass().getSimpleName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("修改用户成功");
    }
}

