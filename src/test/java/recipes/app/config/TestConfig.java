package recipes.app.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import recipes.app.model.Recipe;
import recipes.app.model.RecipeIngredient;

@Configuration
@ComponentScan("recipes.app")
public class TestConfig {
	
	public static Recipe recipeItem() {
		Recipe recipe = new Recipe("Whipped Cream", "creamy whipped cream");
		return recipe;
	}

	public static List<RecipeIngredient> buildRecipeIngredientList() {
		RecipeIngredient val1 = new RecipeIngredient(recipeItem(), "1", "quart", "heavy cream");
		RecipeIngredient val2 = new RecipeIngredient(recipeItem(), "1/2", "cup", "sugar");
		List<RecipeIngredient> ingredients = Arrays.asList(val1, val2);
		return ingredients;
	}
}
