package reviewApp.model;


public class Users {
	
	@Override
	public String toString() {
		return "Users [UserName=" + UserName + ", Password=" + Password + ", FirstName=" + FirstName + ", LastName="
				+ LastName + ", Email=" + Email + ", Phone=" + Phone + "]";
	}
	protected String UserName, Password, FirstName, LastName, Email, Phone;
	
	
	public Users(String UserName, String Password, String FirstName, String LastName, String Email, String Phone) {
		this.UserName = UserName;
		this.Email = Email;
		this.Password = Password;
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.Phone = Phone;
	}
	
	public String getUserName() {
		return UserName;
	}

	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	
}