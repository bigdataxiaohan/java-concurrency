package com.hph.concurrency2.chapter11;

public class QueryFromDBAction {
    public void execute() {
        try {
            Thread.sleep(1_000);
            String name = "Alex"+Thread.currentThread().getName();
            ActionContext.getActionContext().getContext().setName(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
