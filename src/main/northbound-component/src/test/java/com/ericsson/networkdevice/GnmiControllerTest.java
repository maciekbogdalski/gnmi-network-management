package com.ericsson.networkdevice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for GnmiController.
 * It uses Spring's MockMvc to perform web requests and validate the responses for different GNMI operations.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class GnmiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Tests the GET capabilities endpoint of the GnmiController.
     * Ensures that the response status is HTTP 200 OK when requesting GNMI capabilities.
     * @throws Exception if the test encounters errors during execution.
     */
    @Test
    void whenGetCapabilities_thenStatus200() throws Exception {
        mockMvc.perform(get("/gnmi/capabilities/localhost/9359"))
                .andExpect(status().isOk());
    }
}