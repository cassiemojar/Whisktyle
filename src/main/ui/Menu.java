package ui;

import javax.swing.*;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.*;

// Represents a UI for the main menu of the app
public class Menu extends WhisktyleAbstract {
    private static final int CLOSET_WIDTH = 350;
    private static final int CLOSET_HEIGHT = 500;

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
        return createImgIcon(CLOSET_IMG_DIRECTORY + "closet-opened.jpg", CLOSET_WIDTH, CLOSET_HEIGHT);
    }

    // EFFECTS: returns closet closed ImageIcon
    public ImageIcon setClosetClosedUI() {
        return createImgIcon(CLOSET_IMG_DIRECTORY + "closet-closed.jpg", CLOSET_WIDTH, CLOSET_HEIGHT);
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
            }
        });
        return closetLabel;
    }

    // EFFECTS: sets and returns button panel, adding the JButtons to the panel
    public JPanel setButtonsUI() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

        buttonsPanel.setOpaque(false);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(40, 10, 30, 10));

        buttonsPanel.add(createMenuButton(BUTTON_IMG_DIRECTORY + "browse-button.png", "Browse"));
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonsPanel.add(createMenuButton(BUTTON_IMG_DIRECTORY + "outfits-button.png", "Outfits"));
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonsPanel.add(createMenuButton(BUTTON_IMG_DIRECTORY + "save-button.png", "Save"));
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonsPanel.add(createMenuButton(BUTTON_IMG_DIRECTORY + "load-button.png", "Load"));
        buttonsPanel.setVisible(false);

        return buttonsPanel;
    }

    // EFFECTS: Sets size, alignments of button and returns button
    public JButton createMenuButton(String imgPath, String stringUI) {
        JButton menuButton = createButton(imgPath, BUTTON_WIDTH, BUTTON_HEIGHT);

        handleButtonSelection(menuButton, stringUI);
        return menuButton;
    }

    // EFFECTS: handles when button is clicked
    public void handleButtonSelection(JButton menuButton, String stringUI) {
        switch (stringUI) {
            case "Save":
                menuButton.addActionListener(e -> saveProgress());
                break;
            case "Load":
                menuButton.addActionListener(e -> loadProgress());
                break;
            default:
                menuButton.addActionListener(e -> setNewFrame(stringUI));
                break;
        }

    }

 

    // EFFECTS: sets closet label to closet open image when mouse hovers, close
    // close image when not
    public JPanel setClosetUI() {
        ImageIcon closetClosed = setClosetClosedUI();
        ImageIcon closetOpen = setClosetOpenUI();

        JLabel closetLabel = new JLabel(closetClosed);
        closetLabel.setBounds(0, 0, CLOSET_WIDTH, CLOSET_HEIGHT);

        JPanel buttonsPanel = setButtonsUI();
        buttonsPanel.setBounds(0, 0, CLOSET_WIDTH, CLOSET_HEIGHT);
        buttonsPanel.setOpaque(false);

        // Create layered pane to layer buttons on top of image
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(CLOSET_WIDTH, CLOSET_HEIGHT));
        layeredPane.add(closetLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(buttonsPanel, JLayeredPane.PALETTE_LAYER); // on top

        handleCloset(closetLabel, layeredPane, buttonsPanel, closetClosed, closetOpen);

        JPanel closetPanel = new JPanel();
        closetPanel.setOpaque(false);
        closetPanel.add(layeredPane);

        return closetPanel;
    }

}
