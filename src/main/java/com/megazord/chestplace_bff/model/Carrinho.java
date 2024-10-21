package com.megazord.chestplace_bff.model;

import java.util.List;

public class Carrinho {
    private int compradorId;
    private List<Produto> produtos;

    public int getCompradorId() {
        return compradorId;
    }

    public void setCompradorId(int compradorId) {
        this.compradorId = compradorId;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}