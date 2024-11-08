package com.megazord.chestplace_bff.helpers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientResponseException;

public class ResponseEntityHelper {
    
    public static ResponseEntity<String> getResponseEntityByException(RestClientResponseException e) {
        return ResponseEntity
            .status(e.getStatusCode())
            .body(e.getResponseBodyAsString());
    }

}
