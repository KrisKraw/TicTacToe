package com.javaforce.tictactoe;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import static java.awt.FlowLayout.CENTER;

public class Game implements MouseListener {

    private JFrame gameFrame;
    private JPanel splashPanel;
    private JPanel boardPanel;
    private JPanel statusPanel;
    private SplashScreen SplashScreen;
    private Board board;
    private BlinkSquare blinkSquare = new BlinkSquare();
    private static ImageIcon largeXImage;
    private static ImageIcon largeOImage;
    private static ImageIcon mediumXImage;
    private static ImageIcon mediumOImage;
    private static ImageIcon smallXImage;
    private static ImageIcon smallOImage;
    private BoardScrambler boardScrambler = new BoardScrambler();
    private JTextField inputedPlayerName = new JTextField();

    public Game() {

        setLargeXImage();
        setLargeOImage();
        setMediumXImage();
        setMediumOImage();
        setSmallXImage();
        setSmallOImage();
        createFrame();
        showSplashScreen();
        createBoardPanel();
        createStatusPanel();
        boardScrambler.start();
        showPlayButton();
    }

    public void createFrame() {

        gameFrame = new JFrame("TicTacToe by JavaForce");
        gameFrame.setSize(TicTacToe.getBoardWidth(),TicTacToe.getBoardHeight());
        gameFrame.setLayout(new FlowLayout(CENTER));
        gameFrame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setVisible(true);
    }

    public void createBoardPanel()  {

        if(boardPanel != null) {
            gameFrame.getContentPane().remove(0);
            gameFrame.repaint();
        }

        board = new Board(this);
        boardPanel = new JPanel();
        boardPanel.setPreferredSize(new Dimension(TicTacToe.getBoardWidth(),TicTacToe.getBoardWidth()));
        boardPanel.setLayout(new GridLayout(3, 3, 9, 9));
        boardPanel.setBackground(Color.GREEN);
        gameFrame.getContentPane().add(boardPanel);
        gameFrame.setVisible(true);
    }

    public void createStatusPanel()  {

        if(statusPanel != null) {
            gameFrame.getContentPane().remove(0);
            gameFrame.repaint();
        }

        statusPanel = new JPanel();
        statusPanel.setLayout(new FlowLayout(CENTER));
        statusPanel.setAlignmentX(CENTER);
        statusPanel.setAlignmentY(CENTER);
        statusPanel.setPreferredSize(new Dimension(gameFrame.getWidth(), gameFrame.getHeight() / 4));
        statusPanel.setBackground(Color.white);
        statusPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        statusPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameFrame.getContentPane().add(statusPanel);
    }

    public void showSplashScreen() {
        try {
            splashPanel = new JPanel();
            splashPanel.setBackground(Color.white);
            splashPanel.setSize(TicTacToe.getBoardWidth(),TicTacToe.getBoardHeight());

            BufferedImage image = ImageIO.read(getClass().getClassLoader().getResource("jf_logo.png"));
            BufferedImage resizedImage = resizeImage(image, TicTacToe.getBoardWidth() - 20, TicTacToe.getBoardHeight()-60);
            ImageIcon imageIcon = new ImageIcon(resizedImage);

            JLabel logoHolder = new JLabel(imageIcon);
            logoHolder.setHorizontalAlignment(JLabel.CENTER);
            logoHolder.setVerticalAlignment(JLabel.CENTER);

            splashPanel.add(logoHolder);
            gameFrame.getContentPane().add(splashPanel);
            gameFrame.setVisible(true);

            Thread.sleep(3000);

            System.out.println("DONE");

            gameFrame.getContentPane().remove(splashPanel);
            gameFrame.repaint();
            gameFrame.setVisible(true);

        } catch(Exception e) { e.printStackTrace(); }
    }

    public void showPlayButton() {
        try {
            BufferedImage image = ImageIO.read(getClass().getClassLoader().getResource("play.png"));
            int startButtonWidth = TicTacToe.getBoardWidth() / 3;
            int startButtonHeight = (int)(startButtonWidth * .54);
            BufferedImage resizedImage = resizeImage(image, startButtonWidth, startButtonHeight);
            ImageIcon imageIcon = new ImageIcon(resizedImage);
            JLabel buttonHolder = new JLabel(imageIcon,JLabel.CENTER);
            buttonHolder.setName("playbutton");
            buttonHolder.addMouseListener(this);
            buttonHolder.setPreferredSize(new Dimension(300,125));
            //buttonHolder.setHorizontalAlignment(JLabel.CENTER);
            buttonHolder.setVerticalTextPosition(SwingConstants.CENTER);
            buttonHolder.setVerticalAlignment(JLabel.CENTER);
            buttonHolder.setOpaque(true);
            buttonHolder.setBackground(Color.white);
            statusPanel.add(buttonHolder);
            statusPanel.revalidate();

        } catch(Exception e) { e.printStackTrace(); }
    }

    public void startNewGame() {
        setupPlayerInfo();
    }

