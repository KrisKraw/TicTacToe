package com.javaforce.tictactoe;

import javax.swing.*;

public enum PieceType {
    X(Game.getXImage()),
    O(Game.getXImage()),
    E(null);

    public final ImageIcon imageIcon;

    private PieceType(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }
}