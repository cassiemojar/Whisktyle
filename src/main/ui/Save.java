package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

// Reprsents a Save class 
public class Save extends Menu {
    // EFFECTS: Constructor for Save, sets title
    public Save() {
        saveMessage();
    }

    private void saveMessage() {
        int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to save your current progress?",
                "Save Progress",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (choice == JOptionPane.YES_OPTION) {
            try {
                writer.open();
                writer.write(getCloset());
                writer.close();
                JOptionPane.showMessageDialog(this, "Data saved successfully to " + JSON_STORE);
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(this, "Unable to write to file: " + JSON_STORE);
            }
            JOptionPane.showMessageDialog(this, "Progress successfully saved!");

        } else {
            JOptionPane.showMessageDialog(this, "Progress was not saved.");

        }

    }

}
