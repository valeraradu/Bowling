package bowling.action;

import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;


import com.opensymphony.xwork2.ActionSupport;

public class AddPlayers extends ActionSupport implements SessionAware {

	private Map<String, Object> session;
	private String playerName;
	private String bufferKey;
	private String bufferValue;

	public String execute() {

		session.put(playerName, "");
		return SUCCESS;
	}

	public void validate() {

		if (playerName.trim().length() == 0) {
			addFieldError("playerName", "Just type name here ->");
		}
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getBufferValue() {
		return bufferValue;
	}

	public void setBufferValue(String bufferValue) {
		this.bufferValue = bufferValue;
	}

	public String getBufferKey() {
		return bufferKey;
	}

	public void setBufferKey(String bufferKey) {
		this.bufferKey = bufferKey;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}

	public Map<String, Object> getSession() {
		return session;

	}
}
