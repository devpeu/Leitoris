package com.nassau.leitoris.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_usuario")
public class UsuarioModel {

    //Aqui é onde será cadastrado os dados como iD,Nome,Cpf do Úsuario.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//Vai ser gerado um Id para todo usuario cadastrado, onde esse Id vai ser usado para verificar nos emprestimos

    @NotBlank
    private String nome;

    @NotBlank
    @Pattern(regexp = "(^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$)")
    private String cpf;

    @OneToMany(mappedBy = "fkUsuario", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<EmprestimoModel> emprestimoModel;
}