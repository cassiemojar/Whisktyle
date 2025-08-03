package ui;

import java.util.List;

import javax.swing.*;

import model.Pants;
import model.Shirt;

//import model.Shirt;

import java.awt.*;
//import java.awt.event.*;

// Represents a Browse class 
public class Browse extends WhisktyleAbstract {
    private static final int INNER_BUTTON_WIDTH = 50;
    private static final int INNER_BUTTON_HEIGHT = 35;

    private static final int OUTER_CLOSET_WIDTH = 475;
    private static final int OUTER_CLOSET_HEIGHT = 575;

    private static final int INNER_CLOSET_WIDTH = 450;

    private JLabel shirtLabel;
    private JLabel pantsLabel;
    private JLabel shoesLabel;

    // EFFECTS: Constructor for Browse, sets title
    public Browse() {
        setTitle("Whisktyle - Browse");

    }

    @Override
    // EFFECTS: returns title panel with title image added to it
    public JPanel setTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.add(Box.createHorizontalGlue());
        titlePanel.add(createMenuButton(BUTTON_IMG_DIRECTORY + "add-button.png"));
        titlePanel.add(Box.createRigidArea(new Dimension(20, 0)));
        titlePanel.add(setTitle());
        titlePanel.add(Box.createRigidArea(new Dimension(20, 0)));
        titlePanel.add(createMenuButton(BUTTON_IMG_DIRECTORY + "dress-me-button.png"));
        titlePanel.add(Box.createHorizontalGlue());

