package edu.grimalkin;

// import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;

import edu.grimalkin.ui.MainWindow;


public class App {
    private static final String APP_NAME = "Grimalkin";
    private static final String APP_VERSION = "0.1a";
    private static final String APP_AUTHOR = "Dimitri Hude & Laure Blanchard";
    // private static final String SUPPORTED_FORMATS = "cbz, cbr, zip, rar";
    private static final String SUPPORTED_FORMATS = "cbz, cbr";
    private static final String SUPPORTED_FORMATS_DESCRIPTION = "Comic Book Archives";

    public App() {
        MainWindow MainWindow = new MainWindow(APP_NAME + " " + APP_VERSION);
        MainWindow.setVisible(true);
    }

    public String getAppName() {return APP_NAME;}
    public String getAppVersion() {return APP_VERSION;}
    public String getAppAuthor() {return APP_AUTHOR;}
    public String getSupportedFormats() {return SUPPORTED_FORMATS;}
    public String getSupportedFormatsDescription() {return SUPPORTED_FORMATS_DESCRIPTION;}

    public static void main(String[] args) {
        // Set the look and feel to FlatLaf
        // FlatDarkLaf.setup();
        FlatOneDarkIJTheme.setup();
        // FlatNordIJTheme.setup();
        // Wait for the ui to be ready and launch app
        javax.swing.SwingUtilities.invokeLater(App::new);

    }
}
