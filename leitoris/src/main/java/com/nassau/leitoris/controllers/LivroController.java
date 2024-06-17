package com.nassau.leitoris.controllers;

import com.nassau.leitoris.models.LivroModel;
import com.nassau.leitoris.repositories.LivroRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")//EndPoint
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping
    public ResponseEntity<List<LivroModel>> listar(){
        return ResponseEntity.ok(livroRepository.findAll());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<LivroModel> cadastrar(@RequestBody @Valid LivroModel livroModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(livroRepository.save(livroModel));
    }
}