package com.projeto.service;

import com.projeto.model.Patrocinador;
import com.projeto.repository.PatrocinadorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatrocinadorService {

    @Autowired
    private PatrocinadorRepository patrocinadorRepository;

    public Patrocinador atualizarPatrocinador(Long codigo, Patrocinador patrocinador) {
        Optional<Patrocinador> patrocinadorSalvo = patrocinadorRepository.findById(codigo);
        if (patrocinadorSalvo.isPresent() != true)
            throw new EmptyResultDataAccessException(1);
        BeanUtils.copyProperties(patrocinador, patrocinadorSalvo.get(), "codigo");
        return patrocinadorRepository.save(patrocinadorSalvo.get());
    }
}
