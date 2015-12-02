package core;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Game implements Playable {
    private boolean running         = false;
    private ArrayList<Round> rounds = null;
    private Round currentRound      = null;
    private int roundNumber         = 0;

    private HashSet<DogListener> listeners = new HashSet<>();

    public Game(int playerNumber, int iaNumber) throws Exception {
        this.rounds      = new ArrayList<>();
        this.roundNumber = playerNumber;

        this.rounds.add(new Round(playerNumber, iaNumber, this));
        this.currentRound = this.rounds.get(0);
        this.currentRound.setNumber(0);

        for (int i = 1; i < playerNumber; i++) {
            try {
                this.rounds.add(new Round(Game.this.getPlayers(), i));
            } catch (Exception e) {
                throw e;
            }
        }
    }

    public void chooseAlliedCards(Player source, boolean allied) {
        System.out.print(source);
        if (allied) {
            System.out.println("choosed allied");
            int randomBoolean = (new Random()).nextInt(2);
            source.drawAlliedCards((randomBoolean == 1));
        } else {
            System.out.println("choosed 2seeds");
            source.getField().addSmallRockNumber(2);
        }
    }

    /**
     * Add a dog listener
     *
     * @param l The dog listener
     */
    public void addDogListener(DogListener l) {
        this.listeners.add(l);
    }

    @Override
    public SeasonType getActualSeason() {
        return this.currentRound.getActualSeason();
    }

    @Override
    public Player getCurrentPlayer() {
        return this.currentRound.getCurrentPlayer();
    }

    public Round getCurrentRound() {
        return this.currentRound;
    }

    @Override
    public Player getPlayer(int i) {
        return this.currentRound.getPlayer(i);
    }

    @Override
    public int getPlayerNumber() {
        return this.roundNumber;
    }

    @Override
    public ArrayList<Player> getPlayers() {
        return this.currentRound.getPlayers();
    }

    public int getRoundNumber() {
        return this.roundNumber;
    }

    @Override
    public boolean isRunning() {
        return this.running;
    }

    @Override
    public void nextTurn(Card card, ActionType action, Player player) {
        this.currentRound.nextTurn(card, action, player);
        int currentRoundNumber = this.rounds.indexOf(this.currentRound);

        if (!this.currentRound.isRunning()) {
            if (currentRoundNumber == (this.roundNumber - 1)) {
                this.running = false;
            } else {
                this.currentRound = this.rounds.get(currentRoundNumber + 1);
                this.currentRound.start();
            }
        }
    }

    @Override
    public void playDog(Player source) {
        this.currentRound.playDog(source);
    }

    @Override
    public void playFertilizer(Player source, int fertilizeNumber) {
        this.currentRound.playFertilizer(source, fertilizeNumber);

    }

    @Override
    public void playGiant(Player source, int smallRockNumber) {
        this.currentRound.playGiant(source, smallRockNumber);

    }

    @Override
    public void playHobgoblin(Player source, Player target, int hobgoblinNumber) {
        System.out.println("Playing hob");
        int seedsBefore = target.getField().getSmallRockNumber();
        this.currentRound.playHobgoblin(source, target, hobgoblinNumber);
        int stolenSeeds = seedsBefore - target.getField().getSmallRockNumber();

        for (AlliedCard alliedCard : target.getAlliedCards()) {
            if (alliedCard.getType() == CardType.DOG1
                || alliedCard.getType() == CardType.DOG2
                || alliedCard.getType() == CardType.DOG3) {
                this.triggerDogListener(target, stolenSeeds);
            }
        }

    }

    @Override
    public void playTaupe(Player source, Player target, int bigRockNumber) {
        this.currentRound.playTaupe(source, target, bigRockNumber);
    }

    @Override
    public void start() {
        this.currentRound.start();
        this.running = true;
    }

    /**
     * Calls dog listeners to ask them if they want to play dogs
     *
     * @param p           The target player
     * @param stolenSeeds The amount of seeds stolen
     */
    private void triggerDogListener(Player p, int stolenSeeds) {
        for (DogListener listener : this.listeners) {
            listener.wouldPlayerPlayDog(p, stolenSeeds);
        }
    }
}
