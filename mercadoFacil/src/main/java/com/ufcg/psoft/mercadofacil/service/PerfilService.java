package com.ufcg.psoft.mercadofacil.service;


import java.math.BigDecimal;

public interface PerfilService {

    BigDecimal calculaTotalComum(String perfil, BigDecimal valorAcrescimo, BigDecimal  porcentagem);
    BigDecimal calculaTotalPremium(String perfil, BigDecimal valorAcrescimo, BigDecimal  porcentagem);
    BigDecimal calculaTotalEspecial(String perfil, BigDecimal valorAcrescimo, BigDecimal  porcentagem );




}
