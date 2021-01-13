package com.hph.concurrency.chapter1;

public class TemplateMethod {

    //定义final 子类无法覆写
    //模板方法的技巧
    public final void print(String message) {
        System.out.println("######################");
        wrapPint(message);
        System.out.println("######################");
    }

    protected void wrapPint(String message) {
    }

    public static void main(String[] args) {

        TemplateMethod t1 = new TemplateMethod() {
            @Override
            protected void wrapPint(String message) {
                System.out.println("*" + message + "*");
            }
        };

        t1.print("Hello Thread");

        TemplateMethod t2 = new TemplateMethod() {
            @Override
            protected void wrapPint(String message) {
                System.out.println("+" + message + "+");
            }
        };

        t2.print("Hello Thread");
    }
}
