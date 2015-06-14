import java.io.*;
import java.util.*;

public class SocialNetwork implements Serializable {

	private static final long serialVersionUID = 902665912334739241L;
	protected HashMap<String, Profile> registeredUsers;
	protected Profile currentUser;

	public SocialNetwork() {
		this.registeredUsers = new HashMap<String, Profile>();
		this.currentUser = null;
	}

	public SocialNetwork(HashMap<String, Profile> list) {
		this.registeredUsers = list;
	}

	public void DisplayRegisteredUsers() {
		if (!registeredUsers.isEmpty()) {
			int i = 0;
			for (Profile p : registeredUsers.values()) {
				System.out.print("User #"+i+++"\n");
				System.out.println(p);
			}
		} else
			System.out.println("No session recorded.");
	}

	public void friendOfFriend(Profile p,int depth, HashSet<Profile> alreadySeen, String tab){
		if(depth<0)
			return;
		else{
			int i=0;
			for(Profile friend : p.getFriendsList()){
				System.out.println(tab+i+++") "+friend.getName()+" "+friend.getSurname());
				if(!alreadySeen.contains(friend)){
					alreadySeen.add(friend);
					friendOfFriend(friend, depth-1, alreadySeen, tab+"\t");
				}
			}
		}
	}
	
	public int getUsersNumbers() {
		return this.registeredUsers.size();
	}

	public void createAccount() {
		// ask the user for all the informations
		Scanner sc = new Scanner(System.in);
		System.out.println("To create a new account, enter the following informations:");
		System.out.println("name? ");
		String name = sc.nextLine();
		System.out.println("surname? ");
		String surname = sc.nextLine();
		System.out.println("password? ");
		String password = sc.nextLine();
		System.out.println("date of bith (mm-dd-yyyy)? ");
		String birth_date = sc.nextLine();
		System.out.println("town of residence? ");
		String town = sc.nextLine();
		System.out.println("state of residence? ");
		String state = sc.nextLine();
		System.out.println("country of residence? ");
		String country = sc.nextLine();
		System.out.println("email? ");
		String email = sc.nextLine();
		Localization place = new Localization(town, state, country);
		Profile newProfile = new Profile(name, surname, password, birth_date,place, email, true);

		// check if the person is already on the network
		if (registeredUsers.get(email) == null) {
			registeredUsers.put(email, newProfile);
			System.out.println("Account created with success!");
		} else
			System.out.println("The user is already registered");
	}

	public void openSession(String email_in, String password_in) {
		Profile tmp = registeredUsers.get(email_in);
		if (tmp == null)
			System.out.println("Incorrect email.");
		else if (!tmp.getPassword().equals(password_in))
			System.out.println("Incorrect password.");
		else if (tmp.getActivation() == false)
			System.out.println("The account has not yet been activated.");
		else {
			System.out.println("User identified.");
			System.out.println("Welcome, " + tmp.getSurname() + "!");
			currentUser = tmp;
		}
	}

	public void closeSession() {
		currentUser = null;
	}

	public void activateAccount(String email_in, String password_in) {
		Profile tmp = registeredUsers.get(email_in);
		if (tmp == null)
			System.out.println("Incorrect email.");
		else if (!tmp.getPassword().equals(password_in))
			System.out.println("Incorrect password.");
		else if (tmp.getActivation() == true)
			System.out.println("The account is already activated.");
		else {
			tmp.setActivation(true);
			System.out.println("Your account has been activated.");
		}
	}

	public void deactivateAccount(String email_in, String password_in) {
		Profile tmp = registeredUsers.get(email_in);
		if (tmp == null)
			System.out.println("Incorrect email.");
		else if (!tmp.getPassword().equals(password_in))
			System.out.println("Incorrect password.");
		else if (tmp.getActivation() == false)
			System.out.println("The account is already inactivated.");
		else {
			tmp.setActivation(false);
			System.out.println("Your account has been deactivated.");
		}
	}

	public Profile search(String name, String surname) {
		LinkedList<Profile> tmp = new LinkedList<Profile>();
		tmp.addAll(registeredUsers.values());
		Profile found = null;
		for (int i = 0; i < tmp.size(); i++) {
			if (tmp.get(i).getName() == name
					&& tmp.get(i).getSurname() == surname) {
				found = tmp.get(i);
			}
		}
		return found;
	}

	public Profile getCurrentUser() {
		return currentUser;
	}

	public HashMap<String, Profile> getRegisteredUsers() {
		return registeredUsers;
	}

	public void serialiser() throws IOException {
		String nomFic = "facebook.bin";
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFic));
		try {
			oos.writeObject(this);
		} catch (IOException ioe) {
			System.err.println("FATAL ERROR -- "+ ioe.toString());
		}
		oos.close();
	}

	public SocialNetwork deserialiser() throws IOException {
		String nomFic = "facebook.bin";
		SocialNetwork tmp = null;
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFic));
		try {
			tmp = (SocialNetwork) ois.readObject();
		} catch (IOException ioe) {
			System.err.println("ERROR -- " + ioe.toString());
		} catch (ClassNotFoundException cnfe) {
			System.err
					.println("ERROR 'Unknown class' -- " + cnfe.toString());
		}
		ois.close();
		return tmp;
	}
}