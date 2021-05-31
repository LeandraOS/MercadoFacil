package com.ufcg.psoft.mercadofacil.model.Perfil;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Especial extends Perfil{

    public Especial(Long id, BigDecimal desconto) {
        super(id, desconto);
    }

    public Especial() {

    }


}
