
import java.util.ArrayList;

/**
 * Stores the game output for a given step (cell values, step number and score).
 * Will later be pasted in the GUI.
 */
public class SingleStepRecord {

    private int step;
    private String headerOutput;  // overall game score/summary
    private ArrayList<String> cellRecords;

    public SingleStepRecord(int step, String headerOutput, ArrayList<String> cellRecords) {
        this.step = step;
        this.headerOutput = headerOutput;
        this.cellRecords = cellRecords;
    }

    public ArrayList<String> getCellRecords() {
        return cellRecords;
    }

    public String getHeaderOutput() {
        return headerOutput;
    }

    public int getStep() {
        return step;
    }

}
