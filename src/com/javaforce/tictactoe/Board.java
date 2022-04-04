package com.javaforce.tictactoe;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.*;

public class Board implements MouseListener {

    private JFrame gameFrame;
    private JPanel boardPanel;
    private JPanel controlPanel;
    private Map<Integer,Square> squares;
    private static ImageIcon xImg;
    private static ImageIcon oImg;
    private static PieceType currentPiece = PieceType.X;
    private PieceType gameWinner;
    private boolean gameOver = false;
    private int[] winningRow;
    private boolean draw = false;
    BoardScrambler boardScrambler = new BoardScrambler();
    private BlinkSquare blinkSquare = new BlinkSquare();
    private Map<Integer,Player> players = new HashMap<>();
    private JTextField inputedPlayerName = new JTextField(10);
    ;

    public Board(JFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public void initNewBoard()  {

        if(boardPanel != null) {
            gameFrame.getContentPane().remove(0);
            gameFrame.repaint();
        }
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

        gameFrame.getContentPane().add(boardPanel);
        gameFrame.setVisible(true);

        setxImg(createPiece("x"));
        setoImg(createPiece("o"));
    }

    public void initControlPanel()  {
        if(controlPanel != null) {
            gameFrame.getContentPane().remove(0);
            gameFrame.repaint();
        }
        controlPanel = new JPanel();
        controlPanel.setPreferredSize(new Dimension(TicTacToe.getBoardWidth(),TicTacToe.getBoardHeight() / 4));
        controlPanel.setLayout(new GridBagLayout());
        controlPanel.setBackground(Color.white);
        gameFrame.getContentPane().add(controlPanel);
        gameFrame.setVisible(true);
    }

    public void showPlayButton() {
        try {
            BufferedImage image = ImageIO.read(getClass().getClassLoader().getResource("play.png"));
            int startButtonWidth = TicTacToe.getBoardWidth() / 3;
            BufferedImage resizedImage = TicTacToe.resizeImage(image, startButtonWidth, (int)(startButtonWidth * .54));
            ImageIcon imageIcon = new ImageIcon(resizedImage);
            JLabel buttonHolder = new JLabel(imageIcon);
            buttonHolder.setHorizontalAlignment(JLabel.CENTER);
            buttonHolder.setVerticalAlignment(JLabel.CENTER);
            buttonHolder.setBackground(Color.white);
            JPanel buttonPanel = new JPanel();
            buttonPanel.setName("playbutton");
            buttonPanel.addMouseListener(this);
            buttonPanel.setBackground(Color.white);
            buttonPanel.add(buttonHolder);
            controlPanel.add(buttonPanel);
        } catch(Exception e) { e.printStackTrace(); }
    }

    public void hidePlayButton() {
        controlPanel.getComponent(0).setVisible(false);
        controlPanel.revalidate();
    }

    public void startNewGame() {
        initNewBoard();
        initControlPanel();
        getPlayerInfo();
    }

    public void getPlayerInfo() {

        System.out.println("getPlayerInfo");

        if(players.size() > 2) {
            return;
        }

        String piece = "X";
        if(players.size() == 1) {
            piece = "O";
        }

        try {
            JLabel desc = new JLabel("enter " + piece + " player name");

            inputedPlayerName.setText("");
            inputedPlayerName.setFont(TicTacToe.getGameFont());
            //inputedPlayerName.setSize(400,50);

            BufferedImage image = ImageIO.read(getClass().getClassLoader().getResource("next-button.png"));
            int nextButtonWidth = TicTacToe.getBoardWidth() / 5;
            BufferedImage resizedImage = TicTacToe.resizeImage(image, nextButtonWidth, (int)(nextButtonWidth * .40));
            ImageIcon imageIcon = new ImageIcon(resizedImage);
            JLabel nextButtonHolder = new JLabel(imageIcon);
            nextButtonHolder.setHorizontalAlignment(JLabel.CENTER);
            nextButtonHolder.setVerticalAlignment(JLabel.CENTER);
            nextButtonHolder.setBackground(Color.white);

            JPanel playerInputPanel = new JPanel();
            playerInputPanel.addMouseListener(this);
            playerInputPanel.setName("playerInputPanel");
            playerInputPanel.setSize(TicTacToe.getBoardWidth(),TicTacToe.getBoardWidth());
            playerInputPanel.setBackground(Color.WHITE);
            playerInputPanel.add(desc);
            playerInputPanel.add(inputedPlayerName);
            playerInputPanel.add(nextButtonHolder);

            controlPanel.add(playerInputPanel);

        } catch(Exception e) { e.printStackTrace(); }
    }

    public void setPlayerInfo(String playerName, PieceType pieceType) {
        Player player = new Player(playerName, pieceType);
        players.put(player.getPlayerId(),player);
    }

    public void clearBoard() {
        boardPanel.removeAll();
        squares = new HashMap(9);
        for(int i=1;i<10;i++) {
            Square availSquare = new Square(i);
            availSquare.getHolder().addMouseListener(this);
            squares.put(i,availSquare);
            boardPanel.add(availSquare.getHolder());
        }
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

    public boolean isDraw() {
        return false;
    }

    public boolean isWinner() {
        boolean winner = false;
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
        } else if(squares.get(1).getOwner() == getCurrentPiece()) {
            if(squares.get(2).getOwner() == getCurrentPiece() && squares.get(3).getOwner() == getCurrentPiece()) {
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
        System.out.println("winner: " + getCurrentPiece() + "  " + winner);
        gameWinner = getCurrentPiece();
        return winner;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String clickedObjectName = ((JComponent) e.getSource()).getName();
        System.out.println("clicked: " + clickedObjectName);
        if(!gameOver && !getDraw() && clickedObjectName.matches("[0-9]*[0-9]+$")) {
            boolean winner = false;
            System.out.println(getCurrentPiece() + " Clicked square " + clickedObjectName);
            Integer location = Integer.valueOf(clickedObjectName);
            JPanel holder = squares.get(location).getHolder();
            squares.get(location).setOwner(Board.getCurrentPiece());
            JLabel imageHolder;
            winner = isWinner();
            if (Board.getCurrentPiece() == PieceType.O) {
                imageHolder = new JLabel(Board.getoImg());
                Board.setCurrentPiece(PieceType.X);
            } else {
                imageHolder = new JLabel(Board.getxImg());
                Board.setCurrentPiece(PieceType.O);
            }
            imageHolder.setHorizontalAlignment(JLabel.RIGHT);
            imageHolder.setVerticalAlignment(JLabel.CENTER);
            holder.add(imageHolder);
            holder.removeMouseListener(this);
            holder.revalidate();
            if (winner) {
                gameOver = true;
                blinkSquare.start();
                System.out.println(Arrays.toString(winningRow));
                System.out.println(gameWinner + " has won the game !");
            } else if(isDraw()) {
                setDraw(true);
            }
        } else if(clickedObjectName.equals("playbutton")) {
            boardScrambler.showBoardScrambler = false;
            hidePlayButton();
            startNewGame();
        } else if(clickedObjectName.equals("playerInputPanel")) {
            if(players.size() < 2) {
                System.out.println("inputedPlayerName: " + inputedPlayerName.getText());
                setPlayerInfo(inputedPlayerName.getText(), PieceType.X);
                System.out.println(players.get(1).getPlayerId() + "  " + players.get(1).getPlayerName());
                if(players.size() > 1) {
                    System.out.println(players.get(2).getPlayerId() + "  " + players.get(2).getPlayerName());
                }
                getPlayerInfo();
            } else {
                System.out.println(players.get(1).getPlayerId() + "  " + players.get(1).getPlayerName());
                System.out.println(players.get(2).getPlayerId() + "  " + players.get(2).getPlayerName());
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

    public class BoardScrambler extends Thread {

        public boolean showBoardScrambler = true;

        @Override
        public void run() {
            long blinkRate = 200;
            Random random = new Random();
            while(showBoardScrambler) {
                int whichPiece = random.nextInt(2);
                ImageIcon randomPiece;
                if(whichPiece == 0) {
                    randomPiece = Board.getxImg();
                } else {
                    randomPiece = Board.getoImg();
                }

                int whichSquare = random.nextInt(9)  + 1;
                JLabel imageHolder = new JLabel(randomPiece);
                imageHolder.setHorizontalAlignment(JLabel.RIGHT);
                imageHolder.setVerticalAlignment(JLabel.CENTER);
                JPanel randomSquarePanel = squares.get(whichSquare).getHolder();
                randomSquarePanel.add(imageHolder);
                randomSquarePanel.add(imageHolder);
                randomSquarePanel.revalidate();

                try { Thread.sleep(blinkRate); } catch (Exception e) { }

                randomSquarePanel.remove(0);
                randomSquarePanel.revalidate();
                gameFrame.repaint();

                try { Thread.sleep(blinkRate); } catch (Exception e) { }
            }
        }
    }

    public static ImageIcon getxImg() {
        return xImg;
    }

    public static void setxImg(ImageIcon xImg) {
        Board.xImg = xImg;
    }

    public static ImageIcon getoImg() {
        return oImg;
    }

    public static void setoImg(ImageIcon oImg) {
        Board.oImg = oImg;
    }

    public static PieceType getCurrentPiece() {
        return currentPiece;
    }

    public static void setCurrentPiece(PieceType currentPiece) {
        Board.currentPiece = currentPiece;
    }

    public boolean getDraw() { return draw; }

    public void setDraw(boolean draw) { this.draw = draw; }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }
}