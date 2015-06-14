import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class TestProfile {

	public static void main(String[] args) throws IOException {
		
		System.out.println("----toString method----");
		Localization l = new Localization("Gap","PA","USA");
		Profile p = new Profile("Rubino","David","david","05/27/1992",l,"david.m.rubino@gmail.com",true);
		System.out.println(p.toString());
		
		System.out.println("----writeMessage method----");
		ArrayList<Message> list = new ArrayList<Message>();
		Wall w = new Wall(list);
		p.setPersonalWall(w);
		p.writeMessage();
		p.displayWall();
		System.out.println();
		
		System.out.println("----writeMessageToFriend method----");
		p.writeMessageToFriend();
		Localization l1 = new Localization("Chicago","IL","USA");
		Profile p1 = new Profile("Ford","Harrison","indy","07/13/1942",l1,"harrison.ford@gmail.com",true);
		Localization l2 = new Localization("Syracuse","NY","USA");
		Profile p2 = new Profile("Cruise","Tom","topgun","07/03/1962",l2,"tom.cruise@gmail.com",true);
		ArrayList<Profile> friendsList = new ArrayList<Profile>();
		friendsList.add(p1);
		friendsList.add(p2);
		p.setFriendsList(friendsList);
		p.writeMessageToFriend();
		System.out.println();
		
		System.out.println("----displayFriends method----");
		p.displayFriends();
		System.out.println();
		
		System.out.println("----displayFriendProfile method----");
		p.displayFriendProfile();
		
		System.out.println("----displayWall method----");
		Date d = new Date();
		Message m1=new Message(p2,d,"hello world!");
		ArrayList<Message> list1 = new ArrayList<Message>();
		list1.add(m1);
		Wall w1 = new Wall(list1);
		p.setPersonalWall(w1);
		p.displayWall();
		System.out.println();
		
		System.out.println("----displayFriendWall method----");
		p.displayFriendWall();
		System.out.println();
		
		System.out.println("----displayInvitation method----");
		Localization l3 = new Localization("Concord","CA","USA");
		Profile p3 = new Profile("Hanks","Tom","privateRyan","07/09/1956",l3,"tom.hanks@gmail.com",true);
		Invitation i = new Invitation(p3,p);
		ArrayList<Invitation> invitationList = new ArrayList<Invitation>();
		invitationList.add(i);
		p.setInvitationList(invitationList);
		p.displayInvitation();
		System.out.println();
		
		System.out.println("----sendInvitation method----");
		Localization l4 = new Localization("Long Island","NY","USA");
		Profile p4 = new Profile("Williams","John","johnny","02/08/1932",l4,"john.williams@gmail.com",true);
		p4.sendInvitation(p);
		p.displayInvitation();
		System.out.println();
		
		//add friends to John Williams' friends lists
		ArrayList<Profile> johnFriendsList = new ArrayList<Profile>();
		p4.setFriendsList(johnFriendsList);
		p4.getFriendsList().add(p3);
		
		//add friends to Tom Hanks' friends list
		ArrayList<Profile> tomFriendsList = new ArrayList<Profile>();
		p3.setFriendsList(tomFriendsList);
		p3.getFriendsList().add(p4);
		
		System.out.println("->  friendsList of David before accepting the invitation");
		p.displayFriends();
		System.out.println("->  friendsList of Tom before accepting the invitation");
		p3.displayFriends();
		System.out.println("->  friendsList of John before accepting the invitation");
		p4.displayFriends();
		System.out.println("->  invitations in the list");
		p.displayInvitation();
		System.out.println();
		
		System.out.println("----acceptInvitation method----");
		p.acceptInvitation();
		System.out.println("->  friendsList of David after accepting the invitation");
		p.displayFriends();
		System.out.println("->  friendsList of Tom after accepting the invitation");
		p3.displayFriends();
		System.out.println("->  friendsList of John after accepting the invitation");
		p4.displayFriends();
		System.out.println();
		
		System.out.println("----cancelInvitation method----");
		Localization l5 = new Localization("New York","NY","USA");
		Profile p5 = new Profile("Downey Jr.","Robert","ironman","04/04/1965",l5,"robert.downeyjr@gmail.com",true);
		Localization l6 = new Localization("Memphis","TN","USA");
		Profile p6 = new Profile("Freeman","Morgan","eddy","06/01/1937",l6,"morgan.freeman@gmail.com",true);
		ArrayList<Invitation> morganInvitationsList = new ArrayList<Invitation>();
		p6.setInvitationList(morganInvitationsList);
		Invitation i1 = new Invitation(p5,p6);
		p6.getInvitationList().add(i1);
		p6.displayInvitation();
		p5.cancelInvitation();
		p6.displayInvitation();
		System.out.println();
		
		System.out.println("----saveProfile method----");
		p.serialiser("./");
		System.out.println();
		
		System.out.println("----deserialiser method----");
		p.deserialiser("david.m.rubino@gmail.com");
	}

}
