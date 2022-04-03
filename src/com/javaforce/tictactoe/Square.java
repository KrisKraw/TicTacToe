package com.javaforce.tictactoe;

import javax.swing.*;

public class Square {

    private Integer location;
    private PieceType owner;
    private JPanel holder;

    public Square(Integer location) {
        this.location = location;
        owner = PieceType.E;
        holder = getNewHolder();
    }

    public JPanel getNewHolder() {
        holder = new JPanel();
        holder.setName("" + location);
        holder.setSize(TicTacToe.getSquareSize(),TicTacToe.getSquareSize());
        holder.setBackground(TicTacToe.getSquareColor());
        return holder;
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
}