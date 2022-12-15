package edu.grimalkin.ui;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.awt.image.BufferedImage;

import edu.grimalkin.util.ZipUtil;
import edu.grimalkin.util.JSONUtil;
import edu.grimalkin.data.*;

public class MainWindow extends JFrame {
	private Library library; 
	private JSplitPane splitPane = new JSplitPane();
	// Left pane
	private JTabbedPane leftPane = new JTabbedPane();
	private JPanel libraryPanel = new JPanel();
	private JScrollPane libraryScrollPane = new JScrollPane();
	private JPanel thumbnailsPanel = new JPanel();
	// Right pane
	private JTabbedPaneCloseButton rightPane = new JTabbedPaneCloseButton();
	private JPanel quickstartPanel = new JPanel();
	private final JList<String> shortcutsList = new JList<>(new String[] {
		"Open: Ctrl + O",
		"Close: Ctrl + W",
		"Exit: Ctrl + Q",
		"No Rotation (0): Ctrl + Alt + 1",
		"90 degrees clockwise (1): Ctrl + Alt + 2",
		"180 degrees (2): Ctrl + Alt + 3",
		"90 degrees counterclockwise (3): Ctrl + Alt + 4",
		"First page: Ctrl + B",
		"Previous page: Ctrl + P, Left arrow",
		"Next page: Ctrl + B, Right arrow",
		"Last page: Ctrl + L",
		"Zoom in: Ctrl + +",
		"Zoom out: Ctrl + -",
		"Custom zoom: Ctrl + 0",
		"Original fit: Ctrl + Alt + O",
		"Zoom to fit width: Ctrl + Alt + W",
		"Zoom to fit height: Ctrl + Alt + H",
		"Shortcuts: Ctrl + /",
		"About: Ctrl + A"
	});

    public MainWindow(String title) {
		try {
			library = JSONUtil.readJSONFile();
			// display comics infos in the library
			library.displayComics();
			library.initComics();
		} catch (IOException e) {
			e.printStackTrace();
		}
        initWindow(title);
        initMenuBar();
		initContent();
    }
 
    private void initWindow(String title) {
        setTitle(title);
		//set icon to /icons/chat3.png
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/chat3.png")));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setResizable(true);
    }

