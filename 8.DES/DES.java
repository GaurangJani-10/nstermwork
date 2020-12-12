import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.Scanner;  

class DES
{
    private SecretKey secretKey;
    DES() throws Exception
    {
        secretKey=KeyGenerator.getInstance("DES").generateKey();
    }
    private byte[] doEncryption(String plainText) throws Exception
    {
        Cipher cipher=Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);
        return cipher.doFinal(plainText.getBytes());
    }
    private byte[] doDecryption(String cipherText) throws Exception
    {
        Cipher cipher=Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE,secretKey);
        return cipher.doFinal(cipherText.getBytes());
    }
    public static void main(String args[]) throws Exception
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Message : ");
        String plainText=sc.nextLine();
        DES DES=new DES();
        String cipherText=new String(DES.doEncryption(plainText));
        System.out.println("Encrypted Text : " + cipherText);
        System.out.println("Encrypted Text : " + new String(DES.doDecryption(cipherText)));  
    }  
}  
