package com.javaforce.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Options {

    private JFrame gameFrame;
    private JPanel optionPanel;

    public Options(JFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public void initOptions()  {

        optionPanel = new JPanel();
        optionPanel.setPreferredSize(new Dimension(TicTacToe.getBoardWidth(),TicTacToe.getBoardHeight() / 4));
        optionPanel.setLayout(new GridLayout(3, 3, 9, 9));
        optionPanel.setBackground(Color.GREEN);

        gameFrame.getContentPane().add(optionPanel);
        gameFrame.setVisible(true);
    }
}
