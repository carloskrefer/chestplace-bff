package com.megazord.chestplace_bff.service;

import com.megazord.chestplace_bff.model.Carrinho;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CarrinhoService {

    private final RestTemplateBuilder restTemplateBuilder;

    public CarrinhoService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public Carrinho buscarCarrinhoPorCompradorId(int compradorId) {
        String url = "https://chestplace-carrinho.azurewebsites.net/api/carrinhos/" + compradorId;
        RestTemplate restTemplate = this.restTemplateBuilder.build();

        return restTemplate.getForObject(url, Carrinho.class);
    }

}
