package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.TiposDePagamento.Boleto;
import com.ufcg.psoft.mercadofacil.model.TiposDePagamento.CartaoCredito;
import com.ufcg.psoft.mercadofacil.model.TiposDePagamento.Pagamento;
import com.ufcg.psoft.mercadofacil.model.TiposDePagamento.PayPal;
import com.ufcg.psoft.mercadofacil.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PagamentoServiceImpl implements PagamentoService{
    @Autowired
    PagamentoRepository pagamentoRepository;

    @Override
    public BigDecimal calculaTotalAcrescimoBoleto( BigDecimal valorCompra) {
        Pagamento boleto = new Boleto(valorCompra, new BigDecimal(0));
        BigDecimal total = boleto.calculaAcrescimo(new BigDecimal(0), valorCompra);
        BigDecimal totalFinal = total.add(valorCompra);
        boleto.setValorTotal(totalFinal);
        pagamentoRepository.save(boleto);
        return totalFinal;
    }

    @Override
    public BigDecimal calculaTotalAcrescimoPayPal( BigDecimal valorCompra) {
        Pagamento paypal = new PayPal(valorCompra, new BigDecimal(0.02));
        BigDecimal total = paypal.calculaAcrescimo(new BigDecimal(0.02),valorCompra);
        BigDecimal totalFinal = total.add(valorCompra);
        paypal.setValorTotal(totalFinal);
        pagamentoRepository.save(paypal);
        return totalFinal;
    }

    @Override
    public BigDecimal calculaTotalAcrescimoCartaoCredito( BigDecimal valorCompra) {
        Pagamento cartaoCredito = new CartaoCredito(valorCompra, new BigDecimal(0.05));
        BigDecimal total = cartaoCredito.calculaAcrescimo(new BigDecimal(0.05),valorCompra);
        BigDecimal totalFinal = total.add(valorCompra);
        cartaoCredito.setValorTotal(totalFinal);
        pagamentoRepository.save(cartaoCredito);
        return totalFinal;
    }




}
