package com.javaforce.tictactoe;

import java.awt.*;

public class SwingHelpers {

    public static Component getComponentByName(String what, Container from) {
        for (Component component : from.getComponents()) {
            if (null != component.getName() && component.getName().equals(what)) {
                return component;
            }
        }
        return null;
    }
}
