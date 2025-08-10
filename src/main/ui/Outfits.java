package ui;

import javax.swing.*;

import model.Clothing;
import model.Outfit;
import model.Pants;
import model.Shirt;
import model.Shoes;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Set;

// Reprsents a Outfits class 
public class Outfits extends WhisktyleAbstract {
    // EFFECTS: Constructor for Oufits, sets title
    public Outfits() {
        setTitle("Whisktyle - Oufits");
    }

    @Override
    // EFFECTS: returns title panel with title image added to it
    public JPanel setTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.add(Box.createHorizontalGlue());
        titlePanel.add(createMenuButton(BUTTON_IMG_DIRECTORY + "return-button.png", "Return"));
        titlePanel.add(Box.createRigidArea(new Dimension(20, 0)));
        titlePanel.add(setTitle());
        titlePanel.add(Box.createRigidArea(new Dimension(20, 0)));
        titlePanel.add(createMenuButton(BUTTON_IMG_DIRECTORY + "category-button.png", "Save"));
        titlePanel.add(Box.createHorizontalGlue());

        return titlePanel;
    }

    // EFFECTS: creates and returns menu buttons
    public JButton createMenuButton(String imgPath, String selection) {
        JButton button = createButton(imgPath, BUTTON_WIDTH, BUTTON_HEIGHT);
        button.addActionListener(e -> handleMenuButton(selection));
        return button;
    }

    // EFFECTS: handles menu button selection
    public void handleMenuButton(String selection) {
        switch (selection) {
            case "Return":
                setNewFrame("Menu");
                break;

            case "Category":
                handleCategory();
                break;
        }
    }

    @Override
    // EFFECTS: sets up UI components of frame
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

    // EFFECTS: sets closet label to closet open image when mouse hovers, close
    // close image when not
    public JPanel setClosetUI() {
        ImageIcon closetImg = createClosetImg();
        JLabel closetLabel = new JLabel(closetImg);
        closetLabel.setBounds(0, 0, OUTER_CLOSET_WIDTH, OUTER_CLOSET_HEIGHT);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(OUTER_CLOSET_WIDTH, OUTER_CLOSET_HEIGHT));
        layeredPane.setLayout(null);

        JPanel innerPanel = createClosetInnerPanel();
        innerPanel.setBounds(0, 0, OUTER_CLOSET_WIDTH, OUTER_CLOSET_HEIGHT);
        innerPanel.setOpaque(false);

        layeredPane.add(closetLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(innerPanel, JLayeredPane.PALETTE_LAYER); // Becomes layered on top

        JPanel closetPanel = new JPanel();
        closetPanel.setOpaque(false);
        closetPanel.add(layeredPane);

        return closetPanel;
    }

    // EFFECTS: creates and returns inner panel component
    public JPanel createInnerComponent() {
        JPanel innerPanel = new JPanel();
        innerPanel.setOpaque(false);
        innerPanel.setPreferredSize(new Dimension(OUTER_CLOSET_WIDTH, OUTER_CLOSET_HEIGHT));
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));

        innerPanel.add(Box.createRigidArea(new Dimension(0, 10))); // top padding
        return innerPanel;
    }

    // EFFECTS: creates and returns a wrapper with the function of centering
    // components inside closet's inner panels
    public JPanel createWrapper() {
        JPanel upperWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        upperWrapper.setOpaque(false);
        return upperWrapper;
    }

    // TODO:
    public void handleCategory() {
        Set<String> categorySet = getCloset().getSavedOutfits().keySet();
        Object[] options = categorySet.toArray();

        if (!categorySet.isEmpty()) {
            int choiceIndex = JOptionPane.showOptionDialog(null, "Choose which category to view:", "Category",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            if (choiceIndex >= 0 && choiceIndex < options.length) {
                String choice = (String) options[choiceIndex];

            }
        }
    }

    // EFFECTS: Creates, sets up panels for inner closet panel and returns
    public JPanel createClosetInnerPanel() {
        JPanel gridPanel = new JPanel();
        gridPanel.setOpaque(false);

        // 0 rows means it will add as many rows as needed
        gridPanel.setLayout(new GridLayout(0, 3, 10, 10)); // 3 columns, 10px gaps

        // Example: add outfits dynamically
        for (int i = 0; i < getCloset().getSavedOutfits().size(); i++) {
            JPanel outfitBox = createOutfitBox(getCloset().getSavedOutfits().get(i));
            gridPanel.add(outfitBox);
        }

        // Put gridPanel inside a scroll pane
        JScrollPane scrollPane = new JScrollPane(gridPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.add(scrollPane, BorderLayout.CENTER);

        return wrapper;
    }

    // EFFECTS: Creates a single outfit box containing a shirt and pants
    private JPanel createOutfitBox(Outfit outfit) {
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setPreferredSize(new Dimension(150, 150));
        box.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        box.setOpaque(true);
        box.setBackground(Color.WHITE);

        JLabel shirtLabel = new JLabel(outfit.getShirt().getImg());
        JLabel pantsLabel = new JLabel(outfit.getPants().getImg());

        shirtLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pantsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        box.add(shirtLabel);
        box.add(Box.createVerticalStrut(5));
        box.add(pantsLabel);

        return box;
    }

    // EFFECTS: creates and returns upper closet panel
    public JPanel createUpperClosetPanel() {
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.Y_AXIS));
        upperPanel.setOpaque(false);
        // upperPanel.setBackground(Color.RED);
        upperPanel.setMaximumSize(new Dimension(OUTER_CLOSET_WIDTH, (OUTER_CLOSET_HEIGHT / 2) - 50)); // Half of 575
        upperPanel.setPreferredSize(new Dimension(OUTER_CLOSET_WIDTH, (OUTER_CLOSET_HEIGHT / 2) - 50));

        return upperPanel;
    }

    // EFFECTS: creates and returns lower closet panel
    public JPanel createLowerClosetPanel() {
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.Y_AXIS));
        lowerPanel.setOpaque(true);
        lowerPanel.setBackground(Color.RED);
        lowerPanel.setMaximumSize(new Dimension(OUTER_CLOSET_WIDTH, (OUTER_CLOSET_HEIGHT / 2) - 50));
        lowerPanel.setPreferredSize(new Dimension(OUTER_CLOSET_WIDTH, (OUTER_CLOSET_HEIGHT / 2) - 50));

        return lowerPanel;
    }

}
