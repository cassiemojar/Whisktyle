package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Closet;

public abstract class WhiskyleAbstract extends JFrame {
    protected Closet closet;
    protected BackgroundImage background;

    public WhiskyleAbstract() {
        super("Whisktyle");
        closet = new Closet();
        setupFrame();

    }

    // EFFECTS: Makes menu screen visible
    public void setupFrame() {
        setTitle("Whisktyle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        setCursorIcon();
        setContentPane(setBackgroundImage());
        setUI();
        setVisible(true);

    }

    // EFFECTS: returns instance of BackgroundImage
    public BackgroundImage setBackgroundImage() {
        Image img = new ImageIcon(getClass().getResource("img/default-imgs/background.png")).getImage();
        background = new BackgroundImage(img);
        return background;
    }

    // Represents a class to override paint compenent to allow it to draw background
    // image
    class BackgroundImage extends JPanel {
        private Image bg;

        // EFFECTS: Constructor for BackgroundImage
        public BackgroundImage(Image img) {
            this.bg = img;
            setLayout(new BorderLayout());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); // Paints back
            g.setColor(Color.RED);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.drawImage(bg, 0, 0, getWidth(), getHeight(), this); // Draws image

        }
    }

    // EFFECTS: Gets cursor img, scales it, then sets as cursor
    public void setCursorIcon() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        ImageIcon cursorImg = new ImageIcon(getClass().getResource("img/default-imgs/cursor.png"));
        Image scaledCursorImg = cursorImg.getImage().getScaledInstance(100, 150,
                Image.SCALE_SMOOTH);

        Cursor c = toolkit.createCustomCursor(scaledCursorImg, new Point(0, 0), "customCursor");

        setCursor(c);

    }

    // EFFECTS: Adds title panel and closet panel to background panel
    public void setUI() {
        background.setLayout(new BorderLayout());
        background.add(setTitlePanel(), BorderLayout.NORTH);
    }

    // EFFECTS: Creates and returns title image as label
    public JLabel setTitle() {
        ImageIcon headerImg = new ImageIcon(getClass().getResource("img/default-imgs/whisktyle.png"));
        Image image = headerImg.getImage().getScaledInstance(500, 150, Image.SCALE_SMOOTH);

        headerImg = new ImageIcon(image);
        JLabel header = new JLabel(headerImg);
        return header;
    }

    // EFFECTS: returns title panel with title image added to it
    public JPanel setTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        titlePanel.add(setTitle());
        return titlePanel;
    }
}
