package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Perfil.Comum;
import com.ufcg.psoft.mercadofacil.model.Perfil.Perfil;
import com.ufcg.psoft.mercadofacil.model.Perfil.Premium;
import com.ufcg.psoft.mercadofacil.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Locale;

@Service
public class PerfilServiceImpl implements PerfilService {

    @Autowired
    PerfilRepository perfilRepository;

    @Override
    public BigDecimal calculaTotalComum(String perfil, BigDecimal valorAcrescimo, BigDecimal  porcentagem) {
        if(perfil.equals("Comum".toUpperCase())){
            Perfil comum = new Comum();
            BigDecimal total = comum.calculaDesconto(valorAcrescimo, porcentagem);
            BigDecimal totalFinal = valorAcrescimo.subtract(total);
            comum.setValorTotal(totalFinal);
            perfilRepository.save(comum);
            return totalFinal;
        }

        return null;
    }

    @Override
    public BigDecimal calculaTotalPremium(String perfil, BigDecimal valorAcrescimo, BigDecimal  porcentagem) {
        if(perfil.equals("Premium".toUpperCase())){
            Perfil premium = new Premium();
            BigDecimal total = premium.calculaDesconto(valorAcrescimo, porcentagem);
            BigDecimal totalFinal = valorAcrescimo.subtract(total);
            premium.setValorTotal(totalFinal);
            perfilRepository.save(premium);
            return totalFinal;
        }
        return null;
    }

    @Override
    public BigDecimal calculaTotalEspecial(String perfil, BigDecimal valorAcrescimo, BigDecimal  porcentagem) {
        if(perfil.equals("Especial".toUpperCase())){
            Perfil comum = new Comum();
            BigDecimal total = comum.calculaDesconto(valorAcrescimo, porcentagem);
            BigDecimal totalFinal = valorAcrescimo.subtract(total);
            comum.setValorTotal(totalFinal);
            perfilRepository.save(comum);
            return totalFinal;
        }
        return null;
    }
}
