package bowling.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import bowling.dao.GameDao;


public class ShowGames extends BowlingSupport implements ApplicationContextAware  {

	private List gamelist = new ArrayList();

	private GameDao gameDao;

	private ApplicationContext context;

	@Override
	public String execute() throws Exception {
		
		gamelist = gameDao.showGames();

		return SUCCESS;

	}

	public List getGamelist() {
		return gamelist;
	}

	public void setGamelist(List gamelist) {
		this.gamelist = gamelist;
	}


	public GameDao getGameDao() {
		return gameDao;
	}


	public void setGameDao(GameDao gameDao) {
		this.gameDao = gameDao;
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.context=arg0;
		
	}

}
