package com.bankdash.mapper;

import com.bankdash.dto.StatisticDTO;
import com.bankdash.entity.Statistic;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StatisticMapper {
    StatisticMapper INSTANCE = Mappers.getMapper(StatisticMapper.class);
    StatisticDTO toStatisticDTO(Statistic statistic);
    Statistic toStatisticEntity(StatisticDTO statisticDTO);
}

