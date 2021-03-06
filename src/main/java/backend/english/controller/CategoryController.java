package backend.english.controller;

import backend.english.domain.Category;
import backend.english.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("category")
public class CategoryController {

    private final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(this.categoryService.getCategories());
    }

    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody @NonNull Category category) {
        logger.info(String.format("Input arguments: %s", category));
        return ResponseEntity.ok(this.categoryService.saveCategory(category));
    }

    @PostMapping("/update")
    public ResponseEntity<Category> updateCategory(@RequestBody @NonNull Category category) {
        logger.info(String.format("Input arguments: %s", category));
        return ResponseEntity.ok(this.categoryService.updateCategory(category));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable UUID id) {
        logger.info(String.format("Input arguments: %s", id));
        try {
            this.categoryService.deleteCategory(id);
        } catch (EmptyResultDataAccessException e) {
            logger.error(String.format("EmptyResultDataAccessException: %s", e));
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
