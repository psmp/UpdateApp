/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author ami
 */
public class Files {
    
    private static final int BUFFER_SIZE = 4096;
    
    public boolean fileExists(String filePath) {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            return true;
        }
        return false;
    }
    
    public boolean folderExists(String folderPath) {
        File folder = new File(folderPath);
        if (folder.exists() && folder.isDirectory()) {
            return true;
        }
        return false;
    }
    
    public boolean renameFile(String filePath_old, String filePath_new) {
        File oldFile = new File(filePath_old);
        File newFile = new File(filePath_new);
        if (oldFile.isFile() && oldFile.renameTo(newFile)) {
            return true;
        }
        return false;
    }
    
    public boolean renameFolder(String folderPath_old, String folderPath_new)  {
        File oldFolder = new File(folderPath_old);
        File newFolder = new File(folderPath_new);
        if (oldFolder.isDirectory() && oldFolder.renameTo(newFolder)) {
            return true;
        }
        return false;
    }
    
    public boolean copyfile(String filePath_org, String filePath_dst) {
        InputStream inStream = null;
	OutputStream outStream = null;
        try {
            File ofile =new File(filePath_org);
            File dfile =new File(filePath_dst);

            inStream = new FileInputStream(ofile);
            outStream = new FileOutputStream(dfile);

            byte[] buffer = new byte[1024];

            int length;
            //copy the file content in bytes 
            while ((length = inStream.read(buffer)) > 0){

                outStream.write(buffer, 0, length);
            }
            inStream.close();
    	    outStream.close();
    	      
    	    System.out.println("File is copied successful!");
            return true;
        } catch(IOException e){
    		e.printStackTrace();
        }
        
        return false;
    }
    
    public boolean unzipFile(String zipFilePath, String destDirectory) throws IOException {
        // http://www.mkyong.com/java/how-to-decompress-files-from-a-zip-file/
        // http://www.codejava.net/java-se/file-io/programmatically-extract-a-zip-file-using-java
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry entry = zipIn.getNextEntry();
        // iterates over entries in the zip file
        while (entry != null) {
            String filePath = destDirectory + File.separator + entry.getName();
            if (!entry.isDirectory()) {
                // if the entry is a file, extracts it
                extractFile(zipIn, filePath);
            } else {
                // if the entry is a directory, make the directory
                File dir = new File(filePath);
                dir.mkdir();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
        
        return true;
    }

    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }
}
