package com.liang.human.config;

import com.liang.human.domain.User;
import com.liang.human.model.UserVO;

public class UserContext {
    /** ThreadLocal对象常用于防止对可变的单实例变量或全局变量进行共享。set和get方法为每个使用该变量的线程都保存一份独立的副本，因此，
     * get总是返回当前线程在调用set时设置的最新值。
     * 避免每次调用方法时都需要传递上下文信息（该处使用的是ThreadLocal的这个用法）。
     */
    private static ThreadLocal<User> userHolder = new ThreadLocal<>();

    public static void setUser(User user) {
        userHolder.set(user);
    }

    public static User getUser() {
        return userHolder.get();
    }

    public static void removeUser() {
        userHolder.remove();
    }
}
