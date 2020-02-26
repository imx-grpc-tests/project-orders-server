
package com.max.grpc.orders.server.food;

import com.max.grpc.orders.proto.FoodItem;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

@Mapper
public interface FoodDbMapper {
    @IterableMapping(elementTargetType = FoodItem[].class)
    FoodItem[] toProtoModel(DbFoodItem[] fromJson);
}
