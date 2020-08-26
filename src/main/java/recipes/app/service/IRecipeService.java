package recipes.app.service;

import java.util.List;
import java.util.Optional;

import recipes.app.model.RecipeIngredient;

public interface IRecipeService {
	
	Optional<List<RecipeIngredient>> findAllByRecipeId(long id);
	
	void deleteRecipeById(long id);
	
	List<RecipeIngredient> saveAllRecipeIngredient(List<RecipeIngredient> recipeIngredient);
	
	List<RecipeIngredient> findAllByRecipeName(String name);
	
	List<RecipeIngredient> findAllByRecipeNameLike(String name);

	void deleteRecipe(List<RecipeIngredient> recipeIngredient);

}
