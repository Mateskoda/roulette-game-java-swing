package model;

import model.enumeration.BetType;
import model.interfaces.Player;

public class SimplePlayer implements Player {
	
	private String playerId;
	private String playerName;
	private int initialPoints;
	private int bet;
	private BetType betType;

	public SimplePlayer(String playerId, String playerName, int initialPoints) {
		this.playerId = playerId;
		this.playerName = playerName;
		this.initialPoints = initialPoints;
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
		
	}

	@Override
	public int getPoints() {
		return initialPoints;
	}

	@Override
	public void setPoints(int points) {
		this.initialPoints = points;
		
	}

	@Override
	public String getPlayerId() {
		return playerId;
	}

	@Override
	public boolean setBet(int bet) {
		
		if(bet > 0 && bet <= this.initialPoints) {
			this.bet = bet ;
			return true;
		}
		else
			return false;
	}

	@Override
	public int getBet() {
		
		return bet;
	}

	@Override
	public void setBetType(BetType betType) {
		this.betType = betType;
	}

	@Override
	public BetType getBetType() {
		return betType;
	}

	@Override
	public void resetBet() {
		this.bet = 0;
		
	}

	@Override
	public String toString() {
		return "id=" + playerId + ", name= " + playerName 
				+ ", Bet=" + bet + ", betType=" + betType  + ", points=" + initialPoints;
	}
	
	

}
