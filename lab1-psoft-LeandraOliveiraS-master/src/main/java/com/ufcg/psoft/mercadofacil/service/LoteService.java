package com.ufcg.psoft.mercadofacil.service;

import java.util.List;

import com.ufcg.psoft.mercadofacil.model.Lote;
import com.ufcg.psoft.mercadofacil.model.Produto;

public interface LoteService {

	public List<Lote> listarLotes();

	public void salvarLote(Lote lote);

	public Lote criaLote(int numItens, Produto produto);
}