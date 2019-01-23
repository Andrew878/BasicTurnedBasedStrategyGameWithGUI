/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Action Listener for single turn increment button in GUI.
 */
public class SingleStepListen implements ActionListener {

    private GameGUI game;
    private int stepNumber;
    private int maxSteps;
    private ArrayList<Cell> textBoxArray;
    private JTextArea header;

    public SingleStepListen(GameGUI game, JTextArea header, int maxSteps, ArrayList<Cell> textBoxArray) {
        this.game = game;
        this.stepNumber = 1;
        this.maxSteps = maxSteps;
        this.textBoxArray = textBoxArray;
        this.header = header;
    }

    /**
     * Repaints entire GUI to the game state at time 't' when button pressed.
     *
     * @param stepNumber
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        // to stop the button printing after end of game
        if (this.stepNumber <= this.maxSteps) {

            // retrieve record at time 't' 
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

            this.stepNumber++;

            // end message 
        } else {
            System.out.println("Game is finished, thanks for playing. To exit, close window. Otherwise, use slider to see game history.");
        }

    }

    /**
     * Returns the step number.
     *
     * @return
     */
    public int getStepNumber() {
        return stepNumber;
    }

}
