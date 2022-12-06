package edu.grimalkin.data;

import java.util.*;


public class Shelf {
    private String name;
    private String path;
    private int comicCount;
    private List<Comic> comics;

    public Shelf(String name, String path) {
        this.name = name;
        this.path = path;
        this.comicCount = 0;
        this.comics = new ArrayList<Comic>();
    }

    public String getName() {return name;}
    public String getPath() {return path;}
    public int getComicCount() {return comicCount;}
    public List<Comic> getComics() {return comics;}
    
    public void setName(String name) {this.name = name;}
    public void setPath(String path) {this.path = path;}
    public void setComicCount(int comicCount) {this.comicCount = comicCount;}
    public void setComics(List<Comic> comics) {this.comics = comics;}

    public void addComic(Comic comic) {
        comics.add(comic);
        comicCount++;
    }

    public void removeComic(Comic comic) {
        comics.remove(comic);
        comicCount--;
    }

    public Comic getComic(int index) {
        return comics.get(index);
    }

    
}
