package com.store.order.bff

import `in`.specmatic.grpc.junit.SpecmaticGrpcContractTest
import `in`.specmatic.grpc.stub.GrpcStub
import `in`.specmatic.grpc.utils.HOST
import `in`.specmatic.grpc.utils.PORT
import `in`.specmatic.grpc.utils.SPECMATIC_GENERATIVE_TESTS
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ContractTest : SpecmaticGrpcContractTest {
    companion object {
        private var grpcStub: GrpcStub? = null

        @JvmStatic
        @BeforeAll
        fun setup() {
            System.setProperty(HOST, "localhost")
            System.setProperty(PORT, "8080")
            System.setProperty(SPECMATIC_GENERATIVE_TESTS, "true")
            grpcStub = GrpcStub.createGrpcStub(9090)
            grpcStub?.start()
        }

        @JvmStatic
        @AfterAll
        fun tearDown() {
            grpcStub?.stop()
        }
    }
}
