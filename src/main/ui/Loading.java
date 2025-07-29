package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Represents a loading screen for Whisktyle App
public class Loading extends WhisktyleAbstract {

    // EFFECTS: Constructor for Loading, sets title
    public Loading() {
        setTitle("Whisktyle - Loading");
    }

    // EFFECTS: Adds title panel to background panel
    @Override
    public void setUI() {
        background.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;

        background.add(setTitlePanel(), constraints);
    }

    // EFFECTS: returns instance of BackgroundImage
    @Override
    public BackgroundImage setBackgroundImage() {
        Image img = new ImageIcon(getClass().getResource("img/default-imgs/background.png")).getImage();
        background = new BackgroundImage(img);
        return background;
    }

    // EFFECTS: Creates and returns title image as label
    public JLabel setTitle() {
        ImageIcon headerImg = new ImageIcon(getClass().getResource("img/loading-imgs/loading-one.png"));
        Image image = headerImg.getImage().getScaledInstance(350, 200, Image.SCALE_SMOOTH);

        headerImg = new ImageIcon(image);
        JLabel header = new JLabel(headerImg);
        return header;
    }
}
