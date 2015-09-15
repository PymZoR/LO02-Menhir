package core;

import java.util.List;


public class Card {
	static private List<Card> cardList;

	static private void init() {
		int[][] carte1Values = { {1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4} };
		int[][] carte2Values = { {1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4} };
		
		//Card.cardList.add(CardType.TRUC.ordinal(), new Card(carte1Values));
	}

	static public Card getCard(CardType identifier) {
		return Card.cardList.get(identifier.ordinal());
	};
	
	private int[][] valueMatrix;

	private Card(int[][] valueMatrix) {
		this.valueMatrix = valueMatrix;
	}
}
