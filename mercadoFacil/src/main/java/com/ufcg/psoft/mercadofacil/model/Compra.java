package com.ufcg.psoft.mercadofacil.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Carrinho carrinho;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Usuario usuario;

    private BigDecimal valor;

    private String pagamento;

    private Date data;

    private final String BOLETO = "BOLETO";
    private final String CARTAODECREDITO = "CARTAODECREDITO";
    private final String PAYPAL = "PAYPAL";

    public Compra(){

    }

    public Compra(Carrinho carrinho){
        this.carrinho = carrinho;

    }

    public Compra(Carrinho carrinho, String pagamento, Usuario usuario){
        this.carrinho = carrinho;
        this.pagamento = pagamento;
        this.usuario = usuario;
        this.data = new Date();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal calculaValor(){
        if (!this.carrinho.isFinalizado()){
            List<ItensDoCarrinho> itens = carrinho.getListaItensCarrinho();
            BigDecimal valorTotal = new BigDecimal(0);
            for (ItensDoCarrinho item: itens){
                BigDecimal valor = item.getProduto().getPreco().multiply(new BigDecimal(item.getQuantidade()));
                valorTotal = valorTotal.add(valor);

            }
            return valorTotal;
        }
        return null;
    }

    public void setPagamento(String pagamento) {
        switch (pagamento) {
            case BOLETO:
                this.pagamento = "BOLETO";
                break;
            case CARTAODECREDITO:
                this.pagamento = "CARTAODECREDITO";
                break;
            case PAYPAL:
                this.pagamento = "PAYPAL";
                break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compra compra = (Compra) o;
        return id == compra.id && carrinho.equals(compra.carrinho) && valor.equals(compra.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carrinho, valor);
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id = " + id +
                ", itensDaCompra = " + carrinho.toString()
                + "data =" + data +
                " formaDePagamento = " + pagamento +
                ", valor = " + valor +
                '}';
    }


}
