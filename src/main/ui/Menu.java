package ui;

import javax.swing.JFrame;

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
        setResizable(false);
        setVisible(true);
    }
}
