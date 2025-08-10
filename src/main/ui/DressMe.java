package ui;

import javax.swing.*;

import model.NoPersonException;
import model.Outfit;
import model.Pants;
import model.Clothing;
import model.Person;
import model.Shirt;
import model.Shoes;

import java.awt.*;
import java.util.ArrayList;

// Represents a DressMe class that sets up UI
// TODO: handle how to put clothing onto person img
public class DressMe extends WhisktyleAbstract {

    // EFFECTS: Adds title panel to background panel
    public DressMe() {
        setTitle("Whisktyle - Dress Me");
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
        titlePanel.add(createMenuButton(BUTTON_IMG_DIRECTORY + "save-button.png", "Save"));
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

            case "Save":
                handleSaveButton();
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

    // EFFECTS: creates and returns upper closet panel
    public JPanel createPersonPanel() {
        JPanel personPanel = new JPanel();
        personPanel.setLayout(new BoxLayout(personPanel, BoxLayout.Y_AXIS));
        personPanel.setOpaque(false);
        // personPanel.setBackground(Color.RED);
        personPanel.setMaximumSize(new Dimension(OUTER_CLOSET_WIDTH, OUTER_CLOSET_HEIGHT - 25)); // Half of 575
        personPanel.setPreferredSize(new Dimension(OUTER_CLOSET_WIDTH, OUTER_CLOSET_HEIGHT - 25));

        return personPanel;
    }

    // EFFECTS: sets person image to be person label's icon
    public void setPersonUI(JPanel panel) {
        // JLabel personLabel = new JLabel();

        try {
            ImageIcon personIcon = getCloset().getPerson().getImg();
            OutfitPanel outfitPanel = new OutfitPanel(personIcon);

            if (!getCloset().getShirts().isEmpty()) {
                outfitPanel.setShirt(getCloset().getShirts().get(getCloset().getShirtIndex()).getImg());
            }
            if (!getCloset().getPants().isEmpty()) {
                outfitPanel.setPants(getCloset().getPants().get(getCloset().getPantsIndex()).getImg());
            }
            // personLabel.setIcon(icon);

            outfitPanel.setPreferredSize(new Dimension(INNER_CLOSET_WIDTH, OUTER_CLOSET_HEIGHT - 25));
            outfitPanel.setOpaque(false);

            panel.setLayout(new GridBagLayout());
            panel.add(outfitPanel, new GridBagConstraints());

        } catch (NoPersonException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: Creates, sets up panels for inner closet panel and returns
    public JPanel createClosetInnerPanel() {
        JPanel innerPanel = createInnerComponent(); // inner panel centered in a wrapper
        JPanel wrapper = createWrapper();
        JPanel personPanel = createPersonPanel();
        setPersonUI(personPanel);
        personPanel.setPreferredSize(new Dimension(INNER_CLOSET_WIDTH, OUTER_CLOSET_HEIGHT - 25));
        personPanel.setMaximumSize(new Dimension(INNER_CLOSET_WIDTH, OUTER_CLOSET_HEIGHT - 25));
        personPanel.setMinimumSize(new Dimension(INNER_CLOSET_WIDTH, OUTER_CLOSET_HEIGHT - 25));
        // personPanel.setBackground(Color.RED);
        personPanel.setOpaque(false);

        wrapper.add(personPanel);
        innerPanel.add(wrapper);

        return innerPanel;
    }

    // EFFECTS: confirms whether user wants to save the current outfit
    public void handleSaveButton() {
        int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to save this outfit?",
                "Save Outfit", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            saveOutfitOption();
        }
    }

    // EFFECTS: handles saving outfit to closet
    public void saveOutfitOption() {
        String name = JOptionPane.showInputDialog(this, "Enter a name for this outfit:", "Outfit Name",
                JOptionPane.PLAIN_MESSAGE);

        String category = JOptionPane.showInputDialog(this, "Enter a category for this outfit:", "Outfit Category",
                JOptionPane.PLAIN_MESSAGE);

        if (name != null && !name.trim().isEmpty() && category != null && !category.trim().isEmpty()) {
            Clothing shirt = new Shirt(getCloset().getShirts().get(getCloset().getShirtIndex()).getName(),
                    getCloset().getShirts().get(getCloset().getShirtIndex()).getImg());
            Clothing pants = new Pants(getCloset().getPants().get(getCloset().getPantsIndex()).getName(),
                    getCloset().getPants().get(getCloset().getPantsIndex()).getImg());
            Shoes shoes = new Shoes("");
            Outfit newOutfit = new Outfit(name, shirt, pants, shoes);

            getCloset().getSavedOutfits().getOrDefault(category, new ArrayList<>()).add(newOutfit);

            JOptionPane.showMessageDialog(this,
                    "Outfit \"" + name + "\" to \"" + category + "\" now added to your closet!");
        } else {
            JOptionPane.showMessageDialog(this, "Oufit not added. No name or category entered.", "Cancelled",
                    JOptionPane.WARNING_MESSAGE);
        }

    }

}
