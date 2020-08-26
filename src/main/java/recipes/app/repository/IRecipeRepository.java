package recipes.app.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recipes.app.model.RecipeIngredient;

@Repository
public interface IRecipeRepository extends JpaRepository<RecipeIngredient, Long> {

	Optional<List<RecipeIngredient>> findAllByRecipeId(long id);
	
	List<RecipeIngredient> findByRecipeName(String recipeName);

	List<RecipeIngredient> findByRecipeNameLike(String recipeName);
	
}
