package console;


import java.util.Collections;
import java.util.Vector;

import core.ActionType;
import core.Card;
import core.Field;
import core.Game;
import core.Playable;
import core.Player;
import core.Round;
import core.SeasonType;
import helpers.StringUtils;

/**
 * Console-based game
 */
public class ConsoleGame {
	static private int getIntInput() {
		int input = 0;
		Exception error = null;

		do {
			try {
				input = Integer.parseInt(System.console().readLine());
				error = null;
			}
			catch (NumberFormatException e) {
				System.out.println("Input must be an integer");
				error = e;
			}

		} while (error != null);

		return input;
	}

	/**
	 * Get SeasonType as human string
	 * @param season The season
	 * @return Its string value
	 */
	static private String getSeasonName(SeasonType season) {
		String seasonName = "";

		switch (season) {
		case SPRING:
			seasonName = "spring";
			break;
		case SUMMER:
			seasonName = "Summer";
			break;
		case FALL:
			seasonName = "Fall";
			break;
		case WINTER:
			seasonName = "Winter";
			break;
		default:
		}

		return seasonName;
	}

	/**
	 * Prints every player in their rank order
	 * @param game The game to gather players
	 */
	static private void printRankings(Playable game) {
		Vector<Player> scores = new Vector<Player>(game.getPlayers());
		Collections.sort(scores);
		Collections.reverse(scores);

		System.out.println("Rankings:");
		for (int i = 0; i < game.getPlayerNumber(); i++) {
			Field field = scores.get(i).getField();
			System.out.println("    Player " + (i+1) + ". Field: "+ field.getBigRockSum() +
				" big rocks; " + field.getSmallRockSum() + " small rocks;");
		}
	}

	/**
	 * Start the game; console based
	 */
	public ConsoleGame() {
		int playerNumber = 2;
		Playable game    = null;
		int cardId           = 0;
		int actionId         = 0;
		Card card            = null;
		ActionType action    = null;
		int maxActionId      = ActionType.values().length;
		Round currentRound   = null;
		Player currentPlayer = null;
		Field currentField   = null;
		String seasonName    = null;

		System.out.println("Type 1 for a simple game, 2 for enhanced: ");
		int gameType = 0;
		do {
			gameType = ConsoleGame.getIntInput();

			if ((gameType < 1) || (gameType > 2)) {
				System.out.println("Choice must be either 1 or 2");
				gameType = 0;
			}
		} while (gameType == 0);

		System.out.println("Choose the player number: ");
		do {
			playerNumber = ConsoleGame.getIntInput();

			try {
				if (gameType == 1) {
					game = new Round(playerNumber);
				}
				else if (gameType == 2 ){
					game  = new Game(playerNumber);
				}
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		} while (game == null);

		System.out.println(System.lineSeparator() + "Start new game !");
		System.out.println("--------------------------------");
		game.start();

		do {
			seasonName    = ConsoleGame.getSeasonName(game.getActualSeason());
			currentPlayer = game.getCurrentPlayer();
			currentField  = currentPlayer.getField();
			seasonName    = ConsoleGame.getSeasonName(game.getActualSeason());

			if ((game instanceof Game) && (((Game)game).getCurrentRound() != currentRound)) {
				int previousRoundNumber = (currentRound == null) ? 0 : currentRound.getNumber();
				currentRound = (((Game)game).getCurrentRound());

				if (currentRound.getNumber() != previousRoundNumber) {
					System.out.println(System.lineSeparator() + "Turn is finished");
					ConsoleGame.printRankings(game);
				}
				System.out.print(System.lineSeparator() + "Round " + (currentRound.getNumber()+1) + "/" + playerNumber);
			}

			System.out.println(System.lineSeparator() + "Next turn. Player " + (currentPlayer.getNumber()+1) + ":");
			System.out.println("    Current season: " + seasonName);
			System.out.println("    You have " + currentField.getSmallRockNumber() + " small rocks.");
			System.out.println("    You have " + currentField.getBigRockNumber() + " big rocks." + System.lineSeparator());
			System.out.println("Choose your card: ");

			for (int i = 0; i < currentPlayer.getCards().size(); i += 2) {
				if (currentPlayer.getCards().size() <= (i + 1)) {
					String firstCard  = currentPlayer.getCards().get(i).toASCII(i);
					System.out.print(firstCard);
				} else {
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
			}

			System.out.println(System.lineSeparator() + "Choose a card number:");
			do {
				cardId = ConsoleGame.getIntInput();

				if (cardId > currentPlayer.getCards().size()) {
					System.out.println("Card number must be between 1 and " +
							Integer.toString(currentPlayer.getCards().size()) + " included");
					cardId = 0;
				}
			} while (cardId == 0);
			card  = currentPlayer.getCardById(cardId - 1);

			System.out.println(System.lineSeparator() + "Choose an action Number(1 for Giant...):");
			do {
				actionId = ConsoleGame.getIntInput();

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
					System.out.println(System.lineSeparator() + "Choose an other player: ");
					int playerId = ConsoleGame.getIntInput();
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

		System.out.println(System.lineSeparator() + "Game is finished");
		ConsoleGame.printRankings(game);
	}
}
