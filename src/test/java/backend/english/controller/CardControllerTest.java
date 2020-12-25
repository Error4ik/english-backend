package backend.english.controller;

import backend.english.domain.Card;
import backend.english.domain.Category;
import backend.english.service.CardService;
import backend.english.util.SearchParams;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CardController.class)
class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardService cardService;

    private final UUID uuid = UUID.randomUUID();

    private final Card card = new Card();

    private final Category category = new Category();

    private final SearchParams searchParams = new SearchParams();

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void init() {
        category.setId(uuid);
        category.setName("test");
        category.setNumberOfCards(5);

        card.setId(uuid);
        card.setWord("word");
        card.setTranslation("translate");
        card.setTranslatePhrase("translate phrase");
        card.setPhrase("phrase");
        card.setCategory(category);

        searchParams.setPageNumber(0);
        searchParams.setPageLimit(10);
        searchParams.setCategory(uuid);
    }

    @Test
    void whenMappingCardCardsShouldReturnStatusOkAndCallMethodGetCardsOnce() throws Exception {
        this.mockMvc
                .perform(
                        get("/card/cards")
                )
                .andExpect(status().isOk());

        verify(this.cardService, times(1)).getCards();
    }

    @Test
    void whenCardFindCardByParametersShouldReturnStatusOkAndCallMethodFindCardByParamsOnce() throws Exception {
        this.mockMvc
                .perform(
                        post("/card/find-cards-by-parameters")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(searchParams))
                )
                .andExpect(status().isOk());

        verify(this.cardService, times(1)).findCardByParams(searchParams);
    }

    @Test
    void whenMappingCardAddShouldReturnStatusOkAndCallMethodSaveCardOnce() throws Exception {
        this.mockMvc
                .perform(
                        post("/card/add")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(card))
                )
                .andExpect(status().isOk());

        verify(this.cardService, times(1)).saveCard(card);
    }
}