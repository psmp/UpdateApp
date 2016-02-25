/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import core.Files;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author ami
 */
public class Filesmain {
    public static void main(String[] args) throws IOException {
        Files file = new Files();
        boolean folderExists;
        boolean fileExists;
        boolean result;
        
        String pasta1="/home/ami/ftp";
        folderExists = file.folderExists(pasta1);
        if (folderExists) {
            System.out.println("Existe Pasta " + pasta1);
        }
        else {
            System.out.println("Não existe Pasta " + pasta1);
        }
        
        String pasta2="/home/ami/f";
        folderExists = file.folderExists(pasta2);
        if (folderExists) {
            System.out.println("Existe Pasta " + pasta2);
        }
        else {
            System.out.println("Não existe Pasta " + pasta2);
        }
        
        String ficheiro1="/home/ami/ftp/teste.zip";
        fileExists = file.fileExists(ficheiro1);
        if (fileExists) {
            System.out.println("Existe ficheiro " + ficheiro1);
        }
        else {
            System.out.println("Não existe ficheiro " + ficheiro1);
        }
        
        String ficheiro2="/home/ami/ftp/testXe.zip";
        fileExists = file.fileExists(ficheiro2);
        if (fileExists) {
            System.out.println("Existe ficheiro " + ficheiro2);
        }
        else {
            System.out.println("Não existe ficheiro " + ficheiro2);
        }
        
        String ficheiro4 = "/home/ami/ftp/testeC.zip";
        result = file.copyfile(ficheiro1, ficheiro4);
        if (result) {
            System.out.println("Ficheiro copiado para " + ficheiro4);
        }
        else {
            System.out.println("Ficheiro NÃO copiado");
        }

        String ficheiro3 = "/home/ami/ftp/teste2.zip";
        String ficheiro5="/home/ami/ftp/testeX.zip";
        result = file.renameFile(ficheiro4, ficheiro5);
        if (result) {
            System.out.println("Ficheiro renomeado");
            
        }
        else {
            System.out.println("Ficheiro NÃO renomeado");
        }
        
        String dstFolder = "/home/ami/ftp/uzip/";
        result = file.unzipFile(ficheiro5, dstFolder);
        if (result) {
            System.out.println("Ficheiro descompactado");
        }
        else {
            System.out.println("Ficheiro NÃO descompactado");
        }
        
    }
    
}
