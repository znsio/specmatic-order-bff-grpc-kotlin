package com.store.specmatic_order_bff_grpc.handlers

import build.buf.protovalidate.exceptions.ValidationException
import io.grpc.Status
import net.devh.boot.grpc.server.advice.GrpcAdvice
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler

@GrpcAdvice
class GlobalGrpcExceptionHandler {

    @GrpcExceptionHandler(ValidationException::class)
    fun handleValidationException(exception: ValidationException): Status {
        return Status.INVALID_ARGUMENT.withDescription(exception.message).withCause(exception)
    }

    @GrpcExceptionHandler(Exception::class)
    fun handleException(exception: Exception): Status {
        return Status.INTERNAL.withDescription(exception.message).withCause(exception)
    }
}