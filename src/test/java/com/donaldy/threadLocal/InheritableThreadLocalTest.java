package com.donaldy.threadLocal;

/**
 * @author donald
 * @date 2024/8/22
 */
public class InheritableThreadLocalTest {
    private static final ThreadLocal<String> THREAD_LOCAL = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        THREAD_LOCAL.set("main_thread_donald"); // 主线程保存值

        new Thread(() -> {
            // 子线程获取对应的值
            System.out.println("在子线程中获取到的本地变量的值为：" + THREAD_LOCAL.get());
        }).start();

        System.out.println("在主线程中获取到的本地变量的值为：" + THREAD_LOCAL.get());
    }
}
