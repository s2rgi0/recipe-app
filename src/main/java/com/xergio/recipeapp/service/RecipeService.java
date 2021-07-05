package com.xergio.recipeapp.service;

import com.xergio.recipeapp.commands.RecipeCommand;
import com.xergio.recipeapp.domian.Recipe;
import java.util.Set;


public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand findCommandById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    void deleteById(Long idToDelete);

}
