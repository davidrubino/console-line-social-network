import java.io.Serializable;

public class Invitation implements Serializable{

	private static final long serialVersionUID = 2911619761941473143L;
	protected Profile sender, receiver;

	public Invitation(Profile sender, Profile receiver) {
		this.sender = sender;
		this.receiver = receiver;
	}

	public Profile getSender() {
		return sender;
	}

	public Profile getReceiver() {
		return receiver;
	}

	public String toString() {
		return this.getSender().getSurname() + " " + this.getSender().getName()
				+ " has sent an invitation to "
				+ this.getReceiver().getSurname() + " "
				+ this.getReceiver().getName();
	}

}
