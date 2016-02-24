/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesUI;

import core.Empresa;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


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
        
        switch (application) {
            case "compact":
                folder = empresaUpdate.getCompact();
                break;
            case "place":
                folder = empresaUpdate.getPlace();
                break;
            case "dcar":
                folder = empresaUpdate.getDcar();
                break;
            case "reflexini":
                folder = empresaUpdate.getReflexINI();
                break;
            default:
                this.returnError(1);
                return false;
        }
        Path pathFolder = Paths.get(folder);
        if (Files.notExists(pathFolder)) {
            System.out.println(" Não Existe");
            return false;
        }
        return true;
    }
    
    // fazer download dos fcheiros a actualizar
    // para a pasta temp (se necessário criar)
    public void downloadApp(Empresa empresaUpdate, String application) {
        
    }
    // Descompactar (se necessário) e copiar ficheiro
    // para pasta final
    public void updateApp(Empresa empresaUpdate, String application) {
        
    }
    
    // Se aplicável registar dll
    public void registerApp(Empresa empresaUpdate, String application) {
        
    }
    
    // executar aplicação
    public void runApp(Empresa empresaUpdate, String application) {
        
    }
    
    public void returnError(int codeError) {
        switch (codeError) {
            case 1: System.out.println("ERROR: Invalid application to update"); break;
            default: System.out.println("UNKNOWN ERROR");
        }
        
    }
}
