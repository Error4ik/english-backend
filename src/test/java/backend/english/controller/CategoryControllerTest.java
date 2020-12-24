package backend.english.controller;

import backend.english.domain.Category;
import backend.english.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    private final Category category = new Category();

    private final UUID uuid = UUID.randomUUID();

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void init() {
        category.setId(uuid);
        category.setName("test");
        category.setNumberOfCards(5);
    }

    @Test
    void whenMappingCategoryCategoriesShouldReturnStatusOkAndCallGetCategoriesMethodOnce() throws Exception {
        this.mockMvc
                .perform(
                        get("/category/categories")
                )
                .andExpect(status().isOk());

        verify(this.categoryService, times(1)).getCategories();
    }

    @Test
    void whenMappingCategoryAddShouldReturnStatusOkAndCallAddCategoryOnce() throws Exception {
        this.mockMvc
                .perform(
                        post("/category/add")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(category))
                )
                .andExpect(status().isOk());

        verify(this.categoryService, times(1)).saveCategory(category);
    }

    @Test
    void whenMappingCategoryUpdateShouldReturnStatusOkAndCallAddCategoryOnce() throws Exception {
        this.mockMvc
                .perform(
                        post("/category/update")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(category))
                )
                .andExpect(status().isOk());

        verify(this.categoryService, times(1)).updateCategory(category);
    }

    @Test
    void whenMappingCategoryDeleteShouldReturnStatusOkAndCallDeleteCategoryMethodOnce() throws Exception {
        this.mockMvc
                .perform(
                        delete("/category/delete/{id}", uuid)
                )
                .andExpect(status().isOk());

        verify(this.categoryService, times(1)).deleteCategory(uuid);
    }

    @Test
    void whenMappingCategoryDeleteShouldReturnThrowEmptyResultDataAccessExceptionAndCallDeleteCategoryMethodOnce() throws Exception {
        doThrow(new EmptyResultDataAccessException(0)).when(categoryService).deleteCategory(uuid);
        this.mockMvc
                .perform(
                        delete("/category/delete/{id}", uuid)
                )
                .andExpect(status().isOk());

        verify(this.categoryService, times(1)).deleteCategory(uuid);
    }
}
