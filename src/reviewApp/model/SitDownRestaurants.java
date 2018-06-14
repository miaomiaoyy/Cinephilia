package reviewApp.model;

import reviewApp.model.Restaurants;

public class SitDownRestaurants extends Restaurants {

	protected int Capacity;
	
	
	public SitDownRestaurants(String name, String description, String menu, String hour, String street1, String street2,
			String city, String state, String companyNamme, int zip, boolean active, Type cuisineType, int Capacity) {
		super(name, description, menu, hour, street1, street2, city, state, companyNamme, zip, active, cuisineType);
		this.Capacity = Capacity;
	}

	

	public SitDownRestaurants(Restaurants restaurant, int capacity) {
		super(restaurant);
		this.Capacity = capacity;
	}



	@Override
	public String toString() {
		return super.toString()+"\n"+ "SitDownRestaurants [Capacity=" + Capacity + "]";
		
	}



	public int getCapacity() {
		return Capacity;
	}


	public void setCapacity(int capacity) {
		Capacity = capacity;
	}
	

}
