import java.util.ArrayList;
import java.util.Date;


public class TestWall {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Date d = new Date();
		Localization l = new Localization("Seattle","WA","USA");
		Profile p = new Profile("RUBINO","David","indy","05/27/1992",l,"usa-man@hotmail.com",true);
		Message m1=new Message(p,d,"hello world!");
		ArrayList<Message> list = new ArrayList<Message>();
		list.add(m1);
		Wall w = new Wall(list);
		System.out.println(w.toString());
		
		System.out.println("Message deleted");
		w.deleteMessage(m1);
		System.out.println(w.toString());

	}

}
