package com.projeto.service;

import com.projeto.event.Event;
import com.projeto.model.Grupo;
import com.projeto.model.Pessoa;
import com.projeto.model.Usuario;
import com.projeto.repository.PessoaRepository;
import com.projeto.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    public Usuario serviceAdicionarUsuario(Usuario usuario, HttpServletResponse response) {
        Pessoa pessoa = serviceValidarPessoa(usuario.getPessoa().getCodigo());
        usuario.setPessoa(pessoa);
        usuario.setCodigo(pessoa.getCodigo());
        usuarioRepository.save(usuario);
        publisher.publishEvent( new Event(this, response, usuario.getCodigo()));
        return usuario;
    }

    public Usuario serviceAtualizarUsuario(Long codigo, Usuario usuario) {
        Usuario usuarioSalvo = serviceValidarUsuario(codigo);
        BeanUtils.copyProperties(usuario, usuarioSalvo, "codigo");
        return usuarioRepository.save(usuario);
    }

    private Pessoa serviceValidarPessoa(Long codigo) {
        Optional<Pessoa> pessoaSalvo = pessoaRepository.findById(codigo);
        if (pessoaSalvo.isPresent() != true)
            throw new EmptyResultDataAccessException(1);
        return pessoaSalvo.get();
    }

    private Usuario serviceValidarUsuario(Long codigo) {
        Optional<Usuario> usuario = usuarioRepository.findById(codigo);
        if (usuario.isPresent() != true)
            throw new EmptyResultDataAccessException(1);
        return usuario.get();
    }
}
