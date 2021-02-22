package com.hph.concurrency2.chapter11;

public class ActionContext {
    private static final ThreadLocal<Context> threadLocal = new ThreadLocal<Context>() {
        @Override
        protected Context initialValue() {
            return new Context();
        }
    };

    private static class Contextholder {
        private final static ActionContext actionContext = new ActionContext();


    }

    public static ActionContext getActionContext() {
        return Contextholder.actionContext;
    }

    public Context getContext() {
        return threadLocal.get();
    }
}
