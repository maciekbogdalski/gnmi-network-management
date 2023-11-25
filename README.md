# GNMI Microservice for Network Device Management

This repository contains a Spring Boot microservice designed to interface with network devices using the gNMI (gRPC Network Management Interface) protocol. It facilitates the deployment and management of network configurations, streaming telemetry subscriptions, and retrieval of operational data.

## Project Scope

The microservice is tailored to:

- Deploy configurations to network devices.
- View operational data from network devices.
- Support gNMI/gRPC client functionalities.
- Integrate with Open-API for input definition.
- Import and utilize YANG models for structuring gNMI primitives.

## Features

- **Device Management**: Facilitates adding and removing network devices.
- **Subscription Management**: Manages subscriptions for streaming telemetry.
- **Configuration Management**: Retrieves and updates network device configurations.
- **Capability Discovery**: Retrieves supported models, encodings, and gNMI protocol versions from devices.

## Getting Started

Follow these instructions to set up the project on your local machine for development and testing purposes.

### Prerequisites

You need to have the following installed:

- Java JDK 11 or newer
- Gradle (compatible with the Gradle version used in the project)
- Docker (for running gNMI server instances)


### Building the Project

To get a development environment running, follow these steps:

1. Clone the repository to your local machine.
2. Navigate to the project directory.
3. Execute the following command to build the project with Gradle: `gradlew build`


4. After building the project, you can run the application with: `gradlew bootRun`


The application should now be accessible at `http://localhost:8080/swagger-ui.html`.

## Running Tests

To run the automated tests, use the following command: `gradlew test`


## Packaging the Application

To package the application into a JAR file, run: 'gradlew bootJar'


The JAR file will be generated in the `build/libs` directory.

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - The framework used to create the microservice.
* [Gradle](https://gradle.org/) - Build tool and dependency management.
* [Docker](https://www.docker.com/) - Used for running gNMI server instances in containers.


## License

This project is licensed under the MIT License - see the [src/LICENSE.md](LICENSE.md) file for details.
