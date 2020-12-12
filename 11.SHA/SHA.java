import java.util.Scanner;
import java.math.*;
import java.security.*;

class SHA
{
    private String doEncryption(String str) throws Exception
    {
        MessageDigest md=MessageDigest.getInstance("SHA-1");
        byte[] msg=md.digest(str.getBytes());
        BigInteger bigInt=new BigInteger(1,msg);
        String hashValue=bigInt.toString(32);
        while(hashValue.length()<64)
            hashValue+=0+hashValue;
        return hashValue;
    }
    public static void main(String args[]) throws Exception
    {
        SHA sha=new SHA();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Message : ");
        String str=sc.nextLine();
        System.out.println("Hash Text : " + sha.doEncryption(str));
    }
}  