import java.io.*;
import java.util.*;
import java.net.*;

class Prog16_Forwarder
{
    public static void main(String args[])throws Exception
    {
        ServerSocket ss=new ServerSocket(6969);
        Socket s=ss.accept();
        DataInputStream dis=new DataInputStream(s.getInputStream());
        DataOutputStream dos=new DataOutputStream(s.getOutputStream());
        String client_msg=dis.readUTF();
        String n=client_msg.toLowerCase();
        StringTokenizer st=new StringTokenizer(n," ");
        int flag1=0,flag2=0,count=0;
        Socket s1=new Socket("127.0.0.1",9696);
        Socket s2=new Socket("127.0.0.1",9393);
        DataOutputStream dos1=new DataOutputStream(s1.getOutputStream());
        DataInputStream dis1=new DataInputStream(s1.getInputStream());
        DataOutputStream dos2=new DataOutputStream(s2.getOutputStream());
        DataInputStream dis2=new DataInputStream(s2.getInputStream());
        String server1_msg="",server2_msg="";
        while(st.hasMoreTokens())
        {
            int num=Integer.parseInt(st.nextToken());
            if(num%2==0)
            {
                server2_msg=server2_msg+" "+num;
            }
            else
            {
                server1_msg=server1_msg+" "+num;
            }
        }
        dos1.writeUTF(server1_msg);
        dos2.writeUTF(server2_msg);
        String ack1=dis1.readUTF();
        String ack2=dis2.readUTF();
        if(ack1.equals("1")&&ack2.equals("1"))
        {
            dos.writeUTF("packets delivered to servers");
        }
        else
        {
            dos.writeUTF("packets not delivered to servers");
        }
        ss.close();
        s.close();
        s1.close();
        s2.close();
    }
}  