package com.fatecrl.contacorrente.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatecrl.contacorrente.model.Conta;
import com.fatecrl.contacorrente.service.ContaService;

@RestController
@RequestMapping("/conta-corrente")


public class ContaCorrenteController {

    @Autowired
    private ContaService contaService;

    @GetMapping
    public ResponseEntity<List<Conta>> getAll(){
        return ResponseEntity.ok(contaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> get(@PathVariable("id")Long id){
        Conta conta = contaService.find(id);

        if(conta != null){
            return ResponseEntity.ok(conta);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Conta>create(@RequestBody Conta conta){
        contaService.Create(conta);
        URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(conta.getId())
                            .toUri();
        return ResponseEntity.created(location).body(conta);
        //HTTP 201 CREATED
    }
}
