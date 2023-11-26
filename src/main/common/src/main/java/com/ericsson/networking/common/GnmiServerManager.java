package com.ericsson.networking.common;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

@Service
public class GnmiServerManager {

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

    public void stopGnmiServerInstance(String address, int port) throws IOException, InterruptedException {
        String containerName = "gnmi-server-" + port; // Again, use the address in the name if you included it when starting
        String[] stopCommand = {"docker", "stop", containerName};
        String[] rmCommand = {"docker", "rm", containerName};

        executeDockerCommand(stopCommand);
        executeDockerCommand(rmCommand);
    }

    private void executeDockerCommand(String[] command) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();
        if (process.waitFor() != 0) {
            throw new IOException("Failed to execute Docker command");
        }
    }
}
