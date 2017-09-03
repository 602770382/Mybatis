package com.lawrie.pojo;

import java.io.Serializable;

public class Product implements Serializable{
    private int id;
    private String name;
    private float price;
    private Category category;

    public void setCategory(Category cateogory) {
        this.category = cateogory;
    }

    public Category getCategory() {

        return category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
