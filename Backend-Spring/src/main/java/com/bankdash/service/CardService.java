package com.bankdash.service;

import com.bankdash.dto.CardDTO;
import com.bankdash.entity.Card;
import com.bankdash.mapper.CardMapper;
import com.bankdash.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public List<CardDTO> getAllCards() {
        return cardRepository.findAll()
                .stream()
                .map(CardMapper.INSTANCE::toCardDTO)
                .collect(Collectors.toList());
    }

    public CardDTO getCardById(String id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card not found"));
        return CardMapper.INSTANCE.toCardDTO(card);
    }

    public CardDTO createCard(CardDTO cardDTO) {
        Card card = CardMapper.INSTANCE.toCardEntity(cardDTO);
        return CardMapper.INSTANCE.toCardDTO(cardRepository.save(card));
    }

    public void deleteCard(String id) {
        if (!cardRepository.existsById(id)) {
            throw new RuntimeException("Card not found");
        }
        cardRepository.deleteById(id);
    }
}
