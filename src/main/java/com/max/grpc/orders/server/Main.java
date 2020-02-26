
package com.max.grpc.orders.server;

import com.max.grpc.orders.server.services.grpc.GrpcServicesHolder;
import com.max.grpc.orders.server.food.FoodManager;
import com.max.grpc.orders.server.services.ServicesHolder;

public class Main {
    public static final int DEFAULT_SERVER_PORT = 16000;
    public static final String CONFIG_PATH = "src/main/resources/server.properties";
    public static final String FOOD_DATABASE_PATH = "src/main/resources/food.json";

    public static void main(String[] args) {
        var config = new ServerConfig(CONFIG_PATH);
        config.load();
        int serverPort = config.getServerPortOrDefault(DEFAULT_SERVER_PORT);

        var food = new FoodManager();
        food.loadFrom(FOOD_DATABASE_PATH);
        var services = new ServicesHolder(food);
        var grpcServices = new GrpcServicesHolder(services);
        var cafeGrpcServer = new CafeServer(serverPort, grpcServices);

        cafeGrpcServer.start();
        cafeGrpcServer.blockUntilShutdown();
    }
}
