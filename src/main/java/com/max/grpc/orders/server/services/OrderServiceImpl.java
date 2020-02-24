
package com.max.grpc.orders.server.services;

import com.max.grpc.orders.proto.FoodItem;
import com.max.grpc.orders.proto.Order;
import com.max.grpc.orders.proto.OrderReceipt;
import com.max.grpc.orders.proto.OrderServiceGrpc;
import com.max.grpc.orders.server.food.FoodManager;

import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderServiceImpl extends OrderServiceGrpc.OrderServiceImplBase {
    private FoodManager foodManager;

    public OrderServiceImpl(FoodManager foodManager) {
        this.foodManager = foodManager;
    }

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