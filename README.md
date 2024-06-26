# Specmatic Order BFF gRPC Sample

**specmatic-order-bff-grpc** is a gRPC server application built according to the Specmatic Order BFF specifications.<br />
The gRPC specifications are based on
the [order-bff](https://github.com/znsio/specmatic-order-contracts/blob/main/in/specmatic/examples/store/product-search-bff-api_v4.yaml)
OpenAPI specification.<br />

## Requirements
- Java 17 or later
- Specmatic order gRPC Server

## Project Setup

```Start the gRPC Server before starting the client.```

1. Fork or clone the repository
2. Generate Proto files and Install to Local Repository
    - ```bash
      mvn clean install -f proto/pom.xml
      ```
3. Build the Spring Server
    - ```bash
      mvn clean package
      ```
4. Run the server
    - ```bash
      java -jar target/specmatic-order-grpc-0.0.1-SNAPSHOT.jar
      ```
      or
    - ```bash
      mvn spring-boot:run
      ```

## Intellij Notes

If you are utilizing **IntelliJ IDEA**, you have the option to compile or install the proto module via the Maven sidebar.<br/>
Subsequently, you can initiate the Spring server through the Run/Debug menu.