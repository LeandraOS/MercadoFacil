package com.ufcg.psoft.mercadofacil.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity

public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<ItensDoCarrinho> listaItensCarrinho;


    private boolean finalizado;

    public Carrinho(List<ItensDoCarrinho> listaItensCarrinho) {
        this.listaItensCarrinho = listaItensCarrinho;
        this.finalizado = finalizado;
    }

    public Carrinho(){
        this.listaItensCarrinho = new ArrayList<>();
        this.finalizado = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItensDoCarrinho> getListaItensCarrinho() {
        return listaItensCarrinho;
    }

    public void setListaItensCarrinho(List<ItensDoCarrinho> listaItensCarrinho) {
        this.listaItensCarrinho = listaItensCarrinho;
    }

    public ItensDoCarrinho addItemCarrinho(Produto produto, int quantidade){
        ItensDoCarrinho item = new ItensDoCarrinho(produto, quantidade);
        listaItensCarrinho.add(item);
        return item;
    }

    public boolean isFinalizado(){
        return this.finalizado;

    }

    public void setIsFinalizado(boolean isFinalizado){
        this.finalizado = isFinalizado;
    }

    public ItensDoCarrinho getItemProduto(Produto produto){
        for (ItensDoCarrinho item: listaItensCarrinho){
            if(item.getProduto().equals(produto)){
                return item;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "Carrinho{" +
                "id=" + id +
                ", listaItensCarrinho=" + listaItensCarrinho +
                ", finalizado=" + finalizado +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carrinho carrinho = (Carrinho) o;
        return finalizado == carrinho.finalizado && Objects.equals(id, carrinho.id) && Objects.equals(listaItensCarrinho, carrinho.listaItensCarrinho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, listaItensCarrinho, finalizado);
    }
}
