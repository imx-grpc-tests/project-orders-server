
package com.max.grpc.orders.server.services;

import com.max.grpc.orders.proto.CafeMenu;
import com.max.grpc.orders.proto.FoodItem;
import com.max.grpc.orders.server.food.FoodManager;

import java.util.List;

public class MenuService {
    private FoodManager foodManager;

    public MenuService(FoodManager foodManager) {
        this.foodManager = foodManager;
    }

    public CafeMenu getMenu() {
        FoodItem[] menu = foodManager.getMenu();
        return CafeMenu.newBuilder().addAllItems(List.of(menu)).build();
    }
}
