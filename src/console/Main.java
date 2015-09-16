package console;

import core.Game;
import core.Card;
import core.AlliedCard;
import core.Player;
import core.ActionType;
import helpers.StringUtils;

public class Main {
	static public void main(String[] argv) {
		System.out.println("Choose the player number: ");
		int playerNumber = Integer.parseInt(System.console().readLine());
		Game game        = new Game(playerNumber);

		game.start();
		do {
			System.out.println("Next turn. Choose your card: ");

			// TODO: Display the cards

			int cardId           = 0;
			int actionId         = 0;
			Card card            = null;
			ActionType action    = null;
			int maxActionId      = ActionType.values().length;
			Player currentPlayer = game.getCurrentPlayer();
			
			for (int i = 0; i < currentPlayer.getCards().size(); i += 2) {
				String firstCard  = currentPlayer.getCards().get(i).toASCII(i);
				String secondCard = currentPlayer.getCards().get(i + 1).toASCII(i + 1);
				System.out.println(StringUtils.getLine(firstCard, 0) + "    " + StringUtils.getLine(secondCard, 0));
				System.out.println(StringUtils.getLine(firstCard, 1) + "    " + StringUtils.getLine(secondCard, 1));
				System.out.println(StringUtils.getLine(firstCard, 2) + "    " + StringUtils.getLine(secondCard, 2));
				System.out.println(StringUtils.getLine(firstCard, 3) + "    " + StringUtils.getLine(secondCard, 3));
				System.out.println(StringUtils.getLine(firstCard, 4) + "    " + StringUtils.getLine(secondCard, 4));
				System.out.println(StringUtils.getLine(firstCard, 5) + "    " + StringUtils.getLine(secondCard, 5));
				System.out.println(StringUtils.getLine(firstCard, 6) + "    " + StringUtils.getLine(secondCard, 6));
				System.out.println(StringUtils.getLine(firstCard, 7) + "    " + StringUtils.getLine(secondCard, 7));
			}

			System.out.println("Choose a card number");
			do {
				cardId = Integer.parseInt(System.console().readLine());

				if (cardId > Game.CARDS_IN_HAND) {
					System.out.println("Card number must be between 1 and " +
						Integer.toString(Game.CARDS_IN_HAND) + " included");
					cardId = 0;
				}
			} while (cardId == 0);

			System.out.println("You have chosen the card " + Integer.toString(cardId));

			card  = currentPlayer.getCardById(cardId - 1);
			System.out.println("Choose an action number: ");

			// TODO: display actions

			do {
				actionId = Integer.parseInt(System.console().readLine());

				if (actionId > maxActionId) {
					System.out.println("Action number must be between 1 and " +
						Integer.toString(maxActionId) + " included");
					actionId = 0;
				}
			} while (actionId == 0);

			action = ActionType.values()[actionId - 1];
			Player player = null;

			if (action == ActionType.HOBGOBLIN) {
				do {
					System.out.println("Choose an other player: ");
					int playerId = Integer.parseInt(System.console().readLine());
					if (playerId > playerNumber) {
						System.out.println("There is only " + playerNumber +
							" players");
					}
					else {
						player = game.getPlayer(playerId - 1);

						if (player == currentPlayer) {
							System.out.println("You can't target yourself !");
							player = null;
						}
					}
				} while (player == null);

			}

			game.nextTurn(card, action, player);
		} while (game.isRunning());
	}
}
