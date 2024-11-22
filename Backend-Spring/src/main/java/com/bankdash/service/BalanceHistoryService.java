package com.bankdash.service;

import com.bankdash.dto.BalanceHistoryDTO;
import com.bankdash.entity.BalanceHistory;
import com.bankdash.mapper.BalanceHistoryMapper;
import com.bankdash.repository.BalanceHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BalanceHistoryService {

    private final BalanceHistoryRepository balanceHistoryRepository;

    public List<BalanceHistoryDTO> getAllBalanceHistories() {
        return balanceHistoryRepository.findAll()
                .stream()
                .map(BalanceHistoryMapper.INSTANCE::toBalanceHistoryDTO)
                .collect(Collectors.toList());
    }

    public BalanceHistoryDTO getBalanceHistoryById(String id) {
        BalanceHistory balanceHistory = balanceHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Balance history not found"));
        return BalanceHistoryMapper.INSTANCE.toBalanceHistoryDTO(balanceHistory);
    }

    public BalanceHistoryDTO createBalanceHistory(BalanceHistoryDTO balanceHistoryDTO) {
        BalanceHistory balanceHistory = BalanceHistoryMapper.INSTANCE.toBalanceHistoryEntity(balanceHistoryDTO);
        return BalanceHistoryMapper.INSTANCE.toBalanceHistoryDTO(balanceHistoryRepository.save(balanceHistory));
    }

    public void deleteBalanceHistory(String id) {
        if (!balanceHistoryRepository.existsById(id)) {
            throw new RuntimeException("Balance history not found");
        }
        balanceHistoryRepository.deleteById(id);
    }
}
