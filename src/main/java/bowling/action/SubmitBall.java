package bowling.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;


import bowling.dao.FrameDao;
import bowling.dao.GameDao;
import bowling.dao.PlayerDao;
import bowling.dao.ScoreDao;
import bowling.entities.Game;
import bowling.entities.Player;
import bowling.entities.Score;

import com.opensymphony.xwork2.ActionSupport;

public class SubmitBall extends ActionSupport implements SessionAware {

	private static final Logger logger = Logger.getLogger(ActionSupport.class);

	private GameDao gameDao;
	private PlayerDao playerDao;
	private ScoreDao scoreDao;
	private FrameDao frameDao;
	private Map<String, Object> session;
	private Game currentGame;
	private String ballValue;
	private Player currentplayer;
	private int currentframe;
	private int currentball;
	private List gamelist = new ArrayList();

	public String execute() {

			currentGame = gameDao.getCurrentGame();
			currentplayer = (Player) session.get("currentPlayer");
			currentframe = (Integer) session.get("currentFrameNo");
			currentball = (Integer) session.get("currentBall");

			boolean islastframe = frameDao.isLastFrame(currentplayer,
					currentframe);

			Score currentscore = scoreDao.getScoreByGameAndPlayer(
					currentGame, currentplayer);

			if (currentball == 1 && !ballValue.equals("x")) {
				currentscore.getFrames().get(currentframe).setBall1(ballValue);
				session.put("currentBall", 2);
			} else if (currentball == 2 && islastframe) {
				currentscore.getFrames().get(currentframe).setBall2(ballValue);
				session.clear();
				logger.debug("Game finished");
				return "FINISH";

			} else {
				currentscore.getFrames().get(currentframe).setBall2(ballValue);
				session.put("currentPlayer",
						playerDao.getNextPlayerCurrentGame(currentplayer));
				session.put("currentFrameNo", frameDao
						.getNextFrameNoCurrentGamePlayer(currentplayer,
								currentframe));
				session.put("currentBall", 1);
			}

			
			currentGame.getScores().put(currentplayer, currentscore);
			gameDao.updateGame(currentGame);
			logger.debug("Ball submited");
			return SUCCESS;
		

	}

	public void validate() {

		if (ballValue.trim().equals("")) {
			addFieldError("frameValue",
					"type \"-\" if no pins are knocked down");
		}
	}

	public Player getCurrentplayer() {
		return currentplayer;
	}

	public void setCurrentplayer(Player currentplayer) {
		this.currentplayer = currentplayer;
	}

	public int getCurrentframe() {
		return currentframe;
	}

	public void setCurrentframe(int currentframe) {
		this.currentframe = currentframe;
	}

	public int getCurrentball() {
		return currentball;
	}

	public void setCurrentball(int currentball) {
		this.currentball = currentball;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Game getCurrentGame() {
		return currentGame=gameDao.getCurrentGame();
	}

	public void setCurrentGame(Game currentGame) {
		this.currentGame = currentGame;
	}

	public String getFrameValue() {
		return ballValue;
	}

	public void setFrameValue(String frameValue) {
		this.ballValue = frameValue;
	}

	public GameDao getGameDao() {
		return gameDao;
	}

	public void setGameDao(GameDao gameDao) {
		this.gameDao = gameDao;
	}

	public PlayerDao getPlayerDao() {
		return playerDao;
	}

	public void setPlayerDao(PlayerDao playerDao) {
		this.playerDao = playerDao;
	}
	
	public ScoreDao getScoreDao() {
		return scoreDao;
	}

	public void setScoreDao(ScoreDao scoreDao) {
		this.scoreDao = scoreDao;
	}

	public FrameDao getFrameDao() {
		return frameDao;
	}

	public void setFrameDao(FrameDao frameDao) {
		this.frameDao = frameDao;
	}

	public List getGamelist() {
		return gamelist=gameDao.showGames();
	}

	public void setGamelist(List gamelist) {
		this.gamelist = gamelist;
	}

	
}
