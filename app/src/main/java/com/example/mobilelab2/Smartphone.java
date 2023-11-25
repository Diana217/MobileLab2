package com.example.mobilelab2;

public class Smartphone {
    private int id;
    private String producer;
    private String model;
    private double screen_diagonal;
    private String address;
    private double address_longitude;
    private double address_latitude;
    private double price;

    public Smartphone(int id, String producer, String model, double screen_diagonal, String address, double address_longitude, double address_latitude, double price) {
        this.id = id;
        this.producer = producer;
        this.model = model;
        this.screen_diagonal = screen_diagonal;
        this.address = address;
        this.address_longitude = address_longitude;
        this.address_latitude = address_latitude;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Smartphone{" +
                "id=" + id +
                ", producer='" + producer + '\'' +
                ", model='" + model + '\'' +
                ", screen_diagonal=" + screen_diagonal +
                ", address='" + address + '\'' +
                ", address_longitude=" + address_longitude +
                ", address_latitude=" + address_latitude +
                ", price=" + price +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getScreen_diagonal() {
        return screen_diagonal;
    }

    public void setScreen_diagonal(double screen_diagonal) {
        this.screen_diagonal = screen_diagonal;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getAddress_longitude() {
        return address_longitude;
    }

    public void setAddress_longitude(double address_longitude) {
        this.address_longitude = address_longitude;
    }

    public double getAddress_latitude() {
        return address_latitude;
    }

    public void setAddress_latitude(double address_latitude) {
        this.address_latitude = address_latitude;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
