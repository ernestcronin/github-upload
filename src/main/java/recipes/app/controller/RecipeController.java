package recipes.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import recipes.app.dto.RecipeDto;
import recipes.app.dto.helper.RecipeDtoHelper;
import recipes.app.model.RecipeIngredient;
import recipes.app.service.IRecipeService;
import recipes.app.validation.RequestValidation;

@RestController
@RequestMapping(value = "/recipe")
public class RecipeController {

	
	@Autowired
	IRecipeService recipeService;
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<RecipeDto> findOne(@PathVariable Long id){
		List<RecipeIngredient> recipeIngredientList = recipeService.findAllByRecipeId(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find recipe with id " + id));	
		RecipeDtoHelper rdh = new RecipeDtoHelper();
		RecipeDto dto = rdh.transformIngredientListToRecipeDto(recipeIngredientList);
		
		return new ResponseEntity<RecipeDto>(dto, HttpStatus.OK);			
	}
	
	
	@PostMapping(value="/create")
	public ResponseEntity<RecipeDto> createRecipe(@RequestBody RecipeDto recipeDto) {		
		String message = RequestValidation.isValidCreateRecipe(recipeDto);
		if(!"success".equals(message)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
		}
		RecipeDtoHelper rdh = new RecipeDtoHelper();
		List<RecipeIngredient> recipe = rdh.transformRecipeDtoToIngredientList(recipeDto);
		List<RecipeIngredient> savedList = recipeService.saveAllRecipeIngredient(recipe);
		
		return new ResponseEntity<RecipeDto>(rdh.transformIngredientListToRecipeDto(savedList), HttpStatus.CREATED);
	}
	
	
	@GetMapping(value ="/searchRecipes/{recipeName}")
	public ResponseEntity<List<RecipeDto>> searchRecipesByName(@PathVariable String recipeName) {
		
		List<RecipeIngredient> manyRecipesList = recipeService.findAllByRecipeName(recipeName);
		if(manyRecipesList == null || manyRecipesList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find recipe with exact name: " + recipeName);
		}
		RecipeDtoHelper rdh = new RecipeDtoHelper();
		List<RecipeDto> manyRecipeDto = rdh.transformManyIngredientListToManyRecipeDto(manyRecipesList);
		return new ResponseEntity<List<RecipeDto>>(manyRecipeDto, HttpStatus.OK);	
	}
	
	@GetMapping(value ="/searchRecipesPartial/{recipeName}")
	public ResponseEntity<List<RecipeDto>> searchRecipesByNameLike(@PathVariable String recipeName) {	
		List<RecipeIngredient> manyRecipesList = recipeService.findAllByRecipeNameLike(recipeName);
		if(manyRecipesList == null || manyRecipesList.isEmpty()) {
			throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Search of " + recipeName + " did not return any results.");
		}
		RecipeDtoHelper rdh = new RecipeDtoHelper();
		List<RecipeDto> manyRecipeDto = rdh.transformManyIngredientListToManyRecipeDto(manyRecipesList);
		return new ResponseEntity<List<RecipeDto>>(manyRecipeDto, HttpStatus.OK);	
	}
	
	@DeleteMapping(value="/deleteRecipeById/{id}")
	public ResponseEntity<RecipeDto> deleteRecipeById(@PathVariable Long id){
		List<RecipeIngredient> recipeToDelete = recipeService.findAllByRecipeId(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find recipe with id " + id));
		try {
		recipeService.deleteRecipe(recipeToDelete);
		}catch(EmptyResultDataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find Recipe with ID: " + id);
		}
		RecipeDtoHelper rdh = new RecipeDtoHelper();
		RecipeDto dto = rdh.transformIngredientListToRecipeDto(recipeToDelete);
		return new ResponseEntity<RecipeDto>(dto, HttpStatus.OK);
	}
	
}
