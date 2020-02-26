
package com.max.grpc.orders.server.services.grpc;

import com.max.grpc.orders.proto.Order;
import com.max.grpc.orders.proto.OrderReceipt;
import com.max.grpc.orders.proto.OrderServiceGrpc;
import com.max.grpc.orders.server.services.OrdersService;

import io.grpc.stub.StreamObserver;
import org.apache.log4j.Logger;

public class OrdersGrpcServiceImpl extends OrderServiceGrpc.OrderServiceImplBase {
    private OrdersService ordersService;
    private final Logger logger = Logger.getLogger(OrdersGrpcServiceImpl.class);

    public OrdersGrpcServiceImpl(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @Override
    public void makeOrder(Order request, StreamObserver<OrderReceipt> responseObserver) {
        logger.info("make-order: incoming request");
        OrderReceipt receipt = ordersService.makeOrder(request);

        logger.info("make-order: sending response...");
        responseObserver.onNext(receipt);
        responseObserver.onCompleted();
    }
}