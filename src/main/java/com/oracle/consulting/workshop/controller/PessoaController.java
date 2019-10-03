package com.oracle.consulting.workshop.controller;

import com.oracle.consulting.workshop.entity.Pessoa;
import com.oracle.consulting.workshop.exception.APIException;
import com.oracle.consulting.workshop.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @RequestMapping(value = "/pessoa", method = RequestMethod.GET)
    public List<Pessoa> getPessoa(){
        return pessoaService.findAll();
    }

    @RequestMapping(value = "/pessoa/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable(value = "id") long id) throws APIException {
        Optional<Pessoa> pessoa = Optional.ofNullable(pessoaService.findById(id));
        if(pessoa.isPresent())
            return new ResponseEntity<Pessoa>(pessoa.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/pessoa", method =  RequestMethod.POST)
    public void insertPessoa(@Valid @RequestBody Pessoa pessoa) {
        pessoaService.salvarPessoa(pessoa);
    }

    @RequestMapping(value = "/pessoa/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Pessoa> updatePessoa(@PathVariable(value = "id") long id, @Valid @RequestBody Pessoa newPessoa) throws APIException {
        Optional<Pessoa> oldPessoa = Optional.ofNullable(pessoaService.findById(id));
        if(oldPessoa.isPresent()){
            Pessoa pessoa = oldPessoa.get();
            pessoa.setNome(newPessoa.getNome());
            pessoaService.salvarPessoa(pessoa);
            return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/pessoa/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePessoa(@PathVariable(value = "id") long id) throws APIException {
        Optional<Pessoa> pessoa = Optional.ofNullable(pessoaService.findById(id));
        if(pessoa.isPresent()){
            pessoaService.deletarPessoa(pessoa.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
