package ui;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Outfit;

// Represents an outfit box, used in Outfits class
public class OutfitBox {
    private JPanel box;
    private Outfits parent;

    private static final int BOX_WIDTH = 130;
    private static final int BOX_HEIGHT = 200;

    private static final int SHIRT_BOX_WIDTH = 120;
    private static final int SHIRT_BOX_HEIGHT = 70;

    private static final int PANTS_BOX_WIDTH = 100;
    private static final int PANTS_BOX_HEIGHT = 80;

    // EFFECTS: constructor for OutfitBox, setting index of outfit and parent
    public OutfitBox(Outfits parent) {
        this.parent = parent;
    }

    // EFFECTS: Creates a single outfit box containing a shirt and pants
    public JPanel createOutfitBox(Outfit outfit, String category) {
        box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
        box.setMinimumSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
        box.setMaximumSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
        // box.setBackground(Color.WHITE);
        box.setOpaque(false);

        JLabel shirtLabel = parent.createScaledLabel(outfit.getShirt().getImg(), SHIRT_BOX_WIDTH, SHIRT_BOX_HEIGHT);
        JLabel pantsLabel = parent.createScaledLabel(outfit.getPants().getImg(), PANTS_BOX_WIDTH, PANTS_BOX_HEIGHT);
        JButton playButton = parent.createInnerMenuButton(WhisktyleAbstract.BUTTON_IMG_DIRECTORY + "play-button.png",
                outfit, category);

        shirtLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pantsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        box.add(shirtLabel);
        box.add(pantsLabel);
        box.add(playButton);

        return box;
    }

}
