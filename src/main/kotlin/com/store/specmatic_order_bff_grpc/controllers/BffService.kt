package com.store.specmatic_order_bff_grpc.controllers

import com.store.bff.proto.OrderBffServiceGrpc
import com.store.bff.proto.findAvailableProductsRequest
import com.store.order.proto.NewOrder
import com.store.order.proto.OrderId
import com.store.product.proto.NewProduct
import com.store.product.proto.ProductId
import com.store.product.proto.ProductListResponse
import com.store.specmatic_order_bff_grpc.services.OrderService
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService


@GrpcService
class OrderBffService(private val orderService: OrderService): OrderBffServiceGrpc.OrderBffServiceImplBase() {
    override fun findAvailableProducts(
        request: findAvailableProductsRequest?, responseObserver: StreamObserver<ProductListResponse>?
    ) {
        val products = orderService.findProducts(request)
        responseObserver?.onNext(products)
        responseObserver?.onCompleted()
    }

    override fun createProduct(request: NewProduct?, responseObserver: StreamObserver<ProductId>?) {
        val productId = orderService.createProduct(request)
        responseObserver?.onNext(productId)
        responseObserver?.onCompleted()
    }

    override fun createOrder(request: NewOrder?, responseObserver: StreamObserver<OrderId>?) {
        val orderId = orderService.createOrder(request)
        responseObserver?.onNext(orderId)
        responseObserver?.onCompleted()
    }
}