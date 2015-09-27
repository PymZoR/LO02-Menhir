package core;


import java.util.Random;
import java.util.Vector;

/**
 * Represent a player (alive or not)
 */
public class Player implements Comparable<Player> {
	/**
	 * Player cards and field
	 */
	private Vector<Card> cards;
	private Vector<AlliedCard> alliedCards;
	private Field field;

	/**
	 * Player index
	 */
	private int number;

	/**
	 * Reference to the game
	 */
	protected Playable game;

	/**
	 * Create a new player
	 * @param game   Playable reference
	 * @param number Player index
	 */
	public Player(Playable game, int number) {
		this.cards       = new Vector<Card>();
		this.alliedCards = new Vector<AlliedCard>();
		this.field       = new Field();
		this.number      = number;
		this.game        = game;
	}

	/**
	 * Compare two player's scores by their fields
	 */
	@Override
	public int compareTo(Player comparePlayer) {
		return this.getField().compareTo(comparePlayer.getField());
	}

	public void drawAlliedCards(boolean taupe) {
		int offset = taupe ? 0 : 3;

		for (int i = 0; i < Round.ALLIED_CARDS_IN_HAND; i++) {
			CardType randomType;
			AlliedCard newCard;

			do {
				randomType = CardType.values()[new Random().nextInt(3) +
					Card.CARD_NUMBER + offset];
				newCard = AlliedCard.getCard(randomType);
			} while (newCard.isDrawed() == true);

			this.alliedCards.add(newCard);
		}
	}

	/**
	 * Make the player get random cards
	 * @param cardNumber Amount of cards to get
	 */
	private void drawCards() {
		for (int i = 0; i < Round.CARDS_IN_HAND; i++) {
			CardType randomType;
			Card newCard;

			do {
				randomType = CardType.values()[new Random().nextInt(Card.CARD_NUMBER)];
				newCard = Card.getCard(randomType);
			} while (newCard.isDrawed() == true);

			this.cards.add(newCard);
		}
	}

	/**
	 * The the player allied cards (taupe or dogs)
	 * @return The player allied cards
	 */
	public Vector<AlliedCard> getAlliedCards() {
		return this.alliedCards;
	}

	/**
	 * Get one card given the index
	 * @param cardId The card index
	 * @return The card
	 */
	public Card getCardById(int cardId) {
		return this.cards.get(cardId);
	}

	public AlliedCard getAlliedCardById(int cardId) {
		return this.alliedCards.get(cardId);
	}
	/**
	 * Get the player cards
	 * @return The player cards
	 */
	public Vector<Card> getCards() {
		return this.cards;
	}

	/**
	 * Get the player field
	 * @return The player field
	 */
	public Field getField() {
		return this.field;
	}

	/**
	 * Get the ref game
	 * @return The ref game
	 */
	public Playable getGame() {
		return this.game;
	}

	/**
	 * Get the player index
	 * @return The player index
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * Play a card
	 * @param card   The card to play
	 * @param action The action to play
	 * @param target The optional target
	 */
	public void playCard(Card card, ActionType action, Player target) {
		int actionValue = card.getValue(action, this.game.getActualSeason());

		switch (action) {
		case GIANT:
			this.game.playGiant(this, actionValue);
			break;

		case FERTILIZER:
			this.game.playFertilizer(this, actionValue);
			break;

		case HOBGOBLIN:
			this.game.playHobgoblin(this, target, actionValue);
			break;
		}

		this.cards.remove(card);
	}

	/**
	 * Reset player after round
	 */
	public void reset() {
		this.drawCards();
		this.field.reset();
	}

	/**
	 * Format player to string
	 */
	@Override
	public String toString() {
		return "Player " + (this.number+1) + ". " + this.field;
	}
}
