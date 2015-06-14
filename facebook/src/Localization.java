import java.io.Serializable;


public class Localization implements Serializable{

	private static final long serialVersionUID = 257404015441928212L;
	protected String town, state, country;
	
	public Localization(String town, String state, String country) {
		this.town=town;
		this.state=state;
		this.country=country;
	}
	
	public String toString() {
		return town+", "+state+", "+country;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town=town;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state=state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country=country;
	}

}
