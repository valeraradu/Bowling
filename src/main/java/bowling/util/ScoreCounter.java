package bowling.util;

import java.util.List;

import bowling.entities.Frame;


public class ScoreCounter {

	public static Integer getBallValue(String[] balllist, int index) {

		if (index == -1) {
			return 0;
			//there x stand for strike
		} else if (balllist[index].equals("x")) {
			return 10;
			//there / stand for spare
		} else if (balllist[index].equals("/")) {
			return 10 - getBallValue(balllist, index - 1);
		} else if (balllist[index].equals("") || balllist[index].equals(" ") || balllist[index].equals("-")) {
			return 0;
		} else {
			return Integer.valueOf(balllist[index]);
		}

	}

	public static Integer getNextBallIndex(String[] balllist, int index) {

		if (index == balllist.length-1||index<0) {
			return -1;
		} else if (balllist[index + 1].equals("")
				|| balllist[index + 1].equals(" ")) {
			index = getNextBallIndex(balllist, index + 1);
		} else {
			index = index + 1;
		}
		return index;
	}

	public static Integer getNextBallValue(String[] balllist, int index) {
		
		return getBallValue(balllist, getNextBallIndex(balllist, index));

	}

	public static Integer calculateScore(List<Frame> frames) {
		
		String[] balllist = new String[20];
		Integer total = 0;
		
		for (int i = 0; i < 10; i++) {
			balllist[i * 2] = frames.get(i).getBall1();
			balllist[(i * 2) + 1] = frames.get(i).getBall2();
		}

		for (int i = 0; i < 20; i++) {
			//case when strike is earned
			if (balllist[i].equals("x")) {
				total += 10;
				int next = getNextBallIndex(balllist, i);
				total += getBallValue(balllist, next);
				int nextnext = getNextBallIndex(balllist,next);
				total += getBallValue(balllist,nextnext);
		    //case when spare is earned
			} else if (balllist[i].equals("/")) {
				total += getBallValue(balllist, i);
				total += getNextBallValue(balllist, i);
			} else {
				total += getBallValue(balllist, i);
			}
		}
		return total;
	}
}
