package backend.english.service;

import backend.english.domain.Card;
import backend.english.domain.Category;
import backend.english.repository.CardRepository;
import backend.english.util.SearchParams;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CardServiceTest {

    private final CardRepository cardRepository = mock(CardRepository.class);

    private final Category category = mock(Category.class);

    private final UUID uuid = UUID.randomUUID();

    private final Card card = new Card();

    private final CardService cardService = new CardService(cardRepository);

    private final SearchParams searchParams = new SearchParams();

    private final Page<Card> page = mock(Page.class);

    private PageRequest pageRequest;

    @BeforeEach
    public void init() {
        card.setId(uuid);
        card.setCategory(category);
        card.setWord("Word");
        card.setPhrase("phrase");
        card.setTranslation("Translation");
        card.setTranslatePhrase("Translation phrase");
        searchParams.setCategory(uuid);
        searchParams.setPageLimit(5);
        searchParams.setPageNumber(1);

        pageRequest = PageRequest.of(
                searchParams.getPageNumber(),
                searchParams.getPageLimit(),
                Sort.by(Sort.Direction.ASC, "word"));
    }

    @Test
    void whenSaveCardShouldReturnSavedCard() {
        when(this.cardRepository.save(any(Card.class))).thenReturn(card);

        Card actualCard = this.cardService.saveCard(card);

        assertEquals(actualCard, card);
        verify(cardRepository, times(1)).save(card);
    }

    @Test
    void whenGetCardByIdShouldReturnCard() {
        when(this.cardRepository.findById(any(UUID.class))).thenReturn(java.util.Optional.of(card));

        Optional<Card> card = this.cardService.getCardById(uuid);

        assertEquals(card.get(), this.card);
        verify(cardRepository, times(1)).findById(uuid);
    }

    @Test
    void whenGetCardsShouldReturnListOfCards() {
        when(this.cardRepository.findAll()).thenReturn(Lists.newArrayList(card));

        assertThat(cardService.getCards().size(), is(1));
        verify(cardRepository, times(1)).findAll();
    }

    @Test
    void whenDeleteCardShouldCallMethodDeleteByIdOnce() {
        this.cardService.deleteCard(uuid);

        verify(this.cardRepository, times(1)).deleteById(uuid);
    }

    @Test
    void whenUpdateCardShouldReturnUpdatedCard() {
        when(this.cardRepository.save(any(Card.class))).thenReturn(card);

        Card actualCard = this.cardService.updateCard(card);

        assertEquals(actualCard, card);
        verify(cardRepository, times(1)).save(card);
    }

    @Test
    void whenFindCardByParamsShouldReturnPage() {
        when(this.cardRepository.findCardByParams(any(UUID.class), any(Pageable.class))).thenReturn(page);

        Page<Card> cardByParams = this.cardService.findCardByParams(this.searchParams);

        assertEquals(cardByParams, page);
        verify(cardRepository, times(1)).findCardByParams(searchParams.getCategory(), pageRequest);
    }
}