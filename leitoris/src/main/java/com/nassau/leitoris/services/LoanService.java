package com.nassau.leitoris.services;

import com.nassau.leitoris.models.LoanModel;
import com.nassau.leitoris.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public ResponseEntity<LoanModel> cadastrar(LoanModel loanModel) {
        // Verifica se o usuário já possui 3 empréstimos não entregues
        List<LoanModel> statistics = verificaQuantidadeEmprestimo(loanModel);
        if (statistics.size() >= 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Não é possível obter mais livros, o limite são 3 livros!");
        } else {
            // Salva o novo empréstimo no banco de dados
            LoanModel novoEmprestimo = loanRepository.save(loanModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoEmprestimo);
        }
    }

    public List<LoanModel> verificaQuantidadeEmprestimo(LoanModel loanModel) {
        // Obtém o usuário associado ao empréstimo
        Long userId = loanModel.getFkUser().getId();
        List<LoanModel> emprestimosAtivos = loanRepository.findByFkUserIdAndEntregueFalse(userId);
        return emprestimosAtivos;
    }
}
