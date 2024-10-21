package com.megazord.chestplace_bff.controller;

import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

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
        String getUrl = URL + compradorId;

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(getUrl, String.class);
            return ResponseEntity
                .status(response.getStatusCode())
                .headers(response.getHeaders())
                .body(response.getBody());
        } catch(RestClientResponseException e) {
            return ResponseEntity
                .status(e.getStatusCode())
                .body(e.getResponseBodyAsString());
        }
    }

    @PostMapping()
    public ResponseEntity<String> postCarrinho(
        @RequestBody Map<String, Object> carrinho
    ) {
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(URL, carrinho, String.class);
            return ResponseEntity
                .status(response.getStatusCode())
                .headers(response.getHeaders())
                .body(response.getBody());
        } catch(RestClientResponseException e) {
            return ResponseEntity
                .status(e.getStatusCode())
                .body(e.getResponseBodyAsString());
        }
    } 

}
