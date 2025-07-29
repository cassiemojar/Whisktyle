package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Represents a UI for the main menu of the app
public class Menu extends WhisktyleAbstract {
    // EFFECTS: Constructor for the main menu, initializes all model classes
    public Menu() {
        setTitle("Whisktyle - Menu");
    }

    // EFFECTS: Adds title panel and closet panel to background panel
    @Override
    public void setUI() {
        background.setLayout(new BorderLayout());
        background.add(setTitlePanel(), BorderLayout.NORTH);
        background.add(setClosetPanel(), BorderLayout.SOUTH);
    }

    // EFFECTS: returns closet panel with closet image added to it
    public JPanel setClosetPanel() {
        JPanel closetPanel = new JPanel();
        closetPanel.setOpaque(false);
        closetPanel.setLayout(new BoxLayout(closetPanel, BoxLayout.Y_AXIS));
        closetPanel.add(setClosetUI());
        return closetPanel;
    }

    // EFFECTS: returns closet opened ImageIcon
    public ImageIcon setClosetOpenUI() {
        final ImageIcon closetOpenImg = new ImageIcon(getClass().getResource("img/closet-imgs/closet-opened.jpg"));
        Image closetOpen = closetOpenImg.getImage().getScaledInstance(350, 500,
                Image.SCALE_SMOOTH);

        final ImageIcon finalClosetOpenImg = new ImageIcon(closetOpen);

        return finalClosetOpenImg;
    }

    // EFFECTS: returns closet closed ImageIcon
    public ImageIcon setClosetClosedUI() {
        final ImageIcon closetClosedImg = new ImageIcon(getClass().getResource("img/closet-imgs/closet-closed.jpg"));

        Image closetClosed = closetClosedImg.getImage().getScaledInstance(350, 500,
                Image.SCALE_SMOOTH);

        final ImageIcon finalClosetClosedImg = new ImageIcon(closetClosed);

        return finalClosetClosedImg;
    }

    // EFFECTS: handles which closet image to show whether mouse hovering over
    // closet panel or not, returns it after
    public JLabel handleCloset(JLabel closetLabel, JLayeredPane closetPanel, JPanel buttonsPanel,
            ImageIcon closetClosed,
            ImageIcon closetOpen) {
        closetLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                closetLabel.setIcon(closetOpen);
                buttonsPanel.setVisible(true);
                System.out.println("Open");
            }

            // @Override
            // public void mouseExited(MouseEvent e) {
            // closetLabel.setIcon(closetClosed);
            // buttonsPanel.setVisible(false);
            // }
        });
        return closetLabel;
    }

    public JPanel setButtonsUI() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

        buttonsPanel.setOpaque(false);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(40, 10, 30, 10));

        buttonsPanel.add(createMenuButton(createMenuButtonImg("img/button-imgs/browse-button.png"), "Browse"));
        buttonsPanel.add(createMenuButton(createMenuButtonImg("img/button-imgs/outfits-button.png"), "Outfits"));
        buttonsPanel.add(createMenuButton(createMenuButtonImg("img/button-imgs/save-button.png"), "Save"));
        buttonsPanel.add(createMenuButton(createMenuButtonImg("img/button-imgs/load-button.png"), "Load"));
        buttonsPanel.setVisible(false);

        return buttonsPanel;
    }

    public ImageIcon createMenuButtonImg(String imgPath) {
        ImageIcon buttonIcon = new ImageIcon(getClass().getResource(imgPath));

        // TODO: make constants for width + height
        Image scaledButtonImg = buttonIcon.getImage().getScaledInstance(230, 90, Image.SCALE_SMOOTH);
        ImageIcon scaledButtonIcon = new ImageIcon(scaledButtonImg);

        return scaledButtonIcon;
    }

    public JButton createMenuButton(ImageIcon buttonImg, String stringUI) {
        JButton menuButton = new JButton();
        menuButton.setIcon(buttonImg);
        menuButton.setMaximumSize(new Dimension(240, 100));
        menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        menuButton.setOpaque(false);
        menuButton.setContentAreaFilled(false);

        handleButtonSelection(menuButton, stringUI);

        return menuButton;
    }



    public void handleButtonSelection(JButton menuButton, String stringUI) {
        menuButton.addActionListener(e -> setNewFrame(stringUI));

    }

    public void setNewFrame(String stringUI) {
        Loading loadingUI = new Loading(stringUI);
        dispose();
        loadingUI.setVisible(true);
    }

    // EFFECTS: sets closet label to closet open image when mouse hovers, close
    // close image when not
    public JPanel setClosetUI() {
        ImageIcon closetClosed = setClosetClosedUI();
        ImageIcon closetOpen = setClosetOpenUI();

        JLabel closetLabel = new JLabel(closetClosed);
        closetLabel.setBounds(0, 0, 350, 500);

        JPanel buttonsPanel = setButtonsUI();
        buttonsPanel.setBounds(0, 0, 350, 500);
        buttonsPanel.setOpaque(false);

        // Create layered pane to layer buttons on top of image
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(350, 500));
        layeredPane.add(closetLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(buttonsPanel, JLayeredPane.PALETTE_LAYER); // on top

        handleCloset(closetLabel, layeredPane, buttonsPanel, closetClosed, closetOpen);

        JPanel closetPanel = new JPanel();
        closetPanel.setOpaque(false);
        closetPanel.add(layeredPane);

        return closetPanel;
    }

}