    public void setupPlayerInfo() {

        clearPanel(statusPanel);

        try {

            ImageIcon pieceIcon = null;
            if(PlayerFactory.getPlayerMap().isEmpty()) {
                pieceIcon = getSmallXImage();
            } else {
                pieceIcon = getSmallOImage();
            }
            JLabel label1 = new JLabel("enter");
            label1.setHorizontalAlignment(JLabel.CENTER);
            label1.setVerticalAlignment(JLabel.CENTER);
            label1.setFont(new Font("Calibri", Font.BOLD, 25));

            JLabel label2 = new JLabel(pieceIcon);

            JLabel label3 = new JLabel("player name");
            label3.setFont(new Font("Calibri", Font.BOLD, 25));

            inputedPlayerName.setText("");
            inputedPlayerName.setFont(TicTacToe.getGameFont());
            inputedPlayerName.setPreferredSize(new Dimension(175,50));
            inputedPlayerName.setName("playerInputField");

            BufferedImage image = ImageIO.read(getClass().getClassLoader().getResource("next-button.png"));
            int nextButtonWidth = TicTacToe.getBoardWidth() / 5;
            BufferedImage resizedImage = resizeImage(image, nextButtonWidth, (int)(nextButtonWidth * .40));
            ImageIcon imageIcon = new ImageIcon(resizedImage);

            JLabel nextButtonHolder = new JLabel(imageIcon);
            nextButtonHolder.addMouseListener(this);
            nextButtonHolder.setHorizontalAlignment(JLabel.CENTER);
            nextButtonHolder.setVerticalAlignment(JLabel.CENTER);
            nextButtonHolder.setBackground(Color.white);
            nextButtonHolder.setName("nextButtonHolder");

            statusPanel.add(label1);
            statusPanel.add(label2);
            statusPanel.add(label3);
            statusPanel.add(inputedPlayerName);
            statusPanel.add(nextButtonHolder);

        } catch(Exception e) { e.printStackTrace(); }
    }

    public void setBoard() {
        boardPanel.removeAll();
        for(int i=1;i<10;i++) {
            Square availSquare = new Square(i);
            availSquare.getHolder().addMouseListener(this);
            board.getSquares().put(i,availSquare);
            boardPanel.add(availSquare.getHolder());
            boardPanel.revalidate();
        }
    }

    public void clearPanel(JPanel panel) {
        if(panel != null) {
            Component[] componentsToRemove = panel.getComponents();
            for (Component componentToRemove : componentsToRemove) {
                System.out.println(componentToRemove);
                panel.remove(componentToRemove);
            }
            panel.revalidate();
            panel.repaint();
        }
    }

    public void startPlaying() {
        boardScrambler.showBoardScrambler = false;
        setBoard();
        clearPanel(statusPanel);
        showCurrentPlayer();
    }

    public void showCurrentPlayer() {

        board.setNextCurrentPlayer();

        clearPanel(statusPanel);

        String playerName = board.getCurrentPlayer().getPlayerName();
        ImageIcon playerPiece = (board.getCurrentPlayer().getPieceType()).getMediumImage();

        JLabel pieceHolder = new JLabel(playerPiece);

        JLabel playerNameHolder = new JLabel(playerName);
        playerNameHolder.setFont(new Font("Calibri", Font.BOLD, 50));
        playerNameHolder.setForeground(Color.green);

        JLabel messageHolder = new JLabel(TicTacToe.getRandomMove());
        messageHolder.setFont(new Font("Calibri", Font.BOLD, 30));

        statusPanel.add(pieceHolder);
        statusPanel.add(playerNameHolder);
        statusPanel.add(messageHolder);

        statusPanel.revalidate();
        statusPanel.repaint();
    }

    public void showCurrentPlayer() {


    }

    public void gameOver() {
        System.out.println("!! " + board.getCurrentPlayer().getPlayerName() + " YOU WON !!");
        System.out.println("GAME OVER");
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        String clickedObjectName = ((JComponent) e.getSource()).getName();
        boolean squareClicked = clickedObjectName.matches("[0-9]*[0-9]+$");
        System.out.println("clicked: " + clickedObjectName);

        if(!board.isGameOver() && !board.getDraw() && squareClicked) {
            boolean winner = false;
            Integer location = Integer.valueOf(clickedObjectName);
            JPanel holder = board.getSquares().get(location).getHolder();
            board.getSquares().get(location).setOwner(Board.getCurrentPiece());
            JLabel imageHolder;
            winner = board.isWinner();
            if (Board.getCurrentPiece() == PieceType.O) {
                imageHolder = new JLabel(getLargeXImage());
                Board.setCurrentPiece(PieceType.X);
            } else {
                imageHolder = new JLabel(getLargeOImage());
                Board.setCurrentPiece(PieceType.O);
            }
            imageHolder.setHorizontalAlignment(JLabel.RIGHT);
            imageHolder.setVerticalAlignment(JLabel.CENTER);
            holder.add(imageHolder);
            holder.removeMouseListener(this);
            holder.revalidate();
            if (winner) {
                board.setGameOver(true);
                blinkSquare.start();
                gameOver();
            } else if(board.isDraw()) {
                board.setDraw(true);
            }
            showCurrentPlayer();
        } else if(clickedObjectName.equals("playbutton")) {
            hidePlayButton();
            startNewGame();
        } else if(clickedObjectName.equals("nextButtonHolder")) {
            if(PlayerFactory.getPlayerMap().size() < 2) {
                PieceType userPieceType = PieceType.X;
                if(PlayerFactory.getPlayerMap().size() == 1) {
                    userPieceType = PieceType.O;
                }
                PlayerFactory.createPlayer(inputedPlayerName.getText(), userPieceType);

                System.out.println("created player: " + inputedPlayerName.getText() + "  " + userPieceType + "  " + PlayerFactory.getPlayerMap().size());
            }

            if(PlayerFactory.getPlayerMap().size() == 2) {
                startPlaying();
            }
        }
    }

