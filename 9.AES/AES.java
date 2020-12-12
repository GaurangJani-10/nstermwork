import javax.crypto.*;
import javax.crypto.spec.*;  
import java.util.Scanner;  

class AES
{
    private byte[] key;
    AES()
    {
        key="qwertyuiop123456".getBytes();
    }
    private byte[] doEncryption(String plainText) throws Exception
    {
        SecretKeySpec secretKey=new SecretKeySpec(key,"AES");
        Cipher cipher=Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);
        return cipher.doFinal(plainText.getBytes());
    }
    private byte[] doDecryption(String cipherText) throws Exception
    {
        SecretKeySpec secretKey=new SecretKeySpec(key,"AES");
        Cipher cipher=Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE,secretKey);
        return cipher.doFinal(cipherText.getBytes());
    }
    public static void main(String args[]) throws Exception
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Message : ");
        String plainText=sc.nextLine();
        AES aes=new AES();
        String cipherText=new String(aes.doEncryption(plainText));
        System.out.println("Encrypted Text : " + cipherText);
        System.out.println("Encrypted Text : " + new String(aes.doDecryption(cipherText)));
    }  
}  
