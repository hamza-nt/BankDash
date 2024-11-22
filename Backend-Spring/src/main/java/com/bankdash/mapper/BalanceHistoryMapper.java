package com.bankdash.mapper;

import com.bankdash.dto.BalanceHistoryDTO;
import com.bankdash.entity.BalanceHistory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BalanceHistoryMapper {
    BalanceHistoryMapper INSTANCE = Mappers.getMapper(BalanceHistoryMapper.class);
    BalanceHistoryDTO toBalanceHistoryDTO(BalanceHistory balanceHistory);
    BalanceHistory toBalanceHistoryEntity(BalanceHistoryDTO balanceHistoryDTO);
}
