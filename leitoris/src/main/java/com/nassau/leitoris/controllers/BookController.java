package com.nassau.leitoris.controllers;

import com.nassau.leitoris.models.BookModel;
import com.nassau.leitoris.repositories.BookRepository;

import jakarta.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/livro")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public ResponseEntity<List<BookModel>> listar() {
        List<BookModel> livros = bookRepository.findAll();
        if (livros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(livros);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<BookModel> cadastrar(@RequestBody @Validated BookModel bookModel) {
        try {
            BookModel novoLivro = bookRepository.save(bookModel);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(novoLivro.getId())
                    .toUri();
            return ResponseEntity.created(location).body(novoLivro);
        } catch (DataIntegrityViolationException e) {
            logger.error("Erro de violação de integridade ao cadastrar livro", e);
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            logger.error("Erro ao cadastrar livro", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
