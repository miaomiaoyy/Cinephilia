package reviewApp.tools;

import java.math.BigDecimal;

import reviewApp.dal.*;
import reviewApp.model.*;

import java.sql.SQLException;
import java.util.List;


/**
 * main() runner, used for the app demo.
 * 
 * Instructions:
 * 1. Create a new MySQL schema and then run the CREATE TABLE statements from lecture:
 * http://goo.gl/86a11H.
 * 2. Update ConnectionManager with the correct user, password, and schema.
 */
public class Inserter {

	public static void main(String[] args) throws SQLException {
		// DAO instances.
		CompaniesDao companiesDao = CompaniesDao.getInstance();
		CreditCardsDao creditCardsDao = CreditCardsDao.getInstance();
		FoodCartRestaurantsDao foodCartRestaurantsDao = FoodCartRestaurantsDao.getInstance();
		TakeOutRestaurantsDao takeOutRestaurantsDao = TakeOutRestaurantsDao.getInstance();
		SitDownRestaurantsDao sitDownRestaurantsDao = SitDownRestaurantsDao.getInstance();
		RestaurantsDao restaurantsDao =RestaurantsDao.getInstance();
		ReviewsDao reviewsDao = ReviewsDao.getInstance();
		ReservationsDao reservationsDao = ReservationsDao.getInstance();
		RecommendationsDao recommendationsDao = RecommendationsDao.getInstance();
		UsersDao usersDao = UsersDao.getInstance();
		
		// INSERT objects from our model.
		Users user1 = new Users("Bruce","password","Bruce","C","bruce@mail.com","5555555");
		Users user2 = new Users("TT","password","Tony","D","tony@mail.com","5555555");
		Users user3 = new Users("DK","password","Daniel","K","dan@mail.com","5555555");
		Users user4 = new Users("James","password","James","M","james@mail.com","5555555");
		Users user5 = new Users("Steve","password","Steve","N","steve@mail.com","5555555");
		user1 = usersDao.create(user1);
		user2 = usersDao.create(user2);
		user3 = usersDao.create(user3);
		user4 = usersDao.create(user4);
		user5 = usersDao.create(user5);
		user1 = usersDao.getUserByUserName("Bruce");
		
		// Test
		System.out.println("getUserByUserName() result: ");
		System.out.println("\t"+user1.toString());
		usersDao.delete(user5);
		System.out.println("user5 deleted");
		System.out.println();
		
		CreditCards creditCard1 = new CreditCards(user1.getUserName(),11121,java.sql.Date.valueOf("2018-01-01"));
		creditCard1 = creditCardsDao.create(creditCard1);
		CreditCards creditCard2 = new CreditCards(user2.getUserName(),1111, java.sql.Date.valueOf("2018-01-01"));
		creditCard2 = creditCardsDao.create(creditCard2);
		CreditCards creditCard3 = new CreditCards(user3.getUserName(),11112, java.sql.Date.valueOf("2018-01-01"));
		creditCard3 = creditCardsDao.create(creditCard3);
		CreditCards creditCard4 = new CreditCards(user1.getUserName(),111112, java.sql.Date.valueOf("2018-01-01"));
		creditCard4 = creditCardsDao.create(creditCard4);
		CreditCards creditCard5 = creditCardsDao.getCreditCardByCardNumber(creditCard3.getCardNumber());
		
		// Test
		System.out.println("getCreditCardByCardNumber() result: ");
		System.out.println("\t"+creditCard5.toString());
		creditCard4 = creditCardsDao.updateExpiration(creditCard4, java.sql.Date.valueOf("2019-11-01"));
		System.out.println("updateExpiration() result: ");
		System.out.println("\t"+creditCard4.toString());
		List<CreditCards>  creditCards = creditCardsDao.getCreditCardsByUserName("Bruce");
		System.out.println("getCreditCardsByUserName() result: ");
		for (CreditCards card : creditCards ){
			System.out.println("\t"+card.toString());
		}
		creditCardsDao.delete(creditCard2);
		System.out.println("CreditCard2 deleted");
		System.out.println();
		
		
		
		Companies company1 = new Companies("company1","about company1");
		company1 = companiesDao.create(company1);
		Companies company2 = new Companies("company2","about company2");
		company2 = companiesDao.create(company2);
		Companies company4 = new Companies("company4","about company4");
		company4 = companiesDao.create(company4);
		Companies company3 = new Companies("company3","about company3");
		company3 = companiesDao.create(company3);
		company3 = companiesDao.updateContent(company3, "Updated about 3");
		
		//Test
		System.out.println("updateContent() result: ");
		System.out.println("\t"+company3.toString());
		System.out.println("getCompanyByCompanyName() result: ");
		System.out.println("\t"+companiesDao.getCompanyByCompanyName("company3").toString());		
		companiesDao.delete(company4);
		System.out.println("company4 deleted");
		System.out.println();
		
		Restaurants restaurant1 = new Restaurants("Rst1", "Desc1", "Menu1", "Hour1", "St1", "St1",
				"City1", "State1", "company1", 11111, true, Restaurants.Type.ASIAN);
		restaurant1 = restaurantsDao.create(restaurant1);
		Restaurants restaurant2 = new Restaurants("Rst2", "Desc2", "Menu2", "Hour2", "St2", "St2",
				"City2", "State2", "company1", 22222, true, Restaurants.Type.ASIAN);
		restaurant2 = restaurantsDao.create(restaurant2);
		
		// Test
		System.out.println("getRestaurantById() result: ");
		System.out.println("\t"+restaurantsDao.getRestaurantById(restaurant1.getRestaurantId()).toString());			
		List<Restaurants>  restaurants = restaurantsDao.getRestaurantsByCuisine(Restaurants.Type.ASIAN);
		System.out.println("getRestaurantsByCuisine() result: ");
		for (Restaurants restaurant : restaurants ){
			System.out.println("\t"+restaurant.toString());
		}
		restaurants = restaurantsDao.getRestaurantsByCompanyName("company1");
		System.out.println("getRestaurantsByCompanyName() result: ");
		for (Restaurants restaurant : restaurants ){
			System.out.println("\t"+restaurant.toString());
		}
		restaurantsDao.delete(restaurant2);
		System.out.println("restaurant2 deleted");
		System.out.println();
		
		
		SitDownRestaurants sitDownRestaurant1 = new SitDownRestaurants("SitRst3", "Desc3", "SitMenu3", "SitHour3", "SitSt3", "SitSt3",
				"SitCity3", "SitState3", "company1", 33333, true, Restaurants.Type.HISPANIC, 50);
		sitDownRestaurant1 = sitDownRestaurantsDao.create(sitDownRestaurant1);
		SitDownRestaurants sitDownRestaurant2 = new SitDownRestaurants("SitRst4", "Desc4", "SitMenu5", "SitHour5", "SitSt5", "SitSt5",
				"SitCity5", "SitState5", "company1", 33323, true, Restaurants.Type.HISPANIC, 510);
		sitDownRestaurant2 = sitDownRestaurantsDao.create(sitDownRestaurant2);
		
		// Test
		System.out.println("getRestaurantById() result: ");
		System.out.println("\t"+sitDownRestaurantsDao.getSitDownRestaurantById(sitDownRestaurant1.getRestaurantId()).toString());			
		List<SitDownRestaurants> sitDownRestaurants = sitDownRestaurantsDao.getSitDownRestaurantsByCompanyName("company1");
		System.out.println("getRestaurantsByCompanyName() result: ");
		for (SitDownRestaurants sitDownRestaurant : sitDownRestaurants ){
			System.out.println("\t"+sitDownRestaurant.toString());
		}
		sitDownRestaurantsDao.delete(sitDownRestaurant2);
		System.out.println("SitDownRestaurant2 deleted");
		System.out.println();
		
		TakeOutRestaurants takeOutRestaurant1 = new TakeOutRestaurants("tRst4", "tDesc4", "tMenu4", "tHour4", "tSt4", "tSt4",
				"tCity4", "tState4", "company3", 44444, true, Restaurants.Type.AMERICAN, 100);
		takeOutRestaurant1 = takeOutRestaurantsDao.create(takeOutRestaurant1);
		TakeOutRestaurants takeOutRestaurant2 = new TakeOutRestaurants("tRst6", "tDesc6", "tMenu6", "tHour6", "tSt6", "tSt6",
				"tCity6", "tState6", "company3", 44444, true, Restaurants.Type.AMERICAN, 900);
		takeOutRestaurant2 = takeOutRestaurantsDao.create(takeOutRestaurant2);
		
		// Test 
		System.out.println("getRestaurantById() result: ");
		System.out.println("\t"+takeOutRestaurantsDao.getTakeOutRestaurantById(takeOutRestaurant1.getRestaurantId()).toString());			
		List<TakeOutRestaurants> takeOutRestaurants =takeOutRestaurantsDao.getTakeOutRestaurantsByCompanyName("company3");
		System.out.println("getRestaurantsByCompanyName() result: ");
		for (TakeOutRestaurants sitDownRestaurant : takeOutRestaurants ){
			System.out.println("\t"+sitDownRestaurant.toString());
		}
		takeOutRestaurantsDao.delete(takeOutRestaurant2);
		System.out.println("TakeOutRestaurant2 deleted");
		System.out.println();
		
		
		FoodCartRestaurants foodCartRestaurant1 = new FoodCartRestaurants("fRst5", "fDesc5", "fMenu5", "fHour5", "fSt5", "fSt5",
				"fCity5", "fState5", "company3", 55555, true, Restaurants.Type.AFRICAN, true);
		foodCartRestaurant1 = foodCartRestaurantsDao.create(foodCartRestaurant1);
		FoodCartRestaurants foodCartRestaurant2 = new FoodCartRestaurants("fRst7", "fDesc7", "fMenu7", "fHour7", "fSt7", "fSt7",
				"fCity7", "fState7", "company3", 66655, true, Restaurants.Type.AFRICAN, true);
		foodCartRestaurant2 = foodCartRestaurantsDao.create(foodCartRestaurant2);
		
		// Test
		System.out.println("getRestaurantById() result: ");
		System.out.println("\t"+foodCartRestaurantsDao.getFoodCartRestaurantById(foodCartRestaurant1.getRestaurantId()).toString());			
		List<FoodCartRestaurants> foodCartRestaurants =foodCartRestaurantsDao.getFoodCartRestaurantsByCompanyName("company3");
		System.out.println("getRestaurantsByCompanyName() result: ");
		for (FoodCartRestaurants foodCartRestaurant : foodCartRestaurants ){
			System.out.println("\t"+foodCartRestaurant.toString());
		}
		foodCartRestaurantsDao.delete(foodCartRestaurant2);
		System.out.println("FoodCartRestaurant2 deleted");
		System.out.println();
		
		
		Reviews review1 = new Reviews(0, "content1", user4.getUserName(), restaurant1.getRestaurantId(), BigDecimal.ONE, java.sql.Date.valueOf("2012-01-01"));
		review1 = reviewsDao.create(review1);
		Reviews review2 = new Reviews(0, "content2", user3.getUserName(), restaurant1.getRestaurantId(), BigDecimal.ONE, java.sql.Date.valueOf("2012-01-01"));
		review2 = reviewsDao.create(review2);
		
		//Test
		System.out.println("getReviewById() result: ");
		System.out.println("\t"+reviewsDao.getReviewById(review1.getReviewId()).toString());			
		List<Reviews> reviews =reviewsDao.getReviewsByUserName(user4.getUserName());
		System.out.println("getReviewsByUserName() result: ");
		for (Reviews review : reviews ){
			System.out.println("\t"+review.toString());
		}
		reviews =reviewsDao.getReviewsByRestaurantId(restaurant1.getRestaurantId());
		System.out.println("getReviewsByRestaurantId() result: ");
		for (Reviews review : reviews ){
			System.out.println("\t"+review.toString());
		}
		reviewsDao.delete(review2);
		System.out.println("Reviews2 deleted");
		System.out.println();
		
		
		Reservations reservation1 = new Reservations(0, 
				5,sitDownRestaurant1.getRestaurantId(), user4.getUserName(), java.sql.Date.valueOf("2010-01-01"), java.sql.Date.valueOf("2010-01-02"));
		reservation1 = reservationsDao.create(reservation1);
		Reservations reservation2 = new Reservations(0, 
				5,sitDownRestaurant1.getRestaurantId(), user3.getUserName(), java.sql.Date.valueOf("2011-01-01"), java.sql.Date.valueOf("2011-01-02"));
		reservation2 = reservationsDao.create(reservation2);
		
		//Test
		System.out.println("getReservationId() result: ");
		System.out.println("\t"+reservationsDao.getReservationById(reservation1.getReservationId()).toString());			
		List<Reservations> reservations =reservationsDao.getReservationsByUserName(user4.getUserName());
		System.out.println("getReservationsByUserName() result: ");
		for (Reservations reservation : reservations ){
			System.out.println("\t"+reservation.toString());
		}
		reservations =reservationsDao.getReservationsByRestaurantId(sitDownRestaurant1.getRestaurantId());
		System.out.println("getReservationsByRestaurantId() result: ");
		for (Reservations reservation : reservations ){
			System.out.println("\t"+reservation.toString());
		}
		reservationsDao.delete(reservation2);
		System.out.println("Reservation2 deleted");
		System.out.println();
		
		
		Recommendations recommendation1 = new Recommendations(0, restaurant1.getRestaurantId(), user2.getUserName());
		recommendation1 = recommendationsDao.create(recommendation1);
		Recommendations recommendation2 = new Recommendations(0, restaurant1.getRestaurantId(), user1.getUserName());
		recommendation2 = recommendationsDao.create(recommendation2);
		
		//Test
		System.out.println("getRecommendationById() result: ");
		System.out.println("\t"+recommendationsDao.getRecommendationById(recommendation1.getRecommendationId()).toString());			
		List<Recommendations> recommendations =recommendationsDao.getRecommendationsByUserName(user2.getUserName());
		System.out.println("getRecommendationsByUserName() result: ");
		for (Recommendations recommendation : recommendations ){
			System.out.println("\t"+recommendation.toString());
		}
		recommendations =recommendationsDao.getRecommendationsByRestaurantId(takeOutRestaurant1.getRestaurantId());
		System.out.println("getRecommendationsByRestaurantId() result: ");
		for (Recommendations recommendation : recommendations ){
			System.out.println("\t"+recommendation.toString());
		}
		recommendationsDao.delete(recommendation2);
		System.out.println("Recommendation2 deleted");
		System.out.println();
	}
}
