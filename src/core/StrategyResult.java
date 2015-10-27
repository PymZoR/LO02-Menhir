package core;


/**
 * Action chosen by a strategy (contains card, target and alliedCard)
 */
public class StrategyResult {
    public CardType cardType;
    public ActionType type;
    public Player target;
    public CardType alliedCardType;

    /**
     * Create a new StrategyResult
     * @param  cardType       The card type
     * @param  type           The card action
     * @param  target         The target target
     * @param  alliedCardType The optional allied card
     */
    public StrategyResult(CardType cardType, ActionType type, Player target, CardType alliedCardType) {
        this.cardType       = cardType;
        this.type           = type;
        this.target         = target;
        this.alliedCardType = alliedCardType;
    }
}
