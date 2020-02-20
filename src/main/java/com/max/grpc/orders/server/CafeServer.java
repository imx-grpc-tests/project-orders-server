
package com.max.grpc.orders.server;

import com.google.protobuf.Empty;
import com.max.grpc.orders.proto.*;
import com.max.grpc.orders.server.food.FoodManager;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
                .addService(new MenuServiceImpl())
                .addService(new OrderServiceImpl())
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

    class MenuServiceImpl extends MenuServiceGrpc.MenuServiceImplBase {
        @Override
        public void getMenu(Empty request, StreamObserver<CafeMenu> responseObserver) {
            var cafeMenuBuilder = CafeMenu.newBuilder();

            FoodItem[] menu = foodManager.getMenu();
            for (FoodItem foodItem : menu) {
                cafeMenuBuilder.addItems(foodItem);
            }

            CafeMenu cafeMenu = cafeMenuBuilder.build();
            responseObserver.onNext(cafeMenu);
            responseObserver.onCompleted();
        }
    }

    class OrderServiceImpl extends OrderServiceGrpc.OrderServiceImplBase {
        @Override
        public void makeOrder(Order request, StreamObserver<OrderReceipt> responseObserver) {
            List<String> foodIds = request.getItemIdsList();
            List<FoodItem> chosenItems = new ArrayList<>();
            for (String itemId : foodIds) {
                FoodItem item = foodManager.getItemById(itemId);
                if (item != null) {
                    chosenItems.add(item);
                }
            }

            var receiptBuilder = OrderReceipt.newBuilder();
            receiptBuilder.setId(UUID.randomUUID().toString());
            receiptBuilder.setDate((long)(System.currentTimeMillis() / 1e3));
            receiptBuilder.addAllItems(chosenItems);

            int totalPrice = chosenItems.stream().mapToInt(FoodItem::getPrice).sum();
            receiptBuilder.setTotalPrice(totalPrice);

            OrderReceipt receipt = receiptBuilder.build();
            responseObserver.onNext(receipt);
            responseObserver.onCompleted();
        }
    }
}
