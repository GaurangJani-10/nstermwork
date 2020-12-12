import java.io.*;
import java.util.*;
import java.net.*;

class Prog15_Client  
{
    public static void main(String args[])throws Exception
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter packet to be sent to server : ");
        String packet=sc.nextLine();
        Socket s=new Socket("127.0.0.1",6969);
        DataOutputStream dos=new DataOutputStream(s.getOutputStream());
        dos.writeUTF(packet);
        DataInputStream dis=new DataInputStream(s.getInputStream());
        String server_msg=dis.readUTF();
        System.out.println(server_msg);
        s.close();
    }
}  



  
