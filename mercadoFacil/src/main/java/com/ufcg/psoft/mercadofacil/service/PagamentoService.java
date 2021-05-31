package com.ufcg.psoft.mercadofacil.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;


public interface PagamentoService {

    BigDecimal calculaTotalAcrescimoBoleto(BigDecimal valorCompra);

    BigDecimal calculaTotalAcrescimoPayPal(BigDecimal valorCompra);

    BigDecimal calculaTotalAcrescimoCartaoCredito(BigDecimal valorCompra);

}
