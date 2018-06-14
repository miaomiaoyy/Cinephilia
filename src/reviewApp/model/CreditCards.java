package reviewApp.model;

import java.sql.Date;

public class CreditCards {

	@Override
	public String toString() {
		return "CreditCards [CardNumber=" + CardNumber + ", Expiration=" + Expiration + ", UserName=" + UserName + "]";
	}

	protected long CardNumber;
	protected Date Expiration;
	protected String UserName;
	
	public CreditCards(String UserName, long CardNumber, Date Expiration){
		this.UserName = UserName;
		this.CardNumber = CardNumber;
		this.Expiration = Expiration;
	}

	public long getCardNumber() {
		return CardNumber;
	}

	public void setCardNumber(long cardNumber) {
		CardNumber = cardNumber;
	}

	public Date getExpiration() {
		return Expiration;
	}

	public void setExpiration(Date expiration) {
		Expiration = expiration;
	}

	public String getUserName() {
		return UserName;
	}
	
}
