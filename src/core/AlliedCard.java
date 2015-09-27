package core;


import java.util.Vector;
import helpers.StringUtils;

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

		return AlliedCard.cardList.get(identifier.ordinal() - Card.CARD_NUMBER);
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

		AlliedCard.cardList.add(CardType.TAUPE1.ordinal() - Card.CARD_NUMBER,
			new AlliedCard(taupe1Values, CardType.TAUPE1));
		AlliedCard.cardList.add(CardType.TAUPE2.ordinal() - Card.CARD_NUMBER,
			new AlliedCard(taupe2Values, CardType.TAUPE2));
		AlliedCard.cardList.add(CardType.TAUPE3.ordinal() - Card.CARD_NUMBER,
			new AlliedCard(taupe3Values, CardType.TAUPE3));
		AlliedCard.cardList.add(CardType.DOG1.ordinal() - Card.CARD_NUMBER,
			new AlliedCard(dog1Values, CardType.DOG1));
		AlliedCard.cardList.add(CardType.DOG2.ordinal() - Card.CARD_NUMBER,
			new AlliedCard(dog2Values, CardType.DOG2));
		AlliedCard.cardList.add(CardType.DOG3.ordinal() - Card.CARD_NUMBER,
			new AlliedCard(dog3Values, CardType.DOG3));
	}

	/**
	 * Allied cards properties (values and type)
	 */
	private int[] values;

	/**
	 * Card type
	 */
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

	public int getValue(ActionType action, SeasonType season) {
		return this.values[season.ordinal()];
	}

	/**
	 * Render a card
	 * @param cardN The card index
	 * @return A string representation
	 */
	@Override
	public String toASCII(int cardN) {
		String result = "--------------" + System.lineSeparator();
		result       += "| Card  .    |" + System.lineSeparator();
		result       += "|------------|" + System.lineSeparator();
		result       += "|   s S F W  |" + System.lineSeparator();
		result       += "| V . . . .  |" + System.lineSeparator();
		result       += "|            |" + System.lineSeparator();
		result       += "|            |" + System.lineSeparator();
		result       += "|------------|" + System.lineSeparator();

		result = StringUtils.replaceCharAt(result, 23, Integer.toString(cardN + 1));
		result = StringUtils.replaceCharAt(result, 64, Integer.toString(this.values[0]));
		result = StringUtils.replaceCharAt(result, 66, Integer.toString(this.values[1]));
		result = StringUtils.replaceCharAt(result, 68, Integer.toString(this.values[2]));
		result = StringUtils.replaceCharAt(result, 70, Integer.toString(this.values[3]));

		return result;
	}
}
