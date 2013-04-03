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

public class CreateGame extends ActionSupport implements SessionAware {

	private Game currentGame;

	private Map<String, Object> session;

	public String execute() {

		Game g = new Game();
		
		session.values();
		//session.clear();
		
		for(String toBeAddedPlayer : session.keySet()){
			
			Score s = new Score();
			for (int i = 0; i < 10; i++) {
				Frame f = new Frame();
				s.getFrames().add(i, f);
			}

			ServiceDao.persistScore(s);

			g.getScores().put(ServiceDao.getPlayer(toBeAddedPlayer), s);
		}

		ServiceDao.persistGame(g);

		currentGame = ServiceDao.getCurrentGame();
		session.put("currentPlayer", ServiceDao.getPlayersByGame(currentGame)
				.get(0));
		session.put("currentFrameNo", 0);
		session.put("currentBall", 1);
		return SUCCESS;
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
