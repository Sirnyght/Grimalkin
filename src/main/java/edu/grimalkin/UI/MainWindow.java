package edu.grimalkin.UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;
import java.io.File;


// import net.miginfocom.swing.*;
// import net.miginfocom.layout.*;
import edu.grimalkin.data.Comic;
import edu.grimalkin.util.JTabbedPaneCloseButton;

public class MainWindow extends JFrame {
    private static final long serialVersionUID = 1L;

    public MainWindow(String title) {
        initWindow(title);
        initMenuBar();
		initContent();
    }

    private void initWindow(String title) {
        setTitle("Grimalkin");
		//set icon to /icons/chat3.png
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/chat3.png")));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
    }

    private void initMenuBar() {
		JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu();
		JMenuItem openMenuItem = new JMenuItem();
		JMenuItem closeMenuItem = new JMenuItem();
		JMenuItem exitMenuItem = new JMenuItem();
		JMenu editMenu = new JMenu();
		JMenu rotateMenu = new JMenu();
		JMenuItem noRotationMenuItem = new JMenuItem();
		JMenuItem rotate90MenuItem = new JMenuItem();
		JMenuItem rotate180MenuItem = new JMenuItem();
		JMenuItem rotate270MenuItem = new JMenuItem();
		JMenu readMenu = new JMenu();
		JMenuItem firstPageMenuItem = new JMenuItem();
		JMenuItem previousPageMenuItem = new JMenuItem();
		JMenuItem nextPageMenuItem = new JMenuItem();
		JMenuItem lastPageMenuItem = new JMenuItem();
		JMenu viewMenu = new JMenu();
		JMenu pageLayoutMenu = new JMenu();
		JMenuItem originalFitMenuItem = new JMenuItem();
		JMenuItem fitToWidthMenuItem = new JMenuItem();
		JMenuItem fitToHeightMenuItem = new JMenuItem();
		JMenuItem fitToScreenMenuItem = new JMenuItem();
		JMenuItem singlePageMenuItem = new JMenuItem();
		JMenuItem doublePageMenuItem = new JMenuItem();
		JMenuItem rightToLeftMenuItem = new JMenuItem();
		JMenu zoomMenu = new JMenu();
		JMenuItem zoomInMenuItem = new JMenuItem();
		JMenuItem zoomOutMenuItem = new JMenuItem();
		JMenuItem zoomResetMenuItem = new JMenuItem();
		JMenuItem zoomCustomMenuItem = new JMenuItem();
		JMenu helpMenu = new JMenu();
		JMenuItem shortcutsMenuItem = new JMenuItem();
		JMenuItem aboutMenuItem = new JMenuItem();

        // menuBar 
		{
			// fileMenu 
			{
				fileMenu.setText("File");
				fileMenu.setMnemonic((int)'F');
            
				//---- openMenuItem ----
				openMenuItem.setText("Open...");
				openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
				openMenuItem.setMnemonic((int)'O');
				openMenuItem.addActionListener(e -> openActionPerformed());
				fileMenu.add(openMenuItem);

				//---- closeMenuItem ----
				closeMenuItem.setText("Close");
				closeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
				closeMenuItem.setMnemonic('C');
				closeMenuItem.addActionListener(e -> closeActionPerformed());
				fileMenu.add(closeMenuItem);
				fileMenu.addSeparator();

				//---- exitMenuItem ----
				exitMenuItem.setText("Exit");
				exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
				exitMenuItem.setMnemonic('X');
				exitMenuItem.addActionListener(e -> exitActionPerformed());
				fileMenu.add(exitMenuItem);
			}
			menuBar.add(fileMenu);
			// editMenu
			{
				editMenu.setText("Edit");
				editMenu.setMnemonic((int)'E');
				// Rotate
				rotateMenu.setText("Rotate");
				rotateMenu.setMnemonic((int)'R');
				{
					// No Rotation
					noRotationMenuItem.setText("No Rotation");
					// Shortcut is CTRL MAJ 1
					noRotationMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx() | InputEvent.SHIFT_DOWN_MASK));
					noRotationMenuItem.setMnemonic((int)'N');
					noRotationMenuItem.addActionListener(e -> menuItemActionPerformed(e));
					rotateMenu.add(noRotationMenuItem);

					// Rotate 90
					rotate90MenuItem.setText("90\u00b0");
					// Shortcut is CTRL MAJ 2
					rotate90MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx() | InputEvent.SHIFT_DOWN_MASK));
					// rotate90MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
					// rotate90MenuItem.setMnemonic((int)'9');
					rotate90MenuItem.addActionListener(e -> menuItemActionPerformed(e));
					rotateMenu.add(rotate90MenuItem);
					
