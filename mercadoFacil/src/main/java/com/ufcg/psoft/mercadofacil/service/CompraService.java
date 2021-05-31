package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Carrinho;
import com.ufcg.psoft.mercadofacil.model.Compra;
import com.ufcg.psoft.mercadofacil.model.Usuario;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public interface CompraService {

    void criaCompra();

    List<Compra> listaComprasRealizadas();

    Optional<Compra> listaCompraRealizadaId(Long id);

    Compra finalizaCompra(Carrinho carrinho, String pagamento, Usuario usuario);

    BigDecimal getValorCompra(Carrinho carrinho, String pagamento, Usuario usuario);

    boolean deletaCompra(Long id);

    boolean deletaAllCompras();

}
