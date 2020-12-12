import java.io.DataInputStream;
import java.net.Socket;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


class Prog17_Client
{
    static String receiverid;
    static SecretKeySpec receiverkey;
    public static void main(String args[]) throws Exception
    {
        System.out.println("client");
        receiverid="receiver1";
        receiverkey=new SecretKeySpec("36369696".getBytes(),"DES");
        Socket s=new Socket("localhost",6969);
        DataInputStream dis=new DataInputStream(s.getInputStream());
        
        byte[] encryptedsenderid=new byte[dis.readInt()];
        dis.readFully(encryptedsenderid);
        
        byte[] encryptedreceiverid=new byte[dis.readInt()];
        dis.readFully(encryptedreceiverid);
        
        byte[] encryptedsessionkeyclient=new byte[dis.readInt()];
        dis.readFully(encryptedsessionkeyclient);
        
        Cipher cipher=Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE,receiverkey);
        
        byte[] senderid=cipher.doFinal(encryptedsenderid);
        System.out.println("sender id : " +new String(senderid));
        
        byte[] receiverid=cipher.doFinal(encryptedreceiverid);
        System.out.println("receiver id : " +new String(receiverid));
        
        byte[] sessionkey=cipher.doFinal(encryptedsessionkeyclient);
        System.out.println("session key : " + new String(sessionkey));
    }  
}  

