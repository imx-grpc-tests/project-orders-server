
package com.max.grpc.orders.server.controllers;

import com.max.grpc.orders.server.services.ServicesHolder;

public class ControllersHolder {
    private MenuController menu;
    private OrdersController orders;

    public ControllersHolder(ServicesHolder services) {
        this.menu = new MenuController(services.getMenuService());
        this.orders = new OrdersController(services.getOrdersService());
    }

    public MenuController getMenuController() {
        return menu;
    }

    public OrdersController getOrdersController() {
        return orders;
    }
}
