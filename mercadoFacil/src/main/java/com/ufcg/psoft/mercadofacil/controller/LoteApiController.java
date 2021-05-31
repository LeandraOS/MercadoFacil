package com.ufcg.psoft.mercadofacil.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.psoft.mercadofacil.model.Lote;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.service.LoteService;
import com.ufcg.psoft.mercadofacil.service.ProdutoService;
import com.ufcg.psoft.mercadofacil.util.ErroLote;
import com.ufcg.psoft.mercadofacil.util.ErroProduto;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class LoteApiController {

	@Autowired
	LoteService loteService;
	
	@Autowired
	ProdutoService produtoService;
	
	@RequestMapping(value = "/lotes", method = RequestMethod.GET)
	public ResponseEntity<?> listarLotes() {
		
		List<Lote> lotes = loteService.listarLotes();

		if (lotes.isEmpty()) {
			return ErroLote.erroSemLotesCadastrados();
		}
		
		return new ResponseEntity<List<Lote>>(lotes, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/produto/{idProduto}/lote/", method = RequestMethod.POST)
	public ResponseEntity<?> criarLote(@PathVariable("idProduto") long id, @RequestBody int numItens) {
		
		Optional<Produto> optionalProduto = produtoService.getProdutoById(id);
		
		if (!optionalProduto.isPresent()) {
			return ErroProduto.erroProdutoNaoEnconrtrado(id);
		}
		
		Produto produto = optionalProduto.get();
		Lote lote = loteService.criaLote(numItens, produto);
		
		if (!produto.isDisponivel() & (numItens > 0)) {
			produto.tornaDisponivel();
			produtoService.salvarProdutoCadastrado(produto);
		}

		loteService.salvarLote(lote);

		return new ResponseEntity<>(lote, HttpStatus.CREATED);
	}
}