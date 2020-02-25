
package com.max.grpc.orders.server.controllers;

import com.google.protobuf.Empty;

import com.max.grpc.orders.proto.CafeMenu;
import com.max.grpc.orders.proto.MenuServiceGrpc;
import com.max.grpc.orders.server.services.MenuService;

import io.grpc.stub.StreamObserver;

public class MenuController extends MenuServiceGrpc.MenuServiceImplBase {
    private MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public void getMenu(Empty request, StreamObserver<CafeMenu> responseObserver) {
        CafeMenu cafeMenu = menuService.getMenu();
        responseObserver.onNext(cafeMenu);
        responseObserver.onCompleted();
    }
}