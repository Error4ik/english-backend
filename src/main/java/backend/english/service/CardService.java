package backend.english.service;

import backend.english.domain.Card;
import backend.english.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card saveCard(Card card) {
        return this.cardRepository.save(card);
    }

    public Optional<Card> getCardById(UUID cardId) {
        return this.cardRepository.findById(cardId);
    }

    public List<Card> getCards() {
        return this.cardRepository.findAll();
    }

    public void deleteCard(UUID cardId) {
        this.cardRepository.deleteById(cardId);
    }

    public Card updateCard(Card card) {
        return this.cardRepository.save(card);
    }
}
