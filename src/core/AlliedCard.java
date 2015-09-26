package core;


import java.util.Vector;

/**
 * Allied card (taupe or dog) representation
 */
public class AlliedCard extends Card {
	/**
	 * Maximum number of allied cards
	 */
	static public final int ALLIED_CARD_NUMBER = 6;

	/**
	 * Allied cards are unique
	 */
	static private Vector<AlliedCard> cardList = null;

	/**
	 * Get an allied card by its identifier
	 * @param identifier The identifier
	 * @return The card
	 */
	static public AlliedCard getCard(CardType identifier) {
		if (AlliedCard.cardList == null) {
			AlliedCard.init();
		}

		return AlliedCard.cardList.get(identifier.ordinal());
	}
	/**
	 * Create all the allied cards
	 */
	static private void init() {
		AlliedCard.cardList = new Vector<AlliedCard>();

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

	/**
	 * Allied cards properties (values and type)
	 */
	private int[] values;

	private CardType type;

	/**
	 * Create an allied card
	 * @param values The allied card values
	 * @param type The card type
	 */
	private AlliedCard(int[] values, CardType type) {
		super(null, null);
		this.values = values;
		this.type   = type;
	}

	/**
	 * Get the card type
	 * @return The card type
	 */
	@Override
	public CardType getType() {
		return this.type;
	}
}
