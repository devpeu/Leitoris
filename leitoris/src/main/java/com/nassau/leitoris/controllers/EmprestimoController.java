package com.nassau.leitoris.controllers;

import com.nassau.leitoris.models.EmprestimoModel;
import com.nassau.leitoris.repositories.EmprestimoRepository;
import com.nassau.leitoris.services.EmprestimoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimo")//EndPoint
public class EmprestimoController {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping
    public ResponseEntity<List<EmprestimoModel>> listar(){
        return ResponseEntity.ok(emprestimoRepository.findAll());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<EmprestimoModel> cadastrar(@RequestBody @Valid EmprestimoModel emprestimoModel){
        return emprestimoService.cadastrar(emprestimoModel);
    }
}