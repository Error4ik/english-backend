package backend.english.controller;

import backend.english.domain.Category;
import backend.english.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(this.categoryService.getCategories());
    }

    @RequestMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody @NonNull Category category) {
        return ResponseEntity.ok(this.categoryService.saveCategory(category));
    }

    @RequestMapping("/update")
    public ResponseEntity<Category> updateCategory(@RequestBody @NonNull Category category) {
        return ResponseEntity.ok(this.categoryService.updateCategory(category));
    }

    @RequestMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable UUID id) {
        try {
            this.categoryService.deleteCategory(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
