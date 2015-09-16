package core;

import java.util.Random;
import java.util.Vector;


public class Player {
	private Vector<Card> cards;
	private Field field;
	private int number;

	public Player(int number) {
		this.cards  = new Vector<Card>();
		this.field  = new Field();
		this.number = number;
	}

	public void drawCard(int cardNumber) {
		for (int i = 0; i < cardNumber; i++) {
			CardType randomType = CardType.values()[new Random().nextInt(Card.CARD_NUMBER)];
			Card newCard = Card.getCard(randomType);
			this.cards.add(newCard);
		}
	}

	public void playCard(Card card, ActionType action, Player target) {
		switch (card.getType()) {
			case TAUPE1:
			case TAUPE2:
			case TAUPE3:
				this.playTaupe(target);
				break;
			case DOG1:
			case DOG2:
			case DOG3:
				this.playDog();
				break;
			default:
				switch (action) {
					case GIANT:
						this.playGiant(card);
						break;
					case FERTILIZER:
						this.playFertilizer(card);
						break;
					case HOBGOBLIN:
						this.playHobgoblin(card, target);
						break;
				}
		}
	}

	public Card getCardById(int cardId) {
		return this.cards.get(cardId);
	}

	private void playTaupe(Player target) {

	}

	private void playDog() {

	}

	private void playGiant (Card card) {

	}

	private void playFertilizer (Card card) {

	}

	private void playHobgoblin (Card card, Player target) {

	}

	public Field getField() {
		return this.field;
	}

	public int getNumber() {
		return this.number;
	}
}
