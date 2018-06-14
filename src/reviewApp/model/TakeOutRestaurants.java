package reviewApp.model;

import reviewApp.model.Restaurants;

public class TakeOutRestaurants extends Restaurants {
protected int MaxWaitTime;
	public TakeOutRestaurants(String name, String description, String menu, String hour, String street1, String street2,
			String city, String state, String companyNamme, int zip, boolean active, Type cuisineType, int MaxWaitTime) {
		super(name, description, menu, hour, street1, street2, city, state, companyNamme, zip, active, cuisineType);
		this.MaxWaitTime = MaxWaitTime;
	}
	
	@Override
	public String toString() {
		return super.toString()+"\n"+ "TakeOutRestaurants [MaxWaitTime=" + MaxWaitTime + "]";
	}

	public TakeOutRestaurants(Restaurants restaurant, int MaxWaitTime) {
		super(restaurant);
		this.MaxWaitTime = MaxWaitTime;
	}
	
	public int getMaxWaitTime() {
		return MaxWaitTime;
	}
	public void setMaxWaitTime(int maxWaitTime) {
		MaxWaitTime = maxWaitTime;
	}

}
