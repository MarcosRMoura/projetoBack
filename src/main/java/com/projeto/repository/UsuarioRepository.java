package com.projeto.repository;

import com.projeto.model.Usuario;
import com.projeto.repository.usuario.UsuarioRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQuery {

    Optional<Usuario> findByEmail(String email);
}
