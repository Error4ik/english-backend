package backend.english.controller;

import backend.english.domain.Card;
import backend.english.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("card")
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @RequestMapping("/cards")
    public ResponseEntity<List<Card>> getCards() {
        return ResponseEntity.ok(this.cardService.getCards());
    }
}
