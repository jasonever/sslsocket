/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 *
 * @author linuxox
 */
public class Server implements Runnable{    
    Map Online = new HashMap();
    public static ArrayList<Socket> Sockets = new ArrayList<Socket>();
    SSLServerSocket ss;
    DataOutputStream doo;
    DataInputStream di;
    
    public Server() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, KeyManagementException, UnrecoverableKeyException{
        try {
                System.setProperty("javax.net.debug", "ssl");
                System.setProperty("javax.net.ssl.keyStore",System.getProperty("user.dir")+"\\src\\servercert.jks");
                System.setProperty("javax.net.ssl.keyStorePassword","ahmed149");

            SSLServerSocketFactory sf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            ss = (SSLServerSocket) sf.createServerSocket(2149);
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        SSLSocket s;
        try {        
            s = (SSLSocket)ss.accept();
             String inputLine, outputLine;
  			// Create Input / Output Streams for communication with the client
				while(true)
				{
                        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

		        while ((inputLine = in.readLine()) != null) {
		             out.println(inputLine);
		             System.out.println(inputLine);
		        }
                                }
         } catch (IOException ex) {
                 System.out.println(ex.getMessage());
         }finally{
            try {
                ss.close();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, KeyManagementException, UnrecoverableKeyException{
        Server ss = new Server();
        ss.run();
    }
    
}
