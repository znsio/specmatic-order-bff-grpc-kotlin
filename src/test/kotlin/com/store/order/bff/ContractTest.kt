package com.store.order.bff

import io.specmatic.grpc.junit.SpecmaticGrpcContractTest
import io.specmatic.grpc.stub.GrpcStub
import io.specmatic.grpc.utils.HOST
import io.specmatic.grpc.utils.PORT
import io.specmatic.grpc.utils.SPECMATIC_GENERATIVE_TESTS
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
            System.setProperty(PORT, "8085")
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
