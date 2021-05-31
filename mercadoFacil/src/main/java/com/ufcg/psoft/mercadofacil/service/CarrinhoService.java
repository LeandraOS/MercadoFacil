package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Carrinho;
import com.ufcg.psoft.mercadofacil.model.ItensDoCarrinho;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.model.Usuario;

import java.math.BigDecimal;
import java.util.List;


public interface CarrinhoService {

    public void adicionaProduto(Produto produto, int quantidade);

    public boolean deletaItemCarrinho(Produto produto);

    public boolean deletaCarrinho(Long id);

    public boolean deletaAllCarrinho();

    public void finalizaCarrinho();

    public Carrinho exibeCarrinho();

    public void  criaCarrinho();

}