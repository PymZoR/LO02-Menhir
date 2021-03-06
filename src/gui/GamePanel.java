package gui;


import core.ActionType;
import core.DogListener;
import core.Game;
import core.Player;
import core.SeasonType;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Full game GUI
 */
public class GamePanel extends RoundPanel {
    /**
     * Java UID
     */
    private static final long serialVersionUID = -8860204251354754377L;

    /**
     * Panel components
     */
    private final JLabel roundLabel = new JLabel();
    private Card alliedCard = null;

    /**
     * Class that will listen for the dog query
     */
    public class WaitForDogs implements DogListener {
        GamePanel parent;

        /**
         * Constructor. Set the parent
         * 
         * @param parent The parent panel
         */
        public WaitForDogs(GamePanel parent) {
            this.parent = parent;
        }

        @Override
        public void wouldPlayerPlayDog(Player player, int stolenSeeds) {
            String playerN          = String.valueOf(player.getNumber() + 1);
            SeasonType actualSeason = this.parent.getGame().getActualSeason();
            int dogForce            = player.getAlliedCards().get(0).getValue(ActionType.DOG, actualSeason);
            
            int result = JOptionPane.showConfirmDialog(GamePanel.parent, "Joueur " + playerN +
                ", vous êtes attaqué par un farfadet (force: " + stolenSeeds + "). Voulez-vous vous défendre " +
                " en utilisant votre carte Chien (force : " + String.valueOf(dogForce) + ") ?",
                "Attaque de farfadets",
                JOptionPane.YES_NO_OPTION);
            
            if (result == JOptionPane.YES_OPTION) {
                player.playCard(player.getAlliedCards().get(0), ActionType.DOG, null);
            }
        }
    }

    /**
     * Unique listener
     */
    private static WaitForDogs dogsListener;
    private static MainWindow parent;

    /**
     * Create the same window that RoundPanel
     *
     * @param parentWindow Parent window reference
     */
    public GamePanel(MainWindow parentWindow) {
        super(parentWindow);
        GamePanel.parent = parentWindow;

        if (GamePanel.dogsListener == null) {
            GamePanel.dogsListener = new WaitForDogs(this);
        }

        this.remove(this.totalBigRocks);
        this.remove(this.totalBigRocks);

        int totalScore     = this.game.getCurrentPlayer().getField().getBigRockSum();
        this.totalBigRocks = new JLabel("Score total : " + String.valueOf(totalScore));

        ((Game) this.game).addDogListener(GamePanel.dogsListener);

        String thisRound = String.valueOf((((Game) this.game).getCurrentRound()).getNumber() + 1);
        String maxRounds = String.valueOf(this.game.getPlayerNumber());
        this.roundLabel.setText("Manche actuel : " + thisRound + "/" + maxRounds);

        // Ask either seeds or allied card
        if (((Game) this.game).getCurrentRound().getActualSeason() == SeasonType.SPRING) {
            // Choose only if in spring (round start)
            if (this.player.ia() == null) {
                int choice;
                do {
                    choice = JOptionPane.showConfirmDialog(this.parentWindow,
                                                           "Voulez-vous choisir deux graines au lieu d'une carte alliée ?",
                                                           "Bonus de début de round",
                                                           JOptionPane.YES_NO_OPTION);

                    // NO_OPTION = allied
                    ((Game) this.game).chooseAlliedCards(this.player, (choice == JOptionPane.NO_OPTION));
                } while (choice == -1);
            } else {
                int IAchoice = (new Random()).nextInt(2);
                ((Game) this.game).chooseAlliedCards(this.player, (IAchoice == 1));
            }
        }

        if (this.player.getAlliedCards().size() == 1) {
            System.out.println("draw allied card");
            this.alliedCard = new Card(this, this.player.getAlliedCards().get(0).getType());
            this.addAbsolute(this.alliedCard, 460, 30);
        }

        this.addAbsolute(this.totalBigRocks, 250, 10);
        this.addAbsolute(this.roundLabel, 550, 10);
    }

    /**
     * Get the allied card
     *
     * @return The allied card
     */
    public Card getAlliedCard() {
        return this.alliedCard;
    }

    @Override
    protected void playCard(core.Card card, core.ActionType action, core.Player target) {
        System.out.println(this.game.getClass());
        ((core.Game) this.game).nextTurn(card, action, target);
    }

    @Override
    protected void goNextTurn() {
        this.parentWindow.switchPanel("GamePanel", 710, 450);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,
                                               RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHints(rh);

        g2.setColor(Color.black);
        g2.drawLine(445, 25, 445, 120);
    }
}
