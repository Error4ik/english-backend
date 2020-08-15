package backend.english.service;

import backend.english.domain.Category;
import backend.english.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category saveCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    public Optional<Category> getCategoryById(UUID categoryId) {
        return this.categoryRepository.findById(categoryId);
    }

    public List<Category> getCategories() {
        return this.categoryRepository.findAll();
    }

    public void deleteCategory(UUID categoryId) {
        this.categoryRepository.deleteById(categoryId);
    }

    public Category updateCategory(Category category) {
        return this.categoryRepository.save(category);
    }
}
