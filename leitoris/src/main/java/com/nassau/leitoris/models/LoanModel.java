package com.nassau.leitoris.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_loan")
public class LoanModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        private LocalDate dateLoan = LocalDate.now();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        private LocalDate dateDelivery;

        private Boolean delivered = false;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonBackReference
        private BookModel fkBook;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
        private UserModel fkUser;

    public Object getEntregue() {
        throw new UnsupportedOperationException("Unimplemented method 'getEntregue'");
    }
}
