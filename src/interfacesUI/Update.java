/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesUI;

import core.Empresa;
import core.FTPmanager;
import exceptions.FTPException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
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
        if (empresaUpdate.getEnterprise() == 0) {
            return false;
        }
        String folder = null;
        Path pathFolder;
        
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
    public boolean updateCompact(Empresa empresaUpdate, JProgressBar progressBar) {
        boolean result = false;
        progressBar.setValue(25);
        
        
        progressBar.setValue(30);
        if (empresaUpdate.getFtpServer().equals("") ||
                empresaUpdate.getFtpUsername().equals("") ||
                empresaUpdate.getFtpPassword().equals("") ||
                empresaUpdate.getFtpPort()==0) {
            this.returnError(2);
            return false;
        }
        progressBar.setValue(50);
        
        FTPmanager ftp = new FTPmanager(empresaUpdate.getFtpServer(), 
        empresaUpdate.getFtpPort(), empresaUpdate.getFtpUsername(), 
        empresaUpdate.getFtpPassword());
        
        try {
            ftp.connection();
            boolean result1 = ftp.checkFileExists("/Empr"+empresaUpdate.getEnterprise() +
                    "/Software/compact/dutyplan.zip");
            boolean result2 = ftp.checkFileExists("/Empr"+empresaUpdate.getEnterprise() +
                    "/Software/compact/DUTYPLAN.zip");
            if (result1 || result2) {
                result = true;
            }
            if (!result) {
                return false;
            }
            
            // reset result
            result = false;
            // download dutyplan.zip
            if (result1) {
                result = ftp.downloadFile("/Empr"+empresaUpdate.getEnterprise() +
                    "/Software/compact/dutyplan.zip", "temp/dutyplan.zip");
            }
            if (result2) {
                result = ftp.downloadFile("/Empr"+empresaUpdate.getEnterprise() +
                    "/Software/compact/DUTYPLAN.zip", "temp/dutyplan.zip");
            }
            
            if (result) {
                System.out.println("Download ficheiro dutyplan.zip com sucesso");
            }
            
            // reset result
            result = false;
            
            // download PassesBusiness.zip

            result = ftp.downloadFile("/Empr"+empresaUpdate.getEnterprise() +
                "/Software/compact/PassesBusiness.zip", "temp/PassesBusiness.zip");

            if (result) {
                System.out.println("Download ficheiro PassesBusiness.zip com sucesso");
            }
            
            // reset result
            result = false;
            
            // download XmlToBinary.zip

            result = ftp.downloadFile("/Empr"+empresaUpdate.getEnterprise() +
                "/Software/compact/XmlToBinary.zip", "temp/XmlToBinary.zip");

            if (result) {
                System.out.println("Download ficheiro XmlBinary.zip com sucesso");
            }
            
            // reset result
            result = false;
            
            // Backup old version
            
            // copy to Sofware/old
            
            

        } catch (FTPException ex) {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public void returnError(int codeError) {
        switch (codeError) {
            case 1: System.out.println("ERROR: Invalid application to update"); break;
            case 2: System.out.println("ERROR: Invalid FTP configuration"); break;
            default: System.out.println("UNKNOWN ERROR");
        }
        
    }
}
