
package com.max.grpc.orders.server.food;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.max.grpc.orders.proto.FoodItem;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class FoodManager {
    private final Logger logger = Logger.getLogger(FoodManager.class);
    private FoodItem[] foodItems;
    private ObjectMapper jsonMapper;
    private FoodDbMapperImpl foodMapper;

    public FoodManager() {
        this.foodItems = new FoodItem[0];
        this.jsonMapper = new ObjectMapper();
        this.foodMapper = new FoodDbMapperImpl();
    }

    public void loadFrom(String filepath) {
        logger.info("load-db: Loading DB from " + filepath);
        try {
            DbFoodItem[] fromJson = jsonMapper.readValue(new File(filepath), DbFoodItem[].class);
            this.foodItems = foodMapper.toProtoModel(fromJson);
            logger.info("load-db: Database loading completed, " + foodItems.length + " items available");
        }
        catch (IOException ex) {
            logger.error("load-db: failed to load food database", ex);
        }
    }

    public FoodItem[] getMenu() {
        return foodItems;
    }

    public FoodItem getItemById(String itemId) {
        var foodItem = Arrays.stream(foodItems).filter(e -> e.getId().equals(itemId)).findFirst();
        return foodItem.orElse(null);
    }
}
