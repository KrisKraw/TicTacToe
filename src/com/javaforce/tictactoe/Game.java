package com.javaforce.tictactoe;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import static java.awt.FlowLayout.CENTER;
import static java.lang.System.exit;

public class Game implements MouseListener {

    private JFrame gameFrame;
    private JPanel splashPanel;
    private JPanel boardPanel;
    private JPanel statusPanel;
    private JPanel exitPanel;
    private Board board;
    private static ImageIcon largeXImage;
    private static ImageIcon largeX1Image;
    private static ImageIcon mediumX1Image;
    private static ImageIcon smallX1Image;
    private static ImageIcon largeOImage;
    private static ImageIcon mediumXImage;
    private static ImageIcon mediumOImage;
    private static ImageIcon smallXImage;
    private static ImageIcon smallOImage;
    private static ImageIcon alexaIcon;
    private BoardScrambler boardScrambler = new BoardScrambler();
    private JTextField inputedPlayerName = new JTextField();
    public static boolean diffGameVersion = false;

    public Game() {
    }

    public void gameStart() {
        setLargeXImage();
        setLargeOImage();
        setMediumXImage();
        setMediumOImage();
        setSmallXImage();
        setSmallOImage();
        setLargeX1Image();
        setAlexaIcon();
        createFrame();
        showSplashScreen();
        createBoardPanel();
        createStatusPanel();
        createExitPanel();
        boardScrambler.start();
        showGameVersions();
    }

    //configures the application's main frame
    public void createFrame() {
        gameFrame = new JFrame("TicTacToe by JavaForce");
        gameFrame.setSize(TicTacToe.getBoardWidth(),TicTacToe.getBoardHeight());
        gameFrame.setLayout(new FlowLayout(CENTER,0,0));
        gameFrame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setVisible(true);
    }

    //configures the board JPanel
    public void createBoardPanel()  {
        clearPanel(statusPanel);

        board = new Board(this);
        boardPanel = new JPanel();
        boardPanel.setPreferredSize(new Dimension(TicTacToe.getBoardWidth(),TicTacToe.getBoardWidth()));
        boardPanel.setLayout(new GridLayout(3, 3, 9, 9));
        boardPanel.setBackground(TicTacToe.getBoardColor());
        gameFrame.getContentPane().add(boardPanel);
        gameFrame.setVisible(true);
    }

    //configures the status JPanel
    public void createStatusPanel()  {
        clearPanel(statusPanel);

        statusPanel = new JPanel();
        statusPanel.setLayout(new FlowLayout(CENTER));
        statusPanel.setAlignmentX(CENTER);
        statusPanel.setAlignmentY(CENTER);
        statusPanel.setPreferredSize(new Dimension(gameFrame.getWidth(), (gameFrame.getHeight() / 4) - 70));
        statusPanel.setBackground(Color.white);
        statusPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        statusPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameFrame.getContentPane().add(statusPanel);
        gameFrame.setVisible(true);
    }

    //displays the exit button
    public void createExitPanel() {
        clearPanel(statusPanel);

        exitPanel = new JPanel();
        exitPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        exitPanel.setPreferredSize(new Dimension(gameFrame.getWidth(), 31));
        exitPanel.setBackground(Color.white);

        try {
            BufferedImage exitImage = ImageIO.read(getClass().getClassLoader().getResource("exit.png"));
            BufferedImage resizedExitImage = resizeImage(exitImage, 50, 25);
            ImageIcon exitImageIcon = new ImageIcon(resizedExitImage);
            JLabel exitLabel = new JLabel(exitImageIcon, JLabel.CENTER);
            exitLabel.setName("exitLabel");
            exitLabel.addMouseListener(this);
            exitLabel.setPreferredSize(new Dimension(gameFrame.getWidth(), 50));
            exitLabel.setVerticalAlignment(SwingConstants.TOP);
            exitLabel.setHorizontalAlignment(SwingConstants.LEFT);
            exitLabel.setBorder(new EmptyBorder(0,5,0,0));
            exitPanel.add(exitLabel);
            gameFrame.getContentPane().add(exitPanel);
            gameFrame.setVisible(true);
        } catch(Exception e) { e.printStackTrace(); }
    }

    //displays the initial javaForce splash screen
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

