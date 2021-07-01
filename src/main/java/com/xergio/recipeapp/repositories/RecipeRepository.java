package com.xergio.recipeapp.repositories;

import com.xergio.recipeapp.domian.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository <Recipe,Long> {



}
