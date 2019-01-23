/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Drives the UI of the game. As we haven't learnt this in class, I adapted the
 * information on this web site to use as a guide.
 *
 * Hellas, A & Luukkainen, M (2013)
 * Object-Oriented Programming with Java, part II.
 * Retrieved from https://materiaalit.github.io/2013-oo-programming/part2/week-11/
 * Accessed on 19/10/2018
 *
 * @author al278
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.util.ArrayList;
import javax.swing.event.ChangeListener;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;

public class UI implements Runnable {

    private JFrame frame;
    private JTextArea header;
    private ArrayList<Cell> textBoxArray;
    private GridGUI grid;
    private GameGUI game;
    private int gameLength;

    public UI() {

    }

    /**
     * Create frame and call create component methods.
     */
    @Override
    public void run() {
        frame = new JFrame("Farming Game");
        frame.setPreferredSize(new Dimension(800, 400));
        this.textBoxArray = new ArrayList<Cell>();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Create the layout of the GUI. Split into three panels (top, bottom,
     * middle)
     *
     * @param container
     */
    private void createComponents(Container container) {

        // based on the grid dimensions
        int width = this.grid.getWidth();
        int height = this.grid.getHeight();

        // creating/adding the three panels
        container.add(createTopPanel(), BorderLayout.NORTH);
        container.add(createPlayingGrid(height, width));
        container.add(createBottomPanel(), BorderLayout.SOUTH);

        GridLayout layout = new GridLayout(height, width);

    }

    /**
     * Create top, header, panel.
     *
     * @return panel top panel
     */
    private JPanel createTopPanel() {

        JPanel panel = new JPanel(new GridLayout(1, 1));
        this.header = new JTextArea();
        this.game.setHeader(header);

        // initial text
        String instructions = "  INSTRUCTIONS: Key one of these into the boxes below and press play!  Close the window to exit. \n  c for corn farm   ht for horizontal transporter \n  rad for radish     vt for vertical transporter \n  rab for rabbit     nt for nearest transporter \n  be for beaver";
        this.header.setText(instructions);

        // to ensure can't be amended
        this.header.setEnabled(false);

        panel.add(this.header);

        return panel;

    }

    /**
     * Create middle, panel (cells).
     *
     * @return panel middle panel
     */
    private JPanel createPlayingGrid(int height, int width) {

        // create the cells according to grid
        JPanel panel = new JPanel(new GridLayout(height, width));

        // create empty cells one by one
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                JTextField nameField = new JTextField();
                Cell cell = new Cell(nameField, i, j);
                panel.add(nameField);
                this.textBoxArray.add(cell);
            }
        }

        return panel;
    }

    /**
     * Create bottom, panel (buttons and slider).
     *
     * Creates an initialisation button with action listener. Creates an single
     * step increment button with action listener. Creates a slider to display
     * cell information. to leanr the slider, I used this website as a guide.
     *
     * http://www.java2s.com/Tutorial/Java/0240__Swing/JSliderletstheusergraphicallyselectavaluebyslidingaknobwithinaboundedinterval.htm
     *
     * @return panel button panel
     */
    private JPanel createBottomPanel() {

        // create layout
        JPanel panel = new JPanel(new GridLayout(1, 3));

        // create buttons and slider
        JButton playAllButton = new JButton("Initialise game!");
        JButton playOneButton = new JButton("Play! (single turn)");
        playOneButton.setEnabled(false);
        SliderAndUpdater slider = new SliderAndUpdater(this.game, this.textBoxArray, this.header, 1, this.gameLength, 1);
        slider.setEnabled(false);

        // add to panel
        panel.add(playAllButton);
        panel.add(playOneButton);
        panel.add(slider);

        // add listeners
        //initialisatino button
        AllStepsListener allStepsListen = new AllStepsListener(this.textBoxArray, this.grid, this.game, this.gameLength, playAllButton, playOneButton, slider);
        playAllButton.addActionListener(allStepsListen);

        // single step button
        SingleStepListen singleStepListener = new SingleStepListen(this.game, this.header, this.gameLength, this.textBoxArray);
        playOneButton.addActionListener(singleStepListener);

        // slider. Creates a change listener to observe movements in slider
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {

                // if changes occuer, repaint the GUI
                int value = slider.getValue();
                slider.refreshToStepN(value);
            }
        });

        return panel;

    }

    //******* Getters and Setters below
    public JFrame getFrame() {
        return frame;
    }

    public void setGrid(GridGUI grid) {
        this.grid = grid;
    }

    public ArrayList<Cell> getTextBoxArray() {
        return this.textBoxArray;
    }

    public void setGame(GameGUI game) {
        this.game = game;
    }

    public void setGameLength(int gameLength) {
        this.gameLength = gameLength;
    }

    public JTextArea getHeader() {
        return this.header;
    }

}
