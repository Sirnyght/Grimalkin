package edu.grimalkin.data;

import java.util.*;

public class Comic {
    private String name;
    // private String extension;
    private String path;
    private int pageCount;
    private int currentPage;
    private List<Page> pages;

    public Comic(String name, String extension, String path) {
        this.name = name;
        this.path = path;
        this.pageCount = 0;
        this.currentPage = 0;
        this.pages = new ArrayList<Page>();
    }

    public String getName() {return name;}
    // public String getExtension() {return extension;}
    public String getPath() {return path;}
    public int getPageCount() {return pageCount;}
    public int getCurrentPage() {return currentPage;}
    public List<Page> getPages() {return pages;}
    
    public void setName(String name) {this.name = name;}
    // public void setExtension(String extension) {this.extension = extension;}
    public void setPath(String path) {this.path = path;}
    public void setPageCount(int pageCount) {this.pageCount = pageCount;}
    public void setCurrentPage(int currentPage) {this.currentPage = currentPage;}
    public void setPages(List<Page> pages) {this.pages = pages;}
}
