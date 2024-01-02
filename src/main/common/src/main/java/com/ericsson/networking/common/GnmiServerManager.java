package com.ericsson.networking.common;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Manages GNMI server instances by starting and stopping them as Docker containers.
 * This class provides methods to interact with Docker to manage GNMI server instances.
 */
@Service
public class GnmiServerManager {

    /**
     * Starts a new GNMI server instance as a Docker container.
     * @param address The IP address where the server should run.
     * @param port The port on which the server should be exposed.
     * @return The address and port as a string indicating where the server is running.
     * @throws IOException If starting the GNMI server container fails.
     * @throws InterruptedException If the process is interrupted while waiting for the command to complete.
     */
    public String startNewGnmiServerInstance(String address, int port) throws IOException, InterruptedException {
        String containerName = "gnmi-server-" + port; // You might want to include the address in the name if necessary
        String[] command = {
                "docker", "run", "-d",
                "-p", port + ":9339",
                "--name", containerName,
                "diagnostic_gnmi", "/gnmi_target", "-notls"
        };

        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        // Error output should be read to understand issues if the command fails
        if (process.waitFor() != 0) {
            throw new IOException("Failed to start GNMI server container");
        }

        // Assuming you want to return the port to which the server is bound
        return address + ":" + port;
    }

    /**
     * Stops a running GNMI server instance and removes its Docker container.
     * @param address The IP address of the server (currently not used in the command).
     * @param port The port on which the server is running.
     * @throws IOException If executing Docker commands fails.
     * @throws InterruptedException If the process is interrupted while waiting for the command to complete.
     */
    public void stopGnmiServerInstance(String address, int port) throws IOException, InterruptedException {
        String containerName = "gnmi-server-" + port; // Again, use the address in the name if you included it when starting
        String[] stopCommand = {"docker", "stop", containerName};
        String[] rmCommand = {"docker", "rm", containerName};

        executeDockerCommand(stopCommand);
        executeDockerCommand(rmCommand);
    }

    /**
     * Helper method to execute Docker commands.
     * @param command The Docker command to be executed.
     * @throws IOException If executing the Docker command fails.
     * @throws InterruptedException If the process is interrupted while waiting for the command to complete.
     */
    private void executeDockerCommand(String[] command) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();
        if (process.waitFor() != 0) {
            throw new IOException("Failed to execute Docker command");
        }
    }
}
