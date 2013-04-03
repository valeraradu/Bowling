/*
 * $Id: ExampleSupport.java 739661 2009-02-01 00:06:00Z davenewton $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package myaction;

import java.util.List;

import Bowling.Dao.ServiceDao;
import Bowling.entities.Game;
import Bowling.entities.Player;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Base Action class for the Tutorial package.
 */
public class BowlingSupport extends ActionSupport {
	
	public static Player getNextPlayerCurrentGame(Player currentPlayer) {

		Player p;
		Game game = ServiceDao.getCurrentGame();
		List<Player> playerlist = ServiceDao.getPlayersByGame(game);
		int listsize = playerlist.size();
		if (listsize-1 > playerlist.indexOf(currentPlayer)) {
			p = playerlist.get(playerlist.indexOf(currentPlayer)+1);
		} else {
			p = playerlist.get(0);
		}

		return p;
	}
	
	public static int getNextFrameNoCurrentGamePlayer(Player currentPlayer, int frameno) {

		int nextframe;
		Game game = ServiceDao.getCurrentGame();
		List<Player> playerlist = ServiceDao.getPlayersByGame(game);
		int listsize = playerlist.size();
		if (listsize-1 == playerlist.indexOf(currentPlayer)) {
			nextframe = frameno+1;
		} else {
			nextframe = frameno;
		}

		return nextframe;
	}
	
	public static boolean isLastPlayer(Player currentPlayer) {

		Game game = ServiceDao.getCurrentGame();
		List<Player> playerlist = ServiceDao.getPlayersByGame(game);
		int listsize = playerlist.size();
		if (playerlist.get(listsize-1).equals(currentPlayer)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isLastFrame(Player currentPlayer, int frameno) {

		if (isLastPlayer(currentPlayer)&&frameno==9) {
			return true;
		} else {
			return false;
		}
	}
}
