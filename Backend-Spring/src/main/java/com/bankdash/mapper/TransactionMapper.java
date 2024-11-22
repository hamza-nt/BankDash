package com.bankdash.mapper;

import com.bankdash.dto.TransactionDTO;
import com.bankdash.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);
    TransactionDTO toTransactionDTO(Transaction transaction);
    Transaction toTransactionEntity(TransactionDTO transactionDTO);
}
