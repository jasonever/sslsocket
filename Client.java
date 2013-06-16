/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author linuxox
 */
public class Client {
    public Client() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, KeyManagementException{
  	
        try {
                System.setProperty("javax.net.ssl.trustStore", System.getProperty("user.dir")+"\\src\\clientcert.jks");
                System.setProperty("javax.net.ssl.trustStorePassword", "ahmed149"); 
                System.setProperty("javax.net.debug", "ssl");

                SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
                SSLSocket s = (SSLSocket) sf.createSocket("localhost",2149);
            //while(true){
                DataOutputStream doo = new DataOutputStream(s.getOutputStream());

            DataInputStream di = new DataInputStream(s.getInputStream());
            byte[] buffer  = new byte[1024];
            doo.writeUTF(" Hiii From Client");

            System.out.println(di.readUTF());
            //}
            
            doo.close();
            di.close();
                        s.close();
                        

        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, KeyManagementException{
        new Client();
    }
}

