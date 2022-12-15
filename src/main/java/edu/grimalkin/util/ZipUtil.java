package edu.grimalkin.util;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.imageio.ImageIO;

import edu.grimalkin.data.Page;

/**
 * @author Dimitri
 * @version 1.5
 * Classe utilitaire pour la compression et la décompression de fichiers.
 * La classe encapsule plusieurs méthodes permettant de manipuler les données d'un fichier compressé.
 * La classe utilise la librairie Apache Commons Compress.
 * @see <a href="https://commons.apache.org/proper/commons-compress/">Apache Commons Compress</a>
 */
public class ZipUtil {
    /**
     * Constructeur privé pour empêcher l'instanciation de la classe.
     * @throws IllegalStateException
     * @see IllegalStateException
     */
    private ZipUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Méthode permettant de décompresser un fichier dans un fichier destination donné en paramètre.
     * @param source Fichier à décompresser
     * @param destination Dossier de destination
     * @throws IOException
     */
    public static void unzip(File source, File destination) throws IOException {
        try (ZipFile zipFile = new ZipFile(source)) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                File entryDestination = new File(destination.getPath() + File.separator + entry.getName());

                if (entry.isDirectory()) {
                    throw new IOException("The .cbz file should not contain a directory. Please check your .cbz file");
                } else {
                    entryDestination.getParentFile().mkdirs();  
                    System.out.println("Extracting: " + entryDestination);

                    try (InputStream in = zipFile.getInputStream(entry);
                        OutputStream out = new FileOutputStream(entryDestination)) {
                        System.out.println("Extracting: " + entryDestination);
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = in.read(buffer)) >= 0) {
                            out.write(buffer, 0, len);
                        }
                    }
                }
            }
        }
    }

    /**
     * Méthode permettant de décompresser un fichier dans un objet List<Page> donné en paramètre sans créer de fichier temporaire.
     * Les fonctions ImageIO.read() et ImageIO.write() ne sont pas utilisées en raison de leur lenteur.
     * Il leur est préféré l'utilisation de la classe Toolkit qui permet de créer une image à partir d'un tableau de bytes.
     * @param destination Dossier de destination
     * @throws IOException
     */
    public static void unzip(File source, List<Page> destination) throws IOException {
        try (ZipFile zipFile = new ZipFile(source)) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            System.out.println("entries: " + entries);
            // Write each entry to an image
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                System.out.println("entry: " + entry);
                ImageIO.setUseCache(false);
                if (entry.isDirectory()) {
                    System.out.println("The .cbz file should not contain a directory. Please check your .cbz file");
                }
                // if entry is not an image, skip it
                if (!entry.getName().endsWith(".jpg") && !entry.getName().endsWith(".png")) {
                    continue;
                }
                // write entry to image object
                try (InputStream in = zipFile.getInputStream(entry);
                    OutputStream out = new ByteArrayOutputStream()) {
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = in.read(buffer)) >= 0) {
                        out.write(buffer, 0, len);
                    }
                    // convert output stream to buffered image
                    Image image = Toolkit.getDefaultToolkit().createImage(((ByteArrayOutputStream) out).toByteArray());
                    // add image to comic
                    // cut off the file extension
                    String id = entry.getName().substring(0, entry.getName().lastIndexOf("."));
                    destination.add(new Page(id, image.getWidth(null), image.getHeight(null), image));
                }
            }
        }
    }    
}
