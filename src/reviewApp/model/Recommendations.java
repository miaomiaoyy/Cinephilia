package reviewApp.model;

public class Recommendations {
	protected int RecommendationId, RestaurantId;
	protected String UserName;
	
	public Recommendations(int recommendationId, int restaurantId, String userName) {
		super();
		RecommendationId = recommendationId;
		RestaurantId = restaurantId;
		UserName = userName;
	}

	public int getRestaurantId() {
		return RestaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		RestaurantId = restaurantId;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public int getRecommendationId() {
		return RecommendationId;
	}

	public void setRecommendationId(int recommendationId) {
		RecommendationId = recommendationId;
	}

	@Override
	public String toString() {
		return "Recommendations [ReccommendationId=" + RecommendationId + ", RestaurantId=" + RestaurantId
				+ ", UserName=" + UserName + "]";
	}
	
	
}
