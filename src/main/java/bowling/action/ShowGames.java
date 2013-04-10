package myaction;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import Bowling.Dao.ServiceDao;
import Bowling.entities.Game;
import Bowling.entities.Score;

public class ShowGames extends BowlingSupport {
 
	private List gamelist = new ArrayList();

	@Override
	public String execute() throws Exception {
		
		gamelist = ServiceDao.showGames();
		
		return SUCCESS;

	}

	public List getGamelist() {
		return gamelist;
	}

	public void setGamelist(List gamelist) {
		this.gamelist = gamelist;
	}

}
