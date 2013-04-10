package bowling.entities;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyJoinColumn;

@Entity
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int gameId;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@MapKeyJoinColumn(name="player")
	private Map<Player, Score> scores = new HashMap<Player, Score>();

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public Map<Player, Score> getScores() {
		return scores;
	}

	public void setScores(Map<Player, Score> scores) {
		this.scores = scores;
	}
}
