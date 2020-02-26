
package com.max.grpc.orders.server.food;

import com.max.grpc.orders.proto.FoodType;

public class DbFoodItem {
    private String id;
    private String title;
    private FoodType type;
    private double weight;
    private int price;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public FoodType getType() {
        return type;
    }

    public double getWeight() {
        return weight;
    }

    public int getPrice() {
        return price;
    }
}
