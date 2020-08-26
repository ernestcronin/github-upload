package recipes.app.dto;

import java.util.List;

public class RecipeDto {
	
	private String recipe;
	private String description;
	private List<IngredientValuesDto> ingredientValues;
	
	
	
	public String getRecipe() {
		return recipe;
	}

	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<IngredientValuesDto> getIngredientValues() {
		return ingredientValues;
	}

	public void setIngredientValues(List<IngredientValuesDto> ingredientValues) {
		this.ingredientValues = ingredientValues;
	}


}
