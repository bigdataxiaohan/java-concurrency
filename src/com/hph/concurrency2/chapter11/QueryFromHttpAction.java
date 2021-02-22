package com.hph.concurrency2.chapter11;

public class QueryFromHttpAction {
    public void execute() {
        try {
            Context context = ActionContext.getActionContext().getContext();
            Thread.sleep(1_000);
            String name = "Alex";
            context.setName(name);
            String cardId = getCardId(name);
            context.setCarId(cardId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getCardId(String name) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return "4545646456455464" + Thread.currentThread().getName();
    }


}
