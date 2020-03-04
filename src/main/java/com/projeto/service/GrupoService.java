package com.projeto.service;

import com.projeto.event.Event;
import com.projeto.model.Grupo;
import com.projeto.model.Patrocinador;
import com.projeto.model.Pessoa;
import com.projeto.repository.GrupoRepository;
import com.projeto.repository.PatrocinadorRepository;
import com.projeto.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private PatrocinadorRepository patrocinadorRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;


    public Grupo serviceAdicionarGrupo(Grupo grupo, HttpServletResponse response) {
        Grupo grupoSalvo = serviceValidarPatrocinadores(grupo);
        grupoSalvo = serviceSituacaoGrupo(grupoSalvo);
        grupoRepository.save(grupoSalvo);
        publisher.publishEvent( new Event(this, response, grupoSalvo.getCodigo()));
        return grupoSalvo;
    }

    private Grupo serviceValidarPatrocinadores(Grupo grupo) {
        List<Patrocinador> patrocinadorList = new ArrayList<>();
        for ( int cont = 0; cont < grupo.getPatrocinadores().size(); cont++ ) {
            Optional<Patrocinador> patrocinador = patrocinadorRepository.findById(grupo.getPatrocinadores().get(cont).getCodigo());
            if (patrocinador.isPresent() == true)
                    patrocinadorList.add(patrocinador.get());
            else
                throw new EmptyResultDataAccessException(1);
        }
        grupo.setPatrocinadores(patrocinadorList);
        return grupo;
    }

    public Grupo serviceAtualizarGrupo(Long codigo, Grupo grupo) {
        Optional<Grupo> grupoSalvo = grupoRepository.findById(codigo);
        if (grupoSalvo.isPresent() != true)
            throw new EmptyResultDataAccessException(1);
        grupoSalvo.get().setPatrocinadores(grupo.getPatrocinadores());
        Grupo grupoValidado = serviceValidarPatrocinadores(grupoSalvo.get());
        grupoValidado.setNome(grupo.getNome());
        return grupoRepository.save(grupoValidado);
    }

    public Grupo serviceSituacaoGrupo(Grupo grupo) {
        boolean grupoVazio = true;
        List<Pessoa> pessoas = pessoaRepository.findAll();
        for ( Pessoa pessoa : pessoas ) {
            if ( pessoa.getCliente().getGrupo().getCodigo() == grupo.getCodigo() ) {
                grupoVazio = false;
            }
        }
        if ( grupoVazio == true ) {
            grupo.setAtivo(false);
        } else {
            grupo.setAtivo(true);
        }
        return grupo;
    }

    public void serviceAtualizarSituacaoGrupos() {
        boolean grupoVazio = true;
        List<Grupo> grupos = grupoRepository.findAll();
        List<Pessoa> pessoas = pessoaRepository.findAll();
        for ( Grupo grupo : grupos ) {
            for ( Pessoa pessoa : pessoas ) {
                if ( grupo.getCodigo() == pessoa.getCliente().getGrupo().getCodigo() ) {
                    grupoVazio = false;
                    break;
                } else {
                    grupoVazio = true;
                }
            }
            if ( grupoVazio == true ) {
                grupo.setAtivo(false);
            } else {
                grupo.setAtivo(true);
            }
            grupoRepository.save(grupo);
        }

    }

}
