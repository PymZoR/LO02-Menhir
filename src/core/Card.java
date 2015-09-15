package core;

import java.util.Vector;


public class Card {
	static private Vector<Card> cardList;

	static private void init() {
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

		Card.cardList.add(CardType.MOON1.ordinal(), new Card(moon1Values));
		Card.cardList.add(CardType.MOON2.ordinal(), new Card(moon2Values));
		Card.cardList.add(CardType.MOON3.ordinal(), new Card(moon3Values));
		Card.cardList.add(CardType.MERMAID1.ordinal(), new Card(mermaid1Values));
		Card.cardList.add(CardType.MERMAID2.ordinal(), new Card(mermaid2Values));
		Card.cardList.add(CardType.MERMAID3.ordinal(), new Card(mermaid3Values));
		Card.cardList.add(CardType.DRYAD1.ordinal(), new Card(dryad1Values));
		Card.cardList.add(CardType.DRYAD2.ordinal(), new Card(dryad2Values));
		Card.cardList.add(CardType.DRYAD3.ordinal(), new Card(dryad3Values));
		Card.cardList.add(CardType.FOUNTAIN1.ordinal(), new Card(foutain1Values));
		Card.cardList.add(CardType.FOUNTAIN2.ordinal(), new Card(foutain2Values));
		Card.cardList.add(CardType.FOUNTAIN3.ordinal(), new Card(foutain3Values));
		Card.cardList.add(CardType.GOLD1.ordinal(), new Card(gold1Values));
		Card.cardList.add(CardType.GOLD2.ordinal(), new Card(gold2Values));
		Card.cardList.add(CardType.GOLD3.ordinal(), new Card(gold3Values));
		Card.cardList.add(CardType.RAINBOW1.ordinal(), new Card(rainbow1Values));
		Card.cardList.add(CardType.RAINBOW2.ordinal(), new Card(rainbow2Values));
		Card.cardList.add(CardType.RAINBOW3.ordinal(), new Card(rainbow3Values));
		Card.cardList.add(CardType.DOLMEN1.ordinal(), new Card(dolmen1Values));
		Card.cardList.add(CardType.DOLMEN2.ordinal(), new Card(dolmen2Values));
		Card.cardList.add(CardType.DOLMEN3.ordinal(), new Card(dolmen3Values));
		Card.cardList.add(CardType.FAIRY1.ordinal(), new Card(fairy1Values));
		Card.cardList.add(CardType.FAIRY2.ordinal(), new Card(fairy2Values));
		Card.cardList.add(CardType.FAIRY3.ordinal(), new Card(fairy3Values));
	}

	static public Card getCard(CardType identifier) {
		if (Card.cardList == null) {
			Card.init();
		}
		return Card.cardList.get(identifier.ordinal());
	}

	private int[][] valueMatrix;

	protected Card(int[][] valueMatrix) {
		this.valueMatrix = valueMatrix;
	}
}
