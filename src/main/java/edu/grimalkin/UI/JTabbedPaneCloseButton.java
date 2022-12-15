package edu.grimalkin.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**
 * Une classe "JTabbedPaneCloseButton" permettant d'ajouter un bouton de fermeture à un JTabbedPane
 * La classe hérite de JTabbedPane
 * @see JTabbedPane https://docs.oracle.com/javase/7/docs/api/javax/swing/JTabbedPane.html
 * @see JTabbedPaneCloseButton https://gist.github.com/6dc/0c8926f85d701a869bb2
 * @author Original code by 6dc
 * @author Small changes by me
 */
public class JTabbedPaneCloseButton extends JTabbedPane {

    /**
     * Constructeur par défaut
     * Initialise les attributs à des valeurs par défaut
     */
    public JTabbedPaneCloseButton() {
        super();
    }

    /** 
     * Override de AddTab pour ajouter un bouton de fermeture à chaque onglet ajouté en prenant quatre paramètres
     * @param title titre de l'onglet
     * @param icon icone de l'onglet
     * @param component composant de l'onglet
     * @param tip tooltip de l'onglet
     */ 
    @Override
    public void addTab(String title, Icon icon, Component component, String tip) {
        super.addTab(title, icon, component, tip);
        int count = this.getTabCount() - 1;
        setTabComponentAt(count, new CloseButtonTab(component, title, icon));
    }

    
    /** 
     * Override de AddTab pour ajouter un bouton de fermeture à chaque onglet ajouté en prenant trois paramètres
     * @param title titre de l'onglet
     * @param icon icone de l'onglet
     * @param component composant de l'onglet
     */
    @Override
    public void addTab(String title, Icon icon, Component component) {
        addTab(title, icon, component, null);
    }

    
    /** 
     * Override de AddTab pour ajouter un bouton de fermeture à chaque onglet ajouté en prenant deux paramètres
     * @param title titre de l'onglet
     * @param component composant de l'onglet
     */
    @Override
    public void addTab(String title, Component component) {
        addTab(title, null, component);
    }

    /**
     * Méthode permettant d'ajouter un onglet sans bouton de fermeture en prenant quatre paramètres
     * @param title titre de l'onglet
     * @param icon icone de l'onglet
     * @param component composant de l'onglet
     * @param tip tooltip de l'onglet
     */
    public void addTabNoExit(String title, Icon icon, Component component, String tip) {
        super.addTab(title, icon, component, tip);
    }

    
    /** 
     * Méthode permettant d'ajouter un onglet sans bouton de fermeture en prenant trois paramètres
     * @param title titre de l'onglet
     * @param icon icone de l'onglet
     * @param component composant de l'onglet
     */
    public void addTabNoExit(String title, Icon icon, Component component) {
        addTabNoExit(title, icon, component, null);
    }

    
    /** 
     * Méthode permettant d'ajouter un onglet sans bouton de fermeture en prenant deux paramètres
     * @param title titre de l'onglet
     * @param component composant de l'onglet
     */
    public void addTabNoExit(String title, Component component) {
        addTabNoExit(title, null, component);
    }

    /**
     * Une classe "CloseButtonTab" permettant d'ajouter un bouton de fermeture à un onglet
     * La classe hérite de JPanel
     * @see JPanel https://docs.oracle.com/javase/7/docs/api/javax/swing/JPanel.html
     */
    public class CloseButtonTab extends JPanel {
        /**
         * Composant de l'onglet
         * @see Component https://docs.oracle.com/javase/7/docs/api/java/awt/Component.html
         */
        private Component tab;

        /**
         * Constructeur par défaut
         * Initialise les attributs à des valeurs par défaut
         * @param _tab composant de l'onglet
         * @param title titre de l'onglet
         * @param icon icone de l'onglet
         */
        public CloseButtonTab(final Component _tab, String title, Icon icon) {
            tab = _tab;
            setOpaque(false);
            FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 3, 3);
            setLayout(flowLayout);
            JLabel jLabel = new JLabel(title);
            jLabel.setIcon(icon);
            add(jLabel);
            JButton button = new JButton("✖");
            // remove borders and background from button
            button.setBorder(null);
            button.setContentAreaFilled(false);
            button.setBackground(UIManager.getColor("Actions.Red"));
            // set background color to look and feel hover color when mouse is over
            button.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setContentAreaFilled(true);
                    // set button text color to dark grey
                    button.setForeground(Color.DARK_GRAY);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    button.setContentAreaFilled(false);
                    // set button text to default color
                    button.setForeground(UIManager.getColor("Label.foreground"));
                }
            });

            button.setMargin(new Insets(0, 0, 0, 0));
            button.addMouseListener(new CloseListener(tab));
            add(button);
        }
    }
    /**
     * Une classe "CloseListener" permettant de fermer un onglet
     * La classe implémente l'interface MouseListener
     * @see MouseListener https://docs.oracle.com/javase/7/docs/api/java/awt/event/MouseListener.html
     */
    public class CloseListener implements MouseListener
    {
        // Attribut privé Component tab
        private Component tab;

        /**
         * Constructeur par défaut
         * Initialise les attributs à des valeurs par défaut
         * @param _tab composant de l'onglet
         */
        public CloseListener(Component _tab) {
            tab=_tab;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource() instanceof JButton){
                JButton clickedButton = (JButton) e.getSource();
                JTabbedPane tabbedPane = (JTabbedPane) clickedButton.getParent().getParent().getParent();
                tabbedPane.remove(tab);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }
}