    private void initMenuBar() {
		// Components initialization
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
		// JMenu pageLayoutMenu = new JMenu();
		JMenuItem originalFitMenuItem = new JMenuItem();
		JMenuItem fitToWidthMenuItem = new JMenuItem();
		JMenuItem fitToHeightMenuItem = new JMenuItem();
		// JMenuItem fitToScreenMenuItem = new JMenuItem();
		// JMenuItem singlePageMenuItem = new JMenuItem();
		// JMenuItem doublePageMenuItem = new JMenuItem();
		// JMenuItem rightToLeftMenuItem = new JMenuItem();
		JMenu zoomMenu = new JMenu();
		JMenuItem zoomInMenuItem = new JMenuItem();
		JMenuItem zoomOutMenuItem = new JMenuItem();
		JMenuItem zoomCustomMenuItem = new JMenuItem();
		JMenu helpMenu = new JMenu();
		JMenuItem shortcutsMenuItem = new JMenuItem();
		JMenuItem aboutMenuItem = new JMenuItem();

        // Menu Bar Setup
		{
			// File Menu Setup
			{
				fileMenu.setText("File");
				fileMenu.setMnemonic((int)'F');
				// Open Menu Item Setup
				{
					openMenuItem.setText("Open...");
					openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
					openMenuItem.setMnemonic((int)'O');
					openMenuItem.addActionListener(e -> openActionPerformed());
					fileMenu.add(openMenuItem);
				}
				// Close Menu Item Setup
				{
					closeMenuItem.setText("Close");
					closeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
					closeMenuItem.setMnemonic((int)'C');
					closeMenuItem.addActionListener(e -> closeActionPerformed());
					fileMenu.add(closeMenuItem);
				}
				fileMenu.addSeparator();
				// Exit Menu Item Setup
				{
					exitMenuItem.setText("Exit");
					exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
					exitMenuItem.setMnemonic('X');
					exitMenuItem.addActionListener(e -> exitActionPerformed());
					fileMenu.add(exitMenuItem);
				}
				menuBar.add(fileMenu);
			}
			// Edit Menu Setup
			{
				editMenu.setText("Edit");
				editMenu.setMnemonic((int)'E');
				// Rotate Menu Setup
				rotateMenu.setText("Rotate");
				rotateMenu.setMnemonic((int)'R');
				{
					// No Rotation Menu Item Setup 
					{
						noRotationMenuItem.setText("No Rotation");
						// Shortcut is CTRL ALT 1
						noRotationMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx() | InputEvent.ALT_DOWN_MASK));
						noRotationMenuItem.addActionListener(e -> noRotationActionPerformed());
						rotateMenu.add(noRotationMenuItem);
					}		
					// Rotate 90 Menu Item Setup
					{
						rotate90MenuItem.setText("90\u00b0");
						// Shortcut is CTRL ALT 2
						rotate90MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx() | InputEvent.ALT_DOWN_MASK));
						rotate90MenuItem.addActionListener(e -> RotationActionPerformed(e));
						rotateMenu.add(rotate90MenuItem);
					}
					// Rotate 180 Menu Item Setup
					{
						rotate180MenuItem.setText("180\u00b0");
						// Shortcut is CTRL ALT 3
						rotate180MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx() | InputEvent.ALT_DOWN_MASK));
						rotate180MenuItem.addActionListener(e -> RotationActionPerformed(e));
						rotateMenu.add(rotate180MenuItem);
					}
					// Rotate 270 Menu Item Setup
					{
						rotate270MenuItem.setText("270\u00b0");
						// Shortcut is CTRL ALT 4
						rotate270MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx() | InputEvent.ALT_DOWN_MASK));
						rotate270MenuItem.addActionListener(e -> RotationActionPerformed(e));
						rotateMenu.add(rotate270MenuItem);
						editMenu.add(rotateMenu);
					}
				}
				menuBar.add(editMenu);
			}
			// Read Menu Setup
			{
				readMenu.setText("Read");
				readMenu.setMnemonic((int)'R');
				// First Page Menu Item Setup
				{
					firstPageMenuItem.setText("First Page");
					firstPageMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
					firstPageMenuItem.setMnemonic((int)'F');
					firstPageMenuItem.addActionListener(e -> goToFirstPageActionPerformed());
					readMenu.add(firstPageMenuItem);
				}
				// Previous Page Menu Item Setup
				{
					previousPageMenuItem.setText("Previous Page");
					previousPageMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
					previousPageMenuItem.setMnemonic((int)'P');
					previousPageMenuItem.addActionListener(e -> previousPageActionPerformed());
					readMenu.add(previousPageMenuItem);
				}
				// Next Page Menu Item Setup
				{
					nextPageMenuItem.setText("Next Page");
					nextPageMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
					nextPageMenuItem.setMnemonic((int)'N');
					nextPageMenuItem.addActionListener(e -> nextPageActionPerformed());
					readMenu.add(nextPageMenuItem);
				}
				// Last Page Menu Item Setup
				{
					lastPageMenuItem.setText("Last Page");
					lastPageMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
					lastPageMenuItem.setMnemonic((int)'L');
					lastPageMenuItem.addActionListener(e -> goToLastPageActionPerformed());
					readMenu.add(lastPageMenuItem);
				}
				menuBar.add(readMenu);
			}
			// View Menu Setup
			{
				viewMenu.setText("View");
				viewMenu.setMnemonic((int)'V');
				// Zoom Menu Setup
				zoomMenu.setText("Zoom");
				zoomMenu.setMnemonic((int)'Z');
				{
					// Zoom in
					zoomInMenuItem.setText("Zoom In");
					zoomInMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
					zoomInMenuItem.setMnemonic((int)'I');
					zoomInMenuItem.addActionListener(e -> zoomInActionPerformed());
					zoomMenu.add(zoomInMenuItem);
					// Zoom out
					zoomOutMenuItem.setText("Zoom Out");
					zoomOutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
					zoomOutMenuItem.setMnemonic((int)'O');
					zoomOutMenuItem.addActionListener(e -> zoomOutActionPerformed());
					zoomMenu.add(zoomOutMenuItem);
					// Zoom custom
					zoomCustomMenuItem.setText("Zoom Custom");
					zoomCustomMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
					zoomCustomMenuItem.setMnemonic((int)'C');
					zoomCustomMenuItem.addActionListener(e -> customZoomActionPerformed());
					zoomMenu.add(zoomCustomMenuItem);
					// separator
					zoomMenu.addSeparator();
					// Original fit (best view)
					originalFitMenuItem.setText("Original Fit");
					// Shortcut is CTRL + ALT + O
					originalFitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx() | InputEvent.ALT_DOWN_MASK));
					originalFitMenuItem.setMnemonic((int)'O');
					originalFitMenuItem.addActionListener(e -> originalFitActionPerformed());
					zoomMenu.add(originalFitMenuItem);
					// Zoom to fit width
					fitToWidthMenuItem.setText("Fit to Width");
					// Shortcut is CTRL + ALT + W
					fitToWidthMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx() | InputEvent.ALT_DOWN_MASK));
					fitToWidthMenuItem.setMnemonic((int)'W');
					fitToWidthMenuItem.addActionListener(e -> fitToWidthActionPerformed());
					zoomMenu.add(fitToWidthMenuItem);
					// Zoom to fit height
					fitToHeightMenuItem.setText("Fit to Height");
					// Shortcut is CTRL + ALT + H
					fitToHeightMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx() | InputEvent.ALT_DOWN_MASK));
					fitToHeightMenuItem.setMnemonic((int)'H');
					fitToHeightMenuItem.addActionListener(e ->fitToHeightActionPerformed());
					zoomMenu.add(fitToHeightMenuItem);
					viewMenu.add(zoomMenu);
				}
				menuBar.add(viewMenu);
			}
			// help menu
			{
				helpMenu.setText("Help");
				helpMenu.setMnemonic((int)'H');
				// Shortcuts
				shortcutsMenuItem.setText("Shortcuts");
				// Shortcut is CTRL + /
				shortcutsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_SLASH, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));

				shortcutsMenuItem.setMnemonic((int)'S');
				shortcutsMenuItem.addActionListener(e -> shortcutsActionPerformed());
				helpMenu.add(shortcutsMenuItem);
				// About
				aboutMenuItem.setText("About");
				aboutMenuItem.setMnemonic((int)'A');
				aboutMenuItem.addActionListener(e -> aboutActionPerformed());
				helpMenu.add(aboutMenuItem);
				menuBar.add(helpMenu);
			}
			setJMenuBar(menuBar); 
		}
    }
	
	private void initContent() {
		splitPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		splitPane.setResizeWeight(0.2);
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		// Left Panel Setup
		{	
			// add library panel and comic pages panel to left pane
			leftPane.addTab("Library", libraryPanel);
			// leftPane.addTab("Pages", comicPagesPanel);
			libraryPanel.setLayout(new BorderLayout());
			libraryPanel.add(libraryScrollPane, BorderLayout.CENTER);
			libraryScrollPane.setViewportView(thumbnailsPanel);
			// setup scroll bars
			libraryScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			libraryScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			// speed up scrolling
			libraryScrollPane.getVerticalScrollBar().setUnitIncrement(16);
			// set layout for thumbnails panel;
			thumbnailsPanel.setLayout(new FlowLayout());
			// set thumbnailsPanel container size
			thumbnailsPanel.setPreferredSize(new Dimension(180, 0));
			// load library
			// for each comic in library
			for (Comic comic : library.getComics()) {
				displayThumbnail(comic, thumbnailsPanel);
			}
		}
		splitPane.setLeftComponent(leftPane);

		// Right Panel Setup
		{
			// add focus page panel to right pane
			rightPane.addTab("Quickstart", quickstartPanel);

			// set titled border
			quickstartPanel.setLayout(new BorderLayout());
			// List shortcuts on Quickstart tab
			shortcutsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			shortcutsList.setLayoutOrientation(JList.VERTICAL);
			shortcutsList.setVisibleRowCount(-1);
			quickstartPanel.add(new JScrollPane(shortcutsList), BorderLayout.CENTER);
		}
		splitPane.setRightComponent(rightPane);		
	}

	private void updateLibrary() {
		try {
			JSONUtil.writeJSONFile(library);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void updateComic(String title, int currentPage) {
		Comic comic = library.getComic(title);
		comic.setCurrentPage(currentPage);
		comic.setLastPageRead(currentPage);
		System.out.println("Updating comic " + title + " to page " + currentPage);
		updateLibrary();
	}

	private void displayThumbnail(Comic comic, JPanel thumbnailsPanel) {
		// Create new thumbnail
		JLabel thumbnail = new JLabel();
		thumbnail.setName(comic.getTitle());
		// set titled border
		thumbnail.setBorder(BorderFactory.createTitledBorder(comic.getTitle()));
		ImageIcon icon = new ImageIcon(comic.getCover());
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(100, -1,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		thumbnail.setIcon(icon);
		// add event listener to thumbnail
		thumbnail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// if left mouse button is clicked
				if (e.getButton() == MouseEvent.BUTTON1) {
					// if comic is already open
					for (int i = 0; i < rightPane.getTabCount(); i++) {
						if (rightPane.getTitleAt(i).equals(comic.getTitle())) {
							// select tab
							rightPane.setSelectedIndex(i);
							return;
						}
					}
					// if comic is not open
					// add new tab to right pane
					JPanel comicPagesPanel = new JPanel();
					JScrollPane comicPageScrollPane = new JScrollPane();
					JPanel comicPagePanel = new JPanel();
					{
						comicPagesPanel.setLayout(new BorderLayout());
						rightPane.addTab(comic.getTitle(), comicPagesPanel);
						rightPane.setSelectedIndex(rightPane.getTabCount() - 1);
					}
					// Comics Pane Setup
					{
						comicPagesPanel.setLayout(new BorderLayout());
						comicPageScrollPane.setBorder(BorderFactory.createTitledBorder("Page 1"));
						comicPageScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
						comicPageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
						// Speed up scrolling
						comicPageScrollPane.getVerticalScrollBar().setUnitIncrement(16);
						comicPageScrollPane.getHorizontalScrollBar().setUnitIncrement(16);
						comicPageScrollPane.setViewportView(comicPagePanel);
						comicPagePanel.setLayout(new BorderLayout());
					}
					comicPagesPanel.add(comicPageScrollPane, BorderLayout.CENTER);
					// add comic pages to comic pages panel
					Comic comic = library.getComic(thumbnail.getName());
					// Get Comic Page
					Page page = comic.getPages().get(comic.getCurrentPage());
					// Display Comic Page
					displayPage(page, comicPagePanel);
				}
			}
		});
		// add thumbnail to thumbnail panel
		thumbnailsPanel.add(thumbnail);
		// Update thumbnails panel
		thumbnailsPanel.revalidate();
		thumbnailsPanel.repaint();
	}

	private void displayPage(Page page, JPanel comicPagePanel) {
		// Get Scroll Pane
		// Remove all components from comicPagePanel
		comicPagePanel.removeAll();
		// Get Comic Page Image
		ImageIcon icon = new ImageIcon(page.getImage());
		// Resize only image width and keep aspect ratio	
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(600, -1,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		// New Comic Page Label
		JLabel comicPageLabel = new JLabel();
		// Add event listener to comic page label, on mouse click
		comicPageLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// if left mouse button is clicked
				if (e.getButton() == MouseEvent.BUTTON1) {
					// Open Comic Page in new window
					JFrame comicPageFrame = new JFrame();
					comicPageFrame.setTitle("Comic Page");
					comicPageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					comicPageFrame.setSize(600, 800);
					comicPageFrame.setLocationRelativeTo(null);
					comicPageFrame.setVisible(true);	
					// Add Scroll Pane to Comic Page Frame
					JScrollPane comicPageScrollPane = new JScrollPane();
					comicPageScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					comicPageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
					// Speed up scrolling
					comicPageScrollPane.getVerticalScrollBar().setUnitIncrement(16);
					comicPageScrollPane.getHorizontalScrollBar().setUnitIncrement(16);
					comicPageFrame.add(comicPageScrollPane);
					// Add Comic Page Panel to Scroll Pane
					JPanel comicPagePanel = new JPanel();
					comicPagePanel.setLayout(new BorderLayout());
					comicPageScrollPane.setViewportView(comicPagePanel);
					// Add Comic Page Label to Comic Page Panel
					JLabel comicPageLabelFullscreen = new JLabel();
					comicPageLabelFullscreen.setIcon(comicPageLabel.getIcon());
					comicPagePanel.add(comicPageLabelFullscreen, BorderLayout.CENTER);
					// Update Comic Page Frame
					comicPageFrame.revalidate();
					comicPageFrame.repaint();
					// On resize, resize comic page label
					comicPageFrame.addComponentListener(new ComponentAdapter() {
						@Override
						public void componentResized(ComponentEvent e) {
							// Get Comic Page Image
							ImageIcon icon = new ImageIcon(page.getImage());
							// Resize only image width and keep aspect ratio	
							Image img = icon.getImage();
							Image newimg = img.getScaledInstance(comicPageFrame.getWidth()-30, -1,  java.awt.Image.SCALE_SMOOTH);
							icon = new ImageIcon(newimg);
							// Set Comic Page Label Icon
							comicPageLabelFullscreen.setIcon(icon);
							// Update Comic Page Label
							comicPageLabelFullscreen.revalidate();
							comicPageLabelFullscreen.repaint();
						}
					});
				}
			}
		});

		// Set Comic Page Label Icon
		comicPageLabel.setIcon(icon);
		// Add Comic Page Label to Comic Page Panel
		comicPagePanel.add(comicPageLabel, BorderLayout.CENTER);
		// add event listener to scroll pane to change page when scroll further than the end of the page

		// Update Comic Page Panel
		comicPagePanel.revalidate();
		comicPagePanel.repaint();
	}
	


	private void openActionPerformed() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Comic Book Archive (.cbz, .cbr)", "cbz", "cbr");
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(filter);
		int returnVal = fileChooser.showOpenDialog(this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			for (Comic comic : library.getComics()) {
				System.out.println("Checking if comic is already in library.");
				System.out.println("Comic: " + comic.getTitle());
				System.out.println("File: " + file.getName());
				if (comic.getTitle().equals(file.getName())) {
					// Display dialog box
					JOptionPane.showMessageDialog(this, "Comic already in library. Please open it from there.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			JPanel newTab = new JPanel();
			JScrollPane comicPageScrollPane = new JScrollPane();
			JPanel comicPagePanel = new JPanel();
			System.out.println("Opening: " + file.getName() + ".");
			// New Tab Setup
			{
				newTab.setLayout(new BorderLayout());
				rightPane.addTab(file.getName(), newTab);
				rightPane.setSelectedIndex(rightPane.getTabCount() - 1);
			}
			// Comics Pane Setup
			{
				newTab.setLayout(new BorderLayout());
				comicPageScrollPane.setBorder(BorderFactory.createTitledBorder("Page 1"));
				comicPageScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				comicPageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				// Speed up scrolling
				comicPageScrollPane.getVerticalScrollBar().setUnitIncrement(16);
				comicPageScrollPane.getHorizontalScrollBar().setUnitIncrement(16);
				comicPageScrollPane.setViewportView(comicPagePanel);
				comicPagePanel.setLayout(new BorderLayout());
			}
			newTab.add(comicPageScrollPane, BorderLayout.CENTER);
			try {
				List<Page> pages = new ArrayList<Page>();
				ZipUtil.unzip(file, pages);
				Comic unzippedComic = new Comic(file.getName(), file.getAbsolutePath(), pages);
				library.addComic(unzippedComic);
				updateLibrary();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// Display New Comic in Library
			{
				displayThumbnail(library.getComic(file.getName()), thumbnailsPanel);
			}
			// Display Comic in New Tab
			{
				// Get Comic
				System.out.println(file.getName());
				// Print Pages Ids to console
				for (int i = 0; i < library.getComic(file.getName()).getPages().size(); i++) {
					System.out.println(library.getComic(file.getName()).getPages().get(i).getId());
				}
				Comic comic = library.getComic(file.getName());
				// Get Comic Page
				Page page = comic.getPages().get(comic.getCurrentPage());
				// Display Comic Page
				displayPage(page, comicPagePanel);
			}
		}
	}

	private void closeActionPerformed() {
		// get selected tab 
		int selectedTab = rightPane.getSelectedIndex();
		// get selected tab title
		String selectedTabTitle = rightPane.getTitleAt(selectedTab);
		// save comic current page
		updateComic(selectedTabTitle, library.getComic(selectedTabTitle).getCurrentPage());
		// Close Tab
		rightPane.remove(rightPane.getSelectedIndex());

	}

	private void exitActionPerformed() {
		// Close all tabs
		closeAllTabs();
		// Exit
		System.exit(0);
	}

	private void noRotationActionPerformed() {
		// get selected tab
		int selectedTab = rightPane.getSelectedIndex();
		// get image icon from selected tab
		ImageIcon icon = (ImageIcon) ((JLabel) ((JScrollPane) ((JPanel) rightPane.getComponentAt(selectedTab)).getComponent(0)).getViewport().getView()).getIcon();
		// set image icon to corresponding comic current page
		library.getComic(rightPane.getTitleAt(selectedTab)).getPages().get(library.getComic(rightPane.getTitleAt(selectedTab)).getCurrentPage()).setImage(icon.getImage());	
	}

	private void RotationActionPerformed(ActionEvent e) {
		// get selected tab
		int selectedTab = rightPane.getSelectedIndex();
		// get image icon from selected tab
		ImageIcon icon = (ImageIcon) ((JLabel) ((JScrollPane) ((JPanel) rightPane.getComponentAt(selectedTab)).getComponent(0)).getViewport().getView()).getIcon();
		// rotate image icon by e degrees
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(img.getWidth(null), img.getHeight(null),  java.awt.Image.SCALE_SMOOTH);
		AffineTransform tx = new AffineTransform();
		tx.rotate(Math.toRadians(Integer.parseInt(e.getActionCommand())), newimg.getWidth(null)/2, newimg.getHeight(null)/2);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		newimg = op.filter((BufferedImage) newimg, null);
		icon = new ImageIcon(newimg);
		// update image icon
		((JLabel) ((JScrollPane) ((JPanel) rightPane.getComponentAt(selectedTab)).getComponent(0)).getViewport().getView()).setIcon(icon);
	}

	private void nextPageActionPerformed() {
		// get selected tab
		int selectedTab = rightPane.getSelectedIndex();
		// get selected tab title
		String selectedTabTitle = rightPane.getTitleAt(selectedTab);
		// save comic current page
		updateComic(selectedTabTitle, library.getComic(selectedTabTitle).getCurrentPage());
		// get comic
		Comic comic = library.getComic(selectedTabTitle);
		// next page 
		comic.nextPage();
		// get comic page
		Page page = comic.getPages().get(comic.getCurrentPage());
		// get comic page image
		ImageIcon icon = new ImageIcon(page.getImage());
		// resize only image width and keep aspect ratio
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(600, -1,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		// get comic page scroll panel 
		JScrollPane comicPageScrollPanel = (JScrollPane) ((JPanel) rightPane.getComponentAt(selectedTab)).getComponent(0);
		// get comic page panel
		JPanel comicPagePanel = (JPanel) comicPageScrollPanel.getViewport().getView();
		// get comic page label from comic page panel
		JLabel comicPageLabel = (JLabel) comicPagePanel.getComponent(0);
		// set comic page label icon
		comicPageLabel.setIcon(icon);
		// update comic page panel
		comicPagePanel.revalidate();
		comicPagePanel.repaint();
		updateComic(selectedTabTitle, comic.getCurrentPage());
	}

	private void previousPageActionPerformed() {
		// get selected tab
		int selectedTab = rightPane.getSelectedIndex();
		// get selected tab title
		String selectedTabTitle = rightPane.getTitleAt(selectedTab);
		// save comic current page
		updateComic(selectedTabTitle, library.getComic(selectedTabTitle).getCurrentPage());
		// get comic
		Comic comic = library.getComic(selectedTabTitle);
		// previous page 
		comic.previousPage();
		// get comic page
		Page page = comic.getPages().get(comic.getCurrentPage());
		// get comic page image
		ImageIcon icon = new ImageIcon(page.getImage());
		// resize only image width and keep aspect ratio
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(600, -1,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		// get comic page scroll panel 
		JScrollPane comicPageScrollPanel = (JScrollPane) ((JPanel) rightPane.getComponentAt(selectedTab)).getComponent(0);
		// get comic page panel
		JPanel comicPagePanel = (JPanel) comicPageScrollPanel.getViewport().getView();
		// get comic page label from comic page panel
		JLabel comicPageLabel = (JLabel) comicPagePanel.getComponent(0);
		// set comic page label icon
		comicPageLabel.setIcon(icon);
		// update comic page panel
		comicPagePanel.revalidate();
		comicPagePanel.repaint();
		updateComic(selectedTabTitle, comic.getCurrentPage());
	}

	private void goToFirstPageActionPerformed() {
		// get selected tab
		int selectedTab = rightPane.getSelectedIndex();
		// get selected tab title
		String selectedTabTitle = rightPane.getTitleAt(selectedTab);
		// save comic current page
		updateComic(selectedTabTitle, library.getComic(selectedTabTitle).getCurrentPage());
		// get comic
		Comic comic = library.getComic(selectedTabTitle);
		// go to first page 
		comic.setCurrentPage(0);
		// get comic page
		Page page = comic.getPages().get(comic.getCurrentPage());
		// get comic page image
		ImageIcon icon = new ImageIcon(page.getImage());
		// resize only image width and keep aspect ratio
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(600, -1,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		// get comic page scroll panel 
		JScrollPane comicPageScrollPanel = (JScrollPane) ((JPanel) rightPane.getComponentAt(selectedTab)).getComponent(0);
		// get comic page panel
		JPanel comicPagePanel = (JPanel) comicPageScrollPanel.getViewport().getView();
		// get comic page label from comic page panel
		JLabel comicPageLabel = (JLabel) comicPagePanel.getComponent(0);
		// set comic page label icon
		comicPageLabel.setIcon(icon);
		// update comic page panel
		comicPagePanel.revalidate();
		comicPagePanel.repaint();
		updateComic(selectedTabTitle, comic.getCurrentPage());
	}

	private void goToLastPageActionPerformed() {
		// get selected tab
		int selectedTab = rightPane.getSelectedIndex();
		// get selected tab title
		String selectedTabTitle = rightPane.getTitleAt(selectedTab);
		// save comic current page
		updateComic(selectedTabTitle, library.getComic(selectedTabTitle).getCurrentPage());
		// get comic
		Comic comic = library.getComic(selectedTabTitle);
		// go to last page 
		comic.setCurrentPage(comic.getPages().size() - 1);
		// get comic page
		Page page = comic.getPages().get(comic.getCurrentPage());
		// get comic page image
		ImageIcon icon = new ImageIcon(page.getImage());
		// resize only image width and keep aspect ratio
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(600, -1,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		// get comic page scroll panel 
		JScrollPane comicPageScrollPanel = (JScrollPane) ((JPanel) rightPane.getComponentAt(selectedTab)).getComponent(0);
		// get comic page panel
		JPanel comicPagePanel = (JPanel) comicPageScrollPanel.getViewport().getView();
		// get comic page label from comic page panel
		JLabel comicPageLabel = (JLabel) comicPagePanel.getComponent(0);
		// set comic page label icon
		comicPageLabel.setIcon(icon);
		// update comic page panel
		comicPagePanel.revalidate();
		comicPagePanel.repaint();
		updateComic(selectedTabTitle, comic.getCurrentPage());
	}

	private void zoomInActionPerformed() {
		// get selected tab
		int selectedTab = rightPane.getSelectedIndex();
		// get selected tab title
		String selectedTabTitle = rightPane.getTitleAt(selectedTab);
		// get comic page scroll panel 
		JScrollPane comicPageScrollPanel = (JScrollPane) ((JPanel) rightPane.getComponentAt(selectedTab)).getComponent(0);
		// get comic page panel
		JPanel comicPagePanel = (JPanel) comicPageScrollPanel.getViewport().getView();
		// get comic page label from comic page panel
		JLabel comicPageLabel = (JLabel) comicPagePanel.getComponent(0);
		// get comic
		Comic comic = library.getComic(selectedTabTitle);
		// get comic page
		Page page = comic.getPages().get(comic.getCurrentPage());
		// get comic page icon from panel
		ImageIcon icon = (ImageIcon) comicPageLabel.getIcon();
		// zoom in
		Image newimg = page.getImage().getScaledInstance((int) (icon.getIconWidth() * 1.1), -1,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		// set comic page label icon
		comicPageLabel.setIcon(icon);
		// update comic page panel
		comicPagePanel.revalidate();
		comicPagePanel.repaint();
	}

	private void zoomOutActionPerformed() {
		// get selected tab
		int selectedTab = rightPane.getSelectedIndex();
		// get selected tab title
		String selectedTabTitle = rightPane.getTitleAt(selectedTab);
		// get comic page scroll panel 
		JScrollPane comicPageScrollPanel = (JScrollPane) ((JPanel) rightPane.getComponentAt(selectedTab)).getComponent(0);
		// get comic page panel
		JPanel comicPagePanel = (JPanel) comicPageScrollPanel.getViewport().getView();
		// get comic page label from comic page panel
		JLabel comicPageLabel = (JLabel) comicPagePanel.getComponent(0);
		// get comic
		Comic comic = library.getComic(selectedTabTitle);
		// get comic page
		Page page = comic.getPages().get(comic.getCurrentPage());
		// get comic page icon from panel
		ImageIcon icon = (ImageIcon) comicPageLabel.getIcon();
		// zoom in
		Image newimg = page.getImage().getScaledInstance((int) (icon.getIconWidth() * 0.9), -1,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		// set comic page label icon
		comicPageLabel.setIcon(icon);
		// update comic page panel
		comicPagePanel.revalidate();
		comicPagePanel.repaint();
	}

	private void customZoomActionPerformed() {
		// get selected tab
		int selectedTab = rightPane.getSelectedIndex();
		// get selected tab title
		String selectedTabTitle = rightPane.getTitleAt(selectedTab);
		// get comic page scroll panel 
		JScrollPane comicPageScrollPanel = (JScrollPane) ((JPanel) rightPane.getComponentAt(selectedTab)).getComponent(0);
		// get comic page panel
		JPanel comicPagePanel = (JPanel) comicPageScrollPanel.getViewport().getView();
		// get comic page label from comic page panel
		JLabel comicPageLabel = (JLabel) comicPagePanel.getComponent(0);
		// get comic
		Comic comic = library.getComic(selectedTabTitle);
		// get comic page
		Page page = comic.getPages().get(comic.getCurrentPage());
		// get comic page icon from panel
		ImageIcon icon = (ImageIcon) comicPageLabel.getIcon();
		// get custom zoom value
		String customZoomValue = JOptionPane.showInputDialog("Enter custom zoom value (0.1 - 10.0):");
		// zoom custom
		Image newimg = page.getImage().getScaledInstance((int) (icon.getIconWidth() * Double.parseDouble(customZoomValue)), -1,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		// set comic page label icon
		comicPageLabel.setIcon(icon);
		// update comic page panel
		comicPagePanel.revalidate();
		comicPagePanel.repaint();
	}

	private void fitToWidthActionPerformed() {
		// get selected tab
		int selectedTab = rightPane.getSelectedIndex();
		// get selected tab title
		String selectedTabTitle = rightPane.getTitleAt(selectedTab);
		// get comic page scroll panel 
		JScrollPane comicPageScrollPanel = (JScrollPane) ((JPanel) rightPane.getComponentAt(selectedTab)).getComponent(0);
		// get comic page panel
		JPanel comicPagePanel = (JPanel) comicPageScrollPanel.getViewport().getView();
		// get comic page label from comic page panel
		JLabel comicPageLabel = (JLabel) comicPagePanel.getComponent(0);
		// get comic
		Comic comic = library.getComic(selectedTabTitle);
		// get comic page
		Page page = comic.getPages().get(comic.getCurrentPage());
		// get comic page icon from panel
		ImageIcon icon = (ImageIcon) comicPageLabel.getIcon();
		// get scroll pane width
		int scrollPaneWidth = comicPageScrollPanel.getWidth();
		// get scroll pane height
		int scrollPaneHeight = comicPageScrollPanel.getHeight();
		// get image width
		int imageWidth = page.getImage().getWidth(null);
		// get image height
		int imageHeight = page.getImage().getHeight(null);
		// get image aspect ratio
		double imageAspectRatio = (double) imageWidth / (double) imageHeight;
		// get scroll pane aspect ratio
		double scrollPaneAspectRatio = (double) scrollPaneWidth / (double) scrollPaneHeight;
		// zoom to width only
		System.out.println("image width: " + imageWidth);
		System.out.println("image height: " + imageHeight);
		System.out.println("image aspect ratio: " + imageAspectRatio);
		System.out.println("scroll pane width: " + scrollPaneWidth);
		System.out.println("scroll pane height: " + scrollPaneHeight);
		System.out.println("scroll pane aspect ratio: " + scrollPaneAspectRatio);
		Image newimg = page.getImage().getScaledInstance(scrollPaneWidth-20, -1,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		// set comic page label icon
		comicPageLabel.setIcon(icon);
		// update comic page panel
		comicPagePanel.revalidate();
		comicPagePanel.repaint();
	}

	private void fitToHeightActionPerformed() {
		// get selected tab
		int selectedTab = rightPane.getSelectedIndex();
		// get selected tab title
		String selectedTabTitle = rightPane.getTitleAt(selectedTab);
		// get comic page scroll panel 
		JScrollPane comicPageScrollPanel = (JScrollPane) ((JPanel) rightPane.getComponentAt(selectedTab)).getComponent(0);
		// get comic page panel
		JPanel comicPagePanel = (JPanel) comicPageScrollPanel.getViewport().getView();
		// get comic page label from comic page panel
		JLabel comicPageLabel = (JLabel) comicPagePanel.getComponent(0);
		// get comic
		Comic comic = library.getComic(selectedTabTitle);
		// get comic page
		Page page = comic.getPages().get(comic.getCurrentPage());
		// get comic page icon from panel
		ImageIcon icon = (ImageIcon) comicPageLabel.getIcon();
		// get scroll pane width
		int scrollPaneWidth = comicPageScrollPanel.getWidth();
		// get scroll pane height
		int scrollPaneHeight = comicPageScrollPanel.getHeight();
		// get image width
		int imageWidth = page.getImage().getWidth(null);
		// get image height
		int imageHeight = page.getImage().getHeight(null);
		// get image aspect ratio
		double imageAspectRatio = (double) imageWidth / (double) imageHeight;
		// get scroll pane aspect ratio
		double scrollPaneAspectRatio = (double) scrollPaneWidth / (double) scrollPaneHeight;
		System.out.println("image width: " + imageWidth);
		System.out.println("image height: " + imageHeight);
		System.out.println("image aspect ratio: " + imageAspectRatio);
		System.out.println("scroll pane width: " + scrollPaneWidth);
		System.out.println("scroll pane height: " + scrollPaneHeight);
		System.out.println("scroll pane aspect ratio: " + scrollPaneAspectRatio);
		// zoom to height
		Image newimg = page.getImage().getScaledInstance(-1, scrollPaneHeight-50,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		// set comic page label icon
		comicPageLabel.setIcon(icon);
		// update comic page panel
		comicPagePanel.revalidate();
		comicPagePanel.repaint();
	}

	private void originalFitActionPerformed() {
		// get selected tab
		int selectedTab = rightPane.getSelectedIndex();
		// get selected tab title
		String selectedTabTitle = rightPane.getTitleAt(selectedTab);
		// get comic page scroll panel 
		JScrollPane comicPageScrollPanel = (JScrollPane) ((JPanel) rightPane.getComponentAt(selectedTab)).getComponent(0);
		// get comic page panel
		JPanel comicPagePanel = (JPanel) comicPageScrollPanel.getViewport().getView();
		// get comic page label from comic page panel
		JLabel comicPageLabel = (JLabel) comicPagePanel.getComponent(0);
		// get comic
		Comic comic = library.getComic(selectedTabTitle);
		// get comic page
		Page page = comic.getPages().get(comic.getCurrentPage());
		// get comic page icon from panel
		ImageIcon icon = (ImageIcon) comicPageLabel.getIcon();
		// zoom to original fit
		Image newimg = page.getImage().getScaledInstance(-1, -1,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		// set comic page label icon
		comicPageLabel.setIcon(icon);
		// update comic page panel
		comicPagePanel.revalidate();
		comicPagePanel.repaint();
	}

	private void shortcutsActionPerformed() {
		// create shortcuts dialog
		JDialog shortcutsDialog = new JDialog();
		// set shortcuts dialog title
		shortcutsDialog.setTitle("Shortcuts");
		// set shortcuts dialog size
		shortcutsDialog.setSize(400, 400);
		// set shortcuts dialog layout
		shortcutsDialog.setLayout(new BorderLayout());
		// create shortcuts text area
		JTextArea shortcutsTextArea = new JTextArea();
		// set shortcuts text area non editable
		shortcutsTextArea.setEditable(false);
		// JList to list
		// List<String> list = new ArrayList<String>();
		// add each line of JList to list
		for (int i = 0; i < shortcutsList.getModel().getSize(); i++) {
			shortcutsTextArea.append(shortcutsList.getModel().getElementAt(i) + "\n");
		}
		// add shortcuts text area to shortcuts dialog
		shortcutsDialog.add(shortcutsTextArea, BorderLayout.CENTER);
		// set shortcuts dialog visible
		// set shrortcuts dialog location
		shortcutsDialog.setLocationRelativeTo(null);
		shortcutsDialog.setVisible(true);
	}

	private void aboutActionPerformed() {
		// create about dialog
		JDialog aboutDialog = new JDialog();
		// set about dialog title
		aboutDialog.setTitle("About");
		// set about dialog size
		aboutDialog.setSize(400, 400);
		// set about dialog layout
		aboutDialog.setLayout(new BorderLayout());
		// create about text area
		JTextArea aboutTextArea = new JTextArea();
		// set about text area non editable
		aboutTextArea.setEditable(false);
		// add about text to about text area
		aboutTextArea.setText(
		"Grimalkin v1.0\n"
		+ "Created by Dimitri HUDE and Laure BLANCHARD\n"
		+ "2022\n"
		+ "Contact:\n"
		+ "dimitri.hude@etu.univ-amu.fr\n"
		+ "laure.blanchard.1@etu.univ-amu.fr\n"
		+ "Please do not steal or reproduce this software without our permission.\n"
		);					
		// add about text area to about dialog
		aboutDialog.add(aboutTextArea, BorderLayout.CENTER);
		// set about dialog location
		aboutDialog.setLocationRelativeTo(null);
		// set about dialog visible
		aboutDialog.setVisible(true);
	}

	private void closeAllTabs() {
		// go through all tabs
		for (int i = 0; i < rightPane.getTabCount(); i++) {
			// if tab title is "Quickstart"
			if (rightPane.getTitleAt(i).equals("Quickstart")) {
				// remove tab
				rightPane.remove(i);
			} else {
				// get tab title
				String tabTitle = rightPane.getTitleAt(i);
				updateComic(tabTitle, library.getComic(tabTitle).getCurrentPage());
				rightPane.remove(i);
			}
		}
	}



}
