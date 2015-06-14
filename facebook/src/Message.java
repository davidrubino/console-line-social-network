import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable{
	
	private static final long serialVersionUID = 3577281671026193064L;
	protected Profile sender;
	protected Date date;
	protected String content;
	
	public Message(Profile sender, Date date, String content) {
		this.sender=sender;
		this.date=date;
		this.content=content;
	}
	
	public void sendMessage(Profile receiver) {	}

	public Profile getSender() {
		return sender;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date=date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content=content;
	}

}
