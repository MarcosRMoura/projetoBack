package com.projeto.service;

import com.projeto.model.Plano;
import com.projeto.repository.PlanoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlanoService {

    @Autowired
    private PlanoRepository planoRepository;

    public Plano serviceAtualizarPlano(Long codigo, Plano plano) {
        Optional<Plano> planoSalvo = planoRepository.findById(codigo);
        if (planoSalvo.isPresent() != true)
            throw new EmptyResultDataAccessException(1);
        BeanUtils.copyProperties(plano, planoSalvo.get(), "codigo");
        return planoRepository.save(planoSalvo.get());
    }
}
