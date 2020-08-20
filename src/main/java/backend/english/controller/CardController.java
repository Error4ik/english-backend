package backend.english.controller;

import backend.english.domain.Card;
import backend.english.domain.Category;
import backend.english.service.CardService;
import backend.english.util.SearchParams;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("card")
public class CardController {

    private final Logger logger = LoggerFactory.getLogger(CardController.class);

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @RequestMapping("/cards")
    public ResponseEntity<List<Card>> getCards() {
        return ResponseEntity.ok(this.cardService.getCards());
    }

    @RequestMapping("/find-cards-by-parameters")
    public ResponseEntity<Page<Card>> findCardByParameters(@RequestBody @NonNull SearchParams searchParams) {
        logger.info(String.format("Input arguments: %s", searchParams));
        return ResponseEntity.ok(this.cardService.findCardByParams(searchParams));
    }

    @RequestMapping("/add")
    public ResponseEntity<Card> addCard(@RequestBody @NonNull Card card) {
        logger.info(String.format("Input arguments: %s", card));
        Card c = this.cardService.saveCard(card);
        logger.info(String.format("Save: %s", c));
        return ResponseEntity.ok(c);
    }
}
