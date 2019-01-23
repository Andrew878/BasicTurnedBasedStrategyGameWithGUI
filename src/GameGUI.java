
import java.util.ArrayList;
import javax.swing.JTextArea;

/**
 * This inheritance of game records the output for later retrieval, rather than
 * immediately displaying it, otherwise, it identical to game.
 */
public class GameGUI {

    private GridGUI grid;
    private ArrayList<SingleStepRecord> stepRecords;
    private JTextArea header;

    public GameGUI(GridGUI grid) {
        this.grid = grid;
        this.stepRecords = new ArrayList<SingleStepRecord>();
    }

    /**
     * Run the game with the given initial grid for a number of time-steps,
     * records production in a Step Record Array.
     *
     * @param timeSteps Number of time-steps to run the game for
     */
    public void run(int timeSteps) {

        TimeStep t = new TimeStep();

        //initialise for later used
        String headerText = "";

        for (int i = 0; i < timeSteps; i++) {

            grid.processItems(t);

            // these lines are to store the game score/output in a string
            String score;
            headerText = "    Time step: " + t.getValue() + "\n    Total production: " + grid.getTotalProduction() + "\n    Total consumption: " + grid.getTotalConsumption();
            if (grid.getTotalProduction() > 0) {
                score = "\n    Score: " + String.format("%.2f", (double) grid.getTotalConsumption() / grid.getTotalProduction());
            } else {
                score = "\n    Score: NA";
            }

            // store the output for the step
            headerText += score;
            SingleStepRecord step = new SingleStepRecord(i, headerText, this.grid.storeCells());
            stepRecords.add(step);

            // next step
            t.increment();

        }
    }

    /**
     * Passes through the header object (as can't pass through in constructor).
     *
     * @param header the top panel of the GUI
     */
    public void setHeader(JTextArea header) {
        this.header = header;
    }

    /**
     * A record of all moves in a game.
     *
     * @return
     */
    public ArrayList<SingleStepRecord> getStepRecords() {
        return stepRecords;
    }

}
