package edu.grimalkin.compression;

import java.util.zip.*;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;

public class Archive {
    private String path = "C:\\Users\\Kytsune\\Documents\\Comic Lib\\dnspiderman001_test.cbz";

    public Archive() {

        
    }

    private void unzip(String path) throws IOException {
        String fileZip = "";
        File destDir = new File("src/main/resources/unzipTest");

        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
           // ...
        }
    }


}