package edu.grimalkin.data;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * Une classe "Comic" représentant un comic book (Bande dessinée, comic US, manga, etc.)
 * Un comic est composé d'un ensemble de pages (Page.java)
 * La classe encapsule plusieurs méthodes permettant de manipuler les données d'un comic.
 */
public class Comic {
    private String id;
    private String title;
    private String path;
    private transient Image cover;
    private List<Page> pages;
    private int pageCount;
    private int currentPage;
    private int lastPageRead;
    // private boolean isRead;

    /**
     * Constructeur par défaut
     * Initialise les attributs à des valeurs par défaut
     */
    public Comic() {
        id = "";
        title = "";
        path = "";
        cover = null;
        pages = new ArrayList<Page>();
        pageCount = 0;
        currentPage = 0;
        lastPageRead = 0;
    }

    /**
     * Constructeur avec paramètres
     * Initialise les attributs avec les valeurs passées en paramètres
     * @param _title Titre du comic
     * @param _path Chemin d'accès au comic
     */
    public Comic(String _title, String _path) {
        // generate an id for the comic based on the title and the path, and hash it in hexadecimal
        id = String.valueOf((_title + _path).hashCode());
        title = _title;
        path = _path;
        cover = null;
        pages = new ArrayList<Page>();
        pageCount = 0;
        currentPage = 0;
        lastPageRead = 0;
    }

    /**
     * Constructeur avec paramètres
     * Initialise les attributs avec les valeurs passées en paramètres
     * @param _title Titre du comic
     * @param _path Chemin d'accès au comic
     * @param _pages Liste des pages du comic
     */
    public Comic(String _title, String _path, List<Page> _pages) {
        // generate an id for the comic based on the title and the path, and hash it
        id = String.valueOf((_title + _path).hashCode());
        title = _title;
        path = _path;
        cover = _pages.get(0).getImage();
        pages = _pages;
        pageCount = pages.size();
        currentPage = 0;
        lastPageRead = 0;
    }
    
    /**
     * Getter pour l'id du comic
     * @return l'id du comic
     */
    public String getId() {return id;}
    /**
     * Getter pour le titre du comic
     * @return le titre du comic
     */
    public String getTitle() {return title;}
    /**
     * Getter pour le chemin d'accès au comic
     * @return le chemin d'accès au comic
     */
    public String getPath() {return path;}
    /**
     * Getter pour la liste des pages du comic
     * @return la liste des pages du comic
     */
    public Image getCover() {return cover;}
    /**
     * Getter pour la liste des pages du comic
     * @return la liste des pages du comic
     */
    public List<Page> getPages() {return pages;}
    /**
     * Getter pour la liste des pages du comic
     * @return la liste des pages du comic
     */
    public int getPageCount() {return pageCount;}
    /**
     * Getter pour la page courante du comic
     * @return la page courante du comic
     */
    public int getCurrentPage() {return currentPage;}
    /**
     * Getter pour la dernière page lue du comic
     * @return la dernière page lue du comic
     */
    public int getLastPageRead() {return lastPageRead;}


    /**
     * Setter pour l'id du comic
     * @param _id l'id du comic
     */
    public void setId(String _id) {id = _id;}
    /**
     * Setter pour le titre du comic
     * @param _title le titre du comic
     */
    public void setTitle(String _title) {title = _title;}
    /**
     * Setter pour le chemin d'accès au comic
     * @param _path le chemin d'accès au comic
     */
    public void setPath(String _path) {path = _path;}
    /**
     * Setter pour la liste des pages du comic
     * @param _pages la liste des pages du comic
     */
    public void setCover(Image _cover) {cover = _cover;}
    /**
     * Setter pour la liste des pages du comic
     * @param _pages la liste des pages du comic
     */
    public void setPages(List<Page> _pages) {pages = _pages;}
    /**
     * Setter pour la page courante du comic
     * @param _currentPage la page courante du comic
     */
    public void setCurrentPage(int _currentPage) {currentPage = _currentPage;}
    /**
     * Setter pour la dernière page lue du comic
     * @param _lastPageRead la dernière page lue du comic
     */
    public void setLastPageRead(int _lastPageRead) {lastPageRead = _lastPageRead;}


    /** 
     * Méthode permettant l'ajout d'une page à la liste des pages du comic
     * @param page la page à ajouter
     */
    public void addPage(Page page) {
        pages.add(page);
        pageCount++;
    }

    /**
     * Méthode permettant de passer à la page suivante
     */
    public void nextPage() {
        if (currentPage < pageCount) {
            currentPage++;
            lastPageRead = currentPage;
        }
    }

    /**
     * Méthode permettant de passer à la page précédente
     */
    public void previousPage() {
        if (currentPage > 0) {
            currentPage--;
            lastPageRead = currentPage;
        }
    }

    /**
     * Méthode permettant de passer à une page spécifique
     * @param page numéro de la page à atteindre
     */
    public void goToPage(int page) {
        if (page >= 0 && page < pageCount) {
            currentPage = page;
        }
    }

    /**
     * Méthode permettant de remettre le comic à zéro
     * Remet la page courante à 0 et la dernière page lue à 0
     */
    public void reset() {
        currentPage = 0;
        lastPageRead = 0;
        // isRead = false;
    }

    /**
     * Méthode permettant de remettre le comic à la dernière page lue
     * Remet la page courante à la dernière page lue
     */
    public void resetToLastPageRead() {
        currentPage = lastPageRead;
        // isRead = false;
    }
}
