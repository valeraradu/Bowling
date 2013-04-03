package Bowling.util;

import static Bowling.util.ScoreCountHelper.getBallValue;
import static Bowling.util.ScoreCountHelper.getNextBallIndex;
import static Bowling.util.ScoreCountHelper.getNextBallValue;

import java.util.List;

import Bowling.entities.Frame;

public class ScoreCountHelper {

	public static Integer getBallValue(String[] balllist, int index) {

		if (index == -1) {
			return 0;
		} else if (balllist[index].equals("x")) {
			return 10;
		} else if (balllist[index].equals("/")) {
			return 10 - getBallValue(balllist, index - 1);
		} else if (balllist[index].equals("") || balllist[index].equals(" ")) {
			return 0;
		} else {
			return Integer.valueOf(balllist[index]);
		}

	}

	public static Integer getNextBallIndex(String[] balllist, int index) {

		if (index == balllist.length - 1) {
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

		int i = getNextBallIndex(balllist, index);
		return getBallValue(balllist, i);

	}

	public static Integer calculateScore(List<Frame> frames) {
		String[] balllist = new String[20];
		Integer total = 0;
		for (int i = 0; i < 10; i++) {
			balllist[i * 2] = frames.get(i).getBall1();
			balllist[(i * 2) + 1] = frames.get(i).getBall2();
		}

		for (int i = 0; i < 20; i++) {
			if (balllist[i].equals("x")) {
				total += 10;
				total += getNextBallValue(balllist, i);
				total += getNextBallValue(
						balllist,
						getNextBallIndex(balllist,
								getNextBallIndex(balllist, i)));
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
