package com.javaforce.tictactoe;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Square implements MouseListener {

    private Integer location;
    private PieceType owner;
    private JPanel holder;

    public Square(Integer location) {
        this.location = location;
        owner = PieceType.X;
        holder = getNewHolder();
    }

    public JPanel getNewHolder() {
        holder = new JPanel();
        holder.setSize(TicTacToe.getSquareSize(),TicTacToe.getSquareSize());
        holder.setBackground(TicTacToe.getSquareColor());
        holder.addMouseListener(this);
        return holder;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(Board.getCurrentPiece() == PieceType.O) {
            owner = PieceType.X;
            JLabel imageHolder = new JLabel(Board.getxImg());
            imageHolder.setHorizontalAlignment(JLabel.RIGHT);
            imageHolder.setVerticalAlignment(JLabel.CENTER);
            holder.add(imageHolder);
            holder.revalidate();
            System.out.println("MOUSE CLICKED on square: " + location);
            Board.setCurrentPiece(PieceType.X);
        } else {
            owner = PieceType.O;
            JLabel imageHolder = new JLabel(Board.getoImg());
            imageHolder.setHorizontalAlignment(JLabel.RIGHT);
            imageHolder.setVerticalAlignment(JLabel.CENTER);
            holder.add(imageHolder);
            holder.revalidate();
            System.out.println("MOUSE CLICKED on square: " + location);
            Board.setCurrentPiece(PieceType.O);
        }
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public PieceType getOwner() {
        return owner;
    }

    public void setOwner(PieceType owner) {
        this.owner = owner;
    }

    public JPanel getHolder() {
        return holder;
    }

    public void setHolder(JPanel holder) {
        this.holder = holder;
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