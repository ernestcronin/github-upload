package recipes.app.dto;

public class IngredientValuesDto {

	public String quantity;
	public String measurement;
	public String ingredientName;
	
	public IngredientValuesDto(String quantity, String measurement, String ingredientName) {
		super();
		this.quantity = quantity;
		this.measurement = measurement;
		this.ingredientName = ingredientName;
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
	
	
}
