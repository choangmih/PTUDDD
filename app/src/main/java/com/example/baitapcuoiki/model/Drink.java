package com.example.baitapcuoiki.model;

public class Drink {
    private String name;
    private double price;

    // 🔧 Constructor mặc định bắt buộc cho Firebase
    public Drink() {
    }

    // 🔧 Constructor có tham số
    public Drink(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // 🔧 Getter và Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
