package org.openapitools.api;

import org.openapitools.model.SubscriptionPostRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-01T14:29:09.502319800+02:00[Europe/Warsaw]")
@Controller
@RequestMapping("${openapi.northbound.base-path:}")
public class SubscriptionApiController implements SubscriptionApi {

    // ... other methods ...

    // Example of how to use NativeWebRequest in a controller method:
    /*
    @RequestMapping("/someEndpoint")
    public ResponseEntity<?> someMethod(NativeWebRequest webRequest) {
        // Use webRequest here...
    }
    */

}
