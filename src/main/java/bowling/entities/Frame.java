package bowling.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Frame {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int frameId;
	
	private String ball1="";
	private String ball2="";
	
	public String getBall1() {
		return ball1;
	}
	public void setBall1(String ball1) {
		this.ball1 = ball1;
	}
	public String getBall2() {
		return ball2;
	}
	public void setBall2(String ball2) {
		this.ball2 = ball2;
	}
	public int getFrameId() {
		return frameId;
	}
	public void setFrameId(int frameId) {
		this.frameId = frameId;
	}
	@Override
	public String toString() {
		return ball1 + " "+ ball2;
	}
}
