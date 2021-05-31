package com.ufcg.psoft.mercadofacil.model.TiposDePagamento;

import com.ufcg.psoft.mercadofacil.model.TiposDePagamento.Pagamento;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Boleto extends Pagamento {

    public Boleto(Long id, BigDecimal acrescimo, BigDecimal valorTotal) {
        super(id, acrescimo, valorTotal);
    }

    public Boleto(BigDecimal acrescimo, BigDecimal valorTotal) {
        super(acrescimo, valorTotal);
    }

    public Boleto() {
    }
}
