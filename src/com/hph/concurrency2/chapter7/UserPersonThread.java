package com.hph.concurrency2.chapter7;

public class UserPersonThread extends Thread {
    private Person person;

    public UserPersonThread(Person person) {
        this.person = person;
    }

    @Override
    public void run() {

        while (true) {
            System.out.println(Thread.currentThread().getName() + " print " + person);

        }
    }
}
