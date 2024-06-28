package com.store.specmatic_order_bff_grpc.services

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

    @GrpcClient("order-service")
    lateinit var orderServiceGrpc: OrderServiceGrpc.OrderServiceBlockingStub

    @GrpcClient("order-service")
    lateinit var productServiceGrpc: ProductServiceGrpc.ProductServiceBlockingStub

    fun createOrder(newOrder: NewOrder): OrderId {
        val orderId = orderServiceGrpc.addOrder(
            ServiceNewOrder.newBuilder().setProductid(newOrder.productid).setCount(newOrder.count).setStatus(
                ServiceOrderStatus.PENDING
            ).build()
        )
        return OrderId.newBuilder().setId(orderId.id).build()
    }

    fun findProducts(findAvailableProductsRequest: findAvailableProductsRequest): ProductListResponse {
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
        val productId = productServiceGrpc.addProduct(
            ServiceNewProduct.newBuilder().setName(newProduct.name).setType(
                ServiceProductType.forNumber(newProduct.typeValue)
            ).setInventory(newProduct.inventory).build()
        )
        return ProductId.newBuilder().setId(productId.id).build()
    }
}