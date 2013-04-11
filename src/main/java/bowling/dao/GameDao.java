package bowling.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import bowling.entities.Frame;
import bowling.entities.Game;
import bowling.entities.Player;
import bowling.entities.Score;

@Component
@Transactional
public class GameDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private ScoreDao scoreDao;

	@Inject
	private PlayerDao playerDao;

	public List showGames() {

		Query query = entityManager.createQuery("from Game");

		return query.getResultList();

	}

	public void persistGame(Game game) {

		for (Score s : game.getScores().values()) {
			scoreDao.persistScore(s);
		}
		for (Player p : game.getScores().keySet()) {
			playerDao.persistPlayer(p);
		}
		entityManager.persist(game);

	}

	public void updateGame(Game game) {
		entityManager.merge(game);

	}

	public Game getCurrentGame() {

		Game game = (Game) entityManager
				.createQuery(
						"SELECT e FROM Game e	WHERE e.id = (Select MAX(e.id) From Game e)")
				.getSingleResult();

		return game;

	}

	public Game createNewGame(Map<String, Object> session) {

		Game game = new Game();

		for (String toBeAddedPlayer : session.keySet()) {

			Score s = new Score();
			for (int i = 0; i < 10; i++) {
				Frame f = new Frame();
				s.getFrames().add(i, f);
			}

			game.getScores().put(playerDao.findOrCreate(toBeAddedPlayer), s);
		}
		this.persistGame(game);
		return game;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public ScoreDao getScoreDao() {
		return scoreDao;
	}

	public void setScoreDao(ScoreDao scoreDao) {
		this.scoreDao = scoreDao;
	}

	public PlayerDao getPlayerDao() {
		return playerDao;
	}

	public void setPlayerDao(PlayerDao playerDao) {
		this.playerDao = playerDao;
	}
}
