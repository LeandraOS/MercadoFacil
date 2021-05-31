package com.ufcg.psoft.mercadofacil.model.TiposDePagamento;

import com.ufcg.psoft.mercadofacil.model.TiposDePagamento.Pagamento;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class PayPal extends Pagamento {

    public PayPal(Long id, BigDecimal acrescimo, BigDecimal valorTotal) {
        super(id, acrescimo, valorTotal);
    }

    public PayPal(BigDecimal acrescimo, BigDecimal valorTotal) {
        super(acrescimo, valorTotal);
    }

    public PayPal() {
    }
}
