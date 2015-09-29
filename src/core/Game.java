package core;

import java.util.Random;
import java.util.Vector;

public class Game implements Playable {
	private boolean running = false;
	private Vector<Round> rounds = null;
	private Round currentRound = null;
	private int roundNumber = 0;

	public Game(int playerNumber, int iaNumber) throws Exception {
		this.rounds = new Vector<Round>();
		this.roundNumber = playerNumber;

		this.rounds.add(new Round(playerNumber, iaNumber));
		this.currentRound = this.rounds.get(0);
		this.currentRound.setNumber(0);

		for (int i = 1; i < playerNumber; i++) {
			try {
				this.rounds.add(new Round(this.getPlayers(), i));
			} catch (Exception e) {
				throw e;
			}
		}
	}

	public void chooseAlliedCards(Player source, boolean allied) {
		if (allied) {
			int randomBoolean = (new Random()).nextInt(2);
			source.drawAlliedCards((randomBoolean == 1));
		} else {
			source.getField().addSmallRockNumber(2);
		}
	}

	@Override
	public SeasonType getActualSeason() {
		return this.currentRound.getActualSeason();
	}

	@Override
	public Player getCurrentPlayer() {
		return this.currentRound.getCurrentPlayer();
	}

	public Round getCurrentRound() {
		return this.currentRound;
	}

	@Override
	public Player getPlayer(int i) {
		return this.currentRound.getPlayer(i);
	}

	@Override
	public int getPlayerNumber() {
		return this.roundNumber;
	}

	@Override
	public Vector<Player> getPlayers() {
		return this.currentRound.getPlayers();
	}

	public int getRoundNumber() {
		return this.roundNumber;
	}

	@Override
	public boolean isRunning() {
		return this.running;
	}

	@Override
	public void nextTurn(Card card, ActionType action, Player player) {
		this.currentRound.nextTurn(card, action, player);
		int currentRoundNumber = this.rounds.indexOf(this.currentRound);

		if (!this.currentRound.isRunning()) {
			if (currentRoundNumber == (this.roundNumber - 1)) {
				this.running = false;
			} else {
				this.currentRound = this.rounds.get(currentRoundNumber + 1);
				this.currentRound.start();
			}
		}
	}

	@Override
	public void playDog(Player source) {
		this.currentRound.playDog(source);
	}

	@Override
	public void playFertilizer(Player source, int fertilizeNumber) {
		this.currentRound.playFertilizer(source, fertilizeNumber);

	}

	@Override
	public void playGiant(Player source, int smallRockNumber) {
		this.currentRound.playGiant(source, smallRockNumber);

	}

	@Override
	public void playHobgoblin(Player source, Player target, int hobgoblinNumber) {
		this.currentRound.playHobgoblin(source, target, hobgoblinNumber);

	}

	@Override
	public void playTaupe(Player source, Player target, int bigRockNumber) {
		this.currentRound.playTaupe(source, target, bigRockNumber);
	}

	@Override
	public void start() {
		this.currentRound.start();
		this.running = true;
	}
}