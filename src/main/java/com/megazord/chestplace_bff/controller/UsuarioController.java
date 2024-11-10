package com.megazord.chestplace_bff.controller;

import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.megazord.chestplace_bff.helpers.ResponseEntityHelper;

@RestController
@RequestMapping("/api/users")
public class UsuarioController {
    private String URL = "https://chestplace-egceafa0bge8d9bz.brazilsouth-01.azurewebsites.net/users";
    private RestTemplate restTemplate;

    public UsuarioController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping()
    public ResponseEntity<String> getAllUsers() {
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                URL, 
                String.class
            );

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(responseEntity.getBody());
        } catch(RestClientResponseException e) {
            return ResponseEntityHelper.getResponseEntityByException(e);
        } catch (Exception e) {
            return ResponseEntityHelper.getGenericServerErrorResponse(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getCarrinho(@PathVariable int id) {
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                URL + "/" + id, 
                String.class
            );

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(responseEntity.getBody());
                    
        } catch(RestClientResponseException e) {
            return ResponseEntityHelper.getResponseEntityByException(e);
        } catch (Exception e) {
            return ResponseEntityHelper.getGenericServerErrorResponse(e);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> postUser(
        @RequestBody Map<String, Object> user
    ) {
        try {
            return restTemplate.postForEntity(
                URL + "/register", 
                user, 
                String.class
            );
        } catch(RestClientResponseException e) {
            return ResponseEntityHelper.getResponseEntityByException(e);
        } catch (Exception e) {
            return ResponseEntityHelper.getGenericServerErrorResponse(e);
        }
    } 

    @PutMapping("/{id}")
    public ResponseEntity<String> putCarrinho(
        @PathVariable int id,
        @RequestBody Map<String, Object> user
    ) {
        try {
            return restTemplate.exchange(
                URL + "/" + id, 
                HttpMethod.PUT, 
                new HttpEntity<>(user), 
                String.class
            );
        } catch(RestClientResponseException e) {
            return ResponseEntityHelper.getResponseEntityByException(e);
        } catch (Exception e) {
            return ResponseEntityHelper.getGenericServerErrorResponse(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCarrinho(@PathVariable int id) {
        try {
            return restTemplate.exchange(
                URL + "/" + id,
                HttpMethod.DELETE, 
                null, 
                String.class
            );
        } catch(RestClientResponseException e) {
            return ResponseEntityHelper.getResponseEntityByException(e);
        } catch (Exception e) {
            return ResponseEntityHelper.getGenericServerErrorResponse(e);
        }
    }

}
