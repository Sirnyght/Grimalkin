package edu.grimalkin.data;

//import imageio for jpeg
import javax.imageio.ImageIO;

public class Page {
    // private String pageName;
    private String pageExtension;
    private int pageNumber;
    private ImageIO pageImage;

    public Page(String pageName, String pageExtension) {
        // this.pageName = pageName;
        this.pageExtension = pageExtension;
    }

    // public String getPageName() {return pageName;}
    public String getPageExtension() {return pageExtension;}
    public int getPageNumber() {return pageNumber;}
    public ImageIO getPageImage() {return pageImage;}

    // public void setPageName(String pageName) {this.pageName = pageName;}
    public void setPageExtension(String pageExtension) {this.pageExtension = pageExtension;}
    public void setPageNumber(int pageNumber) {this.pageNumber = pageNumber;}
    public void setPageImage(ImageIO pageImage) {this.pageImage = pageImage;}
}
