package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Represents a loading screen for Whisktyle App
public class Loading extends WhisktyleAbstract {
    private List<ImageIcon> loadImgs;
    private int currentIndex;
    private Timer timer;

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

    // EFFECTS: Creates and returns load image as label
    public JLabel setTitle() {
        return switchLoadImg();
    }

    // EFFECTS: Creates load label, handles which loadImg to set through timer
    public JLabel switchLoadImg() {
        setImgList();
        JLabel loadLabel = new JLabel();
        loadLabel.setIcon(loadImgs.get(currentIndex));

        int delay = 600;

        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex++;
                if (currentIndex < loadImgs.size()) {
                    loadLabel.setIcon(loadImgs.get(currentIndex));
                }

                else {
                    timer.stop();
                    Browse browseUI = new Browse();
                    dispose();
                    browseUI.setVisible(true);

                }
            }

        });
        timer.start();
        return loadLabel;
    }

    // MODIFIES: this
    // EFFECTS: initializes image list, adds all loading imgs to it
    public List<ImageIcon> setImgList() {
        loadImgs = new ArrayList<>();
        currentIndex = 0;
        loadImgs.add(createLoadImg("img/loading-imgs/loading-one.png"));
        loadImgs.add(createLoadImg("img/loading-imgs/loading-two.png"));
        loadImgs.add(createLoadImg("img/loading-imgs/loading-three.png"));
        loadImgs.add(createLoadImg("img/loading-imgs/loading-four.png"));
        loadImgs.add(createLoadImg("img/loading-imgs/loading-five.png"));

        return loadImgs;
    }

    // EFFECTS: create and returns image icon and scales it
    public ImageIcon createLoadImg(String imgPath) {
        ImageIcon loadImg = new ImageIcon(getClass().getResource(imgPath));
        Image image = loadImg.getImage().getScaledInstance(350, 200, Image.SCALE_SMOOTH);
        loadImg = new ImageIcon(image);
        return loadImg;
    }
}
