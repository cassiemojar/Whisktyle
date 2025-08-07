package ui;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import model.Pants;
import model.Shirt;

// Represents an abstract class that handles buttons and their functions
public abstract class BrowseButtonController extends WhisktyleAbstract {
    protected JLabel shirtLabel;
    protected JLabel pantsLabel;
    private JLabel shoesLabel;

    public BrowseButtonController() {

    }

    // EFFECTS: Creates a JFileChooser panel for user to add a person w. image if
    // closet doesn't have person
    public void handleNoPerson() {
        JOptionPane optionPane = new JOptionPane(
                "You don't have a person loaded yet. Please select an image of your person.",
                JOptionPane.WARNING_MESSAGE,
                JOptionPane.DEFAULT_OPTION);

        JDialog dialog = optionPane.createDialog("No Person Loaded");
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.setVisible(true);

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a Person Image");
        fileChooser.setAcceptAllFileFilterUsed(false); // Need only img files
        fileChooser.setFileFilter(
                new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "jpeg", "png"));

        int result = JFileChooser.CANCEL_OPTION; // opens file dialogue to see if cancel or accept

        while (result != JFileChooser.APPROVE_OPTION) { // Loops so can't cancel
            result = fileChooser.showOpenDialog(null);
        }

        String imagePath = fileChooser.getSelectedFile().getAbsolutePath();

        String name = JOptionPane.showInputDialog(this, "Enter a name for this Person:", "Name",
                JOptionPane.PLAIN_MESSAGE);

        if (name != null && !name.trim().isEmpty()) {
            ImageIcon shirtImage = createImgIconFromResource(imagePath, 250, 200);
            Shirt newShirt = new Shirt(name.trim(), shirtImage);
            getCloset().getShirts().add(newShirt);
            shirtIndex = getCloset().getShirts().size() - 1; // Show the new shirt
            setShirtLabel();
            JOptionPane.showMessageDialog(this, "Shirt \"" + name + "\" now added to your closet!");
        } else {
            JOptionPane.showMessageDialog(this, "Shirt not added. No name entered.", "Cancelled",
                    JOptionPane.WARNING_MESSAGE);
        }

    }

    // EFFECTS: handles the inner buttons that was selected inside the closet
    public void handleInnerButton(JButton menuButton, String clothing, String direction) {
        if (clothing.equals("Shirt")) {
            handleShirtButton(direction);
        } else {
            handlePantsButton(direction);
        }
    }

    // EFFECTS: handles selection of the menu buttons
    public void handleMenuButton(String selection) {
        Object[] options = { "SHIRT", "PANTS", "SHOES" };

        switch (selection) {
            case "Add":
                int choiceIndex = JOptionPane.showOptionDialog(null, "Choose which clothing to add:", "Add Clothing",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                if (choiceIndex >= 0 && choiceIndex < options.length) {
                    String choice = (String) options[choiceIndex];
                    handleClothingOption(choice);
                }
                break;

            case "Dress Me":
                setNewFrame("Dress Me");
                break;
        }
    }

    // EFFECTS: handles clothing option from JOptionPane's add button
    public void handleClothingOption(Object choice) {
        switch ((String) choice) {
            case "SHIRT":
                addNewShirt();
                break;
            case "PANTS":
                addNewPants();
                break;

            case "SHOES":
        }
    }

    // EFFECTS: creates a JFileChooser panel for user to select shirt img and name
    // and adds to shirtsList
    public void addNewShirt() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a Shirt Image");
        fileChooser.setAcceptAllFileFilterUsed(false); // Need only img files
        fileChooser.setFileFilter(
                new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "jpeg", "png"));
        int result = fileChooser.showOpenDialog(null); // opens file dialogue to see if cancel or accept

        if (result == JFileChooser.APPROVE_OPTION) {
            String imagePath = fileChooser.getSelectedFile().getAbsolutePath();

            String shirtName = JOptionPane.showInputDialog(this, "Enter a name for this shirt:", "Shirt Name",
                    JOptionPane.PLAIN_MESSAGE);

            if (shirtName != null && !shirtName.trim().isEmpty()) {
                ImageIcon shirtImage = createImgIconFromResource(imagePath, 250, 200);
                Shirt newShirt = new Shirt(shirtName.trim(), shirtImage);
                getCloset().getShirts().add(newShirt);
                shirtIndex = getCloset().getShirts().size() - 1; // Show the new shirt
                setShirtLabel();
                JOptionPane.showMessageDialog(this, "Shirt \"" + shirtName + "\" now added to your closet!");
            } else {
                JOptionPane.showMessageDialog(this, "Shirt not added. No name entered.", "Cancelled",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // EFFECTS: creates a JFileChooser panel for user to select pants img and name
    // and adds to pantsList
    public void addNewPants() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a Pants Image");
        fileChooser.setAcceptAllFileFilterUsed(false); // Need only img files
        fileChooser.setFileFilter(
                new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "jpeg", "png"));
        int result = fileChooser.showOpenDialog(null); // opens file dialogue to see if cancel or accept

        if (result == JFileChooser.APPROVE_OPTION) {
            String imagePath = fileChooser.getSelectedFile().getAbsolutePath();

            String pantsName = JOptionPane.showInputDialog(this, "Enter a name for these pants:", "Pants Name",
                    JOptionPane.PLAIN_MESSAGE);

            if (pantsName != null && !pantsName.trim().isEmpty()) {
                ImageIcon pantsImage = createImgIconFromResource(imagePath, 200, 250);
                Pants newPants = new Pants(pantsName.trim(), pantsImage);
                getCloset().getPants().add(newPants);
                pantsIndex = getCloset().getPants().size() - 1; // Show the new pants
                setPantsLabel();
                JOptionPane.showMessageDialog(this, "Pants \"" + pantsName + "\" now added to your closet!");
            } else {
                JOptionPane.showMessageDialog(this, "Pants not added. No name entered.", "Cancelled",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // EFFECTS: handles the button selection for shirt
    public void handleShirtButton(String direction) {
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
    }

    // EFFECTS: handles the button selection for pants
    public void handlePantsButton(String direction) {
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

}
