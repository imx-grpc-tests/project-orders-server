
package com.max.grpc.orders.server;

import com.max.grpc.orders.server.food.FoodManager;

public class Main {
    public static final int DEFAULT_SERVER_PORT = 16000;
    public static final String CONFIG_PATH = "src/main/resources/server.properties";

    public static void main(String[] args) {
        var config = new ServerConfig(CONFIG_PATH);
        config.load();
        int serverPort = config.getServerPortOrDefault(DEFAULT_SERVER_PORT);

        var foodManager = new FoodManager();
        var server = new CafeServer(serverPort, foodManager);
        server.start();
        server.blockUntilShutdown();
    }
}
