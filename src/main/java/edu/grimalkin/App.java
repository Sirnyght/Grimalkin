package edu.grimalkin;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


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

    
    public String getAppName() {return APP_NAME;}
    public String getAppVersion() {return APP_VERSION;}
    public String getAppAuthor() {return APP_AUTHOR;}
    public String getSupportedFormats() {return SUPPORTED_FORMATS;}
    public String getSupportedFormatsDescription() {return SUPPORTED_FORMATS_DESCRIPTION;}

    
    /** 
     * Méthode principale de l'application.
     * Initialise l'interface graphique et lance l'application.
     * @param args
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
