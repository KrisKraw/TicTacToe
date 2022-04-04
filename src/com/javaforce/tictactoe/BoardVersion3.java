package com.javaforce.tictactoe;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static javax.print.attribute.standard.Chromaticity.COLOR;

public class BoardVersion3 implements MouseListener {

    private JFrame gameFrame;
    private JPanel boardPanel;
    private Map<Integer,Square> squares;
    private static ImageIcon xImg;
    private static ImageIcon X1Img;
    private static PieceType currentPiece = PieceType.X;
    private PieceType gameWinner;
    private boolean gameOver = false;
    private int[] winningRow;
    private BlinkSquare blinkSquare = new BlinkSquare();

    public BoardVersion3(JFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public void initNewBoard()  {

        boardPanel = new JPanel();
        boardPanel.setPreferredSize(new Dimension(TicTacToe.getBoardWidth(),TicTacToe.getBoardWidth()));
        boardPanel.setLayout(new GridLayout(3, 3, 9, 9));
        boardPanel.setBackground(Color.GREEN);

        squares = new HashMap(9);
        for(int i=1;i<10;i++) {
            Square availSquare = new Square(i);
            availSquare.getHolder().addMouseListener(this);
            squares.put(i,availSquare);
            boardPanel.add(availSquare.getHolder());
        }

        gameFrame.add(boardPanel);

        setxImg(createPiece("x"));
        setoImg(createPiece("X1"));
    }

    public ImageIcon createPiece(String piece) {
        ImageIcon imageIcon = null;
        try {
            BufferedImage image = ImageIO.read(getClass().getClassLoader().getResource(piece + ".png"));
            BufferedImage image1 = resizeImage(image, TicTacToe.getSquareSize()-20,  TicTacToe.getSquareSize()-20);
            imageIcon = new ImageIcon(image1);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return imageIcon;
    }

    public BufferedImage resizeImage(BufferedImage oldImage, int newX, int newY) {
        Image scaledImage = oldImage.getScaledInstance(newX, newY, Image.SCALE_SMOOTH);
        BufferedImage newImage = new BufferedImage(newX, newY, BufferedImage.TYPE_INT_ARGB);
        newImage.createGraphics().drawImage(scaledImage, 0, 0 , null);
        return newImage;
    }

    public boolean isWinner() {
        int count;
        boolean winner = false;

        // Horizontal Win Loop
        for (int i = 0; i < 3; i++) {
            count = 0;
            for (int j = 1 + 3 * i; j <= 3 + 3 * i; j++) {
                if (squares.get(j).getOwner() != PieceType.E) {
                    count++;
                }
            }
            if (count == 3) {
                winner = true;
                winningRow = new int[]{1 + 3 * i, 2 + 3 * i, 3 + 3 * i};
            }
        }

        // Vertical Win Loop
        for (int i = 0; i < 3; i++) {
            count = 0;
            for (int j = 1 + i; j <= 9; j += 3) {
                if (squares.get(j).getOwner() != PieceType.E) {
                    count++;
                }
            }
            if (count == 3) {
                winner = true;
                winningRow = new int[]{1 + i, 4 + i, 7 + i};
            }
        }

        // Diagonal Win Loop
        count = 0;
        for (int j = 1; j <= 9; j += 4) {
            if (squares.get(j).getOwner() != PieceType.E) {
                count++;
            }
        }
        if (count == 3) {
            winner = true;
            winningRow = new int[]{1, 5, 9};
        }

        count = 0;
        for (int j = 3; j <= 7; j += 2) {
            if (squares.get(j).getOwner() != PieceType.E) {
                count++;
            }
        }
        if (count == 3) {
            winner = true;
            winningRow = new int[]{3, 5, 7};
        }


        /*
        if(squares.get(5).getOwner() == getCurrentPiece()) {
            if(squares.get(1).getOwner() == getCurrentPiece() && squares.get(9).getOwner() == getCurrentPiece()) {
                winner = true;
                winningRow = new int[]{1,5,9};
            } else if(squares.get(3).getOwner() == getCurrentPiece() && squares.get(7).getOwner() == getCurrentPiece()) {
                winner = true;
                winningRow = new int[]{3,5,7};
            } else if(squares.get(2).getOwner() == getCurrentPiece() && squares.get(8).getOwner() == getCurrentPiece()) {
                winner = true;
                winningRow = new int[]{2,5,8};
            } else if(squares.get(4).getOwner() == getCurrentPiece() && squares.get(6).getOwner() == getCurrentPiece()) {
                winner = true;
                winningRow = new int[]{4,5,6};
            }
        } else if(squares.get(1).getOwner() != PieceType.E) {
            if(squares.get(2).getOwner() != PieceType.E && squares.get(3).getOwner() != PieceType.E) {
                winner = true;
                winningRow = new int[]{1,2,3};
            } else if(squares.get(4).getOwner() == getCurrentPiece() && squares.get(7).getOwner() == getCurrentPiece()) {
                winner = true;
                winningRow = new int[]{1,4,7};
            }
        } else if(squares.get(9).getOwner() == getCurrentPiece()) {
            if(squares.get(3).getOwner() == getCurrentPiece() && squares.get(6).getOwner() == getCurrentPiece()) {
                winner = true;
                winningRow = new int[]{3,6,9};
            } else if(squares.get(7).getOwner() == getCurrentPiece() && squares.get(8).getOwner() == getCurrentPiece()) {
                winner = true;
                winningRow = new int[]{7,8,9};
            }
        }
        */

        System.out.println("winner: " + getCurrentPiece() + "  " + winner);
        gameWinner = getCurrentPiece();
        return winner;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(!gameOver) {
            boolean winner = false;
            System.out.println(getCurrentPiece() + " Clicked square " + ((JPanel) e.getSource()).getName());
            Integer location = Integer.valueOf(((JPanel) e.getSource()).getName());
            JPanel holder = squares.get(location).getHolder();
            squares.get(location).setOwner(BoardVersion3.getCurrentPiece());
            JLabel imageHolder;
            winner = isWinner();
            if (BoardVersion3.getCurrentPiece() == PieceType.X1) {
                imageHolder = new JLabel(BoardVersion3.getoImg());
                BoardVersion3.setCurrentPiece(PieceType.X);
            } else {
                imageHolder = new JLabel(BoardVersion3.getxImg());
                BoardVersion3.setCurrentPiece(PieceType.X1);
            }
            imageHolder.setHorizontalAlignment(JLabel.RIGHT);
            imageHolder.setVerticalAlignment(JLabel.CENTER);
            holder.add(imageHolder);
            holder.revalidate();
            if (winner) {
                gameOver = true;
                blinkSquare.start();
                System.out.println(Arrays.toString(winningRow));
                System.out.println(gameWinner + " has won the game !");
            }
        }
    }

    class BlinkSquare extends Thread {
        @Override
        public void run() {
            boolean viewable = false;
            while(true) {
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
            while(true) {
                Random random = new Random(8);
                int randomSquare = random.nextInt(8)  + 1;
                System.out.println("randomSquare :" + randomSquare);
                squares.get(randomSquare).getHolder().getComponent(0).setVisible(true);
                try { Thread.sleep(fastBlink); } catch (Exception e) { }
                squares.get(randomSquare).getHolder().getComponent(0).setVisible(false);
                try { Thread.sleep(fastBlink); } catch (Exception e) { }
            }
        }
    }

    public static ImageIcon getxImg() {
        return xImg;
    }

    public static void setxImg(ImageIcon xImg) {
        BoardVersion3.xImg = xImg;
    }

    public static ImageIcon getoImg() {
        return X1Img;
    }

    public static void setoImg(ImageIcon oImg) {
        BoardVersion3.X1Img = oImg;
    }

    public static PieceType getCurrentPiece() {
        return currentPiece;
    }

    public static void setCurrentPiece(PieceType currentPiece) {
        BoardVersion3.currentPiece = currentPiece;
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }
}