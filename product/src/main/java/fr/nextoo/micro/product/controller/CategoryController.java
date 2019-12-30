package fr.nextoo.micro.product.controller;

import fr.nextoo.micro.common.dto.product.CategoryDto;
import fr.nextoo.micro.product.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    private ResponseEntity<Collection<CategoryDto>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

}
