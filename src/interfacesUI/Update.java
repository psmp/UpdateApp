/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesUI;

import core.Empresa;
import core.FTPmanager;
import core.Filesmanager;
import exceptions.FTPException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import ui.ConfigXML;


/**
 *
 * @author ami
 */
public class Update {
    //Empresa empresaUpdate = new Empresa();
    //String application;
    
    // validar se a aplicação existe no posto local
    // validar acesso ao ftp
    public boolean validateUpdate(Empresa empresaUpdate, String application) {
        String folder = null;
        Path pathFolder;
        
        if (empresaUpdate.getEnterprise() == 0) {
            return false;
        }
        
        switch (application) {
            case "compact":
                folder = empresaUpdate.getCompact();
                pathFolder = Paths.get(folder);
                if (Files.notExists(pathFolder)) {
                    System.out.println("Não Existe");
                    return false;
                }
                break;
            case "place":
                folder = empresaUpdate.getPlace();
                pathFolder = Paths.get(folder);
                if (Files.notExists(pathFolder)) {
                    System.out.println("Não Existe");
                    return false;
                }
                break;
            case "dcar":
                folder = empresaUpdate.getDcar();
                pathFolder = Paths.get(folder);
                if (Files.notExists(pathFolder)) {
                    System.out.println("Não Existe");
                    return false;
                }
                break;
            case "reflexini":
                folder = empresaUpdate.getReflexINI();
                pathFolder = Paths.get(folder);
                if (Files.notExists(pathFolder)) {
                    System.out.println("Não Existe");
                    return false;
                }
                break;
            default:
                this.returnError(1);
                return false;
        }
        
        return true;
    }
       
    // executar aplicação
    public void runApp(Empresa empresaUpdate, String application) {
        
    }
    
    /**
     * Realize update of DATACAR Compact
     * @param empresaUpdate
     * @return 
     */
    public int updateCompact(Empresa empresaUpdate, JTextArea logArea) {
        /**
         * ver link
         * http://www.java2s.com/Code/Java/Swing-JFC/AdemonstrationoftheJProgressBarcomponent.htm
         */
        logArea.append("Linha inicial \n");
        boolean result = false;
        Filesmanager ficheiros = new Filesmanager();

        if (empresaUpdate.getFtpServer().equals("") ||
                empresaUpdate.getFtpUsername().equals("") ||
                empresaUpdate.getFtpPassword().equals("") ||
                empresaUpdate.getFtpPort()==0) {
            this.returnError(2);
            return 4;
        }
        logArea.append("Linha depois FTP \n");
        
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        }
        FTPmanager ftp = new FTPmanager(empresaUpdate.getFtpServer(), 
        empresaUpdate.getFtpPort(), empresaUpdate.getFtpUsername(), 
        empresaUpdate.getFtpPassword());
        
        try {
            logArea.append("Linha 1");
            ftp.connection();
            /*
             * Verificar ficheiros antes de download
             */
            boolean result1 = ftp.checkFileExists("Empr"+empresaUpdate.getEnterprise() +
                    "/Software/compact/dutyplan.zip");
            boolean result2 = ftp.checkFileExists("Empr"+empresaUpdate.getEnterprise() +
                    "/Software/compact/DUTYPLAN.zip");

            if (result1 || result2) {
                result = true;
            }

            if (!result) {
                return 1;
            }
            
            // reset result
            result = false;
            
            /*
             * Download ficheiros existentes
             */
            if (result1) {
                result = ftp.downloadFile("Empr"+empresaUpdate.getEnterprise() +
                    "/Software/compact/dutyplan.zip", "temp/dutyplan.zip");
            }
            if (result2) {
                result = ftp.downloadFile("Empr"+empresaUpdate.getEnterprise() +
                    "/Software/compact/DUTYPLAN.zip", "temp/dutyplan.zip");
            }
            
            if (result) {
                System.out.println("Download ficheiro dutyplan.zip com sucesso");
            }
            else {
                return 2;
            }
            
            // reset result
            result = false;
            
            // download PassesBusiness.zip

            result = ftp.downloadFile("Empr"+empresaUpdate.getEnterprise() +
                "/Software/compact/PassesBusiness.zip", "temp/PassesBusiness.zip");

            if (!result) {
                return 3;
            }
            
            // reset result
            result = false;
            
            // download XmlToBinary.zip

            result = ftp.downloadFile("Empr"+empresaUpdate.getEnterprise() +
                "/Software/compact/XmlToBinary.zip", "temp/XmlToBinary.zip");

            if (!result) {
                return 4;
            }
            
            // reset result
            result = false;
            logArea.append("Linha 2");
            /*
             * Renomear ficheiros antigos
             * Envio ficheiros antigos para ftp
             */
            String fileOld,sufixFile,fileNew;
            
            /* dutyplan */
            fileOld = empresaUpdate.getCompact() + "dutyplan.zip";
            sufixFile = "dutyplan.zip" + "_" + ficheiros.getDate() + ".old";
            fileNew = empresaUpdate.getCompact() + sufixFile;
            
            if (ficheiros.renameFile(fileOld, fileNew)) {
                System.out.println("Ficheiro dutyplan.zip renomeado");
            }
            // move to Sofware/old
            result = ftp.uploadFile(fileNew, "Empr"+empresaUpdate.getEnterprise() +"/Software/old", sufixFile);
            if (result) {
                System.out.println("Ficheiro upload com sucesso");
                if (ficheiros.deleteFile(fileNew)) {
                    System.out.println("Ficheiro old eliminado");
                }
                else {
                    System.out.println("Ficheiro old NAO eliminado");
                }
            }
            if (ficheiros.copyfile("temp/dutyplan.zip", fileOld)) {
                System.out.println("Ficheiro dutyplan.zip copiado - actualizado");
            }
            logArea.append("Linha 3");
            /* passesbusiness */
            /* xmltobinary */

        } catch (FTPException ex) {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 999;
    }
    
    public void returnError(int codeError) {
        switch (codeError) {
            case 1: System.out.println("ERROR: Invalid application to update"); break;
            case 2: System.out.println("ERROR: Invalid FTP configuration"); break;
            default: System.out.println("UNKNOWN ERROR");
        }
        
    }
    public static String returnErrors(int error) {
        switch (error) {
            case 1: return "Ficheiro não existe";
            case 2: return "passesbusiness não existe";
            case 999: return "Procedimento realizado com sucesso";
            default: return "Erro desconhecido";
        }
    }
}
