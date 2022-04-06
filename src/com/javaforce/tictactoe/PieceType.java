package com.javaforce.tictactoe;

import javax.swing.*;

public enum PieceType {
    X(Game.getLargeXImage(),Game.getMediumXImage(),Game.getSmallXImage()),
    O(Game.getLargeOImage(),Game.getMediumOImage(),Game.getSmallOImage()),
    E(Game.getLargeOImage(),Game.getMediumOImage(),Game.getSmallOImage());

    public final ImageIcon largeImage;
    public final ImageIcon mediumImage;
    public final ImageIcon smallImage;

    private PieceType(ImageIcon largeImage, ImageIcon mediumImage, ImageIcon smallImage) {
        this.largeImage = largeImage;
        this.mediumImage = mediumImage;
        this.smallImage = smallImage;
    }

    public ImageIcon getLargeImage() {
        return largeImage;
    }

    public ImageIcon getMediumImage() {
        return mediumImage;
    }

    public ImageIcon getSmallImage() {
        return smallImage;
    }
}