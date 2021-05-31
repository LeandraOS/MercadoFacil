package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.*;
import com.ufcg.psoft.mercadofacil.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CompraServiceImpl implements CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Override
    public void criaCompra() {
        Compra compra = new Compra();
        compraRepository.save(compra);

    }

    @Override
    public List<Compra> listaComprasRealizadas() {
       return compraRepository.findAll();

    }

    @Override
    public Optional<Compra> listaCompraRealizadaId(Long id) {
        return compraRepository.findById(id);
    }



    @Override
    public Compra finalizaCompra(Carrinho carrinho, String pagamento, Usuario usuario) {
        Compra compra = new Compra(carrinho, pagamento, usuario);
        BigDecimal valor = compra.calculaValor();
        compra.setValor(valor);
        compraRepository.save(compra);
        return compra;
    }


    @Override
    public BigDecimal getValorCompra(Carrinho carrinho, String pagamento, Usuario usuario){
        Compra compra = new Compra(carrinho, pagamento, usuario);
        return compra.calculaValor();
    }

    @Override
    public boolean deletaCompra(Long id){
        List<Compra> listaDeCompras = compraRepository.findAll();
        for (Compra compr: listaDeCompras){
            if(compr.getId().equals(id)){
                listaDeCompras.remove(compr);
                compraRepository.delete(compr);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deletaAllCompras() {
        compraRepository.deleteAll();
        return true;
    }


}
