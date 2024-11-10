package com.megazord.chestplace_bff.helpers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientResponseException;

public class ResponseEntityHelper {
    
    public static ResponseEntity<String> getResponseEntityByException(RestClientResponseException e) {
        return ResponseEntity
            .status(e.getStatusCode())
            .contentType(MediaType.APPLICATION_JSON)
            .body(e.getResponseBodyAsString());
    }

    public static ResponseEntity<String> getGenericServerErrorResponse(Exception e) {
        Map<String, String> errorResponse = new HashMap<>();
        
        errorResponse.put("message", e.getMessage());
    
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .contentType(MediaType.APPLICATION_JSON)
            .body("{\"message\": \"" + e.getMessage() + "\"}");
    }

}
