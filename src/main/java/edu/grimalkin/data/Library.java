package edu.grimalkin.data;

import java.util.List;
import java.io.File;
import java.util.ArrayList;

import edu.grimalkin.util.JSONUtil;
import edu.grimalkin.util.ZipUtil;

/**
 * @author Dimitri
 * @version 1.1
 * Une classe "Library" représentant une bibliothèque de comics
 * Une bibliothèque est composée d'un ensemble de comics (Comic.java)
 * La classe encapsule plusieurs méthodes permettant de manipuler les données d'une bibliothèque.
 */
public class Library {
    private List<Comic> comics;
    // private int currentComic;

    /**
     * Constructeur par défaut de la classe Library
     * Initialise les attributs à des valeurs par défaut
     */
    public Library() {
        comics = new ArrayList<Comic>();
    }

    /**
     * Méthode permettant d'ajouter un comic à la bibliothèque
     * @param comic Comic à ajouter à la bibliothèque
     */
    public void addComic(Comic comic) {
        // if the comic is not already in the library, add it
        if (getComic(comic.getTitle()) == null) {
            comics.add(comic);
        }
    }

    /**
     * Méthode permettant de supprimer un comic de la bibliothèque
     * @param title Titre du comic à supprimer de la bibliothèque
     */
    public void removeComic(String title) {
        comics.remove(getComic(title));
    }

    /**
     * Méthode permettant de récupérer un comic de la bibliothèque via son titre
     * @param title Identifiant du comic à récupérer
     * @return Comic correspondant à l'identifiant passé en paramètre
     */
    public Comic getComic(String title) {
        for (Comic comic : comics) {
            if (comic.getTitle().equals(title)) {
                return comic;
            }
        }
        return null;
    }

    /**
     * Méthode permettant de récupérer un comic de la bibliothèque via son index
     * @param index Index du comic à récupérer
     * @return Comic correspondant à l'index passé en paramètre
     */
    public Comic getComic(int index) {
        return comics.get(index);
    }

    /**
     * Méthode permettant de récupérer la liste des comics de la bibliothèque
     * @return Liste des comics de la bibliothèque
     */
    public List<Comic> getComics() {
        return comics;
    }

    /**
     * Méthode permettant de modifier la liste des comics de la bibliothèque
     * @param _comics Nouvelle liste de comics
     */
    public void setComics(List<Comic> _comics) {
        comics = _comics;
    }

    /**
     * Méthode permettant de récupérer le nombre de comics de la bibliothèque
     * @return Nombre de comics de la bibliothèque
     */
    public int getComicCount() {
        return comics.size();
    }

    /**
     * Méthode permettant d'afficher les comics de la bibliothèque
     */
    public void displayComics() {
        for (Comic comic : comics) {
            System.out.println(comic.getTitle());
            System.out.println(comic.getCurrentPage());
        }
    }

    public void initComics() {
        // for each comic in library
        // get path of comics in library
        for (int i = 0; i < comics.size(); i++) {
            List<Page> pages = new ArrayList<Page>();
            String path = comics.get(i).getPath();
            System.out.println(path);
            File file = new File(path);
            
            // new List of pages
            // check if file exists
            if (!file.exists()) {
                System.out.println("File does not exist");
                comics.remove(i);
            }
            else {
                try {
                    // unzip comic
                    ZipUtil.unzip(file, pages);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                comics.get(i).setPages(pages);
                // show comic
                comics.get(i).getPages().get(0).getId();
            }
        }

        try {
            JSONUtil.writeJSONFile(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateCovers();
    }

    private void updateCovers() {
        for (Comic comic : comics) {
            comic.setCover(comic.getPages().get(0).getImage());
        }
    }
}

