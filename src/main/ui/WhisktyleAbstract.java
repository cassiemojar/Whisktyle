package ui;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import model.Closet;
import model.Clothing;
import model.NoPersonException;
import model.Outfit;
import model.Person;
import persistence.JsonReader;
import persistence.JsonWriter;

// Represents an abstract class that has methods for the common frame setup
public abstract class WhisktyleAbstract extends JFrame {
    protected BackgroundImage background;

    protected static final String IMG_DIRECTORY = "img/";
    protected static final String DEFAULT_IMG_DIRECTORY = IMG_DIRECTORY + "default-imgs/";
    protected static final String BUTTON_IMG_DIRECTORY = IMG_DIRECTORY + "button-imgs/";
    protected static final String CLOSET_IMG_DIRECTORY = IMG_DIRECTORY + "closet-imgs/";
    protected static final String LOADING_IMG_DIRECTORY = IMG_DIRECTORY + "loading-imgs/";

    protected static final int BUTTON_WIDTH = 230;
    protected static final int BUTTON_HEIGHT = 90;

    protected static final int INNER_BUTTON_WIDTH = 50;
    protected static final int INNER_BUTTON_HEIGHT = 35;

    protected static final int OUTER_CLOSET_WIDTH = 475;
    protected static final int OUTER_CLOSET_HEIGHT = 575;

    protected static final int INNER_CLOSET_WIDTH = 450;

    private static final Color DARK_BLUE = Color.decode("#44657E");

    private Outfit selectedOutfit;

    // Fields for JSON read, write
    protected static final String JSON_STORE = "./data/testWriterCloset.json";
    protected JsonWriter jsonWriter;
    protected JsonReader jsonReader;

    // EFFECTS: Constructor for WhisktyleAbstract, setting up the frame
    public WhisktyleAbstract() {
        super("Whisktyle");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setCustomOptionPanes();
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

    // EFFECTS: modifies colours of JOptionPanes
    public void setCustomOptionPanes() {
        UIManager.put("OptionPane.messageForeground", Color.WHITE);

        UIManager.put("Panel.background", DARK_BLUE);
        UIManager.put("OptionPane.border", new LineBorder(Color.BLACK, 10));

        UIManager.put("Button.background", Color.DARK_GRAY);
        UIManager.put("Button.foreground", Color.WHITE);
    }

    // EFFECTS: returns instance of BackgroundImage
    public BackgroundImage setBackgroundImage() {

        Image img = new ImageIcon(getClass().getResource(DEFAULT_IMG_DIRECTORY + "background.png")).getImage();
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
            setLayout(new FlowLayout());
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
        ImageIcon cursorImg = new ImageIcon(getClass().getResource(DEFAULT_IMG_DIRECTORY + "cursor.png"));
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
        ImageIcon headerImg = new ImageIcon(getClass().getResource(DEFAULT_IMG_DIRECTORY + "whisktyle.png"));
        Image image = headerImg.getImage().getScaledInstance(500, 150, Image.SCALE_SMOOTH);

        headerImg = new ImageIcon(image);
        JLabel header = new JLabel(headerImg);

        header.setAlignmentY(Component.CENTER_ALIGNMENT);
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

    // EFFECTS: closes this frame, sends to loading frame
    public void setNewFrame(String stringUI) {
        Loading loadingUI = new Loading(stringUI);
        dispose();
        loadingUI.setVisible(true);
    }

    // EFFECTS: returns instance of closet
    public Closet getCloset() {
        return WhisktyleController.getInstance().getCloset();
    }

    // EFFECTS: scales and returns image icon
    public ImageIcon createImgIcon(String imgPath, int width, int height) {
        final ImageIcon icon = new ImageIcon(
                getClass().getResource(imgPath));

        Image img = icon.getImage().getScaledInstance(width, height,
                Image.SCALE_SMOOTH);

        final ImageIcon finalIcon = new ImageIcon(img);

        return finalIcon;
    }

    // EFFECTS: creates, scales img from the absolute path
    public ImageIcon createImgIconFromResource(String filePath, int width, int height) {
        File file = new File(filePath);

        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
        Image scaledImg = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    public JLabel createScaledLabel(ImageIcon icon, int width, int height) {
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(scaledImage));
    }

    // EFFECTS: returns closet closed ImageIcon
    public ImageIcon createClosetImg() {
        return createImgIcon(CLOSET_IMG_DIRECTORY + "browse-closet.png", OUTER_CLOSET_WIDTH, OUTER_CLOSET_HEIGHT);
    }

    // EFFECTS: creates and returns inner menu buttons
    public JButton createButton(String imgPath, int width, int height) {
        JButton button = new JButton();
        ImageIcon innerButtonIcon = createButtonImg(imgPath, width, height);
        button.setIcon(innerButtonIcon);
        button.setPreferredSize(new Dimension(innerButtonIcon.getIconWidth(), innerButtonIcon.getIconHeight()));
        button.setMaximumSize(new Dimension(innerButtonIcon.getIconWidth(), innerButtonIcon.getIconHeight()));

        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setAlignmentY(Component.CENTER_ALIGNMENT);
        button.setOpaque(false);
        button.setContentAreaFilled(false);

        return button;
    }

    // EFFECTS: creates, scales, returns image icon of a button
    public ImageIcon createButtonImg(String imgPath, int width, int height) {
        ImageIcon buttonIcon = new ImageIcon(getClass().getResource(imgPath));

        Image scaledButtonImg = buttonIcon.getImage().getScaledInstance(width, height,
                Image.SCALE_SMOOTH);
        ImageIcon scaledButtonIcon = new ImageIcon(scaledButtonImg);

        return scaledButtonIcon;
    }

    // EFFECTS: sets opacity of image icon and returns
    public ImageIcon makeImageTransparent(ImageIcon icon, float opacity) {
        int w = icon.getIconWidth();
        int h = icon.getIconHeight();

        BufferedImage transparentImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = transparentImage.createGraphics();

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));

        // Draw original image with opacity
        g2d.drawImage(icon.getImage(), 0, 0, null);
        g2d.dispose();

        return new ImageIcon(transparentImage);
    }

