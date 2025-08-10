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
    private JPanel innerPanel;

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
        titlePanel.add(createMenuButton(BUTTON_IMG_DIRECTORY + "category-button.png", "Category"));
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

        // Empty inner panel at first
        innerPanel = new JPanel();
        innerPanel.setBounds(0, 0, OUTER_CLOSET_WIDTH, OUTER_CLOSET_HEIGHT);
        innerPanel.setOpaque(false);

        layeredPane.add(closetLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(innerPanel, JLayeredPane.PALETTE_LAYER);

        JPanel closetPanel = new JPanel();
        closetPanel.setOpaque(false);
        closetPanel.add(layeredPane);

        return closetPanel;
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
                showOutfitsForCategory(choice);

            }
        } else {
            JOptionPane.showMessageDialog(this, "You haven't saved an outfit yet! Please browse your clothes and save an outfit.", "Cancelled",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    // EFFECTS: Creates, sets up panels for inner closet panel and returns
    private void showOutfitsForCategory(String category) {
        innerPanel.removeAll(); // clear old outfits

        JPanel gridPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        gridPanel.setOpaque(false);

        for (Outfit outfit : getCloset().getSavedOutfits().get(category)) {
            gridPanel.add(createOutfitBox(outfit));
        }

        JScrollPane scrollPane = new JScrollPane(gridPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        innerPanel.setLayout(new BorderLayout());
        innerPanel.add(scrollPane, BorderLayout.CENTER);

        innerPanel.revalidate();
        innerPanel.repaint();
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

}
