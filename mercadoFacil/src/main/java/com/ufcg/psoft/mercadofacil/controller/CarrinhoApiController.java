package com.ufcg.psoft.mercadofacil.controller;

import com.ufcg.psoft.mercadofacil.model.*;
import com.ufcg.psoft.mercadofacil.repository.CarrinhoRepository;
import com.ufcg.psoft.mercadofacil.repository.CompraRepository;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;
import com.ufcg.psoft.mercadofacil.repository.UsuarioRepository;
import com.ufcg.psoft.mercadofacil.service.CarrinhoService;
import com.ufcg.psoft.mercadofacil.service.CompraService;
import com.ufcg.psoft.mercadofacil.service.UsuarioService;
import com.ufcg.psoft.mercadofacil.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class CarrinhoApiController {

    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    private CompraService compraService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "/addProduto", method = RequestMethod.POST)
    public ResponseEntity<?> adicionarProdutos(@RequestBody Long idProduto, Long idCarrinho, int quantidade) {

        Optional<Produto> optProduto = produtoRepository.findById(idProduto);

        if (!optProduto.isPresent()) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Produto with id " + idProduto + " not found"),
                    HttpStatus.NOT_FOUND);
        }

        Optional<Carrinho> optionalCarrinho = carrinhoRepository.findById(idCarrinho);

        Produto produtoOpt = optProduto.get();

        if (!optionalCarrinho.isPresent()) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Carrinho with id " + idCarrinho + " not found"),
                    HttpStatus.NOT_FOUND);
        }
        if (produtoOpt.isDisponivel()) {
            System.out.println(produtoOpt.isDisponivel());
            carrinhoService.adicionaProduto(produtoOpt, quantidade);
        }
        return new ResponseEntity<>(carrinhoService.exibeCarrinho(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/criaCarrinho", method = RequestMethod.POST)
    public ResponseEntity<?> criaCarrinho(@RequestBody Long idProduto, int quantidade, Usuario usuario) {

        Optional<Produto> optProduto = produtoRepository.findById(idProduto);

        if (!optProduto.isPresent()) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Produto with id " + idProduto + " not found"),
                    HttpStatus.NOT_FOUND);
        }
        Produto produtoOpt = optProduto.get();

        if (!produtoOpt.isDisponivel()) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Produto with id " + idProduto + " not found"),
                    HttpStatus.NOT_FOUND);
        }

        carrinhoService.criaCarrinho();
        carrinhoService.adicionaProduto(produtoOpt, quantidade);


        return new ResponseEntity<>(carrinhoService.exibeCarrinho(), HttpStatus.CREATED);
    }



    @RequestMapping(value = "/itemCarrinho", method = RequestMethod.DELETE)
    public ResponseEntity<?> itemCarrinho(@RequestBody Produto produto) {

        boolean resp = carrinhoService.deletaItemCarrinho(produto);

        if (!resp) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Produto  " + produto + " não pode ser encontrado!"),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(carrinhoService.exibeCarrinho(), HttpStatus.OK);
    }

    @RequestMapping(value = "/deletaCarrinho", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletaCarrinho(@RequestBody Long id) {

        boolean resp = carrinhoService.deletaCarrinho(id);

        if (!resp) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Carrinho com o id  " + id + " não pode ser encontrado!"),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(carrinhoService.exibeCarrinho(), HttpStatus.OK);
    }

    @RequestMapping(value = "/deletaCarrinhos", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletaAllCarrinhos() {

        compraService.deletaAllCompras();
        carrinhoService.deletaAllCarrinho();

        return new ResponseEntity<>(carrinhoService.exibeCarrinho(), HttpStatus.OK);
    }

    @RequestMapping(value = "/listaProdutos", method = RequestMethod.GET)
    public ResponseEntity<?> listarItens() {

        List<Carrinho> carrinho = carrinhoRepository.findAll();

        if (carrinho.isEmpty()) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Não há produtos registrados no carrinho!"),
                    HttpStatus.NO_CONTENT);
        }

        Carrinho itensDoCarrinhos = carrinhoService.exibeCarrinho();

        return new ResponseEntity<>(itensDoCarrinhos, HttpStatus.OK);
    }

}