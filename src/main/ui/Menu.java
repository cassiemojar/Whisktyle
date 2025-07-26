package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import model.Closet;

// Represents a UI for the main menu of the app
public class Menu extends JFrame {
    private Closet closet;
    private BackgroundImage background;

    // EFFECTS: Constructor for the main menu, initializes all model classes
    public Menu() {
        super("Cher's Closet");
        closet = new Closet();
        setupFrame();

    }

    // EFFECTS: Makes menu screen visible
    public void setupFrame() {
        setTitle("Whisktyle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        // setSize(1000, 500);
        setResizable(true);
        // setLayout(new BorderLayout());

        Image img = new ImageIcon(getClass().getResource("img/background.png")).getImage();

        background = new BackgroundImage(img);
        setContentPane(background);

        setUI();

        setVisible(true);

    }

    class BackgroundImage extends JPanel {
        private Image bg;

        public BackgroundImage(Image img) {
            this.bg = img;
            setLayout(new BorderLayout());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); // Paints back
            g.setColor(Color.RED);
            g.fillRect(0, 0, getWidth(), getHeight());
            if (bg != null) {
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this); // Draws image
            }

        }
    }

    // EFFECTS: Creates background image icon then paints it to the entire screen
    public void setBackgroundImageTest() {
        setLayout(new BorderLayout());

        setContentPane(new JPanel() {
            private Image bg = new ImageIcon(getClass().getResource("img/background.png")).getImage();

            // EFFECTS: Overrides the paintComponent so that it can paint the backgground
            // image
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Paints back
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this); // Draws image

            }

        });
    }

    public void setUI() {
        JPanel boxLayout = new JPanel();
        boxLayout.setOpaque(false);

        boxLayout.setLayout(new BoxLayout(boxLayout, BoxLayout.Y_AXIS));

        JPanel panelTitle = new JPanel();
        panelTitle.setOpaque(false);

        JPanel panelCloset = new JPanel();
        panelCloset.setOpaque(false);

        panelTitle.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelCloset.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        panelTitle.add(setTitle(), BorderLayout.NORTH);
        panelCloset.add(setClosetUI(), BorderLayout.SOUTH);

        boxLayout.add(panelTitle);
        boxLayout.add(panelCloset);

        background.setLayout(new BorderLayout());

        background.add(boxLayout);
    }

    public JLabel setTitle() {
        ImageIcon headerImg = new ImageIcon(getClass().getResource("img/whisktyle.png"));
        Image image = headerImg.getImage().getScaledInstance(500, 134, Image.SCALE_SMOOTH);

        headerImg = new ImageIcon(image);
        JLabel header = new JLabel(headerImg);

        // header.setHorizontalAlignment(JLabel.CENTER);
        // header.setVerticalAlignment(JLabel.BOTTOM);
        return header;
    }

    public JLabel setClosetUI() {
        final ImageIcon closetClosedImg = new ImageIcon(getClass().getResource("img/closet-closed.jpg"));
        final ImageIcon closetOpenImg = new ImageIcon(getClass().getResource("img/closet-opened.jpg"));

        Image closetClosed = closetClosedImg.getImage().getScaledInstance(300, 438,
                Image.SCALE_SMOOTH);
        Image closetOpen = closetOpenImg.getImage().getScaledInstance(300, 438,
                Image.SCALE_SMOOTH);

        final ImageIcon finalClosetClosedImg = new ImageIcon(closetClosed);
        final ImageIcon finalClosetOpenImg = new ImageIcon(closetOpen);

        JLabel closetLabel = new JLabel(finalClosetClosedImg);

        closetLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                closetLabel.setIcon(finalClosetOpenImg);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                closetLabel.setIcon(finalClosetClosedImg);
            }
        });

        return closetLabel;
    }

}
