package Bowling2.example;

import org.junit.*;

import Bowling.entities.Frame;
import Bowling.entities.Score;
import Bowling.util.ScoreCountHelper;
import static org.junit.Assert.*;

public class TestScore {


  @Before
  public void setUp() throws Exception {

  }

  @Test
  public void testScoreMinimal() throws Exception {
	
	  Score score = new Score();
	  for (int i = 0; i < 10; i++) {
			Frame f = new Frame();
			f.setBall1("1");
			f.setBall2("1");
			score.getFrames().add(i, f);
		}
	  
	  assertTrue("Minimal score result",
              score.getTotal()==20);
  }

  @Test
  public void testScoreMaximal() throws Exception {
	  
	  Score score = new Score();
	  for (int i = 0; i < 10; i++) {
			Frame f = new Frame();
			f.setBall1("x");
			f.setBall2("");
			score.getFrames().add(i, f);
		}
	  
	  assertTrue("Maximal score result "+score.getTotal(),
              score.getTotal()==240);
  }
  
  @Test
  public void testScoreSpare() throws Exception {
	
	  Score score = new Score();
	  for (int i = 0; i < 10; i++) {
			Frame f = new Frame();
			f.setBall1("5");
			f.setBall2("/");
			score.getFrames().add(i, f);
		}
	  
	  assertTrue("Maximal score result "+score.getTotal(),
              score.getTotal()==195);
  }
  
  @Test
  public void testScoreNumeric() throws Exception {
	  //Score score = mock(Score.class);
	  Score score = new Score();
	  for (int i = 0; i < 10; i++) {
			Frame f = new Frame();
			f.setBall1(Integer.toString(i));
			f.setBall2("");
			score.getFrames().add(i, f);
		}
	  
	  assertTrue("Average score result " + score.getTotal(),
              score.getTotal()==45);
  }
  
  @Test
  public void testgetNextBallIndex() throws Exception {
	
	  String[] str={"x","","x","","x"};
	  
	  
	  assertTrue("Average score result  index 0 expected 2 get " +ScoreCountHelper.getNextBallIndex(str, 3),
			  ScoreCountHelper.getNextBallIndex(str, 3)==4);
	  assertTrue("Average score result  index 1 expected 2 get " +ScoreCountHelper.getNextBallIndex(str, 4),
			  ScoreCountHelper.getNextBallIndex(str, 4)==-1);
	  
  }
  
  @After
  public void tearDown() throws Exception {

  }
}
