package com.hph.concurrency2.chapter11;


public class ExecutionTask implements Runnable {

    private QueryFromDBAction queryAction = new QueryFromDBAction();

    private QueryFromHttpAction httpAction = new QueryFromHttpAction();

    @Override
    public void run() {
        Context context = new ActionContext().getContext();
        queryAction.execute();
        System.out.println("The name query sucessful ");
        httpAction.execute();
        System.out.println("The card id  query sucessful ");
        System.out.println("The Name is  " + context.getName() + "and CardId " + context.getCarId());

    }
}
