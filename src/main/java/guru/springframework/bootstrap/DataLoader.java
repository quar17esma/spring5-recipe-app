package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {
    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private CategoryRepository categoryRepository;

    public DataLoader(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {

        Recipe guacamole = makeGuacamoleRecipe();
        recipeRepository.save(guacamole);

        Recipe tacos = makeTacosRecipe();
        recipeRepository.save(tacos);
    }

    private Recipe makeGuacamoleRecipe() {
        Recipe guacamole = new Recipe();

        Set<Category> guacamoleCategories = makeGuacamoleCategories();
        Set<Ingredient> guacamoleIngredients = makeGuacamoleIngredients(guacamole);
        Notes guacamoleNotes = makeGuacamoleNotes(guacamole);

        guacamole.setCategories(guacamoleCategories);
        guacamole.setIngredients(guacamoleIngredients);
        guacamole.setNotes(guacamoleNotes);
        guacamole.setCookTime(0);
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setPrepTime(10);
        guacamole.setServings(3);
        guacamole.setSource("SimplyRecipes");
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. " +
                "(See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "");
        guacamole.setDescription("Perfect Guacamole");

        return guacamole;
    }

    private Set<Category> makeGuacamoleCategories() {
        Set<Category> guacamoleCategories = new HashSet<>();
        Category mexican = categoryRepository.findByDescription("Mexican").get();
        guacamoleCategories.add(mexican);

        return guacamoleCategories;
    }

    private Notes makeGuacamoleNotes(Recipe guacamole) {
        Notes guacamoleNotes = new Notes();
        guacamoleNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                "\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.");
        guacamoleNotes.setRecipe(guacamole);

        return guacamoleNotes;
    }

    private Set<Ingredient> makeGuacamoleIngredients(Recipe guacamole) {
        UnitOfMeasure piece = unitOfMeasureRepository.findByDescription("Piece").get();
        UnitOfMeasure teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon").get();
        UnitOfMeasure tablespoon = unitOfMeasureRepository.findByDescription("Tablespoon").get();
        UnitOfMeasure dash = unitOfMeasureRepository.findByDescription("Dash").get();


        Ingredient avocados = new Ingredient("ripe avocados", BigDecimal.valueOf(2), piece);
        Ingredient kosherSalt = new Ingredient("Kosher salt", BigDecimal.valueOf(0.5), teaspoon);
        Ingredient limeJuice = new Ingredient("fresh lime juice or lemon juice", BigDecimal.valueOf(1), tablespoon);
        Ingredient redOnion = new Ingredient("minced red onion or thinly sliced green onion", BigDecimal.valueOf(2), tablespoon);
        Ingredient chiles = new Ingredient("serrano chiles, stems and seeds removed, minced", BigDecimal.valueOf(2), piece);
        Ingredient cilantro = new Ingredient("cilantro (leaves and tender stems), finely chopped", BigDecimal.valueOf(2), tablespoon);
        Ingredient blackPepper = new Ingredient("freshly grated black pepper", BigDecimal.valueOf(1), dash);
        Ingredient tomato = new Ingredient("ripe tomato, seeds and pulp removed, chopped", BigDecimal.valueOf(0.5), piece);

        Set<Ingredient> guacamoleIngredients = new HashSet<>();
        Collections.addAll(guacamoleIngredients,
                avocados, kosherSalt, limeJuice, redOnion, chiles, cilantro, blackPepper, tomato);
        guacamoleIngredients.forEach(i -> i.setRecipe(guacamole));

        return guacamoleIngredients;
    }

    private Recipe makeTacosRecipe() {
        Recipe tacos = new Recipe();

        Set<Category> tacosCategories = makeTacosCategories();
        Set<Ingredient> tacosIngredients = makeTacosIngredients(tacos);
        Notes tacosNotes = makeTacosNotes(tacos);

        tacos.setCategories(tacosCategories);
        tacos.setIngredients(tacosIngredients);
        tacos.setNotes(tacosNotes);
        tacos.setDescription("Spicy Grilled Chicken Tacos");
        tacos.setCookTime(15);
        tacos.setDifficulty(Difficulty.EASY);
        tacos.setPrepTime(20);
        tacos.setServings(5);
        tacos.setSource("SimplyRecipes");
        tacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        tacos.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");

        return tacos;
    }

    private Set<Category> makeTacosCategories() {
        Set<Category> tacosCategories = new HashSet<>();
        Category mexican = categoryRepository.findByDescription("Mexican").get();
        tacosCategories.add(mexican);

        return tacosCategories;
    }

    private Notes makeTacosNotes(Recipe tacos) {
        Notes tacosNotes = new Notes();
        tacosNotes.setRecipeNotes("Spicy grilled chicken tacos! Quick marinade, then grill. Ready in about 30 minutes. " +
                "Great for a quick weeknight dinner, backyard cookouts, and tailgate parties.");
        tacosNotes.setRecipe(tacos);

        return tacosNotes;
    }

    private Set<Ingredient> makeTacosIngredients(Recipe tacos) {
        UnitOfMeasure tablespoon = unitOfMeasureRepository.findByDescription("Tablespoon").get();
        UnitOfMeasure teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon").get();
        UnitOfMeasure piece = unitOfMeasureRepository.findByDescription("Piece").get();

        Ingredient chiliPowder = new Ingredient("ancho chili powder", BigDecimal.valueOf(2), tablespoon);
        Ingredient driedOregano = new Ingredient("dried oregano", BigDecimal.valueOf(1), teaspoon);
        Ingredient driedCumin = new Ingredient("dried cumin", BigDecimal.valueOf(1), teaspoon);
        Ingredient sugar = new Ingredient("sugar", BigDecimal.valueOf(1), teaspoon);
        Ingredient salt = new Ingredient("salt", BigDecimal.valueOf(0.5), teaspoon);
        Ingredient garlic = new Ingredient("clove garlic, finely chopped", BigDecimal.valueOf(1), piece);
        Ingredient orangeZest = new Ingredient("finely grated orange zest", BigDecimal.valueOf(1), tablespoon);

        Set<Ingredient> tacosIngredients = new HashSet<>();
        Collections.addAll(tacosIngredients,
                chiliPowder, driedOregano, driedCumin, sugar, salt, garlic, orangeZest);
        tacosIngredients.forEach(i -> i.setRecipe(tacos));

        return tacosIngredients;
    }
}
