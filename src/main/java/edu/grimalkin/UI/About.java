package edu.grimalkin.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class About extends JDialog {
    private static final long serialVersionUID = 1L;

    public About(String title) {
        initWindow(title);
        initWindowComponents();
    }

    private void initWindow(String title) {
        setTitle("About Grimalkin");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
    
    private void initWindowComponents() {
        
    }
}  
