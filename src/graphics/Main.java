/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author ASUS
 */
public class Main {

    public static void main(String[] args) {
        JFrame frame2 = new JFrame();
        Graphics panel = new Graphics(10, 10);
        panel.setBackground(Color.GRAY);
        panel.setSize(500, 500);
        panel.setFocusable(true);
        Thread t = new Thread(panel);
        t.start();
        JButton test = new JButton("Testing");
        JPanel panel2 = new JPanel();
        panel2.add(test);
        frame2.getContentPane().add(panel);
        frame2.getContentPane().add(panel2);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setSize(1000, 500);
        frame2.setLocationRelativeTo(null);
        frame2.setVisible(true);
        frame2.setLayout(new GridLayout(1, 2));

        panel.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent ev) {
                panel.requestFocus();
            }
        });
    }
}
