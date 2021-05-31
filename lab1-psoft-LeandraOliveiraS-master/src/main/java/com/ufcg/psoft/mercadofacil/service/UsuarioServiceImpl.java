package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Usuario;
import com.ufcg.psoft.mercadofacil.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario usuario;

    @Override
    public void criaUsuario(Long id, String cpf, String nome, String perfil) {
        Usuario usuario = new Usuario(cpf, nome, perfil);
        usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> listaUsuarios() {
        List<Usuario> listaDeUsuarios = usuarioRepository.findAll();
        return listaDeUsuarios;
    }

    @Override
    public boolean deleteAllUsuarios() {
        usuarioRepository.deleteAll();
        return true;
    }

    @Override
    public boolean deleteUsuarioId(Long id) {
        List<Usuario> listaDeUsuarios = usuarioRepository.findAll();

        for(Usuario usuario: listaDeUsuarios){
            if(usuario.getId().equals(id)){
                listaDeUsuarios.remove(usuario);
                usuarioRepository.delete(usuario);
                return true;
            }
        }

        return false;
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) {
        usuario.setNome(usuario.getNome());
        usuario.setCpf(usuario.getCpf());
        usuarioRepository.save(usuario);
        return usuario;
    }


    @Override
    public Usuario getUsuario(Long id) {
        List <Usuario> listDeUsuarios = usuarioRepository.findAll();

        for(Usuario usuario: listDeUsuarios){
            if(usuario.getId().equals(id)){
                return usuario;
            }

        }

        return null;
    }




}
