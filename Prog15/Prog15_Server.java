import java.io.*;  
import java.util.*;  
import java.net.*;  

class Prog15_Server  
{
    public static void main(String args[])throws Exception
    {
        ServerSocket ss=new ServerSocket(9696);
        Socket s=ss.accept();
        DataInputStream dis=new DataInputStream(s.getInputStream());
        String client_msg=dis.readUTF();
        System.out.println("client packet:"+client_msg);
        DataOutputStream dos=new DataOutputStream(s.getOutputStream());
        dos.writeUTF("1");
        s.close();
        ss.close();
    }
}  
