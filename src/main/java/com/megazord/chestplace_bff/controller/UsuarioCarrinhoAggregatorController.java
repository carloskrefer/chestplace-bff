package com.megazord.chestplace_bff.controller;

import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.megazord.chestplace_bff.helpers.ResponseEntityHelper;

@RestController
@RequestMapping("/api/aggregate/usuario-carrinho")
public class UsuarioCarrinhoAggregatorController {
    private String CARRINHO_URL = "https://chestplace-carrinho.azurewebsites.net/api/carrinhos/";
    private String USUARIO_URL = "https://chestplace-egceafa0bge8d9bz.brazilsouth-01.azurewebsites.net/users/";
    private RestTemplate restTemplate;

    public UsuarioCarrinhoAggregatorController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/{compradorId}")
    public ResponseEntity<String> getCarrinhoEUsuario(@PathVariable int compradorId) {
        try {
            ResponseEntity<String> carrinhoResponse = restTemplate.getForEntity(
                CARRINHO_URL + compradorId, 
                String.class
            );

            ResponseEntity<String> usuarioResponse = restTemplate.getForEntity(
                USUARIO_URL + compradorId, 
                String.class
            );

            JSONObject usuarioJson = new JSONObject(usuarioResponse.getBody());
            JSONObject carrinhoJson = new JSONObject(carrinhoResponse.getBody());
            carrinhoJson.remove("compradorId");
            carrinhoJson.remove("_id");
            usuarioJson.put("carrinho", carrinhoJson);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(usuarioJson.toString());

        } catch (RestClientResponseException e) {
            return ResponseEntityHelper.getResponseEntityByException(e);
        } catch (Exception e) {
            return ResponseEntityHelper.getGenericServerErrorResponse(e);
        }
    }

}
