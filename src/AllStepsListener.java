/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JTextField;

/**
 * Initialises all steps of the game, when intialisation button is pressed in
 * GUI.
 */
public class AllStepsListener implements ActionListener {

    private ArrayList<Cell> textBoxArray;
    private GameGUI game;
    private GridGUI grid;
    private int gameLength;
    private JButton intitial;
    private JButton increment;
    private JSlider slider;

    public AllStepsListener(ArrayList<Cell> textBoxArray, GridGUI grid, GameGUI game, int gameLength, JButton intitial, JButton increment, JSlider slider) {
        this.textBoxArray = textBoxArray;
        this.game = game;
        this.grid = grid;
        this.gameLength = gameLength;
        this.intitial = intitial;
        this.increment = increment;
        this.slider = slider;
    }

    /**
     * When button is pressed, initialise the game. i.e. read variables, unlock
     * other JPale components, and start the game.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        // disable this button
        this.intitial.setEnabled(false);

        // enable these features
        this.increment.setEnabled(true);
        this.slider.setEnabled(true);

        //lock each text input cell
        for (Cell cell : this.textBoxArray) {
            cell.getField().setEnabled(false);
        }

        // read the text input provided by user
        this.readInput();

        // start the game
        this.game.run(this.gameLength);

    }

    /**
     * Arranges for all input provided to be read one by one.
     */
    public void readInput() {

        for (Cell cell : this.textBoxArray) {

            this.cellToItemToGrid(cell);
        }
    }

    /**
     * Take text cell, and either create a new item for the game according the
     * code below, or ignore.
     */
    public void cellToItemToGrid(Cell cell) {

        // get text
        String text = cell.getField().getText();

        // takes text input, and then creates the appropriate item in game.
        if (text.equalsIgnoreCase("c")) {
            AbstractItem item = new CornFarmer(this.grid, cell.getX(), cell.getY());
            cell.setItem(item);
            cell.getField().setBackground(Color.YELLOW);

        } else if (text.equalsIgnoreCase("rad")) {
            AbstractItem item = new RadishFarmer(this.grid, cell.getX(), cell.getY());
            cell.setItem(item);
            cell.getField().setBackground(Color.YELLOW);

        } else if (text.equalsIgnoreCase("rab")) {
            AbstractItem item = new Rabbit(this.grid, cell.getX(), cell.getY());
            cell.setItem(item);
            cell.getField().setBackground(Color.CYAN);

        } else if (text.equalsIgnoreCase("be")) {
            AbstractItem item = new Beaver(this.grid, cell.getX(), cell.getY());
            cell.setItem(item);
            cell.getField().setBackground(Color.CYAN);

        } else if (text.equalsIgnoreCase("ht")) {
            AbstractItem item = new HorizontalTransporter(this.grid, cell.getX(), cell.getY(), 10);
            cell.setItem(item);
            cell.getField().setBackground(Color.BLUE);

        } else if (text.equalsIgnoreCase("vt")) {
            AbstractItem item = new VerticalTransporter(this.grid, cell.getX(), cell.getY(), 10);
            cell.setItem(item);
            cell.getField().setBackground(Color.BLUE);
        } else if (text.equalsIgnoreCase("nt")) {
            AbstractItem item = new NearestTransporter(this.grid, cell.getX(), cell.getY(), 10);
            cell.setItem(item);
            cell.getField().setBackground(Color.BLUE);
        } else if (!text.isEmpty()) {
            System.out.println("Have written incorrect input, game will continue though");
        }
    }

}
