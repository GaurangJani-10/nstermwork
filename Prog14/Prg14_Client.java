import java.io.*;
import java.util.*;
import java.net.*;

class Prog14_Client
{
    public static void main(String args[])throws Exception
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter username : ");
        String uname=sc.nextLine();
        System.out.print("Enter password : ");
        String pwd=sc.nextLine();
        Socket s=new Socket("127.0.0.1",6968);
        DataOutputStream dos=new DataOutputStream(s.getOutputStream());
        dos.writeUTF(uname);
        conversion c=new conversion();
        String encrypt_pwd=c.convert((3*-1),pwd);
        dos.writeUTF(encrypt_pwd);
        DataInputStream dis=new DataInputStream(s.getInputStream());
        String msg=dis.readUTF();
        System.out.println(msg);
        s.close();
    } 
}    

class Conversion_Client
{
    char temp,t;
    int asci=0;
    public String convert(int key,String inputmsg)
    {
        String op="";
        int min=0,max=0,flag=0;
        for(int i=0;i<inputmsg.length();i++)
        {
            temp=inputmsg.charAt(i);
            asci=temp+key;
            if (temp>=97&&temp<=122)
            {
                min=97;
                max=122;
                flag=1;
            }
            else if(temp>=48&&temp<=57)
            {
                min=48;
                max=57;
                flag=1;
            }
            else if(temp>=65&&temp<=90)
            {
                min=65;
                max=90;
                flag=1;
            }
            else
                flag=0;
            if(flag==1)
            {
                if(asci>max)
                {
                    int rem=asci-max;
                    t=(char)((min-1)+rem);
                }
                else if(asci<min)
                {
                    int rem=min-asci;
                    t=(char)((max+1)-rem);
                }
                else
                {
                    t=(char)(asci);
                }
                op=op+t;
            }
            else
            {
                op=op+temp;
            }            
        }
        return op;
    } 
}