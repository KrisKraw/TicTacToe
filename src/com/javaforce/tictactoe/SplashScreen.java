package com.javaforce.tictactoe;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SplashScreen {

    private JFrame gameFrame;
    private JPanel splashPanel;
    private JLabel logoHolder;

    public SplashScreen(JFrame gameFrame) {
        this.gameFrame = gameFrame;
        splashPanel = new JPanel();
        splashPanel.setPreferredSize(new Dimension(TicTacToe.getBoardWidth(),TicTacToe.getBoardHeight()));
        this.gameFrame.add(splashPanel);
    }

    public void showLogo() {
        try {
            BufferedImage image = ImageIO.read(getClass().getClassLoader().getResource("jf_logo.png"));
            BufferedImage resizedImage = TicTacToe.resizeImage(image, TicTacToe.getBoardWidth() - 20, TicTacToe.getBoardWidth() - 20);
            ImageIcon imageIcon = new ImageIcon(resizedImage);
            logoHolder = new JLabel(imageIcon);
            logoHolder.setHorizontalAlignment(JLabel.RIGHT);
            logoHolder.setVerticalAlignment(JLabel.CENTER);
            splashPanel.setBackground(Color.white);
            splashPanel.add(logoHolder);
            splashPanel.revalidate();
        } catch(Exception e) { e.printStackTrace(); }
    }

    public void hideLogo() {
        splashPanel.remove(logoHolder);
        splashPanel.revalidate();
        gameFrame.getContentPane().remove(splashPanel);
        gameFrame.repaint();
    }
}
