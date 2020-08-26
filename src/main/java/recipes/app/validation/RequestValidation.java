package recipes.app.validation;

import recipes.app.dto.IngredientValuesDto;
import recipes.app.dto.RecipeDto;

public class RequestValidation {

	public static String isValidCreateRecipe(RecipeDto recipe) {
		if(recipe.getRecipe() == null || recipe.getRecipe().isEmpty()) 
			return "Recipe name must be provided";		
		if(recipe.getDescription() == null || recipe.getDescription().isEmpty())
			return "Recipe description must be provided";
		if(recipe.getIngredientValues() == null || recipe.getIngredientValues().isEmpty())
			return "Ingredient list must be provided";
		for(IngredientValuesDto value : recipe.getIngredientValues()) {
			if(value.getIngredientName() == null || value.getIngredientName().isEmpty())
				return "The ingredient name must be provided";
			if(value.getMeasurement() == null || value.getMeasurement().isEmpty())
				return "The measurment of the ingredient must be provided";
			if(value.getQuantity() == null || value.getQuantity().isEmpty())
				return "The quantity of the ingredient must be provided.";			
		}
		return "success";
		
	}
}
