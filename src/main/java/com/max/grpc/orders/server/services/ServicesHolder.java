
package com.max.grpc.orders.server.services;

import com.max.grpc.orders.server.food.FoodManager;

public class ServicesHolder {
    private MenuService menuService;
    private OrdersService ordersService;

    public ServicesHolder(FoodManager foodManager) {
        this.menuService = new MenuService(foodManager);
        this.ordersService = new OrdersService(foodManager);
    }

    public MenuService getMenuService() {
        return menuService;
    }

    public OrdersService getOrdersService() {
        return ordersService;
    }
}
