
package com.max.grpc.orders.server.controllers;

import com.max.grpc.orders.proto.Order;
import com.max.grpc.orders.proto.OrderReceipt;
import com.max.grpc.orders.proto.OrderServiceGrpc;
import com.max.grpc.orders.server.services.OrdersService;

import io.grpc.stub.StreamObserver;

public class OrdersController extends OrderServiceGrpc.OrderServiceImplBase {
    private OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @Override
    public void makeOrder(Order request, StreamObserver<OrderReceipt> responseObserver) {
        OrderReceipt receipt = ordersService.makeOrder(request);
        responseObserver.onNext(receipt);
        responseObserver.onCompleted();
    }
}