					// Rotate 180
					rotate180MenuItem.setText("180\u00b0");
					// Shortcut is CTRL MAJ 3
					rotate180MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx() | InputEvent.SHIFT_DOWN_MASK));
					// rotate180MenuItem.setMnemonic((int)'8');
					rotate180MenuItem.addActionListener(e -> menuItemActionPerformed(e));
					rotateMenu.add(rotate180MenuItem);

					// Rotate 270
					rotate270MenuItem.setText("270\u00b0");
					// Shortcut is CTRL MAJ 4
					rotate270MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx() | InputEvent.SHIFT_DOWN_MASK));
					// rotate270MenuItem.setMnemonic((int)'7');
					rotate270MenuItem.addActionListener(e -> menuItemActionPerformed(e));
					rotateMenu.add(rotate270MenuItem);
					editMenu.add(rotateMenu);
				}
			}
			menuBar.add(editMenu);
			// read menu
			{
				readMenu.setText("Read");
				readMenu.setMnemonic((int)'R');
				// First page
				firstPageMenuItem.setText("First Page");
				firstPageMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
				firstPageMenuItem.setMnemonic((int)'F');
				firstPageMenuItem.addActionListener(e -> menuItemActionPerformed(e));
				readMenu.add(firstPageMenuItem);
				// Previous page
				previousPageMenuItem.setText("Previous Page");
				previousPageMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
				previousPageMenuItem.setMnemonic((int)'P');
				previousPageMenuItem.addActionListener(e -> menuItemActionPerformed(e));
				readMenu.add(previousPageMenuItem);
				// Next page
				nextPageMenuItem.setText("Next Page");
				nextPageMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
				nextPageMenuItem.setMnemonic((int)'N');
				nextPageMenuItem.addActionListener(e -> menuItemActionPerformed(e));
				readMenu.add(nextPageMenuItem);
				// Last page
				lastPageMenuItem.setText("Last Page");
				lastPageMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
				lastPageMenuItem.setMnemonic((int)'L');
				lastPageMenuItem.addActionListener(e -> menuItemActionPerformed(e));
				readMenu.add(lastPageMenuItem);
			}
			menuBar.add(readMenu);
			// view menu
			{
				viewMenu.setText("View");
				viewMenu.setMnemonic((int)'V');
				// Page Layout menu
				pageLayoutMenu.setText("Page Layout");
				pageLayoutMenu.setMnemonic((int)'P');
				{
					// Original fit
					originalFitMenuItem.setText("Original Fit");
					originalFitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
					originalFitMenuItem.setMnemonic((int)'O');
					originalFitMenuItem.addActionListener(e -> menuItemActionPerformed(e));
					pageLayoutMenu.add(originalFitMenuItem);
					// Zoom to fit screen
					fitToScreenMenuItem.setText("Fit to Screen");
					fitToScreenMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
					fitToScreenMenuItem.setMnemonic((int)'F');
					fitToScreenMenuItem.addActionListener(e -> menuItemActionPerformed(e));
					pageLayoutMenu.add(fitToScreenMenuItem);
					// Zoom to fit width
					fitToWidthMenuItem.setText("Fit to Width");
					fitToWidthMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_9, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
					fitToWidthMenuItem.setMnemonic((int)'W');
					fitToWidthMenuItem.addActionListener(e -> menuItemActionPerformed(e));
					pageLayoutMenu.add(fitToWidthMenuItem);
					// Zoom to fit height
					fitToHeightMenuItem.setText("Fit to Height");
					fitToHeightMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_8, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
					fitToHeightMenuItem.setMnemonic((int)'H');
					fitToHeightMenuItem.addActionListener(e -> menuItemActionPerformed(e));
					pageLayoutMenu.add(fitToHeightMenuItem);
					// separator
					pageLayoutMenu.addSeparator();
					// Single page
					singlePageMenuItem.setText("Single Page");
					singlePageMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
					singlePageMenuItem.setMnemonic((int)'S');
					singlePageMenuItem.addActionListener(e -> menuItemActionPerformed(e));
					pageLayoutMenu.add(singlePageMenuItem);
					// Double page
					doublePageMenuItem.setText("Two Pages");
					doublePageMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
					doublePageMenuItem.setMnemonic((int)'D');
					doublePageMenuItem.addActionListener(e -> menuItemActionPerformed(e));
					pageLayoutMenu.add(doublePageMenuItem);
					// Right to left
					rightToLeftMenuItem.setText("Right to Left");
					rightToLeftMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
					rightToLeftMenuItem.setMnemonic((int)'R');
					rightToLeftMenuItem.addActionListener(e -> menuItemActionPerformed(e));
					pageLayoutMenu.add(rightToLeftMenuItem);
				}
				viewMenu.add(pageLayoutMenu);

				// Zoom menu
				zoomMenu.setText("Zoom");
				zoomMenu.setMnemonic((int)'Z');
				{
					// Zoom in
					zoomInMenuItem.setText("Zoom In");
					zoomInMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
					zoomInMenuItem.setMnemonic((int)'I');
					zoomInMenuItem.addActionListener(e -> menuItemActionPerformed(e));
					zoomMenu.add(zoomInMenuItem);
					// Zoom out
					zoomOutMenuItem.setText("Zoom Out");
					zoomOutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
					zoomOutMenuItem.setMnemonic((int)'O');
					zoomOutMenuItem.addActionListener(e -> menuItemActionPerformed(e));
					zoomMenu.add(zoomOutMenuItem);
					// Zoom reset
					zoomResetMenuItem.setText("Zoom Reset");
					zoomResetMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
					zoomResetMenuItem.setMnemonic((int)'R');
					zoomResetMenuItem.addActionListener(e -> menuItemActionPerformed(e));
					zoomMenu.add(zoomResetMenuItem);
					// Zoom custom
					zoomCustomMenuItem.setText("Zoom Custom");
					zoomCustomMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
					zoomCustomMenuItem.setMnemonic((int)'C');
					zoomCustomMenuItem.addActionListener(e -> menuItemActionPerformed(e));
					zoomMenu.add(zoomCustomMenuItem);
				}
				viewMenu.add(zoomMenu);
			}
			menuBar.add(viewMenu);
			// help menu
			{
				helpMenu.setText("Help");
				helpMenu.setMnemonic((int)'H');
				// Shortcuts
				shortcutsMenuItem.setText("Shortcuts");
				shortcutsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_SLASH, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
				shortcutsMenuItem.setMnemonic((int)'S');
				shortcutsMenuItem.addActionListener(e -> menuItemActionPerformed(e));
				helpMenu.add(shortcutsMenuItem);
				// About
				aboutMenuItem.setText("About");
				aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
				aboutMenuItem.setMnemonic((int)'A');
				aboutMenuItem.addActionListener(e -> menuItemActionPerformed(e));
				helpMenu.add(aboutMenuItem);
			}
			menuBar.add(helpMenu);
		}
        setJMenuBar(menuBar); 
    }
	
	private void initContent() {
		// Display hello world in the center of the window
		// JLabel label = new JLabel("Hello World");
		// label.setHorizontalAlignment(SwingConstants.CENTER);
		// getContentPane().add(label, BorderLayout.CENTER);
		JSplitPane splitPane = new JSplitPane();
		JPanel libraryPanel = new JPanel();
		JPanel comicPagesPanel = new JPanel();
		JPanel focusPagePanel = new JPanel();
		JTabbedPaneCloseButton rightPane = new JTabbedPaneCloseButton();
		JTabbedPane leftPane = new JTabbedPane();
		
		splitPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		splitPane.setResizeWeight(0.2);
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		// Left panel
		{	
			// add library panel and comic pages panel to left pane
			leftPane.addTab("Library", libraryPanel);
			// leftPane.addTab("Pages", comicPagesPanel);
			libraryPanel.setLayout(new BorderLayout());
			comicPagesPanel.setLayout(new BorderLayout());
			// add label "no comic selected" to comic pages panel
			JLabel noComicSelectedLabel = new JLabel("No comic selected");
			noComicSelectedLabel.setHorizontalAlignment(SwingConstants.CENTER);
			comicPagesPanel.add(noComicSelectedLabel, BorderLayout.CENTER);
		}
		splitPane.setLeftComponent(leftPane);

		// Right panel
		{
			// add focus page panel to right pane
			rightPane.addTab("Quickstart", focusPagePanel);

			// set titled border
			focusPagePanel.setLayout(new BorderLayout());
			focusPagePanel.setBorder(BorderFactory.createTitledBorder("Quick start"));
			
			// setup image view
			ImageIcon imageIcon = new ImageIcon("src/main/resources/icons/grimalkin_logo.png");
			imageIcon.setImage(imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
			JLabel imageLabel = new JLabel(imageIcon);
			
			imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
			focusPagePanel.add(imageLabel, BorderLayout.CENTER);
		}
		splitPane.setRightComponent(rightPane);		
	}

	private void openActionPerformed() {
		JFileChooser fileChooser = new JFileChooser();
		// filter formats
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Comic Book Archive (.cbz, .cbr)", "cbz", "cbr");
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(filter);
		// get selected file
		int returnVal = fileChooser.showOpenDialog(this);
		// if open is clicked
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			// print file 
			System.out.println("Opening: " + file.getName() + ".");
		}
		else {
			// do nothing
		}
	}

	private void closeActionPerformed() {
		// close file
		System.out.println("Closing file.");
	}

	private void exitActionPerformed() {
		dispose();	
	}

    private void menuItemActionPerformed( ActionEvent e ) {
		SwingUtilities.invokeLater( () -> {
			JOptionPane.showMessageDialog( this, e.getActionCommand(), "Menu Item", JOptionPane.PLAIN_MESSAGE );
		});
	}
}