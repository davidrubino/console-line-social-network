
public class TestInvitation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Localization l = new Localization("Gap","PA","USA");
		Profile p = new Profile("Rubino","David","david","05/27/1992",l,"david.m.rubino@gmail.com",true);
		Localization l1 = new Localization("Chicago","IL","USA");
		Profile p1 = new Profile("Ford","Harrison","indy","07/13/1942",l1,"harrison.ford@gmail.com",true);
		Invitation i = new Invitation(p1,p);
		System.out.println("----test toString----");
		System.out.println(i.toString());

	}

}
