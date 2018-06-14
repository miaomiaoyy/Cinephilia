package reviewApp.model;

public class FoodCartRestaurants extends Restaurants {

	protected boolean Licensed;
	public FoodCartRestaurants(String name, String description, String menu, String hour, String street1, String street2,
			String city, String state, String companyNamme, int zip, boolean active, Type cuisineType, boolean licensed) {
		super(name, description, menu, hour, street1, street2, city, state, companyNamme, zip, active, cuisineType);
		this.Licensed = licensed;
	}
	
	public FoodCartRestaurants(Restaurants restaurant, boolean licensed) {
		super(restaurant);
		this.Licensed = licensed;
	}
	
	
	@Override
	public String toString() {
		return super.toString()+"\n"+  "FoodCartRestaurants [Licensed=" + Licensed + "]";
	}

	public boolean isLicensed() {
		return Licensed;
	}
	public void setLicensed(boolean licensed) {
		this.Licensed = licensed;
	}

}
