package com.xergio.recipeapp.repositories;

import com.xergio.recipeapp.domian.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {


}
