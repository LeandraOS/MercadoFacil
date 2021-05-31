package com.ufcg.psoft.mercadofacil.controller;

import com.ufcg.psoft.mercadofacil.model.Carrinho;
import com.ufcg.psoft.mercadofacil.model.Compra;
import com.ufcg.psoft.mercadofacil.model.ItensDoCarrinho;
import com.ufcg.psoft.mercadofacil.model.Usuario;
import com.ufcg.psoft.mercadofacil.repository.CarrinhoRepository;
import com.ufcg.psoft.mercadofacil.repository.PagamentoRepository;
import com.ufcg.psoft.mercadofacil.service.CarrinhoService;
import com.ufcg.psoft.mercadofacil.service.CompraService;
import com.ufcg.psoft.mercadofacil.service.PagamentoService;
import com.ufcg.psoft.mercadofacil.service.PerfilService;
import com.ufcg.psoft.mercadofacil.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PagamentoApiController {

    @Autowired
    PagamentoService pagamentoService;

    @Autowired
    PagamentoRepository pagamentoRepository;

    @Autowired
    CarrinhoService carrinhoService;

    @Autowired
    CompraService compraService;

    @Autowired
    PerfilService perfilService;

    @Autowired
    CarrinhoRepository carrinhoRepository;


    @RequestMapping(value = "/finalizarCompra/boleto", method = RequestMethod.POST)
    public ResponseEntity<?> finalizarCompraBoleto(@RequestBody String pagamento, BigDecimal saldoDisponivel, Usuario usuario, String perfil) {


        if (!pagamento.equals("BOLETO") && !pagamento.equals("PAYPAL") && !pagamento.equals("CARTAODECREDITO")) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Opção de pagamento com " + pagamento + " não está disponível!"),
                    HttpStatus.NOT_FOUND);
        }

        Carrinho carrinho = carrinhoService.exibeCarrinho();
        BigDecimal saldo = compraService.getValorCompra(carrinho, pagamento, usuario);
        BigDecimal saldoComAcrescimo = pagamentoService.calculaTotalAcrescimoPayPal(saldo);
        if (saldoDisponivel.compareTo(saldoComAcrescimo) < 0) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Saldo insuficiente!"),
                    HttpStatus.NOT_FOUND);
        }

        Compra compra = compraService.finalizaCompra(carrinho, pagamento, usuario);
        carrinhoService.finalizaCarrinho();

        return new ResponseEntity<>(saldoComAcrescimo, HttpStatus.OK);

    }


    @RequestMapping(value = "/finalizarCompra/paypal", method = RequestMethod.POST)
    public ResponseEntity<?> finalizarCompraPayPal(@RequestBody String pagamento, BigDecimal saldoDisponivel, Usuario usuario) {

        if (!pagamento.equals("BOLETO") && !pagamento.equals("PAYPAL") && !pagamento.equals("CARTAODECREDITO")) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Opção de pagamento com " + pagamento + " não está disponível!"),
                    HttpStatus.NOT_FOUND);
        }

        Carrinho carrinho = carrinhoService.exibeCarrinho();
        BigDecimal saldo = compraService.getValorCompra(carrinho, pagamento, usuario);
        BigDecimal saldoComAcrescimo = pagamentoService.calculaTotalAcrescimoPayPal(saldo);
        if (saldoDisponivel.compareTo(saldoComAcrescimo) < 0) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Saldo insuficiente!"),
                    HttpStatus.NOT_FOUND);
        }

        Compra compra = compraService.finalizaCompra(carrinho, pagamento, usuario);
        carrinhoService.finalizaCarrinho();

        return new ResponseEntity<>(saldoComAcrescimo, HttpStatus.OK);

    }

    @RequestMapping(value = "/finalizarCompra/cartaoCredito", method = RequestMethod.POST)
    public ResponseEntity<?> finalizarCompraCartaoCredito(@RequestBody String pagamento, BigDecimal saldoDisponivel, Usuario usuario) {

        if (!pagamento.equals("BOLETO") && !pagamento.equals("PAYPAL") && !pagamento.equals("CARTAODECREDITO")) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Opção de pagamento com " + pagamento + " não está disponível!"),
                    HttpStatus.NOT_FOUND);
        }

        Carrinho carrinho = carrinhoService.exibeCarrinho();
        BigDecimal saldo = compraService.getValorCompra(carrinho, pagamento, usuario);
        BigDecimal saldoComAcrescimo = pagamentoService.calculaTotalAcrescimoCartaoCredito(saldo);
        if (saldoDisponivel.compareTo(saldoComAcrescimo) < 0) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Saldo insuficiente!"),
                    HttpStatus.NOT_FOUND);
        }

        Compra compra = compraService.finalizaCompra(carrinho, pagamento, usuario);
        carrinhoService.finalizaCarrinho();

        return new ResponseEntity<>(saldoComAcrescimo, HttpStatus.OK);
    }
}
