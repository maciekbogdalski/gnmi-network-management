# Running the Microservice

Once you have compiled and set up the GNMI Microservice, you can run it on your local machine.

## Prerequisites

- Successfully compiled and built JAR file
- Docker running if using containerized services

## Starting the Microservice

1. To start the microservice, navigate to the root directory of the project and execute:

`java -jar build/libs/NetworkDeviceManagementSystem-0.0.1-SNAPSHOT.jar`

Replace NetworkDeviceManagementSystem-0.0.1-SNAPSHOT.jar with the actual name of your compiled JAR file.

The microservice will start and be accessible at `http://localhost:8080`.

If you have a UI like Swagger configured, you can access it at `http://localhost:8080/swagger-ui.html` to interact with the API endpoints.

## Stopping the Microservice
To stop the microservice, you can simply press `Ctrl+C` in the terminal where the service is running, or if running in the background, you can stop the Java process.