        return titlePanel;
    }

    // EFFECTS: creates and returns menu buttons
    public JButton createMenuButton(String imgPath) {
        return createButton(imgPath, BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    // EFFECTS: creates and returns inner menu buttons
    public JButton createInnerMenuButton(JPanel panel, String imgPath, String clothing, String direction) {
        JButton button = createButton(imgPath, INNER_BUTTON_WIDTH, INNER_BUTTON_HEIGHT);
        button.addActionListener(e -> handleInnerButton(button, panel, clothing, direction));
        return button;
    }

    public void handleInnerButton(JButton menuButton, JPanel panel, String clothing, String direction) {
        if (clothing.equals("Shirt")) {
            if (direction.equals("Right")) {
                if (shirtIndex < getCloset().getShirts().size() - 1 && shirtIndex >= 0) {
                    shirtIndex++;
                    System.out.println("Right");
                    setShirtLabel();
                }
            } else if (direction.equals("Left")) {
                if (shirtIndex > 0) {
                    shirtIndex--;
                    System.out.println("Left");
                    setShirtLabel();
                }
            } else { // play
                if (shirtIndex != -1) {
                    setSaveShirtLabel();
                    shirtIndex = -1;

                }

            }
        } else {
            if (direction.equals("Right")) {
                if (pantsIndex < getCloset().getPants().size() - 1 && pantsIndex >= 0) {
                    pantsIndex++;
                    System.out.println("Right");
                    setPantsLabel();
                }
            } else if (direction.equals("Left")) {
                if (pantsIndex != 0) {
                    pantsIndex--;
                    System.out.println("Left");
                    setPantsLabel();
                }
            } else { // play
                if (pantsIndex != -1) {
                    setSavePantsLabel();
                    pantsIndex = -1;

                }
            }
        }
    }

    // EFFECTS: Adds title panel and closet panel to background panel
    @Override
    public void setUI() {
        getCloset().getShirts().add(new Shirt("test 1", createImgIcon(IMG_DIRECTORY + "test-shirt.png", 250, 200)));
        getCloset().getShirts().add(new Shirt("test 2", createImgIcon(IMG_DIRECTORY + "test-shirt-two.png", 250, 200)));
        getCloset().getShirts()
                .add(new Shirt("test 2", createImgIcon(IMG_DIRECTORY + "test-shirt-three.png", 250, 200)));

        getCloset().getPants().add(new Pants("test 1", createImgIcon(IMG_DIRECTORY + "test-pants.png", 200, 250)));
        getCloset().getPants().add(new Pants("test 2", createImgIcon(IMG_DIRECTORY + "test-shirt.png", 200, 250)));
        getCloset().getPants().add(new Pants("test 2", createImgIcon(IMG_DIRECTORY + "test-shirt-two.png", 200, 250)));

        background.setLayout(new BorderLayout());
        background.add(setTitlePanel(), BorderLayout.PAGE_START);
        background.add(setClosetPanel(), BorderLayout.CENTER);
    }

    // EFFECTS: returns closet panel with closet image added to it
    public JPanel setClosetPanel() {
        JPanel closetPanel = new JPanel();
        closetPanel.setOpaque(false);
        // closetPanel.setBackground(Color.RED);
        closetPanel.setLayout(new BoxLayout(closetPanel, BoxLayout.Y_AXIS));
        closetPanel.add(setClosetUI());
        return closetPanel;
    }

    // EFFECTS: returns closet closed ImageIcon
    public ImageIcon createClosetImg() {
        return createImgIcon(CLOSET_IMG_DIRECTORY + "browse-closet.png", OUTER_CLOSET_WIDTH, OUTER_CLOSET_HEIGHT);
    }

    public JPanel setClosetButtonsUI() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

        buttonsPanel.setOpaque(false);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(40, 10, 30, 10));

        return buttonsPanel;
    }

    // EFFECTS: sets closet label to closet open image when mouse hovers, close
    // close image when not
    public JPanel setClosetUI() {
        ImageIcon closetImg = createClosetImg();
        JLabel closetLabel = new JLabel(closetImg);
        closetLabel.setBounds(0, 0, OUTER_CLOSET_WIDTH, OUTER_CLOSET_HEIGHT);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(OUTER_CLOSET_WIDTH, OUTER_CLOSET_HEIGHT));
        layeredPane.setLayout(null);

        JPanel innerPanel = createClosetInnerPanel();
        innerPanel.setBounds(0, 0, OUTER_CLOSET_WIDTH, OUTER_CLOSET_HEIGHT);
        innerPanel.setOpaque(false);

        layeredPane.add(closetLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(innerPanel, JLayeredPane.PALETTE_LAYER); // Becomes layered on top

        JPanel closetPanel = new JPanel();
        closetPanel.setOpaque(false);
        closetPanel.add(layeredPane);

        return closetPanel;
    }

    // EFFECTS: creates and returns inner panel component
    public JPanel createInnerComponent() {
        JPanel innerPanel = new JPanel();
        innerPanel.setOpaque(false);
        innerPanel.setPreferredSize(new Dimension(OUTER_CLOSET_WIDTH, OUTER_CLOSET_HEIGHT));
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));

        innerPanel.add(Box.createRigidArea(new Dimension(0, 10))); // top padding
        return innerPanel;
    }

    // EFFECTS: creates and returns a wrapper with the function of centering
    // components inside closet's inner panels
    public JPanel createWrapper() {
        JPanel upperWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        upperWrapper.setOpaque(false);
        return upperWrapper;
    }

    // EFFECTS: creates and returns a panel with the inner buttons added
    public JPanel setClosetButtonPanel(JPanel panel, String clothing) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        buttonPanel.add(createInnerMenuButton(panel, BUTTON_IMG_DIRECTORY + "left-button.png", clothing, "Left"));
        buttonPanel.add(createInnerMenuButton(panel, BUTTON_IMG_DIRECTORY + "play-button.png", clothing, "Play"));
        buttonPanel.add(createInnerMenuButton(panel, BUTTON_IMG_DIRECTORY + "right-button.png", clothing, "Right"));
        return buttonPanel;
    }

    // MODIFIES: this
    // EFFECTS: sets the label icon to the shirt the index is currently on
    public void setShirtLabel() {
        List<Shirt> shirts = getCloset().getShirts();
        if (!shirts.isEmpty()) {
            shirtLabel.setIcon(shirts.get(shirtIndex).getImg());
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the label icon to the pants the index is currently on
    public void setPantsLabel() {
        List<Pants> pants = getCloset().getPants();
        if (!pants.isEmpty()) {
            pantsLabel.setIcon(pants.get(pantsIndex).getImg());
        }
    }

    // EFFECTS: makes the shirt label icon the index is currently on transparent
    public void setSaveShirtLabel() {
        ImageIcon originalIcon = getCloset().getShirts().get(shirtIndex).getImg();
        ImageIcon transparentIcon = makeImageTransparent(originalIcon, 0.8f);
        shirtLabel.setIcon(transparentIcon);
    }

    // EFFECTS: makes the pants label icon the index is currently on transparent
    public void setSavePantsLabel() {
        ImageIcon originalIcon = getCloset().getPants().get(pantsIndex).getImg();
        ImageIcon transparentIcon = makeImageTransparent(originalIcon, 0.8f);
        pantsLabel.setIcon(transparentIcon);
    }


    // EFFECTS: creates adds shirt label to panel with centering
    public void setShirtUI(JPanel panel) {
        shirtLabel = new JLabel();
        setShirtLabel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        panel.add(shirtLabel, gbc);
        panel.setMaximumSize(panel.getPreferredSize());
    }

    // EFFECTS: creates adds pants label to panel with centering
    public void setPantsUI(JPanel panel) {
        pantsLabel = new JLabel();
        setPantsLabel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        panel.add(pantsLabel, gbc);
        panel.setMaximumSize(panel.getPreferredSize());
    }

    // EFFECTS: Creates, sets up panels for inner closet panel and returns
    public JPanel createClosetInnerPanel() {
        JPanel innerPanel = createInnerComponent(); // upper panel centered in a wrapper
        JPanel upperWrapper = createWrapper();
        JPanel upperPanel = createUpperClosetPanel();
        upperPanel.setPreferredSize(new Dimension(INNER_CLOSET_WIDTH, (OUTER_CLOSET_HEIGHT / 2) - 50));
        upperPanel.setMaximumSize(new Dimension(INNER_CLOSET_WIDTH, (OUTER_CLOSET_HEIGHT / 2) - 50));
        upperPanel.setMinimumSize(new Dimension(INNER_CLOSET_WIDTH, (OUTER_CLOSET_HEIGHT / 2) - 50));
        upperPanel.setBackground(Color.RED);
        upperPanel.setOpaque(true);
        setShirtUI(upperPanel);

        upperWrapper.add(upperPanel);
        innerPanel.add(upperWrapper);

        innerPanel.add(setClosetButtonPanel(upperPanel, "Shirt"));

        JPanel lowerWrapper = createWrapper(); // Lower panel centered in a wrapper
        JPanel lowerPanel = createLowerClosetPanel();
        lowerPanel.setPreferredSize(new Dimension(INNER_CLOSET_WIDTH, (OUTER_CLOSET_HEIGHT / 2) - 50));
        lowerPanel.setMaximumSize(new Dimension(OUTER_CLOSET_WIDTH, (OUTER_CLOSET_HEIGHT / 2) - 50));

        lowerPanel.setBackground(Color.RED);
        lowerPanel.setOpaque(true);
        setPantsUI(lowerPanel);

        lowerWrapper.add(lowerPanel);
        innerPanel.add(lowerWrapper);

        innerPanel.add(setClosetButtonPanel(lowerPanel, "Pants"));
        innerPanel.add(Box.createRigidArea(new Dimension(0, 7)));

        return innerPanel;
    }

    // EFFECTS: creates and returns upper closet panel
    public JPanel createUpperClosetPanel() {
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.Y_AXIS));
        upperPanel.setOpaque(false);
        // upperPanel.setBackground(Color.RED);
        upperPanel.setMaximumSize(new Dimension(OUTER_CLOSET_WIDTH, (OUTER_CLOSET_HEIGHT / 2) - 50)); // Half of 575
        upperPanel.setPreferredSize(new Dimension(OUTER_CLOSET_WIDTH, (OUTER_CLOSET_HEIGHT / 2) - 50));

        return upperPanel;
    }

    // EFFECTS: creates and returns lower closet panel
    public JPanel createLowerClosetPanel() {
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.Y_AXIS));
        lowerPanel.setOpaque(true);
        lowerPanel.setBackground(Color.RED);
        lowerPanel.setMaximumSize(new Dimension(OUTER_CLOSET_WIDTH, (OUTER_CLOSET_HEIGHT / 2) - 50));
        lowerPanel.setPreferredSize(new Dimension(OUTER_CLOSET_WIDTH, (OUTER_CLOSET_HEIGHT / 2) - 50));

        return lowerPanel;
    }

}
