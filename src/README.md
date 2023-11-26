# Source Code Directory

Welcome to the `src` directory of the GNMI Microservice for Network Device Management. This directory contains all the source code necessary to build and run the microservice.

## Structure

The `src` directory is organized as follows:

- `main/java/`: Contains the Java source files for the microservice.
  - `com/ericsson/networkdevice/`: The core package for network device management functionality.
  - `com/ericsson/gnmi/`: Contains implementations for gNMI client communications.
- `main/resources/`: Includes various resources such as property files and configuration files.
- `test/`: Contains all test cases for unit and integration testing.

## Building the Project

To build the project, navigate to the root directory and run the following command:

`gradlew build`

This command will compile the Java files, run tests, and generate the necessary build artifacts.

## Running Tests

To run the automated tests for the project, execute:

`gradlew test`

This will run all tests located in the test directory and provide a report upon completion.

