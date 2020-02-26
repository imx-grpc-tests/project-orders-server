
package com.max.grpc.orders.server.services.grpc;

import com.google.protobuf.Empty;

import com.max.grpc.orders.proto.CafeMenu;
import com.max.grpc.orders.proto.MenuServiceGrpc;
import com.max.grpc.orders.server.services.MenuService;

import io.grpc.stub.StreamObserver;
import org.apache.log4j.Logger;

public class MenuGrpcServiceImpl extends MenuServiceGrpc.MenuServiceImplBase {
    private MenuService menuService;
    private final Logger logger = Logger.getLogger(MenuGrpcServiceImpl.class);

    public MenuGrpcServiceImpl(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public void getMenu(Empty request, StreamObserver<CafeMenu> responseObserver) {
        logger.info("get-menu: incoming request");
        CafeMenu cafeMenu = menuService.getMenu();

        logger.info("get-menu: sending response...");
        responseObserver.onNext(cafeMenu);
        responseObserver.onCompleted();
    }
}