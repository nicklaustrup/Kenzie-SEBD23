package com.kenzie.groupactivity.icecream;

import com.kenzie.groupactivity.icecream.converter.RecipeConverter;
import com.kenzie.groupactivity.icecream.dao.CartonDao;
import com.kenzie.groupactivity.icecream.dao.RecipeDao;
import com.kenzie.groupactivity.icecream.exception.CartonCreationFailedException;
import com.kenzie.groupactivity.icecream.exception.RecipeNotFoundException;
import com.kenzie.groupactivity.icecream.model.Carton;
import com.kenzie.groupactivity.icecream.model.Ingredient;
import com.kenzie.groupactivity.icecream.model.Recipe;
import com.kenzie.groupactivity.icecream.model.Sundae;

import com.google.common.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.inject.Inject;

/**
 * Provides Ice Cream Parlor functionality.
 */
public class IceCreamParlorService {
    private final RecipeDao recipeDao;
    private final CartonDao cartonDao;
    private final IceCreamMaker iceCreamMaker;

    /**
     * Constructs service with the provided DAOs.
     * @param recipeDao the RecipeDao to use for accessing recipes
     * @param cartonDao the CartonDao to use for accessing ice cream cartons
     * @param iceCreamMaker the IceCreamMaker
     */
    @Inject
    public IceCreamParlorService(RecipeDao recipeDao, CartonDao cartonDao, IceCreamMaker iceCreamMaker) {
        this.recipeDao = recipeDao;
        this.cartonDao = cartonDao;
        this.iceCreamMaker = iceCreamMaker;
    }

    /**
     * Creates and returns the sundae defined by the given ice cream flavors.
     * If a flavor is not found or we have none of that flavor left, the sundae
     * is returned, but without that flavor. (We'll only charge the customer for
     * the scoops they are returned)
     * @param flavorNames List of flavors to include in the sundae
     * @return The newly created Sundae
     */
    public Sundae getSundae(List<String> flavorNames) {
        // This does the filtering out of any unknown flavors, so only
        // Cartons of known flavors will be returned.
        List<Carton> cartons = cartonDao.getCartonsByFlavorNames(flavorNames);

        // PHASE 1: Use removeIf() to remove any empty cartons from cartons
        cartons.removeIf(Carton::isEmpty);

        return buildSundae(cartons);
    }

    @VisibleForTesting
    public Sundae buildSundae(List<Carton> cartons) {
        Sundae sundae = new Sundae();

        // PHASE 2: Use forEach() to add one scoop of each flavor
        // remaining in cartons
        cartons.forEach(carton -> sundae.addScoop(carton.getFlavor()));

        return sundae;
    }

    /**
     * Prepares the specified flavors, creating 1 carton of each provided
     * flavor.
     *
     * A flavor name that doesn't correspond
     * to a known recipe will result in CartonCreationFailedException, and
     * no Cartons will be created.
     *
     * @param flavorNames List of names of flavors to create new batches of
     * @return the number of cartons produced by the ice cream maker
     */
    public int prepareFlavors(List<String> flavorNames) {
        List<Recipe> recipes = map(
            flavorNames,
                flavorName -> {
                    // trap the checked exception, RecipeNotFoundException, and
                    // wrap in a runtime exception because our lambda can't throw
                    // checked exceptions
                    try {
                        return recipeDao.getRecipe(flavorName);
                    } catch (RecipeNotFoundException e) {
                        throw new CartonCreationFailedException("Could not find recipe for " + flavorName, e);
                    }
                }
        );

        // PHASE 3: Replace right-hand side: use map() to convert List<Recipe> to List<Queue<Ingredient>>
        List<Queue<Ingredient>> ingredientQueues = map(recipes, RecipeConverter::fromRecipeToIngredientQueue);

        return makeIceCreamCartons(ingredientQueues);
    }

    @VisibleForTesting
    public int makeIceCreamCartons(List<Queue<Ingredient>> ingredientQueues) {
        // don't change any of the lines that touch cartonsCreated.
        int cartonsCreated = 0;
        for (Queue<Ingredient> ingredients : ingredientQueues) {

            // For the future: provide Supplier to prepareIceCream()
            if (iceCreamMaker.prepareIceCreamCarton(null)) {
                cartonsCreated++;
            }
        }

        return cartonsCreated;
    }

    /**
     * Converts input list of type T to a List of type R, where each entry in the return
     * value is the output of converter applied to each entry in input.
     *
     * (We will get to Java streams in a later lesson, at which point we won't need a helper method
     * like this.)
     */
    private <T, R> List<R> map(List<T> input, Function<T, R> converter) {
        return input.stream()
            .map(converter)
            .collect(Collectors.toList());
    }
}
