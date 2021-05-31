package com.ufcg.psoft.mercadofacil.service;

import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.mercadofacil.DTO.ProdutoDTO;
import com.ufcg.psoft.mercadofacil.model.Produto;

public interface ProdutoService {

	public Optional<Produto> getProdutoById(long id);
	
	public List<Produto> getProdutoByCodigoBarra(String codigo);
	
	public void removerProdutoCadastrado(Produto produto);

	public void salvarProdutoCadastrado(Produto produto);

	public List<Produto> listarProdutos();
	
	public Produto criaProduto(ProdutoDTO produto);
	
	public Produto atualizaProduto(ProdutoDTO produtoDTO, Produto produto);
}
