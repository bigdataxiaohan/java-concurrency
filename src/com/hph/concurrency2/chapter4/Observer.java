package com.hph.concurrency2.chapter4;

public abstract class Observer {
    protected Subject subject;


    public Observer(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);

    }

    public abstract void update();

}
