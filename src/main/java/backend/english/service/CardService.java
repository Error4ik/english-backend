package backend.english.service;

import backend.english.domain.Card;
import backend.english.repository.CardRepository;
import backend.english.util.SearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public Page<Card> findCardByParams(SearchParams searchParams) {
        String sortColumn = "word";
        PageRequest pageRequest = PageRequest.of(
                searchParams.getPageNumber(),
                searchParams.getPageLimit(),
                Sort.by(Sort.Direction.ASC, sortColumn));
        return this.cardRepository.findCardByParams(searchParams.getCategory(), pageRequest);
    }
}
