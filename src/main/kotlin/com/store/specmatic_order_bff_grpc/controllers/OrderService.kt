package com.store.specmatic_order_bff_grpc.controllers

import build.buf.protovalidate.Validator
import build.buf.protovalidate.exceptions.ValidationException
import com.google.protobuf.GeneratedMessageV3
import com.store.bff.proto.*
import com.store.specmatic_order_bff_grpc.services.DomainAPIService
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService

@GrpcService
class OrderService(private val domainAPIService: DomainAPIService) : OrderBffServiceGrpc.OrderBffServiceImplBase() {
    private final val validator = Validator()

    private fun validateAndThrowOnFailure(request: GeneratedMessageV3?) {
        val result = validator.validate(request)
        if (result.violations.isNotEmpty()) {
            throw ValidationException(result.toString())
        }
    }

    override fun findAvailableProducts(request: findAvailableProductsRequest, responseObserver: StreamObserver<ProductListResponse>) {
        validateAndThrowOnFailure(request)
        val products = domainAPIService.findProducts(request)
        responseObserver.onNext(products)
        responseObserver.onCompleted()
    }

    override fun createProduct(request: NewProduct, responseObserver: StreamObserver<ProductId>) {
        validateAndThrowOnFailure(request)
        val productId = domainAPIService.createProduct(request)
        responseObserver.onNext(productId)
        responseObserver.onCompleted()
    }

    override fun createOrder(request: NewOrder, responseObserver: StreamObserver<OrderId>) {
        validateAndThrowOnFailure(request)
        val orderId = domainAPIService.createOrder(request)
        responseObserver.onNext(orderId)
        responseObserver.onCompleted()
    }
}