package com.store.order.bff

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpecmaticOrderBffGrpcApplication

fun main(args: Array<String>) {
	runApplication<SpecmaticOrderBffGrpcApplication>(*args)
}
