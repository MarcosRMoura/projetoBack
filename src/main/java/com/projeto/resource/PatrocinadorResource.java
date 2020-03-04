package com.projeto.resource;

import com.projeto.event.Event;
import com.projeto.model.Grupo;
import com.projeto.model.Patrocinador;
import com.projeto.repository.PatrocinadorRepository;
import com.projeto.repository.filter.GrupoFilter;
import com.projeto.repository.filter.PatrocinadorFilter;
import com.projeto.service.PatrocinadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patrocinadores")
public class PatrocinadorResource {

    @Autowired
    private PatrocinadorRepository patrocinadorRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private PatrocinadorService patrocinadorService;

    @GetMapping
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN') and #oauth2.hasScope('read')")
    public Page<Patrocinador> pesquisarPatrocinador(PatrocinadorFilter patrocinadorFilter, Pageable pageable) {
        return patrocinadorRepository.filtrar(patrocinadorFilter, pageable);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Patrocinador> buscarPatrocinador(@PathVariable Long codigo) {
        Optional<Patrocinador> patrocinador = patrocinadorRepository.findById(codigo);
        return patrocinador.isPresent() == true ? ResponseEntity.ok(patrocinador.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN') and #oauth2.hasScope('write')")
    public ResponseEntity<Patrocinador> adicionarPatrocinador(@Valid @RequestBody Patrocinador patrocinador, HttpServletResponse response) {
        Patrocinador patrocinadorSalvo = patrocinadorRepository.save(patrocinador);
        publisher.publishEvent( new Event(this, response, patrocinador.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(patrocinadorSalvo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Patrocinador> atualizarPatrocinador(@PathVariable Long codigo, @Valid @RequestBody Patrocinador patrocinador) {
        Patrocinador patrocinadorSalvo = patrocinadorService.atualizarPatrocinador(codigo, patrocinador);
        return ResponseEntity.ok(patrocinadorSalvo) ;
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerPlano(@PathVariable Long codigo) {
        patrocinadorRepository.deleteById(codigo);
    }

}
