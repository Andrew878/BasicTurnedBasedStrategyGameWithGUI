
import java.util.ArrayList;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Slider object for GUI.
 */
public class SliderAndUpdater extends JSlider {

    private GameGUI game;
    private ArrayList<Cell> textBoxArray;
    private JTextArea header;

    public SliderAndUpdater(GameGUI game, ArrayList<Cell> textBoxArray, JTextArea header, int min, int max, int value) {
        super(min, max, value);
        this.game = game;
        this.textBoxArray = textBoxArray;
        this.header = header;
    }

    /**
     * Repaints entire GUI to the game state at time 't'.
     *
     * @param stepNumber
     */
    public void refreshToStepN(int stepNumber) {

        // retrieve record at time given by the slider
        SingleStepRecord recordAtStep = this.game.getStepRecords().get(stepNumber - 1);

        // retrieve and repaint header with score/summary
        this.header.setText(recordAtStep.getHeaderOutput());
        this.header.repaint();

        // retrieve and repaint each cell with cell contents at time 't'
        for (int i = 0; i < recordAtStep.getCellRecords().size(); i++) {
            JTextField field = this.textBoxArray.get(i).getField();
            String newTextForLable = recordAtStep.getCellRecords().get(i);
            field.setText(newTextForLable);

            field.repaint();

        }
    }

}