    //shows the two game versions as large button panels
    public void showGameVersions() {
        try {
            int panelsWidth = 200;
            int panelsHeight = (int)(panelsWidth*.53);
            BufferedImage image = ImageIO.read(getClass().getClassLoader().getResource("traditional.png"));
            BufferedImage resizedImage = resizeImage(image, panelsWidth, panelsHeight);
            ImageIcon traditionalIcon = new ImageIcon(resizedImage);
            JLabel tradHolder = new JLabel(traditionalIcon);
            tradHolder.setName("traditional");
            tradHolder.addMouseListener(this);
            tradHolder.setPreferredSize(new Dimension(panelsWidth,panelsHeight));
            tradHolder.setVerticalTextPosition(SwingConstants.CENTER);
            tradHolder.setVerticalAlignment(JLabel.CENTER);
            tradHolder.setOpaque(true);
            tradHolder.setBackground(Color.white);

            JLabel label1 = new JLabel("which version ?");
            label1.setHorizontalAlignment(JLabel.CENTER);
            label1.setVerticalAlignment(JLabel.CENTER);
            label1.setFont(new Font("Calibri", Font.BOLD, 25));

            BufferedImage image2 = ImageIO.read(getClass().getClassLoader().getResource("notakto.png"));
            BufferedImage resizedImage2 = resizeImage(image2, panelsWidth, panelsHeight);
            ImageIcon notaktoIcon = new ImageIcon(resizedImage2);
            JLabel notHolder = new JLabel(notaktoIcon);
            notHolder.setName("notHolder");
            notHolder.addMouseListener(this);
            notHolder.setPreferredSize(new Dimension(panelsWidth,panelsHeight));
            notHolder.setBackground(Color.white);

            statusPanel.add(tradHolder);
            statusPanel.add(label1);
            statusPanel.add(notHolder);
            statusPanel.revalidate();

        } catch(Exception e) { e.printStackTrace(); }
    }

    //hides the game version buttons
    public void hideGameVersions() {
        Component panelOne = SwingHelpers.getComponentByName("traditional", statusPanel);
        Component panelTwo = SwingHelpers.getComponentByName("notHolder", statusPanel);
        statusPanel.remove(panelOne);
        statusPanel.remove(panelTwo);
        statusPanel.revalidate();
    }

    //starts a new game
    public void startNewGame() {
        setupPlayerInfo();
    }

