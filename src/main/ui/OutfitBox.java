package ui;

import java.awt.Color;
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
    private int index;
    private Outfits parent;

    // EFFECTS: constructor for OutfitBox, setting index of outfit and parent
    public OutfitBox(int index, Outfits parent) {
        this.index = index;
        this.parent = parent;
    }

    // EFFECTS: Creates a single outfit box containing a shirt and pants
    public JPanel createOutfitBox(Outfit outfit, String category) {
        box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setPreferredSize(new Dimension(130, 200));
        box.setMinimumSize(new Dimension(130, 200));
        box.setMaximumSize(new Dimension(130, 200));
        box.setBackground(Color.WHITE);

        JLabel shirtLabel = parent.createScaledLabel(outfit.getShirt().getImg(), 120, 70);
        JLabel pantsLabel = parent.createScaledLabel(outfit.getPants().getImg(), 100, 80);
        JButton playButton = parent.createInnerMenuButton(WhisktyleAbstract.BUTTON_IMG_DIRECTORY + "play-button.png", outfit, category);

        shirtLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pantsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        box.add(shirtLabel);
        box.add(pantsLabel);
        box.add(playButton);

        return box;
    }

    public int getIndex() {
        return index;
    }

}
