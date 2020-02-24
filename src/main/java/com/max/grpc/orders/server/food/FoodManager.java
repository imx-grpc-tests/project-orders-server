package com.max.grpc.orders.server.food;

import com.max.grpc.orders.proto.FoodItem;
import com.max.grpc.orders.proto.FoodType;

import java.util.Arrays;
import java.util.UUID;

public class FoodManager {
    private FoodItem[] foodItems = new FoodItem[] {
        FoodItem.newBuilder()
            .setId(UUID.randomUUID().toString())
            .setTitle("Apple juice").setType(FoodType.DESSERT).setWeight(0.5f).setPrice(2).build(),
        FoodItem.newBuilder()
            .setId(UUID.randomUUID().toString())
            .setTitle("Roast chicken").setType(FoodType.MAIN_COURSE).setWeight(1).setPrice(20).build(),
        FoodItem.newBuilder()
            .setId(UUID.randomUUID().toString())
            .setTitle("Caesar salad").setType(FoodType.STARTERS).setWeight(0.3f).setPrice(3).build()
    };

    public FoodItem[] getMenu() {
        return foodItems;
    }

    public FoodItem getItemById(String itemId) {
        var foodItem = Arrays.stream(foodItems).filter(e -> e.getId().equals(itemId)).findFirst();
        return foodItem.orElse(null);
    }
}
