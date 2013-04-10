package bowling.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;


import bowling.dao.GameDao;
import bowling.entities.Frame;
import bowling.entities.Game;
import bowling.entities.Player;
import bowling.entities.Score;

import com.opensymphony.xwork2.ActionSupport;

public class StartGame extends ActionSupport implements SessionAware {

	private Game currentGame;
	private GameDao gameDao;
	
	private Map<String, Object> session;

	public String execute() {

		if (session.get("currentPlayer") == null) {

			return SUCCESS;
			
		} else {
			currentGame = gameDao.getCurrentGame();
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

	public GameDao getGameDao() {
		return gameDao;
	}

	public void setGameDao(GameDao gameDao) {
		this.gameDao = gameDao;
	}

}
