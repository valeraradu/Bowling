package bowling.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import static bowling.util.ScoreCounter.*;

@Entity
public class Score {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int scoreId;

	@OneToMany(cascade = CascadeType.ALL, targetEntity = Frame.class, fetch=FetchType.EAGER)
	private List<Frame> frames = new ArrayList<Frame>();

	@Transient
	private int total;

	public int getScoreId() {
		return scoreId;
	}

	public void setScoreId(int scoreId) {
		this.scoreId = scoreId;
	}

	public List<Frame> getFrames() {
		return frames;
	}

	public void setFrames(List<Frame> frames) {
		this.frames = frames;
	}

	public int getTotal() {

		
		return calculateScore(frames);
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
