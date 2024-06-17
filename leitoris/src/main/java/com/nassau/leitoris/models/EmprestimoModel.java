package com.nassau.leitoris.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_emprestimo")
public class EmprestimoModel {

    //Para Emprestimos precisamos do Id,Data para emprestimo e a data que o usuario tem que entregar
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataEmprestimo = LocalDate.now();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dateEntrega;

    private Boolean entregue = false;

    //Verificar os dados para o emprestimo
    @NotNull
    @ManyToOne
    private LivroModel fkLivro;

    @NotNull
    @ManyToOne
    private UsuarioModel fkUsuario;
}