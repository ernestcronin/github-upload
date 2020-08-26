package recipes.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RecipeIngredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private long id;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	private Recipe recipe;
	private String quantity;	
	private String measurement;
	private String ingredientName;
	
	public RecipeIngredient() {}
	
	
	public RecipeIngredient(Recipe recipe, String quantity, String measurement, String ingredientName) {
		super();
		this.recipe = recipe;
		this.quantity = quantity;
		this.measurement = measurement;
		this.ingredientName = ingredientName;
	}
	
	public Recipe getRecipe() {
		return recipe;
	}
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getMeasurement() {
		return measurement;
	}
	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}
	public String getIngredientName() {
		return ingredientName;
	}
	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}
	
	public long getRecipeIdent() {
		return this.getRecipe().getId();
	}
	
	@Override
	public String toString() {
		return "RecipeIngredient [id=" + id + ", recipe=" + recipe + ", quantity=" + quantity + ", measurement="
				+ measurement + ", ingredientName=" + ingredientName + "]";
	}
}

