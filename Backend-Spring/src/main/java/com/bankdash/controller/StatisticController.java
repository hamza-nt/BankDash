package com.bankdash.controller;

import com.bankdash.dto.StatisticDTO;
import com.bankdash.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping
    public List<StatisticDTO> getAllStatistics() {
        return statisticService.getAllStatistics();
    }

    @GetMapping("/{id}")
    public StatisticDTO getStatisticById(@PathVariable String id) {
        return statisticService.getStatisticById(id);
    }

    @PostMapping
    public StatisticDTO createStatistic(@RequestBody StatisticDTO statisticDTO) {
        return statisticService.createStatistic(statisticDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteStatistic(@PathVariable String id) {
        statisticService.deleteStatistic(id);
    }
}
