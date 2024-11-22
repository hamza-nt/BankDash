package com.bankdash.service;

import com.bankdash.dto.TransactionDTO;
import com.bankdash.entity.Transaction;
import com.bankdash.mapper.TransactionMapper;
import com.bankdash.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public List<TransactionDTO> getAllTransactions() {
        return transactionRepository.findAll()
                .stream()
                .map(TransactionMapper.INSTANCE::toTransactionDTO)
                .collect(Collectors.toList());
    }

    public TransactionDTO getTransactionById(String id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        return TransactionMapper.INSTANCE.toTransactionDTO(transaction);
    }

    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = TransactionMapper.INSTANCE.toTransactionEntity(transactionDTO);
        return TransactionMapper.INSTANCE.toTransactionDTO(transactionRepository.save(transaction));
    }

    public void deleteTransaction(String id) {
        if (!transactionRepository.existsById(id)) {
            throw new RuntimeException("Transaction not found");
        }
        transactionRepository.deleteById(id);
    }
}
