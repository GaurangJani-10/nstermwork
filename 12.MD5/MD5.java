import java.util.Scanner;
import java.math.*;
import java.security.*;  

class MD5
{
    private String doEncryption(String text) throws Exception
    {
        MessageDigest msgdgst=MessageDigest.getInstance("MD5");
        byte[] msg=msgdgst.digest(text.getBytes());  
        BigInteger bigInt=new BigInteger(1,msg);
        String hashValue=bigInt.toString(32);
        while(hashValue.length()<64)
            hashValue+=0+hashValue;
        return  hashValue;
    }
    public static void main(String args[]) throws Exception
    {
        MD5 md5=new MD5();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Message : ");
        String text=sc.nextLine();
        System.out.println("Hash Text : " + md5.doEncryption(text));
    }
}  
