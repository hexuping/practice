package com.example.model.single;

public class LazySingle {

    // volatile 1.可见性：所有线程都能看到共享内存的最新状态 2.防止指令重排
    // volatile在Java并发编程中常用于保持内存可见性和防止指令重排序
    private static volatile LazySingle lazySingle = null;

    private LazySingle() {
        System.out.println("单例模式-懒汉式");
    }

    public static LazySingle getInstance() {
        if (lazySingle == null) {
            synchronized (LazySingle.class) {
                if(lazySingle == null) {
                    lazySingle = new LazySingle();
                }
            }
        }
        return lazySingle;
    }
}
