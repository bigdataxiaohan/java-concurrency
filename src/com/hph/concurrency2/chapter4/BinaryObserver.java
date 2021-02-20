package com.hph.concurrency2.chapter4;

public class BinaryObserver extends Observer {


    public BinaryObserver(Subject subject) {
        super(subject);
    }


    @Override
    public void update() {
        System.out.println("Binart String :" + Integer.toBinaryString(subject.getState()));
    }
}
