package com.bankdash.mapper;

import com.bankdash.dto.CardDTO;
import com.bankdash.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CardMapper {
    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);
    CardDTO toCardDTO(Card card);
    Card toCardEntity(CardDTO cardDTO);
}
