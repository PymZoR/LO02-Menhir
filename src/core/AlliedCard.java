package core;

import java.util.Vector;


public class AlliedCard extends Card {
	static private Vector<AlliedCard> cardList = null;
	private int[] values;
	private CardType type;

	static private void init() {
		int[] taupe1Values = { 1, 1, 1, 1 };
		int[] taupe2Values = { 0, 2, 2, 0 };
		int[] taupe3Values = { 0, 1, 2, 1 };
		int[] dog1Values   = { 2, 0, 2, 0 };
		int[] dog2Values   = { 1, 2, 0, 1 };
		int[] dog3Values   = { 0, 1, 3, 0 };

		AlliedCard.cardList.add(CardType.TAUPE1.ordinal(), new AlliedCard(taupe1Values, CardType.TAUPE1));
		AlliedCard.cardList.add(CardType.TAUPE2.ordinal(), new AlliedCard(taupe2Values, CardType.TAUPE2));
		AlliedCard.cardList.add(CardType.TAUPE3.ordinal(), new AlliedCard(taupe3Values, CardType.TAUPE3));
		AlliedCard.cardList.add(CardType.DOG1.ordinal(), new AlliedCard(dog1Values, CardType.DOG1));
		AlliedCard.cardList.add(CardType.DOG2.ordinal(), new AlliedCard(dog2Values, CardType.DOG2));
		AlliedCard.cardList.add(CardType.DOG3.ordinal(), new AlliedCard(dog3Values, CardType.DOG3));
	}

	static public AlliedCard getCard(CardType identifier) {
		return AlliedCard.cardList.get(identifier.ordinal());
	}

	private AlliedCard(int[] values, CardType type) {
		super(null, null);
		this.values = values;
		this.type   = type;
	}
	
	public CardType getType() {
		return this.type;
	}
}
