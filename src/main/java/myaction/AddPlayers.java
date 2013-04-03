package myaction;

import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

import Bowling.entities.Frame;
import Bowling.entities.Score;

import com.opensymphony.xwork2.ActionSupport;
import java.util.UUID;

public class AddPlayers extends ActionSupport implements SessionAware {

	private Map<String, Object> session;
	private String playerName;
	private String bufferKey;
	private String bufferValue;

	public String execute() {
		if (playerName != null) {

			session.put(playerName, "");
		}
		return SUCCESS;
	}

	/*
	 * public String update() {
	 * 
	 * session.put(bufferKey, bufferValue); return SUCCESS; }
	 */

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
