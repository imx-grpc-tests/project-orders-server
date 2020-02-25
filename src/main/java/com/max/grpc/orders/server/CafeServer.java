
package com.max.grpc.orders.server;

import com.max.grpc.orders.server.controllers.ControllersHolder;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;

public class CafeServer {
    private Logger logger = Logger.getLogger(CafeServer.class);
    private int port;
    private Server server;

    public CafeServer (int port, ControllersHolder controllers) {
        this.port = port;
        this.server = ServerBuilder.forPort(port)
            .addService(controllers.getMenuController())
            .addService(controllers.getOrdersController())
            .build();
    }

    public void start() {
        try {
            server.start();
            logger.info("Server started, listening on " + port);
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
