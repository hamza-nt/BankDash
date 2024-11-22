package com.bankdash.mapper;

import com.bankdash.dto.TransferDTO;
import com.bankdash.entity.Transfer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransferMapper {
    TransferMapper INSTANCE = Mappers.getMapper(TransferMapper.class);
    TransferDTO toTransferDTO(Transfer transfer);
    Transfer toTransferEntity(TransferDTO transferDTO);
}

