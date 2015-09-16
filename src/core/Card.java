package core;

import java.util.Vector;


public class Card {
	static public final int CARD_NUMBER = 24;
	static private Vector<Card> cardList;
	private int[][] valueMatrix;
	private CardType type;

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

	static public Card getCard(CardType identifier) {
		if (Card.cardList == null) {
			Card.init();
		}
		return Card.cardList.get(identifier.ordinal());
	}

	protected Card(int[][] valueMatrix, CardType type) {
		this.valueMatrix = valueMatrix;
		this.type        = type;
	}

	public CardType getType() {
		return this.type;
	}
}
