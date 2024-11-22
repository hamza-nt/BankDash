package com.bankdash.controller;

import com.bankdash.dto.BalanceHistoryDTO;
import com.bankdash.service.BalanceHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/balance-history")
@RequiredArgsConstructor
public class BalanceHistoryController {

    private final BalanceHistoryService balanceHistoryService;

    @GetMapping
    public List<BalanceHistoryDTO> getAllBalanceHistories() {
        return balanceHistoryService.getAllBalanceHistories();
    }

    @GetMapping("/{id}")
    public BalanceHistoryDTO getBalanceHistoryById(@PathVariable String id) {
        return balanceHistoryService.getBalanceHistoryById(id);
    }

    @PostMapping
    public BalanceHistoryDTO createBalanceHistory(@RequestBody BalanceHistoryDTO balanceHistoryDTO) {
        return balanceHistoryService.createBalanceHistory(balanceHistoryDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBalanceHistory(@PathVariable String id) {
        balanceHistoryService.deleteBalanceHistory(id);
    }
}
