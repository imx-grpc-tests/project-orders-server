
package com.max.grpc.orders.server;

import com.max.grpc.orders.server.food.FoodManager;

public class Main {
    public static void main(String[] args) {
        var foodManager = new FoodManager();
        var server = new CafeServer(8000, foodManager);
        server.start();
        server.blockUntilShutdown();
    }
}
