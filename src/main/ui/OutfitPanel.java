package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.*;

// Represents OutfitPanel class to draw the shirt and pants over the person
public class OutfitPanel extends JPanel {
    private Image shirtImg;
    private Image pantsImg;
    private Image personImg;

    private static final int HEAD_DISTANCE = -55;
    private static final int LEG_DISTANCE = 100;

    // EFFECTS: constructor for OutfitPanel
    public OutfitPanel(ImageIcon personImg) {
        this.personImg = personImg.getImage();
        setOpaque(false);
    }

    // EFFECTS: converts shirt img icon to img, and as shirtimg
    public void setShirt(ImageIcon shirtImg) {
        this.shirtImg = shirtImg.getImage();
        repaint();
    }

    // EFFECTS: converts pants img icon to img, and as pantsimg
    public void setPants(ImageIcon pantsImg) {
        this.pantsImg = pantsImg.getImage();
        repaint();
    }

    // EFFECTS: override paint compenent to allow it to draw both shirt + pants
    // image
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        if (personImg != null) {
            int x = (panelWidth - personImg.getWidth(this)) / 2; // averages so it's in middle
            int y = (panelHeight - personImg.getHeight(this)) / 2; 
            g.drawImage(personImg, x, y, this);
        }

        if (pantsImg != null) {
            int x = (panelWidth - pantsImg.getWidth(this)) / 2;
            int y = ((panelHeight - pantsImg.getHeight(this)) / 2) + LEG_DISTANCE;
            g.drawImage(pantsImg, x, y, this);
        }

        if (shirtImg != null) {
            int x = (panelWidth - shirtImg.getWidth(this)) / 2;
            int y = ((panelHeight - shirtImg.getHeight(this)) / 2) + HEAD_DISTANCE;
            g.drawImage(shirtImg, x, y, this);
        }
    }

}