    // EFFECTS: handles whether user wants to save their progress. Will write a JSON
    // file to store current data
    public void saveProgress() {
        int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to save your current progress?",
                "Save Progress",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (choice == JOptionPane.YES_OPTION) {
            try {
                jsonWriter.open();
                jsonWriter.write(getCloset());
                jsonWriter.close();
                JOptionPane.showMessageDialog(this, "Data saved successfully to " + JSON_STORE);
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(this, "Unable to write to file: " + JSON_STORE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Progress was not saved.");
        }
    }

    // EFFECTS: handles whether user wants to load their progress. Will read a JSON
    // file to load stored data
    public void loadProgress() {
        int choice = JOptionPane.showConfirmDialog(this,
                "Do you want to load your previous progress?",
                "Load Progress",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (choice == JOptionPane.YES_OPTION) {
            try {
                Closet closet = jsonReader.read();

                WhisktyleController.getInstance().setCloset(closet);
                for (Clothing c : closet.getShirts()) {
                    c.setImg(createImgIconFromResource(c.getImgPath(), 250, 200));
                }
                for (Clothing c : closet.getPants()) {
                    c.setImg(createImgIconFromResource(c.getImgPath(), 200, 250));
                }

                Person person = closet.getPerson();

                person.setImg(createImgIconFromResource(person.getImgPath(), 250, 200));

                Map<String, List<Outfit>> savedOutfits = closet.getSavedOutfits();
                for (Map.Entry<String, List<Outfit>> entry : savedOutfits.entrySet()) {
                    List<Outfit> outfits = entry.getValue();
                    for (Outfit outfit : outfits) {
                        if (outfit.getShirt() != null) {
                            outfit.getShirt().setImg(
                                    createImgIconFromResource(outfit.getShirt().getImgPath(), 250, 200));
                        }
                        if (outfit.getPants() != null) {
                            outfit.getPants().setImg(
                                    createImgIconFromResource(outfit.getPants().getImgPath(), 200, 250));
                        }
                    }
                }
                WhisktyleController.getInstance().setCloset(closet);

                JOptionPane.showMessageDialog(this,
                        "Data loaded successfully from " + JSON_STORE);

            } catch (IOException | NoPersonException e) {
                JOptionPane.showMessageDialog(this,
                        "Unable to read from file: " + JSON_STORE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Progress was not loaded.");
        }
    }
}
