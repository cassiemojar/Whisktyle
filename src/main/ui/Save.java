package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Reprsents a Save class 
public class Save extends JFrame {
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
            JOptionPane.showMessageDialog(this, "Progress successfully saved!");

        } else {
            JOptionPane.showMessageDialog(this, "Progress was not saved.");

        }
       
    }

}
