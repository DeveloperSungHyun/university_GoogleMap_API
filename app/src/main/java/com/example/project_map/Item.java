package com.example.project_map;

public class Item {

    String Name;
    double x;
    double y;
    String Type;
    String category;
    String address_1;
    String address_2;

    public Item(String Name,double x, double y, String Type, String category, String address_1, String address_2){
        this.Name = Name;
        this.x = x;
        this.y = y;
        this.Type = Type;
        this.category = category;
        this.address_1 = address_1;
        this.address_2 = address_2;

    }

    public String getName() {
        return Name;
    }

    public String getType() {
        return Type;
    }

    public String getCategory() {
        return category;
    }

    public String getAddress_1() {
        return address_1;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getAddress_2() {
        return address_2;
    }
}
