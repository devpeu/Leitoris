package com.nassau.leitoris.controllers;

import com.nassau.leitoris.models.LoanModel;
import com.nassau.leitoris.repositories.LoanRepository;
import com.nassau.leitoris.services.LoanService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimo")
public class LoanController {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanService loanService;

    @GetMapping
    public ResponseEntity<List<LoanModel>> listar() {
        List<LoanModel> emprestimos = loanRepository.findAll();
        if (emprestimos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(emprestimos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<LoanModel> cadastrar(@RequestBody @Validated LoanModel loanModel) {
        try {
            return loanService.cadastrar(loanModel);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
