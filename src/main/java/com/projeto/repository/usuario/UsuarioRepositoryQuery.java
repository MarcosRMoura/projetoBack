package com.projeto.repository.usuario;

import com.projeto.model.Usuario;
import com.projeto.repository.filter.UsuarioFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioRepositoryQuery {

    public Page<Usuario> filtrar(UsuarioFilter usuarioFilter, Pageable pageable);
}
