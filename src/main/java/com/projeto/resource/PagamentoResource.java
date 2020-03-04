package com.projeto.resource;

import com.projeto.model.Grupo;
import com.projeto.model.Pagamento;
import com.projeto.repository.PagamentoRepository;
import com.projeto.repository.filter.GrupoFilter;
import com.projeto.repository.filter.PagamentoFilter;
import com.projeto.service.PagamentoService;
import org.hibernate.loader.plan.spi.Return;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/pagamentos")
public class PagamentoResource {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping
    public Page<Pagamento> pesquisarPagamento(PagamentoFilter pagamentoFilter, Pageable pageable) {
        return pagamentoRepository.filtrar(pagamentoFilter, pageable);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Pagamento> buscarPagamento(@PathVariable Long codigo) {
        Optional<Pagamento> pagamento = pagamentoRepository.findById(codigo);
        return pagamento.isPresent() == true ? ResponseEntity.ok(pagamento.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Pagamento> adicionarPagamento(@Valid @RequestBody Pagamento pagamento, HttpServletResponse response) {
        Pagamento pagamentoSalvo = pagamentoService.serviceAdicionaPagamento(pagamento, response);
        return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoSalvo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Pagamento> atualizarPagamento(@PathVariable Long codigo, @Valid @RequestBody Pagamento pagamento, HttpServletResponse response) {
        pagamento.setCodigo(codigo);
        Pagamento pagamentoSalvo = pagamentoService.serviceAdicionaPagamento(pagamento, response);
        return ResponseEntity.ok(pagamentoSalvo);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerPlano(@PathVariable Long codigo) {
        pagamentoService.serviceDeletarPagamento(codigo);
    }
}
