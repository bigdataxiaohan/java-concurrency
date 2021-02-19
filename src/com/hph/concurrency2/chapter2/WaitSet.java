package com.hph.concurrency2.chapter2;


import java.util.Optional;
import java.util.stream.IntStream;

public class WaitSet {

    /**
     * 1.所有的对象都会有一个waiteset,用来存放该对象的wait方法 之后进入block状态线程
     * 2.线程被notify之后不一定立即得到执行 ()
     * 3.
     *
     *
     */

    private static final Object LOCK = new Object();


    public static void main(String[] args) {
        IntStream.rangeClosed(1,10)
                .forEach( i-> {
                    new Thread(String.valueOf(i)){
                        @Override
                        public void run() {
                            synchronized (LOCK){
                                try {
                                    Optional.of(Thread.currentThread().getName()+" will come  to wait to set.").ifPresent(System.out::println);
                                    //BLOCK状态
                                    LOCK.wait();

                                    Optional.of(Thread.currentThread().getName() +"will leave to  waite to set ").ifPresent(System.out::println);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                    }.start();
                } );
    }
}
