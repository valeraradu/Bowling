package bowling.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import bowling.entities.Game;
import bowling.entities.Player;
import bowling.entities.Score;

@Component
@Transactional
public class ScoreDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void persistScore(Score score) {

		entityManager.persist(score);

	}
	
	public Score getScoreByGameAndPlayer(Game game, Player player) {

		Score score;
		Query q = entityManager.createQuery(
				"SELECT u FROM Game u where u = :game");
		q.setParameter("game", game);
		Game g = (Game) q.getSingleResult();
		score = g.getScores().get(player);
		return score;
	}
	
}
