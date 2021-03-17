package com.eShop.commons.bean;

public class ProductList {
    private String name;
    private String salNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalNum() {
        return salNum;
    }

    public void setSalNum(String salNum) {
        this.salNum = salNum;
    }
    @Override
    public String toString() {
        return "ProductList{" +
                "name='" + name + '\'' +
                ", salNum='" + salNum + '\'' +
                '}';
    }
}
