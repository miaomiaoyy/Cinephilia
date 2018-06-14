package reviewApp.model;

import java.sql.Date;


public class Reservations {
	@Override
	public String toString() {
		return "Reservations [ReservationId=" + ReservationId + ", Size=" + Size + ", RestaurantId=" + RestaurantId
				+ ", UserName=" + UserName + ", Start=" + Start + ", End=" + End + "]";
	}
	protected int ReservationId, Size, RestaurantId;
	protected String UserName;
	protected Date Start, End;
	public Reservations(int reservationId, int size, int restaurantId, String userName, Date start, Date end) {
		super();
		Size = size;
		RestaurantId = restaurantId;
		UserName = userName;
		Start = start;
		End = end;
		ReservationId = reservationId;
	}
	public int getSize() {
		return Size;
	}
	public void setSize(int size) {
		Size = size;
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
	public Date getStart() {
		return Start;
	}
	public void setStart(Date start) {
		Start = start;
	}
	public Date getEnd() {
		return End;
	}
	public void setEnd(Date end) {
		End = end;
	}
	public int getReservationId() {
		return ReservationId;
	}
	public void setReservationId(int reservationId) {
		ReservationId = reservationId;
	}
	
	
}
