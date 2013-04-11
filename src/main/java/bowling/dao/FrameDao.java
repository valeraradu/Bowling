package bowling.dao;

import java.util.List;


import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import bowling.entities.Frame;
import bowling.entities.Game;
import bowling.entities.Player;


@Component
@Transactional
public class FrameDao {


	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private PlayerDao playerDao;
	
	@Inject
	private GameDao gameDao;
	
	public void persistFrame(Frame frame) {

		entityManager.persist(frame);
		
	}
	
	public boolean isLastFrame(Player currentPlayer, int frameno) {

		if (playerDao.isLastPlayer(currentPlayer)&&frameno==9) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getNextFrameNoCurrentGamePlayer(Player currentPlayer, int frameno) {

		int nextframe;
		Game game = gameDao.getCurrentGame();
		List<Player> playerlist = playerDao.getPlayersByGame(game);
		int listsize = playerlist.size();
		if (listsize-1 == playerlist.indexOf(currentPlayer)) {
			nextframe = frameno+1;
		} else {
			nextframe = frameno;
		}

		return nextframe;
	}
	
}
