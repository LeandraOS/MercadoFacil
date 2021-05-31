package com.ufcg.psoft.mercadofacil.model.TiposDePagamento;

import com.ufcg.psoft.mercadofacil.model.TiposDePagamento.Pagamento;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class CartaoCredito extends Pagamento {

    public CartaoCredito(Long id, BigDecimal acrescimo, BigDecimal valorTotal) {
        super(id, acrescimo, valorTotal);
    }

    public CartaoCredito(BigDecimal acrescimo, BigDecimal valorTotal) {
        super(acrescimo, valorTotal);
    }

    public CartaoCredito() {
    }
}
