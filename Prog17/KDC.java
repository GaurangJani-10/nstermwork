import java.io.*;  
import java.net.*;   
import java.security.*;  
import javax.crypto.*;  

class KDC
{
    public static void main(String args[]) throws Exception
    {
        SecretKeySpec senderkey,receiverkey;
        byte [] sessionkey,encryptedsessionkey;
        String senderid,receiverid;
        System.out.println("KDC");
        receiverid="receiver1";
        senderid="sender1";
        receiverkey=new SecretKeySpec("36369696".getBytes(),"DES");
        senderkey=new SecretKeySpec("69696363".getBytes(),"DES");
        ServerSocket ss=new ServerSocket(9696);
        Socket s=ss.accept();
        sessionkey=generateSessionKey();
        System.out.println("session key : " +new String(sessionkey));
        DataOutputStream dos=new DataOutputStream(s.getOutputStream());
        Cipher cipher=Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE,senderkey);
        encryptedsessionkey=cipher.doFinal(sessionkey);
        cipher.init(Cipher.ENCRYPT_MODE,receiverkey);
        byte[] encryptedreceiverid=cipher.doFinal(receiverid.getBytes());
        byte[] encryptedsenderid=cipher.doFinal(senderid.getBytes());
        byte[] encryptedsessionkeyclient=cipher.doFinal(sessionkey);
        dos.writeInt(encryptedsessionkey.length);
        dos.write(encryptedsessionkey,0,encryptedsessionkey.length);
        dos.writeInt(encryptedsenderid.length);
        dos.write(encryptedsenderid,0,encryptedsenderid.length);
        dos.writeInt(encryptedreceiverid.length);
        dos.write(encryptedreceiverid,0,encryptedreceiverid.length);
        dos.writeInt(encryptedsessionkeyclient.length);
        dos.write(encryptedsessionkeyclient,0,encryptedsessionkeyclient.length);
    }
    public static byte [] generateSessionKey() throws Exception
    {
        byte[] sessionkey=new byte[8];
        SecureRandom random = new SecureRandom();
        random.nextBytes(sessionkey);
        return sessionkey;
    }
}
    

