
package com.max.grpc.orders.server;

import com.max.grpc.orders.server.food.FoodManager;
import com.max.grpc.orders.server.controllers.MenuController;
import com.max.grpc.orders.server.controllers.OrdersController;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import org.apache.log4j.Logger;

import java.io.IOException;

public class CafeServer {
    private Logger logger = Logger.getLogger(CafeServer.class);
    private int port;
    private Server server;
    private FoodManager foodManager;

    public CafeServer (int port) {
        this(port, new FoodManager());
    }

    public CafeServer (int port, FoodManager foodManager) {
        this.port = port;
        this.foodManager = foodManager;
    }

    public void start() {
        Server server = ServerBuilder.forPort(port)
                .addService(new MenuController(foodManager))
                .addService(new OrdersController(foodManager))
                .build();
        try {
            server.start();
            logger.info("Server started, listening on " + port);
            this.server = server;
        }
        catch (IOException ex) {
            logger.fatal("Server initialization failed");
        }
    }

    public void blockUntilShutdown() {
        if (server != null) {
            try {
                server.awaitTermination();
            }
            catch (InterruptedException ex) {
                logger.fatal("Failed to block runtime until shutdown");
            }
        }
    }
}
