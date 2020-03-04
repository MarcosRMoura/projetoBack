package com.projeto.resource;

import com.projeto.event.Event;
import com.projeto.model.Grupo;
import com.projeto.model.Plano;
import com.projeto.repository.PlanoRepository;
import com.projeto.repository.filter.GrupoFilter;
import com.projeto.repository.filter.PlanoFilter;
import com.projeto.service.PlanoService;
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
@RequestMapping("/planos")
public class PlanoResource {

    @Autowired
    private PlanoRepository planoRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private PlanoService planoService;

    @CrossOrigin(maxAge = 10/*, origins = { "http://localhost:8080" }*/)
    @GetMapping
    public Page<Plano> pesquisarPlano(PlanoFilter planoFilter, Pageable pageable) {
        return planoRepository.filtrar(planoFilter, pageable);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Plano> buscarPlano(@PathVariable Long codigo) {
        Optional<Plano> plano = planoRepository.findById(codigo);
        return plano.isPresent() == true ? ResponseEntity.ok(plano.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Plano> adicionarPlano(@Valid @RequestBody Plano plano, HttpServletResponse response) {
        Plano planoSalvo = planoRepository.save(plano);
        publisher.publishEvent(new Event(this, response, planoSalvo.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(planoSalvo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Plano> atualizarPlano(@PathVariable Long codigo, @Valid @RequestBody Plano plano) {
        Plano planoSalvo = planoService.serviceAtualizarPlano(codigo, plano);
        return ResponseEntity.ok(planoSalvo) ;
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerPlano(@PathVariable Long codigo) {
        planoRepository.deleteById(codigo);
    }
}
