package ui;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import model.Clothing;
import model.Pants;
import model.Person;
import model.Shirt;
import model.Shoes;

// Represents an abstract class that handles buttons and their functions, and additional logic
public abstract class BrowseButtonController extends WhisktyleAbstract {
    protected JLabel shirtLabel;
    protected JLabel pantsLabel;
    private JLabel shoesLabel;

    // EFFECTS: constructor for BrowseButtonController class
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

        JFileChooser fileChooserPerson = new JFileChooser();
        fileChooserPerson.setDialogTitle("Select a Person Image");
        fileChooserPerson.setAcceptAllFileFilterUsed(false); // Need only img files
        fileChooserPerson.setFileFilter(
                new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "jpeg", "png"));

        int resultPerson = JFileChooser.CANCEL_OPTION; // opens file dialogue to see if cancel or accept

        while (resultPerson != JFileChooser.APPROVE_OPTION) { // Loops so can't cancel
            resultPerson = fileChooserPerson.showOpenDialog(null);
        }

        String imagePath = fileChooserPerson.getSelectedFile().getAbsolutePath();

        String name = JOptionPane.showInputDialog(this, "Enter a name for this Person:", "Name",
                JOptionPane.PLAIN_MESSAGE);

        helperNoPerson(name, imagePath);

    }

    // EFFECTS: helper method for handling no person loaded
    public void helperNoPerson(String name, String imagePath) {
        Clothing shirt = addNewClothing("Shirt", getCloset().getShirts());
        Clothing pants = addNewClothing("Pants", getCloset().getPants());

        if (name != null && !name.trim().isEmpty()) {
            ImageIcon personImg = createImgIconFromResource(imagePath, 250, 200);
            Person person = new Person(name.trim(), personImg, shirt, pants, new Shoes(""));
            getCloset().setPerson(person);

            JOptionPane.showMessageDialog(this, "Person \"" + name + "\" now associated with your closet!");
        } else {
            JOptionPane.showMessageDialog(this, "Person not added. No name entered.", "Cancelled",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    // EFFECTS: handles the inner buttons that was selected inside the closet
    public void handleInnerButton(JButton menuButton, String clothing, String direction) {
        if (clothing.equals("Shirt")) {
            getCloset().setShirtIndex(handleInnerButtons(clothing, direction, getCloset().getShirts(),
                    getCloset().getShirtIndex(), shirtLabel));
        } else {
            getCloset().setPantsIndex(handleInnerButtons(clothing, direction, getCloset().getPants(),
                    getCloset().getPantsIndex(), pantsLabel));
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
                addNewClothing("Shirt", getCloset().getShirts());
                break;
            case "PANTS":
                addNewClothing("Pants", getCloset().getPants());
                break;

            case "SHOES":
        }
    }

    // EFFECTS: creates a JFileChooser panel for user to select clothing img + name,
    // then adds to respective clothing list
    public Clothing addNewClothing(String clothing, List<Clothing> listClothing) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a " + clothing + " Image");
        fileChooser.setAcceptAllFileFilterUsed(false); // Need only img files
        fileChooser.setFileFilter(
                new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "jpeg", "png"));
        int result = fileChooser.showOpenDialog(null); // opens file dialogue to see if cancel or accept

        if (result == JFileChooser.APPROVE_OPTION) {
            String imagePath = fileChooser.getSelectedFile().getAbsolutePath();

            String name = JOptionPane.showInputDialog(this, "Enter a name for this clothing:", "Clothing Name",
                    JOptionPane.PLAIN_MESSAGE);

            return helperAddClothing(clothing, name, imagePath);
        }
        return null;
    }

    // EFFECTS: helper method for addNewClothing method
    public Clothing helperAddClothing(String clothing, String name, String imagePath) {
        if (name != null && !name.trim().isEmpty()) {
            switch (clothing) {
                case "Shirt":
                    JOptionPane.showMessageDialog(this, "Shirt \"" + name + "\" now added to your closet!");
                    return addsToClothingList("Shirt", imagePath, name);

                case "Pants":
                    JOptionPane.showMessageDialog(this, "Pants \"" + name + "\" now added to your closet!");
                    return addsToClothingList("Pants", imagePath, name);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Shirt not added. No name entered.", "Cancelled",
                    JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }

    // EFFECTS: handles adding to clothes list when handling add button
    public Clothing addsToClothingList(String clothing, String imagePath, String name) {
        switch (clothing) {
            case "Shirt":
                ImageIcon shirtImage = createImgIconFromResource(imagePath, 250, 200);
                Clothing newShirt = new Shirt(name.trim(), shirtImage);
                getCloset().getShirts().add(newShirt);
                getCloset().setShirtIndex(getCloset().getShirts().size() - 1);
                setClothingLabel(getCloset().getShirts(), shirtLabel, getCloset().getShirtIndex()); // Show the new
                                                                                                    // shirt
                return newShirt;
            case "Pants":
                ImageIcon pantsImage = createImgIconFromResource(imagePath, 200, 250);
                Clothing newPants = new Pants(name.trim(), pantsImage);
                getCloset().getPants().add(newPants);
                getCloset().setPantsIndex(getCloset().getPants().size() - 1); // Show the new pants
                setClothingLabel(getCloset().getPants(), pantsLabel, getCloset().getPantsIndex());
                return newPants;

        }
        return null;
    }

    // EFFECTS: handles inner button selection for closet labels
    public int handleInnerButtons(String clothing, String direction, List<Clothing> listClothing, int index,
            JLabel clothingLabel) {
        switch (direction) {
            case "Right":
                if (index < listClothing.size() - 1 && index >= 0) {
                    index++;
                    System.out.println("Right");
                    setClothingLabel(listClothing, clothingLabel, index);
                }
                break;

            case "Left":
                if (index > 0) {
                    index--;
                    System.out.println("Left");
                    setClothingLabel(listClothing, clothingLabel, index);
                }
                break;

            case "Play":
                if (index != -1) {
                    saveLabel(listClothing, clothingLabel, index);
                    index = -1;
                }
                break;
        }
        return index;

    }

    // EFFECTS: updates clothing labels of closet
    public void setClothingLabel(List<Clothing> listClothing, JLabel clothingLabel, int index) {
        if (!listClothing.isEmpty()) {
            clothingLabel.setIcon(listClothing.get(index).getImg());
        }
    }

    // EFFECTS: fixes the closet label (won't allow it to change) + makes icon
    // transparent
    public void saveLabel(List<Clothing> listClothing, JLabel clothingLabel, int index) {
        ImageIcon originalIcon = listClothing.get(index).getImg();
        ImageIcon transparentIcon = makeImageTransparent(originalIcon, 0.8f);
        clothingLabel.setIcon(transparentIcon);
    }
}
