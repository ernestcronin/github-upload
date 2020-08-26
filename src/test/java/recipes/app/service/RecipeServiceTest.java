package recipes.app.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import recipes.app.config.TestConfig;
import recipes.app.model.RecipeIngredient;

@SpringJUnitConfig(classes = TestConfig.class)
public class RecipeServiceTest {

	@Autowired
	private IRecipeService recipeService;
	
	@Test
	public void whenSearchByRecipeName_thenOK() {
		List<RecipeIngredient> recipeIngredients = TestConfig.buildRecipeIngredientList();
		recipeService.saveAllRecipeIngredient(recipeIngredients);
		String recipeName = TestConfig.recipeItem().getName();
		List<RecipeIngredient> foundRecipeIngredients = recipeService.findAllByRecipeName(recipeName);
		assertEquals(recipeName, foundRecipeIngredients.get(0).getRecipe().getName());
	}
	
	@Test
	public void whenSaveRecipe_thenOK() {
		List<RecipeIngredient> recipeIngredients = TestConfig.buildRecipeIngredientList();
		List<RecipeIngredient> savedRecipeIngredients = recipeService.saveAllRecipeIngredient(recipeIngredients);
		assertNotNull(savedRecipeIngredients);
		assertEquals(recipeIngredients.toString(), savedRecipeIngredients.toString());
	}
	
	@Test
	public void whenDeleteRecipe_thenOK() {
		List<RecipeIngredient> recipeIngredients = TestConfig.buildRecipeIngredientList();
		List<RecipeIngredient> savedRecipeIngredients = recipeService.saveAllRecipeIngredient(recipeIngredients);
		assertNotNull(savedRecipeIngredients);
		long recipeId = savedRecipeIngredients.get(0).getRecipe().getId();
		recipeService.deleteRecipe(savedRecipeIngredients);
		assertFalse(recipeService.findAllByRecipeId(recipeId).isPresent());
	}
}
