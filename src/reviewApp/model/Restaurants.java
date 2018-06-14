package reviewApp.model;

public class Restaurants {
	protected String Name, Description, Menu, Hours, Street1, Street2, City, State, CompanyName;
	protected int RestaurantId, Zip;
	protected boolean Active;

	public enum Type {
		AFRICAN, AMERICAN, ASIAN, EUROPEAN, HISPANIC
	};
	protected Type CuisineType;
	
	public Restaurants(String name, String description, String menu, String hours, String street1, String street2,
			String city, String state, String companyName, int zip, boolean active, Type cuisineType) {

		Name = name;
		Description = description;
		Menu = menu;
		Hours = hours;
		Street1 = street1;
		Street2 = street2;
		City = city;
		State = state;
		CompanyName = companyName;
		Zip = zip;
		Active = active;
		CuisineType = cuisineType;
	}

	public Restaurants(Restaurants restaurant) {
		Name = restaurant.Name;
		Description = restaurant.Description;
		Menu = restaurant.Menu;
		Hours = restaurant.Hours;
		Street1 = restaurant.Street1;
		Street2 = restaurant.Street2;
		City = restaurant.City;
		State = restaurant.State;
		CompanyName = restaurant.CompanyName;
		Zip = restaurant.Zip;
		Active = restaurant.Active;
		CuisineType = restaurant.CuisineType;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getMenu() {
		return Menu;
	}

	public void setMenu(String menu) {
		Menu = menu;
	}

	public String getHours() {
		return Hours;
	}

	@Override
	public String toString() {
		return "Restaurants [Name=" + Name + ", Description=" + Description + ", Menu=" + Menu + ", Hours=" + Hours
				+ ", Street1=" + Street1 + ", Street2=" + Street2 + ", City=" + City + ", State=" + State
				+ ", CompanyName=" + CompanyName + ", RestaurantId=" + RestaurantId + ", Zip=" + Zip + ", Active="
				+ Active + ", CuisineType=" + CuisineType + "]";
	}

	public void setHours(String hours) {
		Hours = hours;
	}

	public String getStreet1() {
		return Street1;
	}

	public void setStreet1(String street1) {
		Street1 = street1;
	}

	public String getStreet2() {
		return Street2;
	}

	public void setStreet2(String street2) {
		Street2 = street2;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}

	public int getZip() {
		return Zip;
	}

	public void setZip(int zip) {
		Zip = zip;
	}

	public boolean isActive() {
		return Active;
	}

	public void setActive(boolean active) {
		Active = active;
	}

	public Type getCuisineType() {
		return CuisineType;
	}

	public void setCuisineType(Type cuisineType) {
		CuisineType = cuisineType;
	}

	public int getRestaurantId() {
		return RestaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.RestaurantId = restaurantId;		
	}

}
