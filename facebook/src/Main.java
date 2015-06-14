import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {

		String email_in, password_in;
		int choice,choice2,choice3;
		Scanner sc = new Scanner(System.in);
		SocialNetwork facebook = new SocialNetwork();
		try {
			facebook=facebook.deserialiser();
		} catch (IOException e1) {
			System.err.println("Loading error.");
		}
		System.out.println("Welcome to Facebook. There are currently "+facebook.getUsersNumbers()+" user(s)");
		do{
			System.out.println("What do you want to do?");
			System.out.println("1 Create account");
			System.out.println("2 Open session");
			System.out.println("3 activate my session");
			System.out.println("4 Deactivate my session");
			System.out.println("5 see the list of session");
			System.out.println("6 Exit");
			try{
				choice = Integer.parseInt(sc.nextLine());
			}catch(Exception e){
				choice=-1;
			}
			switch(choice) {
			case(1):
				facebook.createAccount();
			break;
			case(2):
				System.out.println("Please enter your email and your password:");
				System.out.println("Email? ");
				email_in = sc.nextLine();
				System.out.println("Password?");
				password_in = sc.nextLine();
				facebook.openSession(email_in,password_in);
				if(facebook.getCurrentUser()!=null){
					do{
						System.out.println("1 Send a message on your wall");
						System.out.println("2 Send a message on a friend wall");
						System.out.println("3 See the list of your friends");
						System.out.println("4 See a friend's profile");
						System.out.println("5 Send an invitation");
						System.out.println("6 Cancel an invitation sended");
						System.out.println("7 View your invitation list");
						System.out.println("8 See your wall");
						System.out.println("9 See a friend's wall");
						System.out.println("10 See the friend of yours friends");
						System.out.println("11 Log out");
						try{
							choice2 = Integer.parseInt(sc.nextLine());
						}catch(Exception e){
							choice2=-1;
						}
						switch(choice2) {
						case 1:
							facebook.getCurrentUser().writeMessage();
							break;
						case 2:
							facebook.getCurrentUser().writeMessageToFriend();
							break;
						case 3:
							facebook.getCurrentUser().displayFriends();
							break;
						case 4:
							facebook.getCurrentUser().displayFriendProfile();
							break;
						case 5:
								System.out.println("Enter the email for this invitation");
								Profile p=facebook.getRegisteredUsers().get(sc.nextLine());
								if(p!=null){
									facebook.getCurrentUser().sendInvitation(p);
								}else
									System.out.println("Doesn't exist");
							break;
						case 6:
							facebook.getCurrentUser().cancelInvitation();
							break;
						case 7:
							facebook.getCurrentUser().acceptInvitation();
							break;
						case 8:
							System.out.println(facebook.getCurrentUser().getPersonalWall());
							break;
						case 9:
							facebook.getCurrentUser().displayFriendWall();
							break;
						case 10:
							System.out.println("Enter max depth:");
							try{
								choice3 = Integer.parseInt(sc.nextLine());
							}catch(Exception e){
								choice3=-1;
							}
							facebook.friendOfFriend(facebook.getCurrentUser(), choice3, new HashSet<Profile>(), "");
							break;
						default:
							break;
						}
					}while(choice2!=11);
				}
				break;
			case(3):
				System.out.println("Please enter your email and your password:");
				System.out.println("Email? ");
				email_in = sc.nextLine();
				System.out.println("Password?");
				password_in = sc.nextLine();
				facebook.activateAccount(email_in, password_in);
				break;
			case(4):
				System.out.println("Please enter your email and your password:");
				System.out.println("Email? ");
				email_in = sc.nextLine();
				System.out.println("Password?");
				password_in = sc.nextLine();
				facebook.deactivateAccount(email_in, password_in);
				break;
			case(5):
				facebook.DisplayRegisteredUsers();
				break;
			default:
				break;
			}
		}
		while(choice!=6);
		try {
			facebook.serialiser();
		} catch (IOException e) {
			System.err.println("Error when saving");
		}
	}
}