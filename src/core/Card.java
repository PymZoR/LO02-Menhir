package core;


import java.util.Vector;

import helpers.StringUtils;

/**
 * Card representation
 */
public class Card {
	/**
	 * Maximum number of cards
	 */
	static public final int CARD_NUMBER = 24;

	/**
	 * Cards are singletons
	 */
	static private Vector<Card> cardList;

	/**
	 * Card properties (values and type)
	 */
	private int[][] valueMatrix;
	private CardType type;
	private boolean drawed = false;

	/**
	 * Create all the cards
	 */
	static private void init() {
		Card.cardList = new Vector<Card>();

		int[][] moon1Values    = { {1, 1, 1, 1}, {2, 0, 1, 1}, {2, 0, 2, 0} };
		int[][] moon2Values    = { {2, 0, 1, 1}, {1, 3, 0, 0}, {0, 1, 2, 1} };
		int[][] moon3Values    = { {0, 0, 4, 0}, {0, 2, 2, 0}, {0, 0, 1, 3} };
		int[][] mermaid1Values = { {1, 3, 1, 0}, {1, 2, 1, 1}, {1, 0, 4, 0} };
		int[][] mermaid2Values = { {2, 1, 1, 1}, {1, 0, 2, 2}, {3, 0, 0, 2} };
		int[][] mermaid3Values = { {1, 2, 2, 0}, {1, 1, 2, 1}, {2, 0, 1, 2} };
		int[][] dryad1Values   = { {2, 1, 1, 2}, {1, 1, 1, 3}, {2, 0, 2, 2} };
		int[][] dryad2Values   = { {0, 3, 0, 3}, {2, 1, 3, 0}, {1, 1, 3, 1} };
		int[][] dryad3Values   = { {1, 2, 1, 2}, {1, 0, 1, 4}, {2, 4, 0, 0} };
		int[][] foutain1Values = { {1, 3, 1, 2}, {2, 1, 2, 2}, {0, 0, 3, 4} };
		int[][] foutain2Values = { {2, 2, 0, 3}, {1, 1, 4, 1}, {1, 2, 1, 3} };
		int[][] foutain3Values = { {2, 2, 3, 1}, {2, 3, 0, 3}, {1, 1, 3, 3} };
		int[][] gold1Values    = { {2, 2, 3, 1}, {2, 3, 0, 3}, {1, 1, 3, 3} };
		int[][] gold2Values    = { {2, 2, 2, 2}, {0, 4, 4, 0}, {1, 3, 2, 2} };
		int[][] gold3Values    = { {3, 1, 3, 1}, {1, 4, 2, 1}, {2, 4, 1, 1} };
		int[][] rainbow1Values = { {4, 1, 1, 1}, {1, 2, 1, 3}, {1, 2, 2, 2} };
		int[][] rainbow2Values = { {2, 3, 2, 0}, {0, 4, 3, 0}, {2, 1, 1, 3} };
		int[][] rainbow3Values = { {2, 2, 3, 0}, {1, 1, 1, 4}, {2, 0, 3, 2} };
		int[][] dolmen1Values  = { {3, 1, 4, 1}, {2, 1, 3, 3}, {2, 3, 2, 2} };
		int[][] dolmen2Values  = { {2, 4, 1, 2}, {2, 2, 2, 3}, {1, 4, 3, 1} };
		int[][] dolmen3Values  = { {3, 3, 3, 0}, {1, 3, 3, 2}, {2, 3, 1, 3} };
		int[][] fairy1Values   = { {1, 2, 2, 1}, {1, 2, 3, 0}, {0, 2, 2, 2} };
		int[][] fairy2Values   = { {4, 0, 1, 1}, {1, 1, 3, 1}, {0, 0, 3, 3} };
		int[][] fairy3Values   = { {2, 0, 3, 1}, {0, 3, 0, 3}, {1, 2, 2, 1} };

		Card.cardList.add(CardType.MOON1.ordinal(), new Card(moon1Values, CardType.MOON1));
		Card.cardList.add(CardType.MOON2.ordinal(), new Card(moon2Values, CardType.MOON2));
		Card.cardList.add(CardType.MOON3.ordinal(), new Card(moon3Values, CardType.MOON3));
		Card.cardList.add(CardType.MERMAID1.ordinal(), new Card(mermaid1Values, CardType.MERMAID1));
		Card.cardList.add(CardType.MERMAID2.ordinal(), new Card(mermaid2Values, CardType.MERMAID2));
		Card.cardList.add(CardType.MERMAID3.ordinal(), new Card(mermaid3Values, CardType.MERMAID3));
		Card.cardList.add(CardType.DRYAD1.ordinal(), new Card(dryad1Values, CardType.DRYAD1));
		Card.cardList.add(CardType.DRYAD2.ordinal(), new Card(dryad2Values, CardType.DRYAD2));
		Card.cardList.add(CardType.DRYAD3.ordinal(), new Card(dryad3Values, CardType.DRYAD3));
		Card.cardList.add(CardType.FOUNTAIN1.ordinal(), new Card(foutain1Values, CardType.FOUNTAIN1));
		Card.cardList.add(CardType.FOUNTAIN2.ordinal(), new Card(foutain2Values, CardType.FOUNTAIN2));
		Card.cardList.add(CardType.FOUNTAIN3.ordinal(), new Card(foutain3Values, CardType.FOUNTAIN3));
		Card.cardList.add(CardType.GOLD1.ordinal(), new Card(gold1Values, CardType.GOLD1));
		Card.cardList.add(CardType.GOLD2.ordinal(), new Card(gold2Values, CardType.GOLD2));
		Card.cardList.add(CardType.GOLD3.ordinal(), new Card(gold3Values, CardType.GOLD3));
		Card.cardList.add(CardType.RAINBOW1.ordinal(), new Card(rainbow1Values, CardType.RAINBOW1));
		Card.cardList.add(CardType.RAINBOW2.ordinal(), new Card(rainbow2Values, CardType.RAINBOW2));
		Card.cardList.add(CardType.RAINBOW3.ordinal(), new Card(rainbow3Values, CardType.RAINBOW3));
		Card.cardList.add(CardType.DOLMEN1.ordinal(), new Card(dolmen1Values, CardType.DOLMEN1));
		Card.cardList.add(CardType.DOLMEN2.ordinal(), new Card(dolmen2Values, CardType.DOLMEN2));
		Card.cardList.add(CardType.DOLMEN3.ordinal(), new Card(dolmen3Values, CardType.DOLMEN3));
		Card.cardList.add(CardType.FAIRY1.ordinal(), new Card(fairy1Values, CardType.FAIRY1));
		Card.cardList.add(CardType.FAIRY2.ordinal(), new Card(fairy2Values, CardType.FAIRY2));
		Card.cardList.add(CardType.FAIRY3.ordinal(), new Card(fairy3Values, CardType.FAIRY3));
	}

