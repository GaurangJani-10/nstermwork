import java.io.*;
import java.util.*;
import java.net.*;

class Prog15_Firewall
{
    public static void main(String args[])throws Exception
    {
        ServerSocket ss=new ServerSocket(6969);
        Socket s1=ss.accept();
        DataInputStream dis=new DataInputStream(s1.getInputStream());
        String client_msg=dis.readUTF();
        String chk_pck=client_msg.toLowerCase();
        String threat="terrorist";
        StringTokenizer st=new StringTokenizer(chk_pck," ");
        String err="";
        int flag=0;
        DataOutputStream dos=new DataOutputStream(s1.getOutputStream());
        while(st.hasMoreTokens())
        {
            if(threat.equals(st.nextToken()))
            {
                err="Package contains threat, cannot be delivered";
                dos.writeUTF(err);
                s1.close();
                flag=1;
                break;
            }
        }
        if(flag==0)
        {
            Socket s2=new Socket("127.0.0.1",9696);
            DataOutputStream dos1=new DataOutputStream(s2.getOutputStream());
            dos1.writeUTF(client_msg);
            DataInputStream dis1=new DataInputStream(s2.getInputStream());
            String ack=dis1.readUTF();
            if(ack.equals("1"))
            {
                dos.writeUTF("packet has been recieved by the server");
            }
            else
            {
                dos.writeUTF("Server unavailable");
            }
            s1.close();
            s2.close();
        }
        ss.close();
    }  
}  



  