package com.xergio.recipeapp.controllers;

import com.xergio.recipeapp.domian.Category;
import com.xergio.recipeapp.domian.UnitOfMeasure;
import com.xergio.recipeapp.repositories.CategoryRepository;
import com.xergio.recipeapp.repositories.UnitOfMeasureRepository;
import com.xergio.recipeapp.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
public class IndexController {

//    private CategoryRepository categoryRepository;
//    private UnitOfMeasureRepository unitOfMeasureRepository;

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","/index"})
    public String getIndexPage(Model model){
        log.debug("Getting index page ...");
        model.addAttribute("recipes", recipeService.getRecipes());

//        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
//        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

//        System.out.println("Cat ID is :: "+categoryOptional.get().getId());
//        System.out.println("UOM id is :: "+unitOfMeasureOptional.get().getId());
        return "index";
    }

}
