package com.ufcg.psoft.mercadofacil.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class ItensDoCarrinho {
    @OneToOne
    private Produto produto;
    private int quantidade;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public ItensDoCarrinho(Produto produto, int quantidade){
        this.produto = produto;
        this.quantidade = quantidade;
        this.id = id;
    }

    public ItensDoCarrinho(Produto produto) {
        this.quantidade = quantidade;
    }

    public ItensDoCarrinho() {
    }

    public void diminuiQuantidade(int quantidade) {
        this.quantidade--;
    }


    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ItensDoCarrinho{" +
                "produto=" + produto.getNome() +
                ", quantidade=" + quantidade +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItensDoCarrinho that = (ItensDoCarrinho) o;
        return quantidade == that.quantidade && Objects.equals(produto, that.produto) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto, quantidade, id);
    }

}