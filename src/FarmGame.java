
import javax.swing.SwingUtilities;


/*
 * 
 */
/**
 * A class that can initiate an instance of the game.
 *
 */
public class FarmGame {

    /**
     * @param args the command line arguments 
     */
    public static void main(String[] args) {

        // amend the below create a new game here using the GUI specific classes.
        GridGUI gridGUI = new GridGUI(3, 3);
        GameGUI gameGUI = new GameGUI(gridGUI);
        
        // game length
        int gameLength = 5;

        
        // don't amend the below
        UI ui = new UI();
        ui.setGrid(gridGUI);

        ui.setGame(gameGUI);
        ui.setGameLength(gameLength);

        SwingUtilities.invokeLater(ui);

    }

}
