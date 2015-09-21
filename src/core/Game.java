package core;

import java.util.Vector;

public class Game implements Playable {
	public Game(int playerNumber) {
		
	}

	@Override
	public Player getCurrentPlayer() {
		return null;
	}
	
	public Round getCurrentRound() {
		return null;
	}
	
	@Override
	public SeasonType getActualSeason() {
		return SeasonType.WINTER;
	}

	@Override
	public Player getPlayer(int i) {
		return null;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nextTurn(Card card, ActionType action, Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playTaupe(Player source, Player target, int bigRockNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playDog(Player source) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playGiant(Player source, int smallRockNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playFertilizer(Player source, int fertilizeNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playHobgoblin(Player source, Player target, int hobgoblinNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isRunning() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getPlayerNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Vector<Player> getPlayers() {
		// TODO Auto-generated method stub
		return null;
	}
}