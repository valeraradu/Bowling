package bowling.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import bowling.entities.Game;
import bowling.entities.Player;


@Component
@Transactional
public class PlayerDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Inject
	private GameDao gameDao;

	public void persistPlayer(Player player) {

		entityManager.persist(player);

	}

	public Player findOrCreate(String name) {
		Player p;
		try {
			Query q = entityManager
					.createQuery("SELECT u FROM Player u where u.playerName = :name");
			q.setParameter("name", name);
			p = (Player) q.getSingleResult();
		} catch (NoResultException nsre) {
			p = new Player(name);

		}

		return p;
	}

	public List<Player> getPlayersByGame(Game game) {

		List<Player> p = new ArrayList<Player>();
		Query q = entityManager
				.createQuery("SELECT u FROM Game u where u = :game");
		q.setParameter("game", game);
		Game g = (Game) q.getSingleResult();
		p.addAll(g.getScores().keySet());
		return p;
	}

	public Player getNextPlayerCurrentGame(Player currentPlayer) {

		Player p;
		Game game = gameDao.getCurrentGame();
		List<Player> playerlist = this.getPlayersByGame(game);
		int listsize = playerlist.size();
		if (listsize - 1 > playerlist.indexOf(currentPlayer)) {
			p = playerlist.get(playerlist.indexOf(currentPlayer) + 1);
		} else {
			p = playerlist.get(0);
		}

		return p;
	}

	public boolean isLastPlayer(Player currentPlayer) {

		Game game = gameDao.getCurrentGame();
		List<Player> playerlist = this.getPlayersByGame(game);
		int listsize = playerlist.size();
		if (playerlist.get(listsize - 1).equals(currentPlayer)) {
			return true;
		} else {
			return false;
		}
	}
	

	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
