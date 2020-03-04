package com.projeto.resource;

import com.projeto.model.Grupo;
import com.projeto.repository.GrupoRepository;
import com.projeto.repository.filter.GrupoFilter;
import com.projeto.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/grupos")
public class GrupoResource {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private GrupoService grupoService;

    @GetMapping
    public Page<Grupo> pesquisarGrupo(GrupoFilter grupoFilter, Pageable pageable) {
        return grupoRepository.filtrar(grupoFilter, pageable);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Grupo> buscarGrupo(@PathVariable Long codigo) {
        Optional<Grupo> grupo = grupoRepository.findById(codigo);
        return grupo.isPresent() == true ? ResponseEntity.ok(grupo.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Grupo> adicionarGrupo(@Valid @RequestBody Grupo grupo, HttpServletResponse response) {
        Grupo grupoSalvo = grupoService.serviceAdicionarGrupo(grupo, response);
        return ResponseEntity.status(HttpStatus.CREATED).body(grupoSalvo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Grupo> atualizarGrupo(@PathVariable Long codigo, @Valid @RequestBody Grupo grupo) {
        Grupo grupoSalvo = grupoService.serviceAtualizarGrupo(codigo, grupo);
        return ResponseEntity.ok(grupoSalvo);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerPlano(@PathVariable Long codigo) {
        grupoRepository.deleteById(codigo);
    }
}
