package recipes.app.dto.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import recipes.app.dto.IngredientValuesDto;
import recipes.app.dto.RecipeDto;
import recipes.app.model.Recipe;
import recipes.app.model.RecipeIngredient;

public class RecipeDtoHelper {
	
	public List<RecipeDto> transformManyIngredientListToManyRecipeDto(List<RecipeIngredient> list){
		if(list != null && !list.isEmpty())
			return manyIngredientListToManyRecipeDto(list);
		return null;
	}

	public RecipeDto transformIngredientListToRecipeDto(List<RecipeIngredient> list) {
		if(list != null && !list.isEmpty())
			return ingredientListTORecipeDto(list);
		return null;
	}
	
	
	public List<RecipeIngredient> transformRecipeDtoToIngredientList(RecipeDto dto){
		return recipeDtoToIngredientList(dto);
	}
	
	private RecipeDto ingredientListTORecipeDto(List<RecipeIngredient> list) {
		
		RecipeDto dto = new RecipeDto();
		dto.setRecipe(list.get(0).getRecipe().getName());
		dto.setDescription(list.get(0).getRecipe().getDescription());
		List<IngredientValuesDto> ivList = new ArrayList<>();
		
		for(RecipeIngredient ri : list) {			
			String quantity = ri.getQuantity();
			String measurement = ri.getMeasurement();
			String ingredient = ri.getIngredientName();
			IngredientValuesDto iv = new IngredientValuesDto(quantity, measurement, ingredient);
			ivList.add(iv);
		}	
		dto.setIngredientValues(ivList);
		return dto;
	}
	
	private List<RecipeIngredient> recipeDtoToIngredientList(RecipeDto dto) {
		String name = dto.getRecipe();
		String desc = dto.getDescription();
		Recipe recipe = new Recipe(name, desc);
		List<RecipeIngredient> riList = new ArrayList<>();
		List<IngredientValuesDto> igValList = dto.getIngredientValues();
		for(IngredientValuesDto igv: igValList) {
			RecipeIngredient ri = new RecipeIngredient();
			ri.setRecipe(recipe);
			ri.setMeasurement(igv.getMeasurement());
			ri.setQuantity(igv.getQuantity());
			ri.setIngredientName(igv.getIngredientName());
			riList.add(ri);
		}
		
		return riList;
	}
	
	private List<RecipeDto> manyIngredientListToManyRecipeDto(List<RecipeIngredient> manyRecipeList){
		
		Map<Long, List<RecipeIngredient>> myMap =
				manyRecipeList.stream().collect(Collectors.groupingBy(RecipeIngredient::getRecipeIdent));
		
		List<RecipeDto> manyRecipeDtos = new ArrayList<>();
		for (Map.Entry<Long,List<RecipeIngredient>> entry : myMap.entrySet()) {
			List<RecipeIngredient> uniqueRecipe = entry.getValue();
			RecipeDto uniqueRecipeDto = ingredientListTORecipeDto(uniqueRecipe);
			manyRecipeDtos.add(uniqueRecipeDto);
		}
		return manyRecipeDtos;
	}
}
