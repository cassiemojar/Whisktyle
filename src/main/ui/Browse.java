package ui;

import javax.swing.*;

import model.Shirt;

import java.awt.*;
import java.awt.event.*;

// Reprsents a Browse class 
public class Browse extends WhisktyleAbstract {
    // EFFECTS: Constructor for Browse, sets title
    public Browse() {
        setTitle("Whisktyle - Browse");
    }

    @Override
    // EFFECTS: returns title panel with title image added to it
    public JPanel setTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.add(Box.createHorizontalGlue());
        titlePanel.add(createMenuButton(BUTTON_IMG_DIRECTORY + "add-button.png"));
        titlePanel.add(Box.createRigidArea(new Dimension(20, 0)));
        titlePanel.add(setTitle());
        titlePanel.add(Box.createRigidArea(new Dimension(20, 0)));
        titlePanel.add(createMenuButton(BUTTON_IMG_DIRECTORY + "dress-me-button.png"));
        titlePanel.add(Box.createHorizontalGlue());

        return titlePanel;
    }

    // EFFECTS: creates and returns menu buttons
    public JButton createMenuButton(String imgPath) {
        JButton menuButton = new JButton();
        ImageIcon menuIcon = createMenuButtonImg(imgPath);
        menuButton.setIcon(menuIcon);
        menuButton.setPreferredSize(new Dimension(menuIcon.getIconWidth(), menuIcon.getIconHeight()));
        menuButton.setMaximumSize(new Dimension(menuIcon.getIconWidth(), menuIcon.getIconHeight()));

        menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        menuButton.setOpaque(false);
        menuButton.setContentAreaFilled(false);

        return menuButton;
    }

    public ImageIcon createMenuButtonImg(String imgPath) {
        ImageIcon buttonIcon = new ImageIcon(getClass().getResource(imgPath));

        Image scaledButtonImg = buttonIcon.getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT,
                Image.SCALE_SMOOTH);
        ImageIcon scaledButtonIcon = new ImageIcon(scaledButtonImg);

        return scaledButtonIcon;
    }

}
