package com.training.debonair.controller.client;

import com.training.debonair.entity.Categories;
import com.training.debonair.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addCategory")
    public Categories addCategory(@RequestBody Categories categories) throws IOException {
        return categoryService.add(categories);
    }
}
