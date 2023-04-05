/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package jaydegorton1992.sha256_example;
import java.awt.Component;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
/**
 *
 * @author Jade Gorton
 */


public class SHA256_example {

    
    public static String getPassword(String password, byte[] salt)
    {
             String SHAPassword = null;
        try {
            //Get Message Digest (the Util that encrypts data
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            //add Random Salt bytes.
            
            //Create a byte array using digest.
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            
            //Ensure Encoding is the same for both passwords.
            //I was having errors where I simply got bytes. So researched online regarding it. Found, encoder solution and decided to use it.
            String encoded = Base64.getEncoder().encodeToString(bytes);
            SHAPassword =encoded;// sb.toString();
            
            //SHAPassword = bytes.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return SHAPassword;
    }
    public static void main(String[] args) throws NoSuchAlgorithmException {
        //Get User Input
        String jopTextToEncrypt = 
            JOptionPane.showInputDialog("Enter Password to Encrypt ");

        //Get SHA256

          SecureRandom random = new SecureRandom();
          byte[] salt =  new byte[16];
          random.nextBytes(salt);


        String EncryptedText = getPassword(jopTextToEncrypt,salt);  
        
        JFrame frame = new JFrame();            
        JOptionPane.showMessageDialog(frame,"Encrypted Text: " + EncryptedText);
        
         String SecondEncryptedText = getPassword(JOptionPane.showInputDialog("Enter Password"),salt);

         boolean comparePasswords = EncryptedText.compareTo(SecondEncryptedText) == 0;

         if(comparePasswords)
         {
             JOptionPane.showMessageDialog(frame,"Passwords Match: " + SecondEncryptedText + " : " + EncryptedText);

         } else {
               JOptionPane.showMessageDialog(frame,"Passwords Don't Match: " + SecondEncryptedText + " : " + EncryptedText);
         }
    }
}
  //  JTextArea msg = new JTextArea("TEST");
    // msg.setLineWrap(true);
   //  msg.setWrapStyleWord(true);
    // JScrollPane scrollPane = new JScrollPane(msg);
    // JOptionPane.showMessageDialog(frame, scrollPane);