import java.io.Serializable;
import java.util.ArrayList;

public class Wall implements Serializable {

	private static final long serialVersionUID = -4531720166176709393L;
	protected ArrayList<Message> messageList;
		
	public Wall() {
		this.messageList=new ArrayList<Message>();
	}

	public Wall(ArrayList<Message> list) {
		this.messageList=list;
	}
	
	public void addMessage(Message m){
		messageList.add(m);
	}
	
	public String toString() {
		String toReturn="";
		if(messageList.isEmpty()) {
			toReturn+="no message in the wall";
		}else
			for(int i=0; i<messageList.size(); i++)
				toReturn+="On "+messageList.get(i).getDate()+" From: "+messageList.get(i).getSender().getSurname()+" "+messageList.get(i).getSender().getName()+"\n\t"+messageList.get(i).getContent()+"\n";
		return toReturn;
	}
	
	public void deleteMessage(Message mess) {
		for(int i=0; i<messageList.size(); i++) {
			if(messageList.get(i)==mess) {
				messageList.remove(i);
			}
		}
	}

	public ArrayList<Message> getMessageList() {
		return messageList;
	}

	public void setMessageList(ArrayList<Message> messageList) {
		this.messageList = messageList;
	}

}
