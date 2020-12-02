package backend.english.service;

import backend.english.domain.Category;
import backend.english.repository.CategoryRepository;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    private final CategoryRepository categoryRepository = mock(CategoryRepository.class);

    private final UUID uuid = UUID.randomUUID();

    private final Category category = new Category();

    @BeforeEach
    public void init() {
        category.setId(uuid);
        category.setName("test");
        category.setNumberOfCards(5);
    }

    private final CategoryService categoryService = new CategoryService(categoryRepository);

    @Test
    void whenSaveCategoryShouldReturnSavedCategory() {
        when(this.categoryRepository.save(any(Category.class))).thenReturn(category);

        Category actualCategory = this.categoryService.saveCategory(category);

        assertEquals(actualCategory, category);
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    void whenGetCategoryByIdShouldReturnCategory() {
        when(this.categoryRepository.findById(any(UUID.class))).thenReturn(java.util.Optional.of(category));

        Optional<Category> category = this.categoryService.getCategoryById(uuid);

        assertEquals(category.get(), this.category);
        verify(categoryRepository, times(1)).findById(uuid);
    }

    @Test
    void whenGetCategoriesShouldReturnListOfCategory() {
        when(this.categoryRepository.findAll()).thenReturn(Lists.newArrayList(category));

        assertThat(categoryService.getCategories().size(), is(1));
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void whenDeleteCategoryShouldCallMethodDeleteCategoryOnce() {
        this.categoryService.deleteCategory(uuid);

        verify(this.categoryRepository, times(1)).deleteById(uuid);
    }

    @Test
    void whenUpdateCategoryShouldReturnUpdatedCategory() {
        when(this.categoryRepository.save(any(Category.class))).thenReturn(category);

        Category actualCategory = this.categoryService.updateCategory(category);

        assertEquals(actualCategory, category);
        verify(categoryRepository, times(1)).save(category);
    }
}