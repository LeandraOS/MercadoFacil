package com.ufcg.psoft.mercadofacil.model.TiposDePagamento;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public abstract class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected BigDecimal acrescimo;

    protected BigDecimal valorTotal;

    public Pagamento(Long id, BigDecimal acrescimo, BigDecimal valorTotal) {
        this.id = id;
        this.acrescimo = acrescimo;
        this.valorTotal= valorTotal;
    }

    public Pagamento(BigDecimal acrescimo, BigDecimal valorTotal) {
        this.acrescimo = acrescimo;
        this.valorTotal= valorTotal;

    }

    public Pagamento() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAcrescimo() {
        return acrescimo;
    }

    public void setAcrescimo(BigDecimal acrescimo) {
        this.acrescimo = acrescimo;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal calculaAcrescimo(BigDecimal porcentagemAcrescimo, BigDecimal valorCompra){
        BigDecimal valor = valorCompra.multiply(porcentagemAcrescimo);
        BigDecimal total = this.valorTotal.add(valor);
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(id, pagamento.id) && Objects.equals(acrescimo, pagamento.acrescimo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, acrescimo);
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "id=" + id +
                ", acrescimo=" + acrescimo +
                '}';
    }
}
