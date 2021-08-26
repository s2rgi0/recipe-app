package com.xergio.recipeapp.service;

import com.xergio.recipeapp.commands.IngredientCommand;
import com.xergio.recipeapp.converters.IngredientCommandToIngredient;
import com.xergio.recipeapp.converters.IngredientToIngredientCommand;
import com.xergio.recipeapp.domian.Ingredient;
import com.xergio.recipeapp.domian.Recipe;
import com.xergio.recipeapp.repositories.RecipeRepository;
import com.xergio.recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;


    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!(recipeOptional).isPresent()){

            log.error("recipe id not found. Id: " + recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map( ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        if(!ingredientCommandOptional.isPresent()){

            log.error("Ingredient id not found: " + ingredientId);
        }else{
            return ingredientCommandOptional.get();
        }

        return null;
    }

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        log.info("saveIngredient :::::::::   command.toString "+command.toString());
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if(!recipeOptional.isPresent()){

            //todo toss error if not found!
            log.error("Recipe not found for id: " + command.getRecipeId());
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();
            log.info("ingredient Optional ");
            try{
                if(ingredientOptional.isPresent()){
                    Ingredient ingredientFound = ingredientOptional.get();
                    ingredientFound.setDescription(command.getDescription());
                    ingredientFound.setAmount(command.getAmount());
                    ingredientFound.setUom(unitOfMeasureRepository
                            .findById(command.getUom().getId())
                            .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
                } else {
                    //add new Ingredient
                    Ingredient ingredient = ingredientCommandToIngredient.convert(command);
                    ingredient.setRecipe(recipe);
                    recipe.addIngredient(ingredientCommandToIngredient.convert(command));
                }

                Recipe savedRecipe = recipeRepository.save(recipe);

                Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                        .findFirst();

                if(!savedIngredientOptional.isPresent()){
                    //not totally safe... But best guess
                    savedIngredientOptional = savedRecipe.getIngredients().stream()
                            .filter(recipeIngredients -> recipeIngredients.getDescription().equals(command.getDescription()))
                            .filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
                            .filter(recipeIngredients -> recipeIngredients.getUom().getId().equals(command.getUom().getId()))
                            .findFirst();
                }

                //to do check for fail
                return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
            }
            catch(Exception ex){
                ex.printStackTrace();
            }


            return null;

        }
    }


}
