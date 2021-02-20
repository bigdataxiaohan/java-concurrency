package com.hph.concurrency2.chapter5;

public class Client {
    public static void main(String[] args) {

        Gate gate = new Gate();
        User bj = new User("Baobao", "BeiJing", gate);
        User sh = new User("ShangLao", "Shanghai", gate);
        User gz = new User("GuangLao", "GuangZhou", gate);

        bj.start();
        sh.start();
        gz.start();
    }
}
