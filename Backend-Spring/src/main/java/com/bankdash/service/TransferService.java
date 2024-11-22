package com.bankdash.service;

import com.bankdash.dto.TransferDTO;
import com.bankdash.entity.Transfer;
import com.bankdash.mapper.TransferMapper;
import com.bankdash.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransferRepository transferRepository;

    public List<TransferDTO> getAllTransfers() {
        return transferRepository.findAll()
                .stream()
                .map(TransferMapper.INSTANCE::toTransferDTO)
                .collect(Collectors.toList());
    }

    public TransferDTO getTransferById(String id) {
        Transfer transfer = transferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transfer not found"));
        return TransferMapper.INSTANCE.toTransferDTO(transfer);
    }

    public TransferDTO createTransfer(TransferDTO transferDTO) {
        Transfer transfer = TransferMapper.INSTANCE.toTransferEntity(transferDTO);
        return TransferMapper.INSTANCE.toTransferDTO(transferRepository.save(transfer));
    }

    public void deleteTransfer(String id) {
        if (!transferRepository.existsById(id)) {
            throw new RuntimeException("Transfer not found");
        }
        transferRepository.deleteById(id);
    }
}
