package com.xergio.recipeapp.bootstrap;

import com.xergio.recipeapp.domian.*;
import com.xergio.recipeapp.repositories.CategoryRepository;
import com.xergio.recipeapp.repositories.RecipeRepository;
import com.xergio.recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
        log.debug("loading bootstrap Data ...");
    }

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    private List<Recipe> getRecipes(){

        List<Recipe> recipes = new ArrayList<Recipe>(2);

        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
        if(!eachUomOptional.isPresent()){
            throw new RuntimeException("Expected UNO not Found");
        }
        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if(!tableSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UNO not Found");
        }
        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if(!teaSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UNO not Found");
        }
        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
        if(!dashUomOptional.isPresent()){
            throw new RuntimeException("Expected UNO not Found");
        }
        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
        if(!pintUomOptional.isPresent()){
            throw new RuntimeException("Expected UNO not Found");
        }
        Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");
        if(!cupsUomOptional.isPresent()){
            throw new RuntimeException("Expected UNO not Found");
        }

        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoomUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure cupsUom = cupsUomOptional.get();

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if(!americanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected UNO not Found");
        }
        Optional<Category> italianCategoryOptional = categoryRepository.findByDescription("Italian");
        if(!italianCategoryOptional.isPresent()){
            throw new RuntimeException("Expected UNO not Found");
        }
        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if(!mexicanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected UNO not Found");
        }
        Optional<Category> fastFoodCategoryOptional = categoryRepository.findByDescription("Fast Food");
        if(!fastFoodCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected UNO not Found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();


        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setServings(4);
        guacRecipe.setSource("Simply Recipes");
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacRecipe.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) " +
                "Place in a bowl."
                +"\n"+
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)"
                +"\n"+"3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("The word “guacamole”, and the dip, are both originally from Mexico, where avocados have been cultivated for thousands of years. The name is derived from two Aztec Nahuatl words—ahuacatl (avocado) and molli (sauce).");
        guacRecipe.setNotes(guacNotes);
        //guacNotes.setRecipe(guacRecipe);
        guacRecipe.addIngredient(new Ingredient("Ripe Avocadoes",new BigDecimal(2),eachUom));
        guacRecipe.addIngredient(new Ingredient("Kosher Salt",new BigDecimal(.24),teaSpoonUom));
        guacRecipe.addIngredient(new Ingredient("fresh lime or lemon juice",new BigDecimal(1),tableSpoomUom));
        guacRecipe.addIngredient(new Ingredient("minced red onions or thinly sliced green onion",new BigDecimal(2),tableSpoomUom));
        guacRecipe.addIngredient(new Ingredient("Freshly grated black pepper",new BigDecimal(2),dashUom));
        guacRecipe.addIngredient(new Ingredient("Cilantro",new BigDecimal(2),tableSpoomUom));
        guacRecipe.addIngredient(new Ingredient("ripe tomatoe, seeds, and pulp removed ",new BigDecimal(.5),eachUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        recipes.add(guacRecipe);

        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Tacos");
        tacosRecipe.setPrepTime(9);
        tacosRecipe.setCookTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);
        tacosRecipe.setSource("Simply Recipes");
        tacosRecipe.setServings(6);
        tacosRecipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n"+
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");
        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.");

        tacosRecipe.setNotes(tacoNotes);
        //tacoNotes.setRecipe(tacosRecipe);
        tacosRecipe.addIngredient(new Ingredient("ancho chilli powder",new BigDecimal(2),tableSpoomUom));
        tacosRecipe.addIngredient(new Ingredient("dried oregano",new BigDecimal(1),teaSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("dried cummin",new BigDecimal(1),teaSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("sugar",new BigDecimal(1),teaSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("salt",new BigDecimal(.5),teaSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("clove garlic, finely chopped",new BigDecimal(1), eachUom));
        tacosRecipe.addIngredient(new Ingredient("finely grated orange zest",new BigDecimal(1),teaSpoonUom));
        tacosRecipe.getCategories().add(mexicanCategory);
        recipes.add(tacosRecipe);

        return recipes;
    }

}
