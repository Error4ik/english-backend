package backend.english.controller;

import backend.english.domain.Card;
import backend.english.service.CardService;
import backend.english.util.SearchParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("card")
public class CardController {

    private final Logger logger = LoggerFactory.getLogger(CardController.class);

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/cards")
    public ResponseEntity<List<Card>> getCards() {
        return ResponseEntity.ok(this.cardService.getCards());
    }

    @PostMapping("/find-cards-by-parameters")
    public ResponseEntity<Page<Card>> findCardByParameters(@RequestBody @NonNull SearchParams searchParams) {
        logger.info(String.format("Input arguments: %s", searchParams));
        return ResponseEntity.ok(this.cardService.findCardByParams(searchParams));
    }

    @PostMapping("/add")
    public ResponseEntity<Card> addCard(@RequestBody @NonNull Card card) {
        logger.info(String.format("Input arguments: %s", card));
        Card c = this.cardService.saveCard(card);
        logger.info(String.format("Save: %s", c));
        return ResponseEntity.ok(c);
    }
}
