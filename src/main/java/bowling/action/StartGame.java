package myaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

import Bowling.Dao.ServiceDao;
import Bowling.entities.Frame;
import Bowling.entities.Game;
import Bowling.entities.Player;
import Bowling.entities.Score;

import com.opensymphony.xwork2.ActionSupport;

public class StartGame extends ActionSupport implements SessionAware {

	private Game currentGame;
	
	private Map<String, Object> session;

	public String execute() {

		if (session.get("currentPlayer") == null) {

			return SUCCESS;
			
		} else {
			currentGame = ServiceDao.getCurrentGame();
			return "RESUME";
		}
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}

	public Map<String, Object> getSession() {
		return session;

	}

	public Game getCurrentGame() {
		return currentGame;
	}

	public void setCurrentGame(Game currentGame) {
		this.currentGame = currentGame;
	}
}
