import java.util.HashMap;

public class TestSocialNetwork {

	public static void main(String[] args) {
		
		Localization l = new Localization("Chicago","IL","USA");
		Profile p = new Profile("FORD","Harrison","indy","07-13-1942",l,"harrison.ford@gmail.com",true);
		System.out.println(p.toString());
		HashMap<String,Profile> list = new HashMap<String,Profile>();
		list.put("harrison.ford@gmail.com",p);
		SocialNetwork facebook = new SocialNetwork(list);
		
		System.out.println("------------createAccount method------------\n");
		facebook.createAccount();
		System.out.println();
		
		System.out.println("------------displayRegisteredUsers method------------\n");
		facebook.DisplayRegisteredUsers();
		System.out.println();

		System.out.println("------------openSession method------------\n");
		facebook.openSession("harrison.ford@gmail.com","han_solo");
		facebook.openSession("harrisonford@gmail.com","indy");
		facebook.openSession("harrison.ford@gmail.com","indy");
		System.out.println();
		
		System.out.println("------------search method------------");
		System.out.println(facebook.search("FORD","Harrison").toString());

		System.out.println("------------deactivateAccount method------------\n");
		facebook.deactivateAccount("harrison.ford@gmail.com", "han_solo");
		facebook.deactivateAccount("harrisonford@gmail.com", "indy");
		facebook.deactivateAccount("harrison.ford@gmail.com", "indy");
		p.setActivation(false);
		facebook.deactivateAccount("harrison.ford@gmail.com", "indy");
	}
}