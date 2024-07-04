# Specmatic Order BFF gRPC Sample

This sample project demonstrates [Specmatic](https://specmatic.in/) **gRPC support** which allows you to use your **proto files for Contract Testing and Intelligent Service Virtualisation (stubbing/mocking)**.
[Specmatic](https://specmatic.in/) **gRPC support** can also help you use your proto files for #nocode backward compatibility testing and more.

![Specmatic gRPC Sample Project Architecture](assets/SpecmaticGRPCSupport.gif)

The **specmatic-order-bff-grpc-kotlin** is a gRPC server application developed according to the following proto file:
* [bff.proto](https://github.com/znsio/specmatic-order-contracts/blob/grpc-contracts/in/specmatic/examples/store/order_bff_grpc/bff.proto)

This BFF project relies on the [OrderAPI domain service](https://github.com/znsio/specmatic-order-api-grpc-kotlin) which implements the following proto files:
* [order.proto](https://github.com/znsio/specmatic-order-contracts/blob/grpc-contracts/in/specmatic/examples/store/order_api_grpc/order.proto)
* [product.proto](https://github.com/znsio/specmatic-order-contracts/blob/grpc-contracts/in/specmatic/examples/store/order_api_grpc/product.proto)

The `ContractTest` class demonstrates how to use Specmatic to test **specmatic-order-bff-grpc-kotlin** gRPC server app using the above proto files.

## Requirements

- Java 17 or later

## Project Setup

1. Fork or clone the repository
   ```shell
   git clone https://github.com/znsio/specmatic-order-bff-grpc-kotlin
   ```
2. Initialize and update the contract-repo submodule
   ```shell
   git submodule update --init --recursive --remote
   ```
3. To run contract tests, execute
   ```shell
   ./gradlew clean test   
   ```

4. If you intend to only run the gRPC server using Gradle, you can execute
   ```shell
   ./gradlew bootRun
   ```