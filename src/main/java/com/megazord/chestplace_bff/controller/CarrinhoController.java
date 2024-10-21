package com.megazord.chestplace_bff.controller;

import com.megazord.chestplace_bff.model.Carrinho;
import com.megazord.chestplace_bff.service.CarrinhoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;

@RestController
@RequestMapping("/api/carrinhos")
public class CarrinhoController {
    public CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @GetMapping("/{compradorId}")
    public ResponseEntity<Carrinho> getCarrinho(@PathVariable("compradorId") int id) {
        try {
            Carrinho carrinho = carrinhoService.buscarCarrinhoPorCompradorId(id);
            return ResponseEntity.ok(carrinho);
        } catch (RestClientResponseException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

}
