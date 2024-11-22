package com.bankdash.controller;

import com.bankdash.dto.TransferDTO;
import com.bankdash.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @GetMapping
    public List<TransferDTO> getAllTransfers() {
        return transferService.getAllTransfers();
    }

    @GetMapping("/{id}")
    public TransferDTO getTransferById(@PathVariable String id) {
        return transferService.getTransferById(id);
    }

    @PostMapping
    public TransferDTO createTransfer(@RequestBody TransferDTO transferDTO) {
        return transferService.createTransfer(transferDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTransfer(@PathVariable String id) {
        transferService.deleteTransfer(id);
    }
}
