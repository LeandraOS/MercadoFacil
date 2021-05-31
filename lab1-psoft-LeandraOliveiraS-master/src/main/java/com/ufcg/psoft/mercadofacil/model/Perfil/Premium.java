package com.ufcg.psoft.mercadofacil.model.Perfil;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Premium extends Perfil{

    public Premium(Long id, BigDecimal desconto) {
        super(id, desconto);
    }

    public Premium() {

    }
}
