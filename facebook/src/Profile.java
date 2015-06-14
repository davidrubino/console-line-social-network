import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class Profile implements Serializable {

	private static final long serialVersionUID = 3646937831314970326L;
	protected String name, surname, password, birth_date, email;
	protected Localization place;
	protected Wall personalWall;
	protected ArrayList<Invitation> invitationList, invitationSendedList;
	protected ArrayList<Profile> friendsList;
	protected boolean activation;

	public Profile() {}

	public Profile(String name, String surname, String password,
			String birth_date, Localization place, String email,
			boolean activation) {
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.birth_date = birth_date;
		this.place = place;
		this.email = email;
		this.activation = activation;
		this.personalWall = new Wall();
		this.invitationList = new ArrayList<Invitation>();
		this.invitationSendedList = new ArrayList<Invitation>();
		this.friendsList = new ArrayList<Profile>();
	}

	public String toString() {
		return "name: " + this.name + "\nsurname: " + this.surname
				+ "\nbirthdate: " + this.birth_date.toString() + "\nplace: "
				+ this.place.toString() + "\nemail: " + this.email + "\n";
	}

	public void writeMessage() {
		Scanner sc = new Scanner(System.in);
		Date d = new Date();
		System.out.println("Your message : ");
		Message m = new Message(this, d, sc.nextLine());
		this.getPersonalWall().getMessageList().add(m);
	}

	public void writeMessageToFriend() {
		Scanner sc = new Scanner(System.in);
		int choice;
		if (!this.friendsList.isEmpty()) {
			displayFriends();
			System.out.println("Choose a friend: ");
			try {
				choice = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				choice = -1;
			}
			if (choice > -1 && choice < this.friendsList.size()) {
				Date d = new Date();
				System.out.println("Your message: ");
				Message m = new Message(this, d, sc.nextLine());
				this.friendsList.get(choice).getPersonalWall().getMessageList()
						.add(m);
			} else
				System.out.println("Invalid input.");
		} else
			System.out.println("You have no friend.");
	}

	public void displayFriends() {
		for (int i = 0; i < this.friendsList.size(); i++)
			System.out.println(i + ") " + this.friendsList.get(i).getSurname()
					+ " " + this.friendsList.get(i).getName());
	}

	public void displayFriendProfile() {
		Scanner sc = new Scanner(System.in);
		int choice;
		if (!this.friendsList.isEmpty()) {
			displayFriends();
			System.out.println("Choose a friend: ");
			try {
				choice = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				choice = -1;
			}
			if (choice > -1 && choice < this.friendsList.size()) {
				System.out.println(this.friendsList.get(choice));
			} else
				System.out.println("Invalid input.");
		} else
			System.out.println("You have no friend.");
	}

	public void displayWall() {
		System.out.println(this.getPersonalWall().toString());
	}

	public void displayFriendWall() {
		Scanner sc = new Scanner(System.in);
		int choice;
		if (!this.friendsList.isEmpty()) {
			displayFriends();
			System.out.println("Choose a friend: ");
			try {
				choice = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				choice = -1;
			}
			if (choice > -1 && choice < this.friendsList.size()) {
				this.friendsList.get(choice).displayWall();
			} else
				System.out.println("Invalid input.");
		} else
			System.out.println("You have no friend.");
	}

	public void displayInvitation() {
		if (!this.getInvitationList().isEmpty())
			System.out.println(this.getInvitationList().toString());
		else
			System.out.println("There are no new invitations yet.");
	}

	public void sendInvitation(Profile p) {
		if(!this.friendsList.contains(p)){
			this.invitationSendedList.add(new Invitation(this, p));
			p.getInvitationList().add(new Invitation(this, p));
			System.out.println("An invitation was sended");
		}else
			System.out.println("Already your friend");
	}

	public void acceptInvitation() {
		Scanner sc = new Scanner(System.in);
		while (!this.getInvitationList().isEmpty()) {
			Invitation i = this.invitationList.get(0);
			System.out.println(i.getSender().getSurname() + " "
					+ i.getSender().getName()
					+ " wants to add you on Facebook.");
			System.out.println("a  accept    r  refuse");
			char answer = sc.nextLine().charAt(0);
			if (answer == 'a') {
				if(!this.friendsList.contains(i.sender)){
					this.getFriendsList().add(i.getSender());
					i.getSender().getFriendsList().add(i.getReceiver());
					this.getInvitationList().remove(i);
					i.getSender().getInvitationSendedList().remove(i);
					System.out.println(i.sender + "was added to your friend list");
				}else
					System.out.println("already one of your friends");
			} else if (answer == 'r') {
				this.getInvitationList().remove(i);
				i.getSender().getInvitationSendedList().remove(i);
			} else {
				System.out.println("Character unvalid. Try again.");
			}
		}
	}

	public void cancelInvitation() {
		Scanner sc = new Scanner(System.in);
		int choice;
		if (!this.getInvitationSendedList().isEmpty()) {
			int k = 0;
			for (Invitation i : this.invitationSendedList)
				System.out.println(k + ") " + i);
			System.out.println("Choose an invitation: ");
			try {
				choice = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				choice = -1;
			}
			if (choice > -1 && choice < this.invitationSendedList.size()) {
				Invitation i = this.invitationSendedList.get(choice);
				i.getReceiver().getInvitationList().remove(i);
				this.invitationSendedList.remove(i);
				System.out.println("Invitation removed.");
			} else
				System.out.println("Invalid input.");
		} else
			System.out.println("There are no invitation.");
	}

	public void serialiser(String path) throws IOException {
		String nomFic = path + this.email + ".bin";
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				nomFic));
		try {
			oos.writeObject(this);
		} catch (IOException ioe) {
			System.err.println("FATAL ERROR -- " + ioe.toString());
		}
		oos.close();
	}

	public Profile deserialiser(String email) throws IOException {
		String nomFic = email+".bin";
		Profile tmp = null;
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				nomFic));
		try {
			tmp = (Profile) ois.readObject();
		} catch (IOException ioe) {
			System.err.println("FATAL ERROR -- " + ioe.toString());
		} catch (ClassNotFoundException cnfe) {
			System.err.println("ERROR 'Unknown class' -- " + cnfe.toString());
		}
		ois.close();
		return tmp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getBirthdate() {
		return birth_date;
	}

	public void setBirthdate(String birth_date) {
		this.birth_date = birth_date;
	}

	public Localization getPlace() {
		return place;
	}

	public void setPlace(Localization place) {
		this.place = place;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getActivation() {
		return activation;
	}

	public void setActivation(boolean activation) {
		this.activation = activation;
	}

	public ArrayList<Profile> getFriendsList() {
		return this.friendsList;
	}

	public void setFriendsList(ArrayList<Profile> friendsList) {
		this.friendsList = friendsList;
	}

	public Wall getPersonalWall() {
		return personalWall;
	}

	public void setPersonalWall(Wall personalWall) {
		this.personalWall = personalWall;
	}

	public String getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}

	public ArrayList<Invitation> getInvitationList() {
		return invitationList;
	}

	public void setInvitationList(ArrayList<Invitation> invitationList) {
		this.invitationList = invitationList;
	}

	public ArrayList<Invitation> getInvitationSendedList() {
		return invitationSendedList;
	}

}