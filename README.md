# Specmatic Order BFF gRPC Sample

This sample project demonstrates [Specmatic](https://specmatic.in/) **gRPC support** which allows you to use your **proto files for Contract Testing and Intelligent Service Virtualisation (stubbing/mocking)**.
[Specmatic](https://specmatic.in/) **gRPC support** can also help you use your proto files for #nocode backward compatibility testing and more.

![Specmatic gRPC Sample Project Architecture](assets/SpecmaticGRPCSupport.gif)

The **specmatic-order-bff-grpc-kotlin** is a gRPC server application built as per below proto file:
* [`src/main/proto/bff.proto`](src/main/proto/bff.proto)

This BFF project depends on the [OrderAPI domain service](https://github.com/znsio/specmatic-order-api-grpc-kotlin) which implements the following proto files:
* [`src/main/proto/order.proto`](src/main/proto/order.proto)
* [`src/main/proto/product.proto`](src/main/proto/product.proto)

NOTE: In a real-world scenario, these proto files would be on a Central Contract Repo so that we have single source of truth for all stakeholders. We have the files locally here for demo purposes.

The `ContractTest` class demonstrates how to use Specmatic to test **specmatic-order-bff-grpc-kotlin** gRPC server app using the above proto files.

## Requirements

- Java 17 or later

## Project Setup

1. Fork or clone the repository
   ```shell
   git clone https://github.com/znsio/specmatic-order-bff-grpc-kotlin
   ```

2. To run contract tests, execute
   ```shell
   ./gradlew clean test   
   ```

3. In case you want to run just the gRPC server using Gradle you can execute
   ```shell
   ./gradlew bootRun
   ```