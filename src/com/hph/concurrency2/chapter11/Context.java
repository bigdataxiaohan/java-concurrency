package com.hph.concurrency2.chapter11;

public class Context {

    private  String name;
    private String carId;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCarId(String carId) {
        this.carId= carId;

    }

    public String getCarId() {
        return carId;
    }
}
