package core;


import java.util.Random;
import java.util.Vector;

/**
 * Represent a player (alive or not)
 */
public class Player {
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
	private Game game;

	/**
	 * Create a new player
	 * @param game   Game reference
	 * @param number Player index
	 */
	public Player(Game game, int number) {
		this.cards       = new Vector<Card>();
		this.alliedCards = new Vector<AlliedCard>();
		this.field       = new Field();
		this.number      = number;
		this.game        = game;
	}

	/**
	 * Make the player get random cards
	 * @param cardNumber Amount of cards to get
	 */
	public void drawCard(int cardNumber) {
		for (int i = 0; i < cardNumber; i++) {
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
			this.game.playHobgoblin(target, actionValue);
			break;
		}
	}

	/**
	 * Get the player cards
	 * @return The player cards
	 */
	public Vector<Card> getCards() {
		return this.cards;
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

	/**
	 * Get the player field
	 * @return The player field
	 */
	public Field getField() {
		return this.field;
	}

	/**
	 * Get the player index
	 * @return The player index
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * Get the ref game
	 * @return The ref game
	 */
	public Game getGame() {
		return this.game;
	}
}
