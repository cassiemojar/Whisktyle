package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Represents a loading screen for Whisktyle App
public class Loading extends WhisktyleAbstract {
    private List<ImageIcon> loadImgs;
    private int currentIndex;
    private Timer timer;
    private String stringUI;

    // EFFECTS: Constructor for Loading, sets title
    public Loading(String stringUI) {
        setTitle("Whisktyle - Loading");
        this.stringUI = stringUI;
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

    // EFFECTS: Creates and returns load image as label
    public JLabel setTitle() {
        return switchLoadImg();
    }

    // EFFECTS: Creates load label, handles which loadImg to set through timer
    public JLabel switchLoadImg() {
        setImgList();
        JLabel loadLabel = new JLabel();
        loadLabel.setIcon(loadImgs.get(currentIndex));

        int delay = 450;

        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex++;
                if (currentIndex < loadImgs.size()) {
                    loadLabel.setIcon(loadImgs.get(currentIndex));
                } else {
                    timer.stop();
                    handleViewNextFrame();

                }
            }

        });
        timer.start();
        return loadLabel;
    }

    // EFFECTS: handles which UI frame to show next, closes Loading frame
    @SuppressWarnings("methodlength")
    public void handleViewNextFrame() {
        switch (stringUI) {
            case "Menu":
                Menu menuUI = new Menu();
                menuUI.setVisible(true);
                break;
            case "Browse":
                Browse browseUI = new Browse();
                browseUI.setVisible(true);
                break;

            case "Outfits":
                Outfits outfitsUI = new Outfits();
                outfitsUI.setVisible(true);
                break;

            case "Dress Me":
                DressMe dressMeUI = new DressMe();
                dressMeUI.setVisible(true);
                break;
        }

        dispose();

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
