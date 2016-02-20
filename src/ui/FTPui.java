/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import core.FTPmanager;
import exceptions.FTPException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author psmp
 */
public class FTPui {
    
   public FTPmanager ftp2;
    /**
     * Connection
     * 
     * @param server
     * @param port
     * @param user
     * @param pwd
     * @return 
     */
    public String connect (String server, int port, String user, String pwd) {
        FTPmanager ftp = new FTPmanager(server, port, user, pwd);
        try {    
            ftp.connection();
            ftp2=ftp;
            return ftp.showServerCode();
        } catch (FTPException ex) {
            return ftp.showServerCode();
            //Logger.getLogger(FTPui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Disconnect
     * @return 
     */
    public String disconnect() {
        if (ftp2.disconnect()) {
            return "Disconnected";
        }
        return "Not connected";
    }
    
    public String updateCompact(String org, String dst) {
        try {
            System.out.println("connexão: " + ftp2.showServerCode());
            boolean result = ftp2.downloadFile(org, dst);
            System.out.println("result: " + result);
            if (result) {
                return ftp2.showServerCode();
            }
            else {
                String erro = "Transferência sem sucesso. Verifique o ficheiro "
                        + "de log para mais informações";
                return erro;
            }
        } catch (Exception e) {
            return "Not Connected";
        }
        
        
    }
}
