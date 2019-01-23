
import java.util.ArrayList;

/**
 * This inheritance of grid stores the game output at a particular point in time
 * which is used for the GUI. Otherwise, it identical to grid.
 *
 */
public class GridGUI extends Grid {

    private ArrayList<Cell> textBoxArray;

    public GridGUI(int height, int width) {
        super(height, width);
    }

    /**
     * Records the state of the game when called (ie the string content of each
     * cell)
     *
     * @return a String ArrayList with each entry being a game cells string
     * output
     *
     */
    public ArrayList<String> storeCells() {

        ArrayList<String> stepRecord = new ArrayList<String>();

        // an adaption of the Grid.display method that stores the cells, rather than
        // printing them
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                if (grid[i][j] != null) {
                    stepRecord.add(grid[i][j].toString());
                } else {
                    stepRecord.add("");
                }
            }
        }

        return stepRecord;

    }

}
