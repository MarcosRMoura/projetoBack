package com.projeto.repository.filter;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class PagamentoFilter {

    private Long codigo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataPagamentoDe;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataPagamentoAte;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public LocalDate getDataPagamentoDe() {
        return dataPagamentoDe;
    }

    public void setDataPagamentoDe(LocalDate dataPagamentoDe) {
        this.dataPagamentoDe = dataPagamentoDe;
    }

    public LocalDate getDataPagamentoAte() {
        return dataPagamentoAte;
    }

    public void setDataPagamentoAte(LocalDate dataPagamentoAte) {
        this.dataPagamentoAte = dataPagamentoAte;
    }
}
