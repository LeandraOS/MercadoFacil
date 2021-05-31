package com.ufcg.psoft.mercadofacil.model.Perfil;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Comum extends Perfil {

    public Comum(Long id, BigDecimal desconto) {
        super(id, desconto);
    }

    public Comum() {
    }
}
