package com.ufcg.psoft.mercadofacil.service;


import com.ufcg.psoft.mercadofacil.model.Usuario;

import java.util.List;

public interface UsuarioService {

    public void criaUsuario(Long id, String cpf, String nome, String perfil);

    public List<Usuario> listaUsuarios();

    public boolean deleteAllUsuarios();

    public boolean deleteUsuarioId(Long id);

    public Usuario updateUsuario(Usuario usuario);

    public Usuario getUsuario(Long id);
}
