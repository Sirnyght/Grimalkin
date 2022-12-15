package edu.grimalkin;

// import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;

import edu.grimalkin.ui.MainWindow;

/**
 * Classe principale de l'application.
 * Initialise l'interface graphique et lance l'application.
 * @see MainWindow
 * @see <a href="https://commons.apache.org/proper/commons-compress/">Apache Commons Compress</a>
 */
public class App {
    private static final String APP_NAME = "Grimalkin";
    private static final String APP_VERSION = "0.8a";
    private static final String APP_AUTHOR = "Dimitri Hude & Laure Blanchard";
    private static final String SUPPORTED_FORMATS = "cbz, cbr";
    private static final String SUPPORTED_FORMATS_DESCRIPTION = "Comic Book Archives";

    /**
     * Constructeur de la classe App.
     * Initialise l'interface graphique et lance l'application.
     */
    public App() {
        MainWindow MainWindow = new MainWindow(APP_NAME + " " + APP_VERSION);
        // listen for window closing event
        MainWindow.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                MainWindow.windowClosing(windowEvent);
                System.exit(0);
            }
        });
        MainWindow.setVisible(true);
    }

    /**
     * Accesseur en lecture du nom de l'application
     * @return Nom de l'application
     */
    public String getAppName() {return APP_NAME;}
    /**
     * Accesseur en lecture de la version de l'application
     * @return Version de l'application
     */
    public String getAppVersion() {return APP_VERSION;}
    /**
     * Accesseur en lecture de l'auteur de l'application
     * @return Auteur de l'application
     */
    public String getAppAuthor() {return APP_AUTHOR;}
    /**
     * Accesseur en lecture des formats supportés par l'application
     * @return Formats supportés par l'application
     */
    public String getSupportedFormats() {return SUPPORTED_FORMATS;}
    /**
     * Accesseur en lecture de la description des formats supportés par l'application
     * @return Description des formats supportés par l'application
     */
    public String getSupportedFormatsDescription() {return SUPPORTED_FORMATS_DESCRIPTION;}

    
    /** 
     * Méthode principale de l'application.
     * Initialise l'interface graphique et lance l'application.
     * @param args Arguments passés en ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        // Set the look and feel to FlatLaf
        // FlatDarkLaf.setup();
        FlatOneDarkIJTheme.setup();
        // FlatNordIJTheme.setup();
        // Wait for the ui to be ready and launch app
        System.setProperty("Xmx", "2048m");
        javax.swing.SwingUtilities.invokeLater(App::new);

    }
}
