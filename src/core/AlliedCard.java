package core;

import java.util.Vector;


public class AlliedCard extends Card {
	static private Vector<AlliedCard> cardList = null;

	static private void init() {
		int[] taupe1Values = { 1, 1, 1, 1 };
		int[] taupe2Values = { 0, 2, 2, 0 };
		int[] taupe3Values = { 0, 1, 2, 1 };
		int[] dog1Values   = { 2, 0, 2, 0 };
		int[] dog2Values   = { 1, 2, 0, 1 };
		int[] dog3Values   = { 0, 1, 3, 0 };

		AlliedCard.cardList.add(AlliedCardType.TAUPE1.ordinal(), new AlliedCard(taupe1Values));
		AlliedCard.cardList.add(AlliedCardType.TAUPE2.ordinal(), new AlliedCard(taupe1Values));
		AlliedCard.cardList.add(AlliedCardType.TAUPE3.ordinal(), new AlliedCard(taupe1Values));
		AlliedCard.cardList.add(AlliedCardType.DOG1.ordinal(), new AlliedCard(dog1Values));
		AlliedCard.cardList.add(AlliedCardType.DOG2.ordinal(), new AlliedCard(dog2Values));
		AlliedCard.cardList.add(AlliedCardType.DOG3.ordinal(), new AlliedCard(dog3Values));
	}

	static public AlliedCard getCard(AlliedCardType identifier) {
		return AlliedCard.cardList.get(identifier.ordinal());
	}

	int[] values;

	private AlliedCard(int[] values) {
		super(null);
		this.values = values;
	}
}
