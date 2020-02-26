
package com.max.grpc.orders.server.services;

import com.max.grpc.orders.proto.FoodItem;
import com.max.grpc.orders.proto.Order;
import com.max.grpc.orders.proto.OrderReceipt;
import com.max.grpc.orders.server.food.FoodManager;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrdersService {
    private FoodManager foodManager;
    private final Logger logger = Logger.getLogger(OrdersService.class);

    public OrdersService(FoodManager foodManager) {
        this.foodManager = foodManager;
    }

    public OrderReceipt makeOrder(Order order) {
        logger.info("make-order: creating order...");
        List<String> foodIds = order.getItemIdsList();

        List<FoodItem> chosenItems = new ArrayList<>();
        for (String itemId : foodIds) {
            FoodItem item = foodManager.getItemById(itemId);
            if (item != null) {
                chosenItems.add(item);
            }
        }

        logger.info("make-order: forming order receipt...");
        var receiptBuilder = OrderReceipt.newBuilder();
        receiptBuilder.setId(UUID.randomUUID().toString());

        long currentTimeSeconds = System.currentTimeMillis() / 1000;
        receiptBuilder.setDate(currentTimeSeconds);

        receiptBuilder.addAllItems(chosenItems);

        int totalPrice = chosenItems.stream().mapToInt(FoodItem::getPrice).sum();
        receiptBuilder.setTotalPrice(totalPrice);

        return receiptBuilder.build();
    }
}
