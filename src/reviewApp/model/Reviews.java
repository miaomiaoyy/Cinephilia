package reviewApp.model;

import java.math.BigDecimal;

import java.sql.Date;


public class Reviews {
	@Override
	public String toString() {
		return "Reviews [Content=" + Content + ", UserName=" + UserName + ", ReviewId=" + ReviewId + ", RestaurantId="
				+ RestaurantId + ", Rating=" + Rating + ", Created=" + Created + "]";
	}

	protected String Content, UserName;
	protected int ReviewId, RestaurantId;
	protected BigDecimal Rating;
	protected Date Created;
	
	public Reviews(int reviewId, String content, String userName, int restaurantId, BigDecimal rating, Date created) {

		Content = content;
		UserName = userName;
		RestaurantId = restaurantId;
		Rating = rating;
		this.Created = created;
		ReviewId = reviewId;
	}

	public void setReviewId(int reviewId) {
		ReviewId = reviewId;
	}

	public void setCreated(Date created) {
		Created = created;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public int getRestaurantId() {
		return RestaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		RestaurantId = restaurantId;
	}

	public BigDecimal getRating() {
		return Rating;
	}

	public void setRating(BigDecimal rating) {
		Rating = rating;
	}

	public int getReviewId() {
		return ReviewId;
	}

	public Date getCreated() {
		return Created;
	}
	
	
}
