package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import model.Closet;

// Represents a UI for the main menu of the app
public class Menu extends JFrame {
    private Closet closet;

    // EFFECTS: Constructor for the main menu, initializes all model classes
    public Menu() {
        super("Cher's Closet");
        setupFrame();

    }

    // EFFECTS: Makes menu screen visible
    public void setupFrame() {
        setTitle("Cher's Closet");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setSize(400, 400);
        setResizable(false);
        setLayout(new BorderLayout());
        setBackgroundImage();
        setVisible(true);

    }

    public void setBackgroundImage() {
        setContentPane(new JPanel() {
            private Image bg = new ImageIcon(getClass().getResource("images/background.png")).getImage();

            // EFFECTS: Overrides the paintComponent so that it can paint the backgground
            // image
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Paints back
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this); // Draws image
            }
        });
    }
}
