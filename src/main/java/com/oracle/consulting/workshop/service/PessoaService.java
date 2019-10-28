package com.oracle.consulting.workshop.service;

import com.oracle.consulting.workshop.entity.Pessoa;
import com.oracle.consulting.workshop.exception.APIException;
import com.oracle.consulting.workshop.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    public List<Pessoa> findAll(){
        return pessoaRepository.findAll();
    }

    public Pessoa findById(final Long id) throws APIException{
        return pessoaRepository.findById(id).orElseThrow(() -> new APIException(HttpStatus.NO_CONTENT));
    }

    public void salvarPessoa(Pessoa pessoa){
        pessoaRepository.save(pessoa);
    }

    public void deletarPessoa(Pessoa pessoa) {
        pessoaRepository.deleteById(pessoa.getId());
    }

    public List<Pessoa> findByNome(final String nome) {
        return pessoaRepository.findByNome(nome);
    }
}
