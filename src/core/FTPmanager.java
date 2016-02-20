package core;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import exceptions.FTPException; 

public class FTPmanager {
	private String server;
	private int port;
	private String username;
	private String password;
	private FTPClient ftp = new FTPClient();
	private int replyCode;
	
	/**
	 * FTPmanager Object
	 * 
	 * @param server
	 * @param port
	 * @param user
	 * @param pass
	 */
	public FTPmanager(String server, int port, String user, String pass) {
        this.server = server;
        this.port = port;
        this.username = user;
        this.password = pass;
        }
        
        /**
         * Set connection parameters
         * @param server
         * @param port
         * @param username
         * @param password 
         */
	public void setParams(String server, int port, String username, String password) {
            this.server=server;
            this.port=port;
            this.username=username;
            this.password=password;
        }
	/**
	 * Connect and login to the server.
	 * 
	 * @throws FTPException
	 */
	public void connection() throws FTPException {
		try {
			ftp.connect(this.server, this.port);
			
			replyCode = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                throw new FTPException("FTP server refused connection.");
            }
			
			boolean logged = ftp.login(this.username, this.password);
            if (!logged) {
                // failed to login
                ftp.disconnect();
                System.out.println("Username or Password incorrect!");
            }
            
            // Change to passive mode 
            ftp.enterLocalPassiveMode();
			
		} catch (IOException ex) {
	        System.out.println("Server not available");
			//throw new FTPException("I/O error: " + ex.getMessage());
	        
	    }
	}
	
	/**
	 * Get status from last request to server
	 */
	public void showServerReply() {
        String[] replies = ftp.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                System.out.println("SERVER: " + aReply);
            }
        }
	}
	
	/**
	 * Get code from last request to server
	 */
	public String showServerCode() {
        String[] replies = ftp.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                return aReply;
            }
        }
        return null;
	}
	
	/**
	 * Change remote folder
	 * @param folder
	 * @return true if exists
	 * @throws IOException
	 */
	public boolean changeWorkingDirectory(String folder) throws IOException {
		// TODO Auto-generated method stub
		return ftp.changeWorkingDirectory(folder);
	}
	
	/**
	 * Disconnect form server
	 */
	public boolean disconnect() {
		try {
                    ftp.disconnect();
                    return true;
		} catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return false;
		}
	}
	
	public boolean downloadFile (String filename, String dstfinal) {
            try {
                ftp.setFileType(FTP.BINARY_FILE_TYPE);
                boolean resultado;

                OutputStream outputStream2 = new BufferedOutputStream(new FileOutputStream(dstfinal));
                InputStream inputStream = ftp.retrieveFileStream(filename);
            
                try {
                    byte[] bytesArray = new byte[4096];
                    int bytesRead = -1;
                    while ((bytesRead = inputStream.read(bytesArray)) != -1) {
                        outputStream2.write(bytesArray, 0, bytesRead);
                        }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    System.out.println("Impossivel fazer download do ficheiro." +
                                    "\nVerifique o nome ficheiro!");
                    return false;
                }
 
            resultado = ftp.completePendingCommand();
            if (resultado) {
                System.out.println("File " + filename + " has been downloaded successfully.");
            }
            outputStream2.close();
            inputStream.close();
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Impossivel fazer download do ficheiro." +
					"\nVerifique a pasta de destino!");
                        return false;
		}
            return true;
	}
}
