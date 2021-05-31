package com.ufcg.psoft.mercadofacil.controller;

import com.ufcg.psoft.mercadofacil.DTO.ProdutoDTO;
import com.ufcg.psoft.mercadofacil.model.Perfil.Perfil;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.model.Usuario;
import com.ufcg.psoft.mercadofacil.repository.UsuarioRepository;
import com.ufcg.psoft.mercadofacil.service.CarrinhoService;
import com.ufcg.psoft.mercadofacil.service.CompraService;
import com.ufcg.psoft.mercadofacil.service.UsuarioService;
import com.ufcg.psoft.mercadofacil.util.CustomErrorType;
import com.ufcg.psoft.mercadofacil.util.ErroProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UsuarioApiController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CompraService compraService;

    @Autowired
    CarrinhoService carrinhoService;

    @RequestMapping(value = "/criaUsuario", method = RequestMethod.POST)
    public ResponseEntity<?> criaUsuario(@RequestBody Long idUsuario, String cpf, String nome, String perfil){

        usuarioService.criaUsuario(idUsuario, cpf, nome, perfil );

        return new ResponseEntity<>(usuarioService.listaUsuarios(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/listaUsuarios", method = RequestMethod.GET)
    public ResponseEntity<?> listarUsuarios() {

        List<Usuario> usuario = usuarioRepository.findAll();

        if (usuario.isEmpty()) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Não há usuários registrados no mercado fácil!"),
                    HttpStatus.NO_CONTENT);
        }

        List<Usuario> usuarios = usuarioService.listaUsuarios();

        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @RequestMapping(value = "/listaUsuario/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> listaUsuarioId(@PathVariable("id") Long id) {

        Optional<Usuario> usuario= usuarioRepository.findById(id);

        if (!usuario.isPresent()) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Não há usuário com id " + id +  " registrado!"),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @RequestMapping(value = "/deletaUsuarios", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletaAllUsuarios() {

        compraService.deletaAllCompras();
        carrinhoService.deletaAllCarrinho();
        usuarioService.deleteAllUsuarios();

        return new ResponseEntity<>(usuarioService.listaUsuarios(), HttpStatus.OK);
    }

    @RequestMapping(value = "/deletaUsuarioId", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletaUsuario(Long idUsuario) {

        boolean resp = usuarioService.deleteUsuarioId(idUsuario);

        if (!resp) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Usuario com o id  " + idUsuario + " não pode ser encontrado!"),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(usuarioService.listaUsuarios(), HttpStatus.OK);
    }

    @RequestMapping(value = "/atualizaUsuario", method = RequestMethod.PUT)
    public ResponseEntity<?> atualizarUsuario(@RequestBody Usuario usuario) {

        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuario.getId());

        if (!optionalUsuario.isPresent()) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Usuario com o id  " + usuario.getId() + " não pode ser encontrado!"),
                    HttpStatus.NOT_FOUND);
        }

        Usuario user = optionalUsuario.get();

        Usuario usuarioUp = usuarioService.updateUsuario(usuario);

        return new ResponseEntity<>(usuarioUp, HttpStatus.OK);
    }

}
