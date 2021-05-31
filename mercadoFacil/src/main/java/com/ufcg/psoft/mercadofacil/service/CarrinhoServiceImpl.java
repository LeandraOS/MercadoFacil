package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.*;
import com.ufcg.psoft.mercadofacil.repository.CarrinhoRepository;
import com.ufcg.psoft.mercadofacil.repository.ItensDoCarrinhoRepository;
import com.ufcg.psoft.mercadofacil.repository.LoteRepository;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoServiceImpl implements CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ItensDoCarrinhoRepository carrinhoItemRepository;

    public void adicionaProduto(Produto produto, int quantidade){

        List<Carrinho> listaCarrinhos = carrinhoRepository.findAll();

        for(Carrinho carrinho: listaCarrinhos) {
            if (!carrinho.isFinalizado()) {
                if (carrinho.getItemProduto(produto) != null) {
                    ItensDoCarrinho item = carrinho.getItemProduto(produto);
                    int qtd = item.getQuantidade() + quantidade;
                    item.setQuantidade(qtd);
                    carrinhoItemRepository.save(item);
                    carrinhoRepository.save(carrinho);
                } else {
                    ItensDoCarrinho itemSalvo = carrinho.addItemCarrinho(produto, quantidade);
                    carrinhoItemRepository.save(itemSalvo);
                    carrinhoRepository.save(carrinho);
                }
            }
        }
    }

    @Override
    public Carrinho exibeCarrinho(){
        List<Carrinho> listaDeCarrinhos = carrinhoRepository.findAll();

        for(Carrinho carrinho: listaDeCarrinhos){
            if(!carrinho.isFinalizado() || carrinho.isFinalizado()){
                return carrinho;
            }
        }
        return null;
    }

    @Override
    public void criaCarrinho() {
        Carrinho carrinho = new Carrinho();
        carrinhoRepository.save(carrinho);
    }

    @Override
    public boolean deletaItemCarrinho(Produto produto) {
        List<Carrinho> listaDeCarrinhos = carrinhoRepository.findAll();

        for (Carrinho carrinho: listaDeCarrinhos){
            List<ItensDoCarrinho> listaDeItens = carrinho.getListaItensCarrinho();
            ItensDoCarrinho item = carrinho.getItemProduto(produto);
            if(item.getProduto().equals(produto)){
                listaDeItens.remove(item);
                carrinhoItemRepository.delete(item);
                carrinhoRepository.save(carrinho);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deletaCarrinho(Long id) {
        List<Carrinho> listaDeCarrinhos = carrinhoRepository.findAll();
        for (Carrinho carrinho : listaDeCarrinhos) {
            if (carrinho.getId().equals(id)) {
                listaDeCarrinhos.remove(carrinho);
                carrinhoRepository.delete(carrinho);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deletaAllCarrinho() {
        carrinhoRepository.deleteAll();
        return true;
    }

    @Override
    public void finalizaCarrinho() {
        List<Carrinho> listCarrinho = carrinhoRepository.findAll();

        for (Carrinho carrinho: listCarrinho){
            if(!carrinho.isFinalizado()){
                carrinho.setIsFinalizado(true);
                carrinhoRepository.save(carrinho);
            }
        }
    }


}