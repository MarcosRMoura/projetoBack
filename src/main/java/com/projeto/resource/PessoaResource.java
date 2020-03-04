package com.projeto.resource;

import com.projeto.event.Event;
import com.projeto.model.Grupo;
import com.projeto.model.Pessoa;
import com.projeto.repository.PessoaRepository;
import com.projeto.repository.filter.GrupoFilter;
import com.projeto.repository.filter.PessoaFilter;
import com.projeto.service.PessoaService;
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
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public Page<Pessoa> pesquisarPessoa(PessoaFilter pessoaFilter, Pageable pageable) {
        return pessoaRepository.filtrar(pessoaFilter, pageable);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Pessoa> buscarPessoa(@PathVariable Long codigo) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(codigo);
        return pessoa.isPresent() == true ? ResponseEntity.ok(pessoa.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Pessoa> adicionarPessoa(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
        Pessoa pessoaSalvo = pessoaService.serviceAdicionarPessoa(pessoa, response);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalvo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa) {
        Pessoa pessoaSalvo = pessoaService.serviceAtualizarPessoa(codigo, pessoa);
        return ResponseEntity.ok(pessoaSalvo);
    }

    @PutMapping("/{codigo}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
        pessoaService.serviceAtualizarAtivo(codigo, ativo);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerPessoa(@PathVariable Long codigo) {
        pessoaRepository.deleteById(codigo);
    }
}
