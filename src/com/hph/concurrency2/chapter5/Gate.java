package com.hph.concurrency2.chapter5;


/**
 * SharedResource
 * 临界值
 */
public class Gate {
    private int counter = 0;
    private String name = "Noboday";
    private String address = "Nowhere";

    /**
     * 临界值
     * 使用synchronized 关键字效率低 可以使用读写锁分离优化
     * @param name
     * @param address
     */
    public synchronized void pass(String name, String address) {
        this.counter++;
        this.name = name;
        this.address = address;

        verify();
    }

    private void verify() {
        if (this.name.charAt(0) != this.address.charAt(0)) {
            System.out.println("***************BROKEN***************" + toString());
        }
    }

    @Override
    public synchronized String toString() {
        return "No." + counter + ":" + name + "," + address;

    }
}
