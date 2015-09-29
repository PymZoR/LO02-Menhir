package console;


import java.util.Collections;
import java.util.Vector;

import core.ActionType;
import core.AlliedCard;
import core.Card;
import core.Field;
import core.Game;
import core.IAPlayer;
import core.Playable;
import core.Player;
import core.Round;
import core.SeasonType;
import helpers.StringUtils;

/**
 * Console-based game
 */
public class ConsoleGame {

	public enum GameType {
		ROUND,
		GAME
	}

	private Playable game               = null;
	private GameType gameType           = null;
	private int playerNumber            = 0;
	private int iaNumber                = -1;
	private String currentSeasonName    = "";
	private Player currentPlayer        = null;
	private Player targetPlayer         = null;
	private IAPlayer ia                 = null;
	private Vector<Player> otherPlayers = null;
	private Round currentRound          = null;
	private int previousRoundNumber     = 0;
	private Card card                   = null;
	private ActionType action           = null;

	/**
	 * Clear the console
	 */
	static private void clearConsole() {
	    try
	    {
	        final String os = System.getProperty("os.name");

	        if (os.contains("Windows"))
	        {
	            Runtime.getRuntime().exec("cls");
	        }
	        else
	        {
	            System.out.print("\033[H\033[2J");
	            System.out.flush();
	        }
	    }
	    catch (final Exception e)
	    {
	    	e.printStackTrace();
	    }
	}

