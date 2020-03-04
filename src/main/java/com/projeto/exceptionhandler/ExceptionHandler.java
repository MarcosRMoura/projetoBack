package com.projeto.exceptionhandler;

import com.projeto.service.exception.PessoaComPagamentoEmAbertoException;
//import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;


    //Tratamento de mensagens para retorno de erros de entrada de dados não esperado
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        String mensagemUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
        List<Erro> erros = Arrays.asList( new Erro(mensagemUsuario, mensagemDesenvolvedor) );
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    //Tratamento metodo de argumentos inválidos
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Erro> erros = criarListaDeErros(ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    //Trata o erro de utilização de recurso não existente, Exemplo exlusão de item já excluído
    @org.springframework.web.bind.annotation.ExceptionHandler( { EmptyResultDataAccessException.class } )
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<Erro> erros = Arrays.asList( new Erro(mensagemUsuario, mensagemDesenvolvedor) );
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    //Trata o erro de utilização de recurso não existente, Exemplo adicionar por meio de chave estrangeira onde o registro é inexistente
    @org.springframework.web.bind.annotation.ExceptionHandler( { DataIntegrityViolationException.class } )
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("recurso.operacao-nao-permitida", null, LocaleContextHolder.getLocale());
        //String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        //List<Erro> erros = Arrays.asList( new Erro(mensagemUsuario, mensagemDesenvolvedor) );
        return handleExceptionInternal(ex,"ERRO" /*erros*/, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler( {PessoaComPagamentoEmAbertoException.class} )
    public ResponseEntity<Object> handlePessoaComPagamentoEmAbertoException(PessoaComPagamentoEmAbertoException ex) {
        String mensagemUsuario = messageSource.getMessage("pessoa.pagamento-em-aberto", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<Erro> erros = Arrays.asList( new Erro(mensagemUsuario, mensagemDesenvolvedor) );
        return ResponseEntity.badRequest().body(erros);
    }





    private List<Erro> criarListaDeErros(BindingResult bindingResult) {
        List<Erro> erros = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String mensagemDesenvolvedor = fieldError.toString();
            erros.add( new Erro(mensagemUsuario, mensagemDesenvolvedor) );
        }
        return erros;
    }

    public static class Erro {
        private String mensagemUsuario;
        private String mensagemDesenvolvedor;

        public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
            this.mensagemUsuario = mensagemUsuario;
            this.mensagemDesenvolvedor = mensagemDesenvolvedor;
        }

        public String getMensagemUsuario() {
            return mensagemUsuario;
        }

        public String getMensagemDesenvolvedor() {
            return mensagemDesenvolvedor;
        }
    }
}
