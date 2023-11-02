package com.ericsson.networkdevice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.ericsson.networkdevice" , "com.ericsson.networking" , "com.ericsson.southbound"})
public class NorthboundComponentApplication {

    public static void main(String[] args) {
        SpringApplication.run(NorthboundComponentApplication.class, args);

    }
}
