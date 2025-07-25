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
        closet = new Closet();
        setupFrame();

    }

    // EFFECTS: Makes menu screen visible
    public void setupFrame() {
        setTitle("Cher's Closet");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        // setSize(1000, 500);
        setResizable(true);
        setLayout(new BorderLayout());

        setBackgroundImage();

        setVisible(true);

    }

    // EFFECTS: Creates background image icon then paints it to the entire screen
    public void setBackgroundImage() {
        setContentPane(new JPanel() {
            private Image bg = new ImageIcon(getClass().getResource("img/background.png")).getImage();

            // EFFECTS: Overrides the paintComponent so that it can paint the backgground
            // image
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Paints back
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this); // Draws image

            }

        });

        setUI();

        setVisible(true);
    }

    public void setUI() {
        JPanel panel = new JPanel();
        ImageIcon headerImg = new ImageIcon(getClass().getResource("img/whisktyle.png"));
        Image image = headerImg.getImage().getScaledInstance(456, 134, Image.SCALE_SMOOTH);

        headerImg = new ImageIcon(image);
        JLabel header = new JLabel(headerImg);

        header.setHorizontalAlignment(JLabel.CENTER);
        header.setVerticalAlignment(JLabel.TOP);

        panel.add(header);
        add(panel);
    }

}