    public static ImageIcon getLargeXImage() {
        return largeXImage;
    }

    public void setLargeXImage() {
        largeXImage = createPiece("x", TicTacToe.getSquareSize() - 20, TicTacToe.getSquareSize()
                - 20);
    }

    public static ImageIcon getLargeOImage() {
        return largeOImage;
    }

    public void setLargeOImage() {
        largeOImage = createPiece("o", TicTacToe.getSquareSize() - 20, TicTacToe.getSquareSize());
    }

    public static ImageIcon getMediumXImage() {
        return mediumXImage;
    }

    public void setMediumXImage() {
        mediumXImage = createPiece("x", TicTacToe.getSquareSize() / 2, TicTacToe.getSquareSize() / 2);
    }

    public static ImageIcon getMediumOImage() {
        return mediumOImage;
    }

    public void setMediumOImage() {
        mediumOImage = createPiece("o", TicTacToe.getSquareSize() / 4, TicTacToe.getSquareSize() / 4);
    }

    public static ImageIcon getSmallXImage() {
        return smallXImage;
    }

    public void setSmallXImage() {
        smallXImage = createPiece("x", TicTacToe.getSquareSize() / 6, TicTacToe.getSquareSize() / 6);
    }

    public static ImageIcon getSmallOImage() { return smallOImage; }

    public void setSmallOImage() {
        smallOImage = createPiece("o", TicTacToe.getSquareSize() - 20, TicTacToe.getSquareSize());
    }

    public ImageIcon createPiece(String piece, int sizeX, int sizeY) {
        ImageIcon imageIcon = null;
        try {
            BufferedImage image = ImageIO.read(getClass().getClassLoader().getResource(piece + ".png"));
            BufferedImage image1 = resizeImage(image, sizeX,  sizeY);
            imageIcon = new ImageIcon(image1);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return imageIcon;
    }

    public static BufferedImage resizeImage(BufferedImage oldImage, int newX, int newY) {
        Image scaledImage = oldImage.getScaledInstance(newX, newY, Image.SCALE_SMOOTH);
        BufferedImage newImage = new BufferedImage(newX, newY, BufferedImage.TYPE_INT_ARGB);
        newImage.createGraphics().drawImage(scaledImage, 0, 0 , null);
        return newImage;
    }

    public void hidePlayButton() {
        statusPanel.getComponent(0).setVisible(false);
        statusPanel.revalidate();
    }

    public JFrame getGameFrame() {
        return gameFrame;
    }

    public void setGameFrame(JFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public JPanel getSplashPanel() {
        return splashPanel;
    }

    public void setSplashPanel(JPanel splashPanel) {
        this.splashPanel = splashPanel;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    class BlinkSquare extends Thread {
        @Override
        public void run() {
            boolean viewable = false;
            while(true) {
                for (int i : board.getWinningRow()) {
                    board.getSquares().get(i).getHolder().getComponent(0).setVisible(viewable);
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

            for(int i=1;i<10;i++) {
                Square availSquare = new Square(i);
                board.getSquares().put(i,availSquare);
                boardPanel.add(availSquare.getHolder());
            }

            while(showBoardScrambler) {
                try {
                    int whichPiece = random.nextInt(2);
                    ImageIcon randomPiece = getLargeXImage();
                    if(whichPiece == 0) {
                        randomPiece = getLargeOImage();
                    }

                    int whichSquare = random.nextInt(9)  + 1;
                    JLabel imageHolder = new JLabel(randomPiece);
                    imageHolder.setHorizontalAlignment(JLabel.CENTER);
                    imageHolder.setVerticalAlignment(JLabel.CENTER);
                    board.getSquares().get(whichSquare).getHolder().add(imageHolder);
                    gameFrame.repaint();
                    gameFrame.setVisible(true);

                    Thread.sleep(blinkRate);

                    board.getSquares().get(whichSquare).getHolder().remove(0);

                } catch (Exception e) { e.printStackTrace(); }
            }
        }
    }
}