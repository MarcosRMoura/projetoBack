package com.projeto.service;

import com.projeto.event.Event;
import com.projeto.model.Pagamento;
import com.projeto.model.Pessoa;
import com.projeto.repository.PagamentoRepository;
import com.projeto.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;


    public Pagamento serviceValidarPessoa(Pagamento pagamento) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(pagamento.getPessoa().getCodigo());
        if (pessoa.isPresent() != true)
            throw new EmptyResultDataAccessException(1);
        pagamento.setPessoa(pessoa.get());
        return pagamento;
    }

    public Pagamento serviceValidarPagamento(Long codigo) {
        Optional<Pagamento> pagamento = pagamentoRepository.findById(codigo);
        if (pagamento.isPresent() != true)
            throw new EmptyResultDataAccessException(1);
        return pagamento.get();
    }

    public void serviceDeletarPagamento(Long codigo) {
        Pagamento pagamento = serviceValidarPagamento(codigo);
        Pessoa pessoa = pagamento.getPessoa();
        pagamentoRepository.deleteById(codigo);
        pessoa = serviceAtualizarSituacao(pessoa);
        pessoaRepository.save(pessoa);
    }

    public Pagamento serviceAdicionaPagamento(Pagamento pagamento, HttpServletResponse response) {
        Pagamento pagamentoSalvo = serviceValidarPessoa(pagamento);
        pagamentoSalvo.setValor(pagamentoSalvo.getPessoa().getCliente().getGrupo().getPlano().getValormensal() - (pagamentoSalvo.getPessoa().getCliente().getGrupo().getPlano().getValormensal() / 100)
                * pagamentoSalvo.getPessoa().getCliente().getPatrocinador().getDescontadoemfolha());
        pagamentoRepository.save(pagamentoSalvo);
        Pessoa pessoa = serviceAtualizarSituacao(pagamentoSalvo.getPessoa());
        pessoaRepository.save(pessoa);
        publisher.publishEvent( new Event(this, response, pagamentoSalvo.getCodigo()));
        return pagamentoSalvo;
    }

    public Pessoa serviceAtualizarSituacao(Pessoa pessoa) {
        Boolean situacaoPessoa = false;
        List<Pagamento> pagamentos = pagamentoRepository.findAll();
        List<Pagamento> pagamentosPessoa = new ArrayList<>();

        List<LocalDate> datas = new ArrayList<>();

        for ( Pagamento pagamento : pagamentos ) {
            if ( pagamento.getPessoa().getCodigo() == pessoa.getCodigo()) {
                pagamentosPessoa.add(pagamento);
                datas.add(pagamento.getDataPagamento());
            }
        }
        if ( pagamentosPessoa == null ) {
            pessoa.setAtivo(false);
        }
        else if (pagamentosPessoa.size() == 1 ) {
            situacaoPessoa = true;
        }
        else {
            for ( int cont = 0; cont < pagamentosPessoa.size() - 1 ; cont++ ) {
                Period periodo = Period.between(pagamentosPessoa.get(cont).getDataPagamento(),
                    pagamentosPessoa.get(cont+1).getDataPagamento());
                int dia = periodo.getDays();
                int mes = periodo.getMonths();
                int ano = periodo.getYears();

                System.out.println(cont);
                System.out.println(pagamentosPessoa.get(cont).getDataPagamento());
                System.out.println(pagamentosPessoa.get(cont+1).getDataPagamento());
                System.out.println("Dia = " + dia + " Mes = " + mes + " Ano = " + ano);
                System.out.println("--------------------------------");

                if ( mes >= 2 ) {
                    situacaoPessoa = false;
                    break;
                }
                else {
                    situacaoPessoa = true;
                }
            }
        }
        if ( situacaoPessoa == false ) {
            pessoa.setAtivo(false);
        }
        else {
            pessoa.setAtivo(true);
        }
        return pessoa;
    }


}
