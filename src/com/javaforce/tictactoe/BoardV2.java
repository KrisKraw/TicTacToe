package com.javaforce.tictactoe;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static javax.print.attribute.standard.Chromaticity.COLOR;

public class BoardV2 implements MouseListener {

    private JFrame gameFrame;
    private JPanel boardPanel;
    private Map<Integer, Square> squares;
    private static ImageIcon xImg;
    private static ImageIcon oImg;

    private Player gameWinner; // Changed to be Player, instead of PieceType.
    private boolean gameOver = false;
    private int[] winningRow;
    private BlinkSquare blinkSquare = new BlinkSquare();

    private static final Player playerOne = PlayerFactory.getPlayerMap().get("Player-1"); // ADDED: Used for our checks now, instead of PieceType.
    private static final Player playerTwo = PlayerFactory.getPlayerMap().get("Player-2"); // ADDED: Used for our checks now, instead of PieceType.
    private static Player currentPlayer = playerOne; // At start current player will be playerOne. TODO: look into allowing alternate players start the game instead of just player one

    public BoardV2(JFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public void initNewBoard() {

        boardPanel = new JPanel();
        boardPanel.setPreferredSize(new Dimension(TicTacToe.getBoardWidth(), TicTacToe.getBoardWidth()));
        boardPanel.setLayout(new GridLayout(3, 3, 9, 9));
        boardPanel.setBackground(Color.GREEN);

        squares = new HashMap(9);
        for (int i = 1; i < 10; i++) {
            Square availSquare = new Square(i);
            availSquare.getHolder().addMouseListener(this);
            squares.put(i, availSquare);
            boardPanel.add(availSquare.getHolder());
        }

        gameFrame.add(boardPanel);

        setxImg(createPiece("x"));
        setoImg(createPiece("o"));
    }

    public ImageIcon createPiece(String piece) {
        ImageIcon imageIcon = null;
        try {
            BufferedImage image = ImageIO.read(new File("resources/" + piece + ".png")); // "resources/o.png"
            BufferedImage image1 = resizeImage(image, TicTacToe.getSquareSize() - 20, TicTacToe.getSquareSize() - 20); //
            imageIcon = new ImageIcon(image1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return imageIcon;
    }

    public BufferedImage resizeImage(BufferedImage oldImage, int newX, int newY) {
        Image scaledImage = oldImage.getScaledInstance(newX, newY, Image.SCALE_SMOOTH);
        BufferedImage newImage = new BufferedImage(newX, newY, BufferedImage.TYPE_INT_ARGB);
        newImage.createGraphics().drawImage(scaledImage, 0, 0, null);
        return newImage;
    }

    public boolean isWinner() { // Changed getCurrentPiece() to getCurrentPlayer().getPieceType() in all checks.
        boolean winner = false;
        if (squares.get(1).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(4).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(7).getOwner() == getCurrentPlayer().getPieceType()) {
            winner = true;
            winningRow = new int[]{1, 4, 7};
        }
        if (squares.get(2).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(5).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(8).getOwner() == getCurrentPlayer().getPieceType()) {
            winner = true;
            winningRow = new int[]{2, 5, 8};
        }
        if (squares.get(3).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(6).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(9).getOwner() == getCurrentPlayer().getPieceType()) {
            winner = true;
            winningRow = new int[]{3, 6, 9};
        }
        if (squares.get(1).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(2).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(3).getOwner() == getCurrentPlayer().getPieceType()) {
            winner = true;
            winningRow = new int[]{1, 2, 3};
        }
        if (squares.get(4).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(5).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(6).getOwner() == getCurrentPlayer().getPieceType()) {
            winner = true;
            winningRow = new int[]{4, 5, 6};
        }
        if (squares.get(7).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(8).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(9).getOwner() == getCurrentPlayer().getPieceType()) {
            winner = true;
            winningRow = new int[]{7, 8, 9};
        }
        if (squares.get(1).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(5).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(9).getOwner() == getCurrentPlayer().getPieceType()) {
            winner = true;
            winningRow = new int[]{1, 5, 9};
        }
        if (squares.get(3).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(5).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(7).getOwner() == getCurrentPlayer().getPieceType()) {
            winner = true;
            winningRow = new int[]{3, 5, 7};
        }
        System.out.println("winner: " + getCurrentPlayer().getPlayerName() + "  " + winner);
        gameWinner = getCurrentPlayer(); // Just a holder, (does not mean they have won yet, this is used else where.)
        return winner; // This returns false, until a win condition is met.
    }

    // CHANGED
    @Override
    public void mouseClicked(MouseEvent e) {
        Integer location = Integer.valueOf(((JPanel) e.getSource()).getName());
        if (!gameOver && squares.get(location).getOwner() == PieceType.E) { // Check game is over and if square is empty first
            boolean winner = false;
            System.out.println(getCurrentPlayer() + " Clicked square " + ((JPanel) e.getSource()).getName() + " and placed " + getCurrentPlayer().getPieceType());
            JPanel holder = squares.get(location).getHolder();
            squares.get(location).setOwner(BoardV2.getCurrentPlayer().getPieceType());
            JLabel imageHolder = null;
            winner = isWinner();
            if (BoardV2.getCurrentPlayer() == playerOne) { // ADDED: switch statements for checking by player instead of piece type.
                switch (playerOne.getPieceType()) { // If player one, run X or O case
                    case X:
                        imageHolder = new JLabel(BoardV2.getxImg());
                        BoardV2.setCurrentPlayer(playerTwo);
                        break;
                    case O:
                        imageHolder = new JLabel(BoardV2.getoImg());
                        BoardV2.setCurrentPlayer(playerTwo);
                        break;
                }
            } else { // Else player two, run X or O case
                switch (playerTwo.getPieceType()) {
                    case X:
                        imageHolder = new JLabel(BoardV2.getxImg());
                        BoardV2.setCurrentPlayer(playerOne);
                        break;
                    case O:
                        imageHolder = new JLabel(BoardV2.getoImg());
                        BoardV2.setCurrentPlayer(playerOne);
                        break;
                }
            }

            imageHolder.setHorizontalAlignment(JLabel.RIGHT);
            imageHolder.setVerticalAlignment(JLabel.CENTER);
            holder.add(imageHolder);
            holder.revalidate();
            if (winner) {
                gameWinner.win(); // ADDED: Increment win counter for that player.
                gameOver = true;
                blinkSquare.start();
                System.out.println(Arrays.toString(winningRow));
                System.out.println(gameWinner.getPlayerName() + " has won the game !");
                System.out.println(gameWinner);
            }
        }
    }

    class BlinkSquare extends Thread {
        @Override
        public void run() {
            boolean viewable = false;
            while (true) {
                for (int i : winningRow) {
                    squares.get(i).getHolder().getComponent(0).setVisible(viewable);
                }
                try {
                    Thread.sleep(500);
                } catch (Exception e) {

                }
                if (viewable) {
                    viewable = false;
                } else {
                    viewable = true;
                }
            }
        }
    }

    class SplashScreen extends Thread {
        @Override
        public void run() {
            long fastBlink = 100;
            boolean viewable = false;
            while (true) {
                Random random = new Random(8);
                int randomSquare = random.nextInt(8) + 1;
                System.out.println("randomSquare :" + randomSquare);
                squares.get(randomSquare).getHolder().getComponent(0).setVisible(true);
                try {
                    Thread.sleep(fastBlink);
                } catch (Exception e) {
                }
                squares.get(randomSquare).getHolder().getComponent(0).setVisible(false);
                try {
                    Thread.sleep(fastBlink);
                } catch (Exception e) {
                }
            }
        }
    }

    public static ImageIcon getxImg() {
        return xImg;
    }

    public static void setxImg(ImageIcon xImg) {
        BoardV2.xImg = xImg;
    }

    public static ImageIcon getoImg() {
        return oImg;
    }

    public static void setoImg(ImageIcon oImg) {
        BoardV2.oImg = oImg;
    }

    public static Player getCurrentPlayer() { // Used to be getCurrentPiece
        return currentPlayer;
    }

    public static void setCurrentPlayer(Player currentPlayer) { // Used to be setCurrentPiece
        BoardV2.currentPlayer = currentPlayer;
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}