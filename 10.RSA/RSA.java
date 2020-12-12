import java.security.*;  
import javax.crypto.*;  
import javax.crypto.spec.*;  

class RSA
{
    public KeyPairGenerator keygen;
    public KeyPair myKey;
    Cipher ciph;
    public RSA() throws Exception
    {
        keygen = KeyPairGenerator.getInstance("RSA");
        keygen.initialize(512) ;
        myKey = keygen.generateKeyPair();
        ciph = Cipher.getInstance("RSA"); 
    }
    public byte[] doEncryption(String s) throws Exception
    {
        ciph.init(Cipher.ENCRYPT_MODE,myKey.getPublic());
        byte[] text = s.getBytes();
        byte[] textEncrypted = ciph.doFinal(text);
        return(textEncrypted);
    }
    public String doDecryption(byte[] s)throws Exception
    {
        ciph.init(Cipher.DECRYPT_MODE,myKey.getPrivate());
        byte[] textDecrypted = ciph.doFinal(s);
        return(new String(textDecrypted));
    }
    public static void main(String[] argv) throws Exception
    {
        RSA d=new RSA();
        byte[] str=d.doEncryption("GaurangJani");
        System.out.println("Encrypted String : "+str);
        System.out.println("Decrypted String : "+d.doDecryption(str));
    }
}  
