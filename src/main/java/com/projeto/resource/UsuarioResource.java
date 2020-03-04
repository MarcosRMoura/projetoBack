package com.projeto.resource;

import com.projeto.model.Grupo;
import com.projeto.model.Usuario;
import com.projeto.repository.UsuarioRepository;
import com.projeto.repository.filter.GrupoFilter;
import com.projeto.repository.filter.UsuarioFilter;
import com.projeto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public Page<Usuario> pesquisarUsuario(UsuarioFilter usuarioFilter, Pageable pageable) {
        return usuarioRepository.filtrar(usuarioFilter, pageable);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable Long codigo) {
        Optional<Usuario> usuario = usuarioRepository.findById(codigo);
        return usuario.isPresent() == true ? ResponseEntity.ok(usuario.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Usuario> adicionarUsuario(@Valid @RequestBody Usuario usuario, HttpServletResponse response) {
        Usuario usuarioSalvo = usuarioService.serviceAdicionarUsuario(usuario, response);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long codigo, @Valid @RequestBody Usuario usuario) {
        Usuario usuarioSalvo = usuarioService.serviceAtualizarUsuario(codigo, usuario);
        return ResponseEntity.ok(usuarioSalvo);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerPlano(@PathVariable Long codigo) {
        usuarioRepository.deleteById(codigo);
    }
}