	/**
	 * Read an integer from the console
	 * @return The user input
	 */
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
			System.out.println("    Player " + (i +1)+ ". Field: " + field.getBigRockSum() +
				" menhirs; " + field.getSmallRockSum() + " seeds.");
		}
	}

	/**
	 * Ask the user to choose a single Round or a Game
	 */
	private void chooseGameType() {
		System.out.println("Type 1 for a simple game, 2 for enhanced: ");
		int gameType = 0;

		do {
			gameType = ConsoleGame.getIntInput();

			if ((gameType < 1) || (gameType > 2)) {
				System.out.println("Choice must be either 1 or 2");
			}
			else {
				this.gameType = (gameType == 1) ? GameType.ROUND : GameType.GAME;
			}
		} while (this.gameType == null);
	}

	/**
	 * Ask the user to choose IA number
	 */
	private void chooseIANumber() {
		System.out.println("Choose the IA number: ");
		int iaNumber = 0;

		do {
			iaNumber = ConsoleGame.getIntInput();

			if ((iaNumber < 0) || (iaNumber > 5)) {
				System.out.println("IA number must be between 0 and 5 included");
			}
			else {
				this.iaNumber = iaNumber;
			}
		} while (this.iaNumber == -1);
	}

	/**
	 * Ask the user to choose the player number
	 */
	private void choosePlayerNumber() {
		System.out.println("Choose the player number: ");
		int playerNumber = 0;

		do {
			playerNumber = ConsoleGame.getIntInput();

			if ((playerNumber == 1) && (this.iaNumber == 0)) {
				System.out.println("You can't play alone !");
				continue;
			}
			try {
				if (gameType == GameType.ROUND) {
					this.game = new Round(playerNumber, this.iaNumber);
				}
				else if (gameType == GameType.GAME){
					this.game  = new Game(playerNumber, this.iaNumber);
				}

				this.playerNumber = playerNumber;
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		} while (this.game == null);
	}

	/**
	 * Ask a player to choose between seeds or AlliedCard
	 * @param player
	 */
	private void chooseAlliedCards() {
		int choice = 0;

		for (int i = 0; i < this.playerNumber; i++) {
			choice = 0;
			System.out.println("Player " + i +
					", choose either seeds, taupe or dog (1, 2, 3): ");

			do {
				choice = ConsoleGame.getIntInput();

				if ((choice < 1) || (choice > 3)) {
					System.out.println("Choice must be either 1, 2 or 2");
					choice = 0;
				}

				if (choice == 2) {
					((Game)game).chooseAlliedCards(this.game.getPlayer(i), true);
				}
				else if (choice == 3) {
					((Game)game).chooseAlliedCards(this.game.getPlayer(i), false);
				}
			} while (choice == 0);
		}
	}

	/**
	 * Get the current IA choice
	 */
	private void makeIAChoice() {
		/*
		this.ia.makeChoice();
		System.out.println("IA CHOICE");
		System.out.println(ia.getAction());
		System.out.println(ia.getCard().name());
		System.out.println(ia.getTarget());
		System.out.println("DONE");
		 */
		//TODO
	}

	/**
	 * Print Game info: current round, round state, and rankings in Game mode
	 */
	private void printGameState() {
		this.previousRoundNumber = (this.currentRound == null) ? 0 : this.currentRound.getNumber();
		this.currentRound        = (((Game) game).getCurrentRound());
		int currentRoundNumber   = this.currentRound.getNumber();

		if (currentRoundNumber != this.previousRoundNumber) {
			System.out.println(System.lineSeparator() + "Round is finished");
			ConsoleGame.printRankings(game);
		}

		System.out.print(System.lineSeparator() + "Round " + (currentRoundNumber+1) +
			"/" + this.playerNumber);
	}

	/**
	 * Print current turn info: actual season, actual player, other players info
	 */
	private void printTurnState() {
		System.out.println(System.lineSeparator() + "Next turn. Current season: " + this.currentSeasonName);
		System.out.println("Actual player : " + this.currentPlayer);

		System.out.println(System.lineSeparator() + "Other players :");
		for (int i = 0; i < this.otherPlayers.size(); i++) {
			System.out.println(this.otherPlayers.get(i));
		}
		System.out.println();
	}

	/**
	 * Print current player's cards, and allied cards in Game mode
	 */
	private void printCards() {
		int currentCardNumber = this.currentPlayer.getCards().size();
		int i;

		for (i = 0; i < currentCardNumber; i += 2) {
			if (this.currentPlayer.getCards().size() <= (i + 1)) {
				String firstCard  = this.currentPlayer.getCards().get(i).toASCII(i);
				System.out.print(firstCard);
			}
			else {
				String firstCard  = this.currentPlayer.getCards().get(i).toASCII(i);
				String secondCard = this.currentPlayer.getCards().get(i + 1).toASCII(i + 1);
				System.out.println(StringUtils.getLine(firstCard, 0) + " " + StringUtils.getLine(secondCard, 0));
				System.out.println(StringUtils.getLine(firstCard, 1) + " " + StringUtils.getLine(secondCard, 1));
				System.out.println(StringUtils.getLine(firstCard, 2) + " " + StringUtils.getLine(secondCard, 2));
				System.out.println(StringUtils.getLine(firstCard, 3) + " " + StringUtils.getLine(secondCard, 3));
				System.out.println(StringUtils.getLine(firstCard, 4) + " " + StringUtils.getLine(secondCard, 4));
				System.out.println(StringUtils.getLine(firstCard, 5) + " " + StringUtils.getLine(secondCard, 5));
				System.out.println(StringUtils.getLine(firstCard, 6) + " " + StringUtils.getLine(secondCard, 6));
				System.out.println(StringUtils.getLine(firstCard, 7) + " " + StringUtils.getLine(secondCard, 7));
			}
		}

		if (gameType == GameType.GAME) {
			int currentAlliedCardNumber = this.currentPlayer.getAlliedCards().size();

			for (int j = 0; j < currentAlliedCardNumber; j += 2) {
				if (this.currentPlayer.getAlliedCards().size() <= (j + 1)) {
					String firstCard  = this.currentPlayer.getAlliedCards().get(j).toASCII(j + i);
					System.out.print(firstCard);
				}
				else {
					String firstCard  = this.currentPlayer.getAlliedCards().get(j).toASCII(j + i);
					String secondCard = this.currentPlayer.getAlliedCards().get(j + 1).toASCII(j + i + 1);
					System.out.println(StringUtils.getLine(firstCard, 0) + " " + StringUtils.getLine(secondCard, 0));
					System.out.println(StringUtils.getLine(firstCard, 1) + " " + StringUtils.getLine(secondCard, 1));
					System.out.println(StringUtils.getLine(firstCard, 2) + " " + StringUtils.getLine(secondCard, 2));
					System.out.println(StringUtils.getLine(firstCard, 3) + " " + StringUtils.getLine(secondCard, 3));
					System.out.println(StringUtils.getLine(firstCard, 4) + " " + StringUtils.getLine(secondCard, 4));
					System.out.println(StringUtils.getLine(firstCard, 5) + " " + StringUtils.getLine(secondCard, 5));
					System.out.println(StringUtils.getLine(firstCard, 6) + " " + StringUtils.getLine(secondCard, 6));
					System.out.println(StringUtils.getLine(firstCard, 7) + " " + StringUtils.getLine(secondCard, 7));
				}
			}
		}
	}

	/**
	 * Ask player to choose a card
	 */
	private void chooseCard() {
		System.out.println(System.lineSeparator() + "Choose a card number:");
		int cardId = 0;
		int maxCardNumber = this.currentPlayer.getCards().size();
		int maxACardNumber = this.currentPlayer.getAlliedCards().size();

		do {
			cardId = ConsoleGame.getIntInput();

			if (((cardId < 1) || (cardId > maxCardNumber)) &&
			    ((cardId < maxCardNumber) || (cardId > (maxCardNumber + maxACardNumber)))) {
				System.out.println("Card number incorrect");
			}
			else {
				if (cardId <= maxCardNumber) {
					this.card = this.currentPlayer.getCardById(cardId - 1);
				}
				else {
					this.card = this.currentPlayer.getAlliedCardById(cardId - 1 - maxCardNumber);
				}
				if (this.card.getType().toString().indexOf("DOG") != -1) {
					System.out.println("You must wait for a hobgoblin attack");
					this.card = null;
				}
			}
		} while (this.card == null);
	}

	/**
	 * Ask player to choose an action
	 */
	private void chooseAction() {
		if (this.card instanceof AlliedCard) {
			this.action = ActionType.TAUPE;

			this.chooseTargetPlayer();
			return;
		}

		System.out.println(System.lineSeparator() + "Choose an action Number(1 for Giant...):");

		int actionId = 0;
		int maxActionId = ActionType.values().length;
		do {
			actionId = ConsoleGame.getIntInput();

			if (actionId > maxActionId) {
				System.out.println("Action number must be between 1 and " +
						Integer.toString(maxActionId) + " included");
				actionId = 0;
			}
		} while (actionId == 0);

		this.action = ActionType.values()[actionId - 1];

		if (this.action == ActionType.HOBGOBLIN) {
			this.chooseTargetPlayer();
		}
	}

	private void chooseTargetPlayer() {
		do {
			System.out.println(System.lineSeparator() + "Choose an other player: ");
			int playerId = ConsoleGame.getIntInput();
			if (playerId > playerNumber) {
				System.out.println("There is only " + playerNumber +
					" players");
			}
			else {
				this.targetPlayer = game.getPlayer(playerId - 1);

				if (this.targetPlayer == currentPlayer) {
					System.out.println("You can't target yourself !");
					this.targetPlayer = null;
				}
			}
		} while (this.targetPlayer == null);
	}

	/**
	 * Start a console-based game
	 */
	public ConsoleGame() {
		this.chooseGameType();
		this.chooseIANumber();
		this.choosePlayerNumber();

		System.out.println(System.lineSeparator() + "Start new game !");
		System.out.println("--------------------------------");
		this.game.start();

		if (this.gameType == GameType.GAME) {
			this.chooseAlliedCards();
		}

		// Main loop
		do {
			ConsoleGame.clearConsole();

			this.currentSeasonName = game.getActualSeason().toString();
			this.currentPlayer     = game.getCurrentPlayer();
			this.targetPlayer      = null;
			this.card              = null;
			this.ia                = currentPlayer.ia();
			this.otherPlayers      = (Vector<Player>) game.getPlayers().clone();
			this.otherPlayers.removeElement(currentPlayer);

			// Handle IA
			if (this.ia != null) {
				this.makeIAChoice();
			}

			if (this.gameType == GameType.GAME) {
				this.printGameState();
			}

			this.printTurnState();
			this.printCards();

			// Handle player
			this.chooseCard();
			this.chooseAction();

			game.nextTurn(this.card, this.action, this.targetPlayer);
		} while (this.game.isRunning());

		System.out.println(System.lineSeparator() + "Game is finished");
		ConsoleGame.printRankings(this.game);
	}
}
