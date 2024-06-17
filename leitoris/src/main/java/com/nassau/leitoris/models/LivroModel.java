package com.nassau.leitoris.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_livro")
public class LivroModel {

    //Cadastro de livros usando iD,Nome,Descrição,Foto e Ano de publicação para conseguir cadastrar o livro
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    private String foto;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JoinColumn(unique = false)
    private LocalDate anoPublicacao;

    @OneToMany(mappedBy = "fkLivro", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<EmprestimoModel> emprestimoModel;
}