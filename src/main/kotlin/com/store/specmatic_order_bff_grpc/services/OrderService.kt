package com.store.specmatic_order_bff_grpc.services

import build.buf.protovalidate.Validator
import build.buf.protovalidate.exceptions.ValidationException
import com.google.protobuf.GeneratedMessageV3
import com.store.bff.proto.findAvailableProductsRequest
import com.store.order.proto.NewOrder
import com.store.order.proto.OrderId
import com.store.order.proto.OrderServiceGrpc
import com.store.product.proto.*
import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.stereotype.Service


@Service
class OrderService {

    private final val validator = Validator()

    @GrpcClient("order-service")
    lateinit var orderServiceGrpc: OrderServiceGrpc.OrderServiceBlockingStub

    @GrpcClient("order-service")
    lateinit var productServiceGrpc: ProductServiceGrpc.ProductServiceBlockingStub

    private fun validateOrThrow(request: GeneratedMessageV3?) {
        val result = validator.validate(request)
        if (result.violations.isNotEmpty()) {
            throw ValidationException(result.toString())
        }
    }

    fun createOrder(newOrder: NewOrder?): OrderId {
        validateOrThrow(newOrder)
        return orderServiceGrpc.addOrder(newOrder)
    }

    fun findProducts(findAvailableProductsRequest: findAvailableProductsRequest?): ProductListResponse {
        validateOrThrow(findAvailableProductsRequest)
        return productServiceGrpc.searchProducts(
            ProductSearchRequest.newBuilder().setType(findAvailableProductsRequest?.type).build()
        )
    }

    fun createProduct(newProduct: NewProduct?): ProductId {
        validateOrThrow(newProduct)
        return productServiceGrpc.addProduct(newProduct)
    }
}