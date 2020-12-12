import java.io.*; 
import java.net.*;  
import javax.crypto.*;

class Server
{
    static String senderid;
    static SecretKeySpec senderkey;
    static byte[] encryptedreceiverid,encryptedsenderid,encryptedsessionkeyclient;
    public static void main(String args[]) throws Exception
    {
        System.out.println("Server");
        senderid="sender1"; 
        senderkey=new SecretKeySpec("69696363".getBytes(),"DES");
        getSessionInfoServer();
        ServerSocket ss=new ServerSocket(6969);
        Socket s=ss.accept();
        DataOutputStream dos=new DataOutputStream(s.getOutputStream());
        dos.writeInt(encryptedsenderid.length);
        dos.write(encryptedsenderid,0,encryptedsenderid.length);
        dos.writeInt(encryptedreceiverid.length);
        dos.write(encryptedreceiverid,0,encryptedreceiverid.length);
        dos.writeInt(encryptedsessionkeyclient.length);
        dos.write(encryptedsessionkeyclient,0,encryptedsessionkeyclient.length);
    }
    public static void getSessionInfoServer() throws Exception
    {
        Socket s=new Socket(InetAddress.getLocalHost(),9696);
        DataInputStream dis=new DataInputStream(s.getInputStream());
        byte[] encryptedsessionkey=new byte[dis.readInt()];
        dis.readFully(encryptedsessionkey);
        encryptedsenderid=new byte[dis.readInt()];
        dis.readFully(encryptedsenderid);
        encryptedreceiverid=new byte[dis.readInt()];
        dis.readFully(encryptedreceiverid);
        encryptedsessionkeyclient=new byte[dis.readInt()];
        dis.readFully(encryptedsessionkeyclient);
        Cipher cipher=Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE,senderkey);
        byte[] sessionkey=cipher.doFinal(encryptedsessionkey);
        System.out.println("server session key : " +new String(sessionkey));
    }
}  

