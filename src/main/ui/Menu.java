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
        Image img = new ImageIcon(getClass().getResource("img/background.png")).getImage();
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
        ImageIcon cursorImg = new ImageIcon(getClass().getResource("img/cursor.png"));
        Image scaledCursorImg = cursorImg.getImage().getScaledInstance(100, 150,
                Image.SCALE_SMOOTH);

        Cursor c = toolkit.createCustomCursor(scaledCursorImg, new Point(0, 0), "customCursor");

        setCursor(c);

    }

    // EFFECTS: Adds title panel and closet panel to background panel
    public void setUI() {
        background.setLayout(new BorderLayout());
        background.add(setTitlePanel(), BorderLayout.NORTH);
        background.add(setClosetPanel(), BorderLayout.SOUTH);
    }

    // EFFECTS: Creates and returns title image as label
    public JLabel setTitle() {
        ImageIcon headerImg = new ImageIcon(getClass().getResource("img/whisktyle.png"));
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

    // EFFECTS: returns closet panel with closet image added to it
    public JPanel setClosetPanel() {
        JPanel closetPanel = new JPanel();
        closetPanel.setOpaque(false);
        closetPanel.setLayout(new BoxLayout(closetPanel, BoxLayout.Y_AXIS));
        closetPanel.add(setClosetUI());
        return closetPanel;
    }

    // EFFECTS: returns closet opened ImageIcon
    public ImageIcon setClosetOpenUI() {
        final ImageIcon closetOpenImg = new ImageIcon(getClass().getResource("img/closet-imgs/closet-opened.jpg"));
        Image closetOpen = closetOpenImg.getImage().getScaledInstance(350, 500,
                Image.SCALE_SMOOTH);

        final ImageIcon finalClosetOpenImg = new ImageIcon(closetOpen);

        return finalClosetOpenImg;
    }

    // EFFECTS: returns closet closed ImageIcon
    public ImageIcon setClosetClosedUI() {
        final ImageIcon closetClosedImg = new ImageIcon(getClass().getResource("img/closet-imgs/closet-closed.jpg"));

        Image closetClosed = closetClosedImg.getImage().getScaledInstance(350, 500,
                Image.SCALE_SMOOTH);

        final ImageIcon finalClosetClosedImg = new ImageIcon(closetClosed);

        return finalClosetClosedImg;
    }

    // EFFECTS: handles which closet image to show whether mouse hovering over
    // closet panel or not, returns it after
    public JLabel handleCloset(JLabel closetLabel, JLayeredPane closetPanel, JPanel buttonsPanel,
            ImageIcon closetClosed,
            ImageIcon closetOpen) {
        closetLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                closetLabel.setIcon(closetOpen);
                buttonsPanel.setVisible(true);
                System.out.println("Open");
            }

            // @Override
            // public void mouseExited(MouseEvent e) {
            // closetLabel.setIcon(closetClosed);
            // buttonsPanel.setVisible(false);
            // }
        });
        return closetLabel;
    }

    public JPanel setButtonsUI() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

        buttonsPanel.setOpaque(false);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(40, 10, 30, 10));

        //buttonsPanel.add(Box.createVerticalGlue());

        buttonsPanel.add(createMenuButton(createMenuButtonImg("img/button-imgs/browse-button.png")));
        buttonsPanel.add(createMenuButton(createMenuButtonImg("img/button-imgs/outfits-button.png")));
        buttonsPanel.add(createMenuButton(createMenuButtonImg("img/button-imgs/save-button.png")));
        buttonsPanel.add(createMenuButton(createMenuButtonImg("img/button-imgs/load-button.png")));
        
        //buttonsPanel.add(createMenuButton(createMenuButtonImg("img/browse.png")));
        //buttonsPanel.add(createMenuButton(createMenuButtonImg("img/browse.png")));
        buttonsPanel.setVisible(false);

        return buttonsPanel;
    }

    public ImageIcon createMenuButtonImg(String imgPath) {
        ImageIcon buttonIcon = new ImageIcon(getClass().getResource(imgPath));

        // TODO: make constants for width + height
        Image scaledButtonImg = buttonIcon.getImage().getScaledInstance(230, 90, Image.SCALE_SMOOTH);
        ImageIcon scaledButtonIcon = new ImageIcon(scaledButtonImg);

        return scaledButtonIcon;
    }

    public JButton createMenuButton(ImageIcon buttonImg) {
        JButton menuButton = new JButton();
        menuButton.setIcon(buttonImg);
        menuButton.setMaximumSize(new Dimension(240, 100));
        menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        menuButton.setOpaque(false);
        menuButton.setContentAreaFilled(false);
        return menuButton;
    }

    // EFFECTS: sets closet label to closet open image when mouse hovers, close
    // close image when not
    public JPanel setClosetUI() {
        ImageIcon closetClosed = setClosetClosedUI();
        ImageIcon closetOpen = setClosetOpenUI();

        JLabel closetLabel = new JLabel(closetClosed);
        closetLabel.setBounds(0, 0, 350, 500);

        JPanel buttonsPanel = setButtonsUI();
        buttonsPanel.setBounds(0, 0, 350, 500);
        buttonsPanel.setOpaque(false);

        // Create layered pane to layer buttons on top of image
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(350, 500));
        layeredPane.add(closetLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(buttonsPanel, JLayeredPane.PALETTE_LAYER); // on top

        handleCloset(closetLabel, layeredPane, buttonsPanel, closetClosed, closetOpen);

        JPanel closetPanel = new JPanel();
        closetPanel.setOpaque(false);
        closetPanel.add(layeredPane);

        return closetPanel;
    }

}
