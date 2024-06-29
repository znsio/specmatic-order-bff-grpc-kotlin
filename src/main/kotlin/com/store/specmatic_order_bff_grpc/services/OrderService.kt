package com.store.specmatic_order_bff_grpc.services

import build.buf.protovalidate.Validator
import build.buf.protovalidate.exceptions.ValidationException
import com.google.protobuf.GeneratedMessageV3
import com.store.bff.proto.*
import com.store.order.proto.OrderServiceGrpc
import com.store.product.proto.ProductServiceGrpc
import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.stereotype.Service
import com.store.order.proto.NewOrder as ServiceNewOrder
import com.store.order.proto.OrderStatus as ServiceOrderStatus
import com.store.product.proto.NewProduct as ServiceNewProduct
import com.store.product.proto.ProductSearchRequest as ServiceProductSearchRequest
import com.store.product.proto.ProductType as ServiceProductType

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

    fun createOrder(newOrder: NewOrder): OrderId {
        validateOrThrow(newOrder)
        val orderId = orderServiceGrpc.addOrder(
            ServiceNewOrder.newBuilder().setProductId(newOrder.productId).setCount(newOrder.count)
                .setStatus(ServiceOrderStatus.PENDING).build()
        )
        return OrderId.newBuilder().setId(orderId.id).build()
    }

    fun findProducts(findAvailableProductsRequest: findAvailableProductsRequest): ProductListResponse {
        validateOrThrow(findAvailableProductsRequest)
        val products = productServiceGrpc.searchProducts(
            ServiceProductSearchRequest.newBuilder().setType(
                ServiceProductType.forNumber(findAvailableProductsRequest.typeValue)
            ).build()
        ).productsList.map {
            Product.newBuilder().setId(it.id).setName(it.name).setInventory(it.inventory).setType(
                ProductType.forNumber(it.typeValue)
            ).build()
        }
        return ProductListResponse.newBuilder().addAllProducts(products).build()
    }

    fun createProduct(newProduct: NewProduct): ProductId {
        validateOrThrow(newProduct)
        val productId = productServiceGrpc.addProduct(
            ServiceNewProduct.newBuilder().setName(newProduct.name).setType(
                ServiceProductType.forNumber(newProduct.typeValue)
            ).setInventory(newProduct.inventory).build()
        )
        return ProductId.newBuilder().setId(productId.id).build()
    }
}