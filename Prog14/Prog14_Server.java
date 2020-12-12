import java.util.*;
import java.io.*;
import java.net.*;
import java.math.*;
import java.security.*;
import java.sql.*;

class Prog14_Server
{
    public static void main(String args[])throws Exception
    {
        ServerSocket ss=new ServerSocket(6969);
        Socket s=ss.accept();
        DataInputStream dis=new DataInputStream(s.getInputStream());
        String uname=dis.readUTF();
        String e_password=dis.readUTF();
        conversion c=new conversion();
        String d_password=c.convert(3,e_password);
        SHA hash=new SHA();
        String hash_password=hash.doEncryption(d_password);
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
        Statement st= con.createStatement();
        String sql="select hash_code from user_details where user_id='"+uname+"';";
        ResultSet rs=st.executeQuery(sql);
        rs.next();
        String db_hash_code=rs.getString(1);
        con.close();
        DataOutputStream dos=new DataOutputStream(s.getOutputStream());
        if(hash_password.equals(db_hash_code))
        {
            dos.writeUTF("Login Successful");
        }
        else
        {
            dos.writeUTF("Incorrect Password");
        }
    }  
}  



class Conversion_Server 
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

class SHA
{
    public String doEncryption(String text) throws Exception
    {
        MessageDigest md=MessageDigest.getInstance("SHA-1");
        byte[] msg=md.digest(text.getBytes());
        BigInteger bigInt=new BigInteger(1,msg);
        String hashValue=bigInt.toString(16);
        while(hashValue.length()<32)
            hashValue+=0+hashValue;
        return hashValue;
    }
}  

