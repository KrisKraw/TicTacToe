package com.javaforce.tictactoe;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private JFrame boardFrame;
    private Map<Integer,Square> squares;
    private static ImageIcon xImg;
    private static ImageIcon oImg;
    private static PieceType currentPiece = PieceType.X;

    public Board() {
    }

    public void initNewBoard()  {

        boardFrame = new JFrame("TicTacToe by JavaForce");
        boardFrame.setSize(TicTacToe.getBoardSize(),TicTacToe.getBoardSize());
        boardFrame.setBackground(TicTacToe.getBoardColor());
        boardFrame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardFrame.setLayout(new GridLayout(3,3, 9, 9));

        squares = new HashMap(9);
        for(int i=1;i<10;i++) {
            Square availSquare = new Square(i);
            squares.put(i,availSquare);
            boardFrame.getContentPane().add(availSquare.getHolder());
            System.out.println(i);
        }

        xImg = createPiece("x");
        oImg = createPiece("o");

        boardFrame.setVisible(true);
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

    public JFrame getBoardFrame() {
        return boardFrame;
    }

    public void setBoardFrame(JFrame boardFrame) {
        this.boardFrame = boardFrame;
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
}