	/**
	 * Get a card by its identifier
	 * @param identifier The identifier
	 * @return The card
	 */
	static public Card getCard(CardType identifier) {
		if (Card.cardList == null) {
			Card.init();
		}
		return Card.cardList.get(identifier.ordinal());
	}

	static public void resetCards() {
		if (Card.cardList == null) {
			Card.init();
		}

		for (int i = 0; i < Card.CARD_NUMBER; i++) {
			Card.cardList.get(i).setDrawed(false);
		}
	}

	/**
	 * Create a card
	 * @param valueMatrix The card values
	 * @param type The card type
	 */
	protected Card(int[][] valueMatrix, CardType type) {
		this.valueMatrix = valueMatrix;
		this.type        = type;
	}

	/**
	 * Get the card type
	 * @return The card type
	 */
	public CardType getType() {
		return this.type;
	}

	/**
	 * Get the card value
	 * @param action The action (equivalent to the row)
	 * @param season The season (equivalent to the column)
	 * @return The amount of the matrix
	 */
	public int getValue(ActionType action, SeasonType season) {
		return this.valueMatrix[action.ordinal()][season.ordinal()];
	}

	/**
	 * Get the card values
	 * @return The card values
	 */
	public int[][] getValueMatrix() {
		return this.valueMatrix;
	}

	/**
	 * Render a card
	 * @param cardN The card index
	 * @return A string representation
	 */
	public String toASCII(int cardN) {
		String result = "--------------\n";
		result       += "| Card  .    |\n";
		result       += "|------------|\n";
		result       += "|   s S F W  |\n";
		result       += "| G . . . .  |\n";
		result       += "| F . . . .  |\n";
		result       += "| H . . . .  |\n";
		result       += "|------------|\n";

		result = StringUtils.replaceCharAt(result, 23, Integer.toString(cardN + 1));
		result = StringUtils.replaceCharAt(result, 64, Integer.toString(this.valueMatrix[0][0]));
		result = StringUtils.replaceCharAt(result, 66, Integer.toString(this.valueMatrix[0][1]));
		result = StringUtils.replaceCharAt(result, 68, Integer.toString(this.valueMatrix[0][2]));
		result = StringUtils.replaceCharAt(result, 70, Integer.toString(this.valueMatrix[0][3]));
		result = StringUtils.replaceCharAt(result, 79, Integer.toString(this.valueMatrix[1][0]));
		result = StringUtils.replaceCharAt(result, 81, Integer.toString(this.valueMatrix[1][1]));
		result = StringUtils.replaceCharAt(result, 83, Integer.toString(this.valueMatrix[1][2]));
		result = StringUtils.replaceCharAt(result, 85, Integer.toString(this.valueMatrix[1][3]));
		result = StringUtils.replaceCharAt(result, 94, Integer.toString(this.valueMatrix[2][0]));
		result = StringUtils.replaceCharAt(result, 96, Integer.toString(this.valueMatrix[2][1]));
		result = StringUtils.replaceCharAt(result, 98, Integer.toString(this.valueMatrix[2][2]));
		result = StringUtils.replaceCharAt(result, 100, Integer.toString(this.valueMatrix[2][3]));

		return result;
	}

	public boolean isDrawed() {
		return this.drawed;
	}

	public void setDrawed(boolean drawed) {
		this.drawed = drawed;
	}
}
