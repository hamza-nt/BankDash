package com.bankdash.service;

import com.bankdash.dto.StatisticDTO;
import com.bankdash.entity.Statistic;
import com.bankdash.mapper.StatisticMapper;
import com.bankdash.repository.StatisticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticService {

    private final StatisticRepository statisticRepository;

    public List<StatisticDTO> getAllStatistics() {
        return statisticRepository.findAll()
                .stream()
                .map(StatisticMapper.INSTANCE::toStatisticDTO)
                .collect(Collectors.toList());
    }

    public StatisticDTO getStatisticById(String id) {
        Statistic statistic = statisticRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Statistic not found"));
        return StatisticMapper.INSTANCE.toStatisticDTO(statistic);
    }

    public StatisticDTO createStatistic(StatisticDTO statisticDTO) {
        Statistic statistic = StatisticMapper.INSTANCE.toStatisticEntity(statisticDTO);
        return StatisticMapper.INSTANCE.toStatisticDTO(statisticRepository.save(statistic));
    }

    public void deleteStatistic(String id) {
        if (!statisticRepository.existsById(id)) {
            throw new RuntimeException("Statistic not found");
        }
        statisticRepository.deleteById(id);
    }
}
