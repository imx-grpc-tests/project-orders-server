
package com.max.grpc.orders.server.services.grpc;

import com.max.grpc.orders.server.services.ServicesHolder;

public class GrpcServicesHolder {
    private MenuGrpcServiceImpl menu;
    private OrdersGrpcServiceImpl orders;

    public GrpcServicesHolder(ServicesHolder services) {
        this.menu = new MenuGrpcServiceImpl(services.getMenuService());
        this.orders = new OrdersGrpcServiceImpl(services.getOrdersService());
    }

    public MenuGrpcServiceImpl getMenuGrpcService() {
        return menu;
    }

    public OrdersGrpcServiceImpl getOrdersGrpcService() {
        return orders;
    }
}