    //prompts for players info
    public void setupPlayerInfo() {

        clearPanel(statusPanel);

        try {
            ImageIcon pieceIcon = null;
            if (PlayerFactory.getPlayerMap().isEmpty()) {
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
            inputedPlayerName.setPreferredSize(new Dimension(175, 50));
            inputedPlayerName.setName("playerInputField");

            BufferedImage image4 = ImageIO.read(getClass().getClassLoader().getResource("play.png"));
            try{
                if (PlayerFactory.getPlayerMap().isEmpty()) {
                    image4 = ImageIO.read(getClass().getClassLoader().getResource("next.png"));
                }
            } catch(Exception e) { e.printStackTrace(); }

            int nextButtonWidth = TicTacToe.getBoardWidth() / 5;
            BufferedImage resizedImage = resizeImage(image4, nextButtonWidth, (int)(nextButtonWidth * .40));
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

            if(Board.playingAlexa == false) {
                JLabel label4 = new JLabel("or click alexa to play against her ");
                label4.setFont(new Font("Calibri", Font.BOLD, 25));
                statusPanel.add(label4);

                JLabel alexaLabel = new JLabel(getAlexaIcon());
                alexaLabel.setName("play-alexa");
                alexaLabel.addMouseListener(this);
                statusPanel.add(alexaLabel);
            }

            statusPanel.revalidate();

        } catch(Exception e) { e.printStackTrace(); }
    }

    //clears the game piece and sets owner to PieceType.E for all 9 squares
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

    //clears all children components from a JPanel
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

    //executed when the game is started
    public void startPlaying() {
        boardScrambler.showBoardScrambler = false;
        setBoard();
        clearPanel(statusPanel);
        showCurrentPlayer();
    }

    //updates the status panel with the current users info
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

    //after a win is detected, theis method update the status panel to show that win
    public void showWinner(Player winner) {

        clearPanel(statusPanel);

        JLabel label1 = new JLabel("yea " + winner.getPlayerName());
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setVerticalAlignment(JLabel.CENTER);
        label1.setFont(new Font("Calibri", Font.BOLD, 25));

        JLabel label2 = new JLabel(winner.getPieceType().getMediumImage());

        JLabel label3 = new JLabel("you won !");
        label3.setFont(new Font("Calibri", Font.BOLD, 25));

        JLabel jLabel4 = null;
        try {
            BufferedImage image4 = ImageIO.read(getClass().getClassLoader().getResource("play-again.png"));
            ImageIcon label4 = new ImageIcon(image4);
            jLabel4 = new JLabel(label4);
            jLabel4.setName("play-again");
            jLabel4.addMouseListener(this);
        } catch(Exception e) { e.printStackTrace(); }

        statusPanel.add(label1);
        statusPanel.add(label2);
        statusPanel.add(label3);
        statusPanel.add(jLabel4);

        statusPanel.revalidate();
        statusPanel.repaint();
    }

    //changes the status panel to show that a draw has occurred
    public void showDraw() {

        clearPanel(statusPanel);

        JLabel jlabel1 = new JLabel("aahhhhhh it was a draw !");
        jlabel1.setHorizontalAlignment(JLabel.CENTER);
        jlabel1.setVerticalAlignment(JLabel.CENTER);
        jlabel1.setFont(new Font("Calibri", Font.BOLD, 25));

        JLabel jLabel2 = null;
        try {
            BufferedImage image4 = ImageIO.read(getClass().getClassLoader().getResource("play-again.png"));
            ImageIcon label2 = new ImageIcon(image4);
            jLabel2 = new JLabel(label2);
            jLabel2.setName("play-again");
            jLabel2.addMouseListener(this);
        } catch(Exception e) { e.printStackTrace(); }

        statusPanel.add(jlabel1);
        statusPanel.add(jLabel2);
        statusPanel.revalidate();
        statusPanel.repaint();
    }

    //this method start a new game
    public void playAgain() {
        board.setGameOver(false);
        setBoard();
        showCurrentPlayer();
    }

    //this method is ran when the game is over
    public void gameOver() {
        System.out.println("!! " + board.getCurrentPlayer().getPlayerName() + " YOU WON !!");
        System.out.println("GAME OVER");
    }

    //detects mouse clicks and person corresponding tasks
    @Override
    public void mouseClicked(MouseEvent e) {

        String clickedObjectName = ((JComponent) e.getSource()).getName();
        boolean squareClicked = clickedObjectName.matches("[0-9]*[0-9]+$");
        System.out.println("clicked: " + clickedObjectName);

        if(clickedObjectName.equals("exitLabel")) {
            exit(0);
        } else if(!board.isGameOver() && squareClicked) {
            boolean winner = false;
            Integer location = Integer.valueOf(clickedObjectName);
            JPanel holder = board.getSquares().get(location).getHolder();
            board.getSquares().get(location).setOwner(Board.getCurrentPlayer().getPieceType());
            JLabel imageHolder = null;
            winner = board.isWinner();

            if (Board.playerOne.equals(Board.getCurrentPlayer())) {
                imageHolder = new JLabel(getLargeXImage());
            } else if (Board.playerTwo.equals(Board.getCurrentPlayer()) && !diffGameVersion) {
                imageHolder = new JLabel(getLargeOImage());
            } else if (Board.playerTwo.equals(Board.getCurrentPlayer()) && diffGameVersion) {
                imageHolder = new JLabel(getLargeX1Image());
            }

            imageHolder.setHorizontalAlignment(JLabel.RIGHT);
            imageHolder.setVerticalAlignment(JLabel.CENTER);
            holder.add(imageHolder);
            holder.removeMouseListener(this);
            holder.revalidate();

            if (winner) {
                Player xOnlyWinner;
                if(winner && !diffGameVersion) {
                    Board.getCurrentPlayer().win();
                    System.out.println(Board.getCurrentPlayer().getWins());
                    System.out.println("winner: " + Board.getCurrentPlayer().getPlayerName() + "  " + winner);
                } else {
                    if(Board.getCurrentPlayer().getPlayerId() == 1) {
                        System.out.println(Board.getCurrentPlayer().getPlayerId());
                        xOnlyWinner = Board.playerTwo;
                    } else {
                        xOnlyWinner = Board.playerOne;
                    }
                    xOnlyWinner.win();
                    System.out.println("winner: " + xOnlyWinner.getPlayerName() + "  " + winner);
                    System.out.println("PLayer-1: " + Board.playerOne.getWins() + " win; Player-2 " + Board.playerTwo.getWins() + " win;");
                }
                board.setGameOver(true);
                startBlinkSquare();
                gameOver();
                showWinner(Board.getCurrentPlayer());
                System.out.println("==== WINNER ====");
                //setBoard();
                //TODO: add logic for draw
            } else if(board.isDraw()) {
                showDraw();
            } else {
                showCurrentPlayer();
            }
        } else if(clickedObjectName.equals("playbutton")) {

        } else if(clickedObjectName.equals("nextButtonHolder") || clickedObjectName.equals("play-alexa")) {

            if(clickedObjectName.equals("play-alexa")) {
                inputedPlayerName.setText("alexa");
                Board.playingAlexa = true;
            }

            if(PlayerFactory.getPlayerMap().size() == 0) {
                PieceType userPieceType = PieceType.X;
                PlayerFactory.createPlayer(inputedPlayerName.getText(), userPieceType);
                System.out.println("created player: " + inputedPlayerName.getText() + "  " + userPieceType + "  " + PlayerFactory.getPlayerMap().size());
                setupPlayerInfo();
            } else {
                // Change to X1 based on the game type.
                PieceType userPieceType = PieceType.O;
                PlayerFactory.createPlayer(inputedPlayerName.getText(), userPieceType);
                System.out.println("created player: " + inputedPlayerName.getText() + "  " + userPieceType + "  " + PlayerFactory.getPlayerMap().size());
            }
            // TODO: Add option for Computer player around here.
            //playerInputField
            //Component component = getComponentByName(someOtherFrame, "jButton1");

            // radio check mark button,
            // checked means true (other version) unchecked means false (basic version)
            diffGameVersion = false;
            if(PlayerFactory.getPlayerMap().size() == 2) {
                startPlaying();
            }
        } else if(clickedObjectName.equals("traditional") || clickedObjectName.equals("notHolder")) {
            if(clickedObjectName.equals("traditional")) {
                diffGameVersion = false;
            } else {
                diffGameVersion = true;
            }
            hideGameVersions();
            startNewGame();
        } else if(clickedObjectName.equals("play-again")) {
            playAgain();
        }
    }

    public static ImageIcon getLargeXImage() {
        return largeXImage;
    }

    public void setLargeXImage() {
        largeXImage = createPiece("x", TicTacToe.getSquareSize() - 20, TicTacToe.getSquareSize()
                - 20);
    }

    public void setSmallX1Image() {
        smallX1Image = createPiece("x1", TicTacToe.getSquareSize() - 20, TicTacToe.getSquareSize()
                - 20);
    }

    public static ImageIcon getSmallX1Image() {
        return smallX1Image;
    }

    public static ImageIcon getMediumX1Image() {
        return mediumX1Image;
    }

    public void setMediumX1Image() {
        mediumX1Image = createPiece("x1", TicTacToe.getSquareSize() - 20, TicTacToe.getSquareSize()
                - 20);
    }

    public static ImageIcon getLargeX1Image() {
        return largeX1Image;
    }

    public void setLargeX1Image() {
        largeX1Image = createPiece("x1", TicTacToe.getSquareSize() - 20, TicTacToe.getSquareSize()
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
        mediumOImage = createPiece("o", TicTacToe.getSquareSize() / 2, TicTacToe.getSquareSize() / 2);
    }

    public static ImageIcon getSmallXImage() {
        return smallXImage;
    }

    public void setSmallXImage() {
        smallXImage = createPiece("x", TicTacToe.getSquareSize() / 6, TicTacToe.getSquareSize() / 6);
    }

    public static ImageIcon getSmallOImage() { return smallOImage; }

    public void setSmallOImage() {
        smallOImage = createPiece("o", TicTacToe.getSquareSize() / 6, TicTacToe.getSquareSize() / 6);
    }

    public static ImageIcon getAlexaIcon() { return alexaIcon; }

    //sets the alexa image icon
    public void setAlexaIcon() {
        int width = 95;
        alexaIcon = createPiece("a", width, (int) (width*.855));
    }

    //create various sizes of game pieces
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

    //resizes images
    public static BufferedImage resizeImage(BufferedImage oldImage, int newX, int newY) {
        Image scaledImage = oldImage.getScaledInstance(newX, newY, Image.SCALE_SMOOTH);
        BufferedImage newImage = new BufferedImage(newX, newY, BufferedImage.TYPE_INT_ARGB);
        newImage.createGraphics().drawImage(scaledImage, 0, 0 , null);
        return newImage;
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    public void startBlinkSquare() {
        BlinkSquare blinkSquare = new BlinkSquare();
        blinkSquare.start();
    }

    //blinks winning row when a winner is detected
    class BlinkSquare extends Thread {

        public boolean blinkSquares = true;

        @Override
        public void run() {
            boolean viewable = false;
            while(blinkSquares) {
                try {
                    for (int i : board.getWinningRow()) {
                        board.getSquares().get(i).getHolder().getComponent(0).setVisible(viewable);
                    }

                    Thread.sleep(500);

                    if (viewable) {
                        viewable = false;
                    } else {
                        viewable = true;
                    }
                } catch(Exception e) { blinkSquares = false; }
            }
        }
    }

    //scrambles X's and O's during game setup
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

                } catch (Exception e) { showBoardScrambler = false; }
            }
        }
    }
}