package Bowling.Dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import Bowling.entities.Frame;
import Bowling.entities.Game;
import Bowling.entities.Player;
import Bowling.entities.Score;
//import Bowling.entities.Player;
import Bowling.util.EntityManagerProducer;

public class ServiceDao {

	private ServiceDao() {

	}

	public static void persistGame(Game game) {

		for (Score s : game.getScores().values()) {
			persistScore(s);
		}
		for (Player p : game.getScores().keySet()) {
			persistPlayer(p);
		}
		EntityManagerProducer.getEntityManager().getTransaction().begin();
		EntityManagerProducer.getEntityManager().persist(game);
		EntityManagerProducer.getEntityManager().getTransaction().commit();

	}

	public static List<Game> showGames() {

		List<Game> games = EntityManagerProducer.getEntityManager()
				.createQuery("select object(e) from Game as e").getResultList();

		return games;

	}

	public static Game getCurrentGame() {

		Game game = (Game) EntityManagerProducer
				.getEntityManager()
				.createQuery(
						"SELECT e FROM Game e	WHERE e.id = (Select MAX(e.id) From Game e)")
				.getSingleResult();

		return game;

	}

	public static void persistPlayer(Player player) {

		EntityManagerProducer.getEntityManager().getTransaction().begin();
		EntityManagerProducer.getEntityManager().persist(player);
		EntityManagerProducer.getEntityManager().getTransaction().commit();

	}

	public static void persistFrame(Frame frame) {

		EntityManagerProducer.getEntityManager().getTransaction().begin();
		EntityManagerProducer.getEntityManager().persist(frame);
		EntityManagerProducer.getEntityManager().getTransaction().commit();

	}

	public static void persistScore(Score score) {

		EntityManagerProducer.getEntityManager().getTransaction().begin();
		EntityManagerProducer.getEntityManager().persist(score);
		EntityManagerProducer.getEntityManager().getTransaction().commit();

	}

	public static Score getScoreByGameAndPlayer(Game game, Player player) {

		Score score;
		Query q = EntityManagerProducer.getEntityManager().createQuery(
				"SELECT u FROM Game u where u = :game");
		q.setParameter("game", game);
		Game g = (Game) q.getSingleResult();
		score = g.getScores().get(player);
		return score;
	}

	public static Player getPlayer(String name) {
		Player p;
		try {
			Query q = EntityManagerProducer.getEntityManager().createQuery(
					"SELECT u FROM Player u where u.playerName = :name");
			q.setParameter("name", name);
			p = (Player) q.getSingleResult();
		} catch (NoResultException nsre) {
			p = new Player(name);

		}

		return p;
	}

	public static List<Player> getPlayersByGame(Game game) {

		List<Player> p = new ArrayList<Player>();
		Query q = EntityManagerProducer.getEntityManager().createQuery(
				"SELECT u FROM Game u where u = :game");
		q.setParameter("game", game);
		Game g = (Game) q.getSingleResult();
		p.addAll(g.getScores().keySet());
		return p;
	}
}
