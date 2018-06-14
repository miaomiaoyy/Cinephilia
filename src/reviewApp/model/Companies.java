package reviewApp.model;

public class Companies {
	protected String CompanyName, About;
	
	public Companies(String CompanyName, String About){
		this.About = About;
		this.CompanyName = CompanyName;
	}

	public String getAbout() {
		return About;
	}

	public void setAbout(String about) {
		About = about;
	}

	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	@Override
	public String toString() {
		return "Companies [CompanyName=" + CompanyName + ", About=" + About + "]";
	}
}
