package com.store.order.bff.controllers

import com.store.order.bff.proto.*
import com.store.order.bff.services.DomainAPIService
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService

@GrpcService
class OrderService(private val domainAPIService: DomainAPIService) : OrderServiceGrpc.OrderServiceImplBase() {

    override fun findAvailableProducts(request: findAvailableProductsRequest, responseObserver: StreamObserver<ProductListResponse>) {
        val products = domainAPIService.findProducts(request)
        responseObserver.onNext(products)
        responseObserver.onCompleted()
    }

    override fun createProduct(request: NewProduct, responseObserver: StreamObserver<ProductId>) {
        val productId = domainAPIService.createProduct(request)
        responseObserver.onNext(productId)
        responseObserver.onCompleted()
    }

    override fun createOrder(request: NewOrder, responseObserver: StreamObserver<OrderId>) {
        val orderId = domainAPIService.createOrder(request)
        responseObserver.onNext(orderId)
        responseObserver.onCompleted()
    }
}
