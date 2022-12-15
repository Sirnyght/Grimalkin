package edu.grimalkin.data;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
// import java.awt.Toolkit;


/**
 * Une classe "Page" représentant une page d'un comic book (Bande dessinée, comic US, manga, etc.)
 * Une page est composée d'une image (Image.java)
 * La classe encapsule plusieurs méthodes permettant de manipuler les données d'une page.
 */
public class Page {
    private String id;
    private int width;
    private int height;
    private transient Image image;

    /**
     * Constructeur par défaut
     * Initialise les attributs à des valeurs par défaut
     */
    public Page() {
        id = "";
        width = 0;
        height = 0;
        image = null;
    }

    /**
     * Constructeur avec paramètres
     * Initialise les attributs avec les valeurs passées en paramètres
     * @param _id Identifiant de la page
     * @param _width Largeur de la page
     * @param _height Hauteur de la page
     * @param _image Image de la page
     */
    public Page(String _id, int _width, int _height, Image _image) {
        id = _id;
        width = _width;
        height = _height;
        image = _image;
    }

    /**
     * Accesseur en lecture de l'attribut id
     * @return Identifiant de la page
     */
    public String getId() {return id;}
    /**
     * Accesseur en lecture de l'attribut width
     * @return Largeur de la page
     */
    public int getWidth() {return width;}
    /**
     * Accesseur en lecture de l'attribut height
     * @return Hauteur de la page
     */
    public int getHeight() {return height;}
    /**
     * Accesseur en lecture de l'attribut image
     * @return Image de la page
     */
    public Image getImage() {return image;}

    /**
     * Accesseur en écriture de l'attribut id
     * @param _id Identifiant de la page
     */
    public void setId(String _id) {id = _id;}
    /**
     * Accesseur en écriture de l'attribut width
     * @param _width Largeur de la page
     */
    public void setWidth(int _width) {width = _width;}
    /**
     * Accesseur en écriture de l'attribut height
     * @param _height Hauteur de la page
     */
    public void setHeight(int _height) {height = _height;}
    /**
     * Accesseur en écriture de l'attribut image
     * @param _image Image de la page
     */
    public void setImage(Image _image) {image = _image;}

    /**
     * Méthode permettant d'effectuer la rotation d'une page d'un comic book.
     * On effectue la rotation si l'angle de rotation est égal à 90, 180 ou 270 ; sinon, on retourne une exception.
     * @param _angle Angle de rotation
     */
    public void rotate(int _angle) {
        // Si l'angle est différent de 90, 180 ou 270, on retourne une exception    
        if (_angle != 90 && _angle != 180 && _angle != 270) {
            throw new IllegalArgumentException("L'angle de rotation doit être égal à 90, 180 ou 270");
        } 
        // Sinon, on effectue la rotation
        else {
            // On récupère la largeur et la hauteur de l'image
            int w = image.getWidth(null);
            int h = image.getHeight(null);
            // On crée une nouvelle image de la taille de l'image d'origine
            BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            // On crée un nouveau contexte graphique
            Graphics2D g = newImage.createGraphics();
            // On effectue la rotation
            g.rotate(Math.toRadians(_angle), w/2, h/2);
            // On dessine l'image d'origine dans le nouveau contexte graphique
            g.drawImage((BufferedImage) image, null, 0, 0);
            // On met à jour l'image de la page
            image = newImage;
            // On met à jour la largeur et la hauteur de la page
            width = w;
            height = h;
        }
    }
}
