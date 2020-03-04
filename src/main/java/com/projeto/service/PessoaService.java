package com.projeto.service;

import com.projeto.event.Event;
import com.projeto.model.Pessoa;
import com.projeto.repository.GrupoRepository;
import com.projeto.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private ApplicationEventPublisher publisher;

    public Pessoa serviceAdicionarPessoa(Pessoa pessoa, HttpServletResponse response) {
        pessoa = pagamentoService.serviceAtualizarSituacao(pessoa);
        Pessoa pessoaSalvo = pessoaRepository.save(pessoa);
        publisher.publishEvent( new Event(this, response, pessoaSalvo.getCodigo()));
        grupoService.serviceAtualizarSituacaoGrupos();
        return pessoa;
    }

    public Pessoa serviceAtualizarPessoa(Long codigo, Pessoa pessoa) {
        Optional<Pessoa> pessoaSalvo = serviceValidarPessoa(codigo);
        BeanUtils.copyProperties(pessoa, pessoaSalvo.get(), "codigo");
        return pessoaRepository.save(pessoaSalvo.get());
    }

    private Optional<Pessoa> serviceValidarPessoa(Long codigo) {
        Optional<Pessoa> pessoaSalvo = pessoaRepository.findById(codigo);
        if (pessoaSalvo.isPresent() != true)
            throw new EmptyResultDataAccessException(1);
        return pessoaSalvo;
    }

    public void serviceAtualizarAtivo(Long codigo, Boolean ativo) {
        Optional<Pessoa> pessoa = serviceValidarPessoa(codigo);
        pessoa.get().setAtivo(ativo);
        pessoaRepository.save(pessoa.get());
    }
}
