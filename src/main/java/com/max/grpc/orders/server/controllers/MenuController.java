
package com.max.grpc.orders.server.controllers;

import com.google.protobuf.Empty;

import com.max.grpc.orders.proto.CafeMenu;
import com.max.grpc.orders.proto.FoodItem;
import com.max.grpc.orders.proto.MenuServiceGrpc;
import com.max.grpc.orders.server.food.FoodManager;

import io.grpc.stub.StreamObserver;

import java.util.List;

public class MenuController extends MenuServiceGrpc.MenuServiceImplBase {
    private FoodManager foodManager;

    public MenuController(FoodManager foodManager) {
        this.foodManager = foodManager;
    }

    @Override
    public void getMenu(Empty request, StreamObserver<CafeMenu> responseObserver) {
        FoodItem[] menu = foodManager.getMenu();
        CafeMenu cafeMenu = CafeMenu.newBuilder().addAllItems(List.of(menu)).build();

        responseObserver.onNext(cafeMenu);
        responseObserver.onCompleted();
    }
}