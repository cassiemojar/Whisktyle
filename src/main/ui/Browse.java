package ui;

import javax.swing.*;

import model.Shirt;

import java.awt.*;
import java.awt.event.*;

// Represents a Browse class 
// TODO: Refactor, clean up code

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
        // titlePanel.add(createReturnButton(BUTTON_IMG_DIRECTORY + "return.png"));
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

    // EFFECTS: creates and returns menu buttons
    public JButton createReturnButton(String imgPath) {
        JButton returnButton = new JButton();
        ImageIcon returnIcon = createReturnButtonImg(imgPath);
        returnButton.setIcon(returnIcon);
        returnButton.setPreferredSize(new Dimension(returnIcon.getIconWidth(), returnIcon.getIconHeight()));
        returnButton.setMaximumSize(new Dimension(returnIcon.getIconWidth(), returnIcon.getIconHeight()));

        returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        returnButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        returnButton.setOpaque(false);
        returnButton.setContentAreaFilled(false);

        return returnButton;
    }

    // EFFECTS: creates and returns inner menu buttons
    public JButton createInnerMenuButton(String imgPath) {
        JButton innerButton = new JButton();
        ImageIcon innerButtonIcon = createInnerButtonImg(imgPath);
        innerButton.setIcon(innerButtonIcon);
        innerButton.setPreferredSize(new Dimension(innerButtonIcon.getIconWidth(), innerButtonIcon.getIconHeight()));
        innerButton.setMaximumSize(new Dimension(innerButtonIcon.getIconWidth(), innerButtonIcon.getIconHeight()));

        innerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        innerButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        innerButton.setOpaque(false);
        innerButton.setContentAreaFilled(false);

        return innerButton;
    }

    // EFFECTS: creates and returns play menu button
    public JButton createPlayButton(String imgPath) {
        JButton playButton = new JButton();
        ImageIcon playButtonIcon = createInnerButtonImg(imgPath);
        playButton.setIcon(playButtonIcon);
        playButton.setPreferredSize(new Dimension(playButtonIcon.getIconWidth(), playButtonIcon.getIconHeight()));
        playButton.setMaximumSize(new Dimension(playButtonIcon.getIconWidth(), playButtonIcon.getIconHeight()));

        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        playButton.setOpaque(false);
        playButton.setContentAreaFilled(false);

        return playButton;
    }

    // TODO: Make create img abstract method, reduce code duplication
    public ImageIcon createReturnButtonImg(String imgPath) {
        ImageIcon buttonIcon = new ImageIcon(getClass().getResource(imgPath));

        Image scaledButtonImg = buttonIcon.getImage().getScaledInstance(50, 50,
                Image.SCALE_SMOOTH);
        ImageIcon scaledButtonIcon = new ImageIcon(scaledButtonImg);

        return scaledButtonIcon;
    }

    public ImageIcon createInnerButtonImg(String imgPath) {
        ImageIcon buttonIcon = new ImageIcon(getClass().getResource(imgPath));

        Image scaledButtonImg = buttonIcon.getImage().getScaledInstance(50, 35,
                Image.SCALE_SMOOTH);
        ImageIcon scaledButtonIcon = new ImageIcon(scaledButtonImg);

        return scaledButtonIcon;
    }

    public ImageIcon createPlayButtonImg(String imgPath) {
        ImageIcon buttonIcon = new ImageIcon(getClass().getResource(imgPath));

        Image scaledButtonImg = buttonIcon.getImage().getScaledInstance(90, 35,
                Image.SCALE_SMOOTH);
        ImageIcon scaledButtonIcon = new ImageIcon(scaledButtonImg);

        return scaledButtonIcon;
    }

    public ImageIcon createMenuButtonImg(String imgPath) {
        ImageIcon buttonIcon = new ImageIcon(getClass().getResource(imgPath));

        Image scaledButtonImg = buttonIcon.getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT,
                Image.SCALE_SMOOTH);
        ImageIcon scaledButtonIcon = new ImageIcon(scaledButtonImg);

        return scaledButtonIcon;
    }

    // EFFECTS: Adds title panel and closet panel to background panel
    @Override
    public void setUI() {
        background.setLayout(new BorderLayout());
        background.add(setTitlePanel(), BorderLayout.PAGE_START);
        background.add(setClosetPanel(), BorderLayout.CENTER);
    }

    // EFFECTS: returns closet panel with closet image added to it
    public JPanel setClosetPanel() {
        JPanel closetPanel = new JPanel();
        closetPanel.setOpaque(false);
        // closetPanel.setBackground(Color.RED);
        closetPanel.setLayout(new BoxLayout(closetPanel, BoxLayout.Y_AXIS));
        closetPanel.add(setClosetUI());
        return closetPanel;
    }

    // EFFECTS: returns closet closed ImageIcon
    // TODO: in abstract, make this an abstract method too much code duplication
    public ImageIcon createClosetImg() {
        final ImageIcon closetIcon = new ImageIcon(
                getClass().getResource(CLOSET_IMG_DIRECTORY + "browse-closet.png"));

        Image closetImg = closetIcon.getImage().getScaledInstance(475, 575,
                Image.SCALE_SMOOTH);

        final ImageIcon finalClosetIcon = new ImageIcon(closetImg);

        return finalClosetIcon;
    }

    public JPanel setClosetButtonsUI() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

        buttonsPanel.setOpaque(false);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(40, 10, 30, 10));

        return buttonsPanel;
    }

    // EFFECTS: sets closet label to closet open image when mouse hovers, close
    // close image when not
    public JPanel setClosetUI() {
        ImageIcon closetImg = createClosetImg();
        JLabel closetLabel = new JLabel(closetImg);
        closetLabel.setBounds(0, 0, 475, 575);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(475, 575));
        layeredPane.setLayout(null);

        JPanel innerPanel = createClosetInnerPanel();
        innerPanel.setBounds(0, 0, 475, 575);
        innerPanel.setOpaque(false); // keep this transparent

        // Add background image to base layer
        layeredPane.add(closetLabel, JLayeredPane.DEFAULT_LAYER);

        // Add interactive panels on top
        layeredPane.add(innerPanel, JLayeredPane.PALETTE_LAYER);

        JPanel closetPanel = new JPanel();
        closetPanel.setOpaque(false);
        closetPanel.add(layeredPane);

        return closetPanel;
    }

    public JPanel createClosetInnerPanel() {
        JPanel innerPanel = new JPanel();
        innerPanel.setOpaque(false);
        innerPanel.setPreferredSize(new Dimension(475, 575));
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));

        innerPanel.add(Box.createRigidArea(new Dimension(0, 10))); // top padding

        // Upper panel (centered in a wrapper)
        JPanel upperWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        upperWrapper.setOpaque(false);

        JPanel upperPanel = createUpperClosetPanel();
        upperPanel.setPreferredSize(new Dimension(450, 288));
        //upperPanel.setBackground(Color.RED);
        upperPanel.setOpaque(false);

        upperWrapper.add(upperPanel);
        innerPanel.add(upperWrapper);

        // Upper button
        JPanel upperButtonPanel = new JPanel();
        upperButtonPanel.setLayout(new BoxLayout(upperButtonPanel, BoxLayout.X_AXIS));

        upperButtonPanel.add(createInnerMenuButton(BUTTON_IMG_DIRECTORY + "left-button.png"));
        upperButtonPanel.add(createPlayButton(BUTTON_IMG_DIRECTORY + "play-button.png"));
        upperButtonPanel.add(createInnerMenuButton(BUTTON_IMG_DIRECTORY + "right-button.png"));

        innerPanel.add(upperButtonPanel);


        // Lower panel (centered in a wrapper)
        JPanel lowerWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        lowerWrapper.setOpaque(false);

        JPanel lowerPanel = createLowerClosetPanel();
        lowerPanel.setPreferredSize(new Dimension(450, 288));
        //lowerPanel.setBackground(Color.RED);
        lowerPanel.setOpaque(false);

    
        lowerWrapper.add(lowerPanel);
        innerPanel.add(lowerWrapper);

        // Lower button
        JPanel lowerButtonPanel = new JPanel();
        lowerButtonPanel.setLayout(new BoxLayout(lowerButtonPanel, BoxLayout.X_AXIS));
        lowerButtonPanel.add(createInnerMenuButton(BUTTON_IMG_DIRECTORY + "left-button.png"));
        lowerButtonPanel.add(createPlayButton(BUTTON_IMG_DIRECTORY + "play-button.png"));
        lowerButtonPanel.add(createInnerMenuButton(BUTTON_IMG_DIRECTORY + "right-button.png"));
        
        innerPanel.add(lowerButtonPanel);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 7))); 

        return innerPanel;
    }

    public JPanel createUpperClosetPanel() {
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.Y_AXIS));
        upperPanel.setOpaque(false);
        //upperPanel.setBackground(Color.RED);
        upperPanel.setMaximumSize(new Dimension(475, 288)); // Half of 575
        upperPanel.setPreferredSize(new Dimension(475, 288));
        // TODO: create constants for width + height

        JLabel upperLabel = new JLabel(createInnerClosetImg());
        upperLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton upperButton = new JButton("Upper Button");
        upperButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        upperButton.setMaximumSize(new Dimension(120, 30)); // or whatever fits

        // upperPanel.add(upperLabel);
        // upperPanel.add(Box.createRigidArea(new Dimension(0, 5))); // spacing
        // upperPanel.add(upperButton);

        return upperPanel;
    }

    // TODO: Implement
    public JPanel createLowerClosetPanel() {
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.Y_AXIS));
        upperPanel.setOpaque(false);
        //upperPanel.setBackground(Color.RED);
        upperPanel.setMaximumSize(new Dimension(475, 288)); // Half of 575
        upperPanel.setPreferredSize(new Dimension(475, 288));
        // TODO: create constants for width + height

        JLabel upperLabel = new JLabel(createInnerClosetImg());
        upperLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // upperPanel.add(upperLabel);
        // upperPanel.add(Box.createRigidArea(new Dimension(0, 5))); // spacing
        // upperPanel.add(upperButton);

        return upperPanel;
    }

    public ImageIcon createInnerClosetImg() {
        final ImageIcon closetIcon = new ImageIcon(
                getClass().getResource(CLOSET_IMG_DIRECTORY + "inner-closet.png"));

        Image closetImg = closetIcon.getImage().getScaledInstance(475, 288,
                Image.SCALE_SMOOTH);

        final ImageIcon finalClosetIcon = new ImageIcon(closetImg);

        return finalClosetIcon;
    }

    public void setInnerClosetButtons() {

    }

    public void setInnerClosetImg(){

    }
}
