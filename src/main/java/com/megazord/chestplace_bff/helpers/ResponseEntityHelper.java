package com.megazord.chestplace_bff.helpers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientResponseException;

public class ResponseEntityHelper {
    
    public static ResponseEntity<String> duplicateExceptionResponse(RestClientResponseException e) {
        return ResponseEntity
            .status(e.getStatusCode())
            .body(e.getResponseBodyAsString());
    }

    public static ResponseEntity<String> duplicateSuccessResponse(ResponseEntity<String> response) {
        return ResponseEntity
            .status(response.getStatusCode())
            .headers(response.getHeaders())
            .body(response.getBody());
    }

}
