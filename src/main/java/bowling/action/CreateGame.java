package bowling.action;

import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;


import bowling.dao.GameDao;
import bowling.dao.PlayerDao;
import bowling.entities.Frame;
import bowling.entities.Game;
import bowling.entities.Score;

import com.opensymphony.xwork2.ActionSupport;

public class CreateGame extends ActionSupport implements SessionAware {

	private Game currentGame;
	private PlayerDao playerDao;
	private GameDao gameDao;

	private Map<String, Object> session;

	public String execute() {

		Game g = new Game();
		
		for(String toBeAddedPlayer : session.keySet()){
			
			Score s = new Score();
			for (int i = 0; i < 10; i++) {
				Frame f = new Frame();
				s.getFrames().add(i, f);
			}

			//serviceDao.persistScore(s);

			g.getScores().put(playerDao.getPlayer(toBeAddedPlayer), s);
		}

		gameDao.persistGame(g);

		currentGame = gameDao.getCurrentGame();
		session.put("currentPlayer", playerDao.getPlayersByGame(currentGame)
				.get(0));
		session.put("currentFrameNo", 0);
		session.put("currentBall", 1);
		return SUCCESS;
	}

	public void validate() {

		if (session.keySet().isEmpty()) {
			addFieldError("playerName", "Add players if you want to play ;)");
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
	

	public PlayerDao getPlayerDao() {
		return playerDao;
	}

	public void setPlayerDao(PlayerDao playerDao) {
		this.playerDao = playerDao;
	}

	public GameDao getGameDao() {
		return gameDao;
	}

	public void setGameDao(GameDao gameDao) {
		this.gameDao = gameDao;
	}

}
