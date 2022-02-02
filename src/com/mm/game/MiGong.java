package com.mm.game;

import com.mm.game.util.R;

import javax.swing.*;
import java.awt.*;

public class MiGong extends JFrame{
    private JPanel panel = new MyPanel();
    public MiGong(){
        setTitle(R.TITLE);
        setSize(R.SCREEN_WIDTH,R.SCREEN_HEIGHT);
        Container con = getContentPane();
        con.add(panel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MiGong();
    }
}
