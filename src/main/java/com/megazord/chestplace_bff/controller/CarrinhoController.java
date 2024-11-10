package com.megazord.chestplace_bff.controller;

import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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
@RequestMapping("/api/carrinhos")
public class CarrinhoController {
    private String URL = "https://chestplace-carrinho.azurewebsites.net/api/carrinhos/";
    private RestTemplate restTemplate;

    public CarrinhoController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/{compradorId}")
    public ResponseEntity<String> getCarrinho(@PathVariable int compradorId) {
        try {
            return restTemplate.getForEntity(
                URL + compradorId, 
                String.class
            );
        } catch(RestClientResponseException e) {
            return ResponseEntityHelper.getResponseEntityByException(e);
        } catch (Exception e) {
            return ResponseEntityHelper.getGenericServerErrorResponse(e);
        }
    }

    @PostMapping()
    public ResponseEntity<String> postCarrinho(
        @RequestBody Map<String, Object> carrinho
    ) {
        try {
            return restTemplate.postForEntity(
                URL, 
                carrinho, 
                String.class
            );
        } catch(RestClientResponseException e) {
            return ResponseEntityHelper.getResponseEntityByException(e);
        } catch (Exception e) {
            return ResponseEntityHelper.getGenericServerErrorResponse(e);
        }
    } 

    @PutMapping("/{compradorId}")
    public ResponseEntity<String> putCarrinho(
        @PathVariable int compradorId,
        @RequestBody Map<String, Object> carrinho
    ) {
        try {
            return restTemplate.exchange(
                URL + compradorId, 
                HttpMethod.PUT, 
                new HttpEntity<>(carrinho), 
                String.class
            );
        } catch(RestClientResponseException e) {
            return ResponseEntityHelper.getResponseEntityByException(e);
        } catch (Exception e) {
            return ResponseEntityHelper.getGenericServerErrorResponse(e);
        }
    }

    @DeleteMapping("/{compradorId}")
    public ResponseEntity<String> deleteCarrinho(@PathVariable int compradorId) {
        try {
            return restTemplate.exchange(
                URL + compradorId, 
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
