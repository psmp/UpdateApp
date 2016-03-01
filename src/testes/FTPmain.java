package testes;

import java.io.IOException;

import core.FTPmanager;
import exceptions.FTPException;

public class FTPmain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String server = "localhost";
		int port = 21;
		String username = "psmp";
		String password = "03081981";
		boolean resultado;
		
		// Ligação ftp
		FTPmanager ftp = new FTPmanager(server, port, username, password);
		try {
			ftp.connection();
		} catch (FTPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Verificar se directoria existe

		String directoria = "Teste";
		String directoria2 = "ftp2";
		try {
			resultado = ftp.changeWorkingDirectory(directoria);
			if (!resultado) {
				System.out.println("A directoria " + directoria + " não existe!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			resultado = ftp.changeWorkingDirectory(directoria2);
			if (!resultado) {
				System.out.println("A directoria " + directoria2 + " não existe!");
			}
			else {
			ftp.changeWorkingDirectory(directoria2);
			System.out.println("Alterada pasta no servidor para " + directoria2); 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Download ficheiro
		String downloadFile = "ftp/teste.txt";
		String dstFolder = "/home/psmp/Deskmtop/ftp/";
		String dstfinal = dstFolder + "teste2.txt";
		
		ftp.downloadFile(downloadFile, dstfinal);
                
                boolean result = ftp.uploadFile("/home/psmp/ftp/teste.txt", "ftp/2/", "teste2u.txt");
                if (result) {
                    System.out.println("upload com sucesso");
                }
                else {
                    System.out.println("upload SEM sucesso");
                }
                
                //Desligar
		System.out.println("Disconnecting...");
		ftp.disconnect();
		System.out.println("Disconnected");
		
	}
}
