package com.ufcg.psoft.mercadofacil.controller;

import com.ufcg.psoft.mercadofacil.model.Compra;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.model.Usuario;
import com.ufcg.psoft.mercadofacil.repository.CompraRepository;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;
import com.ufcg.psoft.mercadofacil.service.CarrinhoService;
import com.ufcg.psoft.mercadofacil.service.CompraService;
import com.ufcg.psoft.mercadofacil.service.UsuarioService;
import com.ufcg.psoft.mercadofacil.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CompraApiController {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private CompraService compraService;

    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProdutoRepository produtoRepository;


    @RequestMapping(value = "/criaCompra", method = RequestMethod.POST)
    public ResponseEntity<?> criaCompra(@RequestBody Long idProduto, int quantidade) {

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
        compraService.criaCompra();
        carrinhoService.criaCarrinho();
        carrinhoService.adicionaProduto(produtoOpt, quantidade);


        return new ResponseEntity<>(carrinhoService.exibeCarrinho(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/listaUltimasCompras", method = RequestMethod.GET)
    public ResponseEntity<?> listaUltimasCompras() {

        List<Compra> compra = compraRepository.findAll();

        if (compra.isEmpty()) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Não há compras registradas!"),
                    HttpStatus.NO_CONTENT);
        }

        List<Compra> listComprasRealizadas = compraService.listaComprasRealizadas();

        return new ResponseEntity<>(listComprasRealizadas, HttpStatus.OK);
    }

    @RequestMapping(value = "/listaUltimasCompras/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> listaUltimasComprasId(@PathVariable("id") Long id) {

        Optional<Compra> listComprasRealizadasId = compraService.listaCompraRealizadaId(id);

        if (!listComprasRealizadasId.isPresent()) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Não há compras registradas!"),
                    HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>(listComprasRealizadasId, HttpStatus.OK);

    }

    @RequestMapping(value = "/deletaCompra", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletaCompra(@RequestBody Long id) {

        boolean resp = compraService.deletaCompra(id);

        if (!resp) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Compra com o id  " + id + " não pode ser encontrado!"),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(compraService.listaComprasRealizadas(), HttpStatus.OK);
    }

    @RequestMapping(value = "/deletaCompras", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletaAllCompras() {

        compraService.deletaAllCompras();
        carrinhoService.deletaAllCarrinho();

        return new ResponseEntity<>(compraService.listaComprasRealizadas(), HttpStatus.OK);
    }
}
