import java.io.*;
import java.util.*;
import java.net.*;

class Prog16_ServerOdd
{
 public static void main(String args[])throws Exception
 {
  ServerSocket ss=new ServerSocket(9696);
  Socket s=ss.accept();
  DataInputStream dis=new DataInputStream(s.getInputStream());
  DataOutputStream dos=new DataOutputStream(s.getOutputStream());
  String client_msg=dis.readUTF();
  if(client_msg.equals(""))
  {
   dos.writeUTF("0");
  }
  else
  { 
   System.out.println("client packet:"+client_msg);
   dos.writeUTF("1");
  }
  s.close();
  ss.close();
 }
}  
