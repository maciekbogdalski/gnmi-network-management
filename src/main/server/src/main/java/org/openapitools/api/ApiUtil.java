package org.openapitools.api;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiUtil {

    private static final Logger log = LoggerFactory.getLogger(ApiUtil.class);

    public static void setExampleResponse(HttpServletResponse res, String contentType, String example) {
        try {
            if (res.isCommitted()) {
                log.warn("Response already committed. Cannot set example response.");
                return;
            }

            res.setCharacterEncoding("UTF-8");

            // Check if content type is already set
            if (res.getContentType() == null) {
                res.setContentType(contentType);
            }

            res.getWriter().print(example);
        } catch (IOException e) {
            log.error("Error setting example response: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
