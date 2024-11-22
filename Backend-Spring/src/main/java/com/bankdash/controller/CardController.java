package com.bankdash.controller;

import com.bankdash.dto.CardDTO;
import com.bankdash.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping
    public List<CardDTO> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping("/{id}")
    public CardDTO getCardById(@PathVariable String id) {
        return cardService.getCardById(id);
    }

    @PostMapping
    public CardDTO createCard(@RequestBody CardDTO cardDTO) {
        return cardService.createCard(cardDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable String id) {
        cardService.deleteCard(id);
    }
}
