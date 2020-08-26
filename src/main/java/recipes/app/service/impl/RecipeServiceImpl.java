package recipes.app.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import recipes.app.model.RecipeIngredient;
import recipes.app.repository.IRecipeRepository;
import recipes.app.service.IRecipeService;

@Service
public class RecipeServiceImpl implements IRecipeService {

	@Autowired
	IRecipeRepository recipeRepository;
	
	private static final Logger LOG = LoggerFactory.getLogger(RecipeServiceImpl.class);

	@Override
	public Optional<List<RecipeIngredient>> findAllByRecipeId(long id) {
		
		return recipeRepository.findAllByRecipeId(id);
	}

	@Override
	public List<RecipeIngredient> saveAllRecipeIngredient(List<RecipeIngredient> recipeIngredient) {
		LOG.debug("Recipe Service >> Save All Recipe Ingredient {}", 
								 recipeIngredient.stream().map(Object::toString).collect(Collectors.joining(",")));
		return recipeRepository.saveAll(recipeIngredient);
	}


	@Override
	public List<RecipeIngredient> findAllByRecipeName(String name) {
		LOG.debug("Recipe Service >> Find recipe by name - {}", name);
		List<RecipeIngredient> list= recipeRepository.findByRecipeName(name);
		if(list == null || list.isEmpty())
			return null;
		return list;
	}

	@Override
	public List<RecipeIngredient> findAllByRecipeNameLike(String name) {
		LOG.debug("Recipe Service >> Find recipes by similar name - {}", name);
		List<RecipeIngredient> list= recipeRepository.findByRecipeNameLike(name);
		if(list == null || list.isEmpty())
			return null;
		return list;
	}

	@Override
	public void deleteRecipeById(long id) {
		LOG.debug("Recipe Service >> Delete recipe by id - {}", id);
		recipeRepository.deleteById(id);	
	}
	
	@Override
	public void deleteRecipe(List<RecipeIngredient> recipeIngredient) {
		LOG.debug("Recipe Service >> Delete recipe with number of - {}", 
				 recipeIngredient.stream().map(Object::toString).collect(Collectors.joining(",")));
		recipeRepository.deleteAll(recipeIngredient);
	}
	

}
