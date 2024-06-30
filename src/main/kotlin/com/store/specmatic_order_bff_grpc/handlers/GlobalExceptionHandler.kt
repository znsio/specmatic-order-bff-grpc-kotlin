package com.store.specmatic_order_bff_grpc.handlers

import io.grpc.Status
import net.devh.boot.grpc.server.advice.GrpcAdvice
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler
import build.buf.protovalidate.exceptions.ValidationException
import io.grpc.StatusRuntimeException
import io.grpc.protobuf.ProtoUtils
import com.google.rpc.Status as RpcStatus

@GrpcAdvice
class GlobalGrpcExceptionHandler {
    @GrpcExceptionHandler(Exception::class)
    fun handleException(exception: Exception): Status {
        return Status.INTERNAL.withDescription(exception.message).withCause(exception)
    }

    @GrpcExceptionHandler(ValidationException::class)
    fun handleValidationException(exception: ValidationException): StatusRuntimeException? {
        val rpcStatus = RpcStatus.newBuilder()
            .setCode(Status.INVALID_ARGUMENT.code.value())
            .setMessage(exception.message ?: "Invalid argument")
            .build()

        val trailers = io.grpc.Metadata()
        trailers.put(ProtoUtils.keyForProto(RpcStatus.getDefaultInstance()), rpcStatus)

        return Status.INVALID_ARGUMENT
            .withDescription(exception.message)
            .asRuntimeException(trailers)
    }
}