
package com.max.grpc.orders.server.services;

import com.max.grpc.orders.proto.CafeMenu;
import com.max.grpc.orders.proto.FoodItem;
import com.max.grpc.orders.server.food.FoodManager;

import org.apache.log4j.Logger;

import java.util.List;

public class MenuService {
    private FoodManager foodManager;
    private final Logger logger = Logger.getLogger(MenuService.class);

    public MenuService(FoodManager foodManager) {
        this.foodManager = foodManager;
    }

    public CafeMenu getMenu() {
        logger.info("get-menu: retrieving menu...");
        FoodItem[] menu = foodManager.getMenu();
        logger.info("get-menu: forming response...");
        return CafeMenu.newBuilder().addAllItems(List.of(menu)).build();
    }
}
