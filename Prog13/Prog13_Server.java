import java.net.*;
import java.util.*;
import java.io.*;

class Prog13_Server
{
    static HashMap<String,String> user_data = new HashMap<String,String>();
    public static void main(String args[]) throws IOException
    {
        user_data.put("Gaurang","Gaurang");
        user_data.put("MCA510","MCA510");
        DatagramSocket server = new DatagramSocket(6969);
        byte[] send = new byte[65536];
        byte[] recieve = new byte[65536];
        DatagramPacket packet = new DatagramPacket(recieve,recieve.length);
        server.receive(packet);
        String[] message=(convertToString(recieve)).split(",");
        String username=message[0];
        String password=decryptPassword(message[1]);
        InetAddress ip = packet.getAddress();
        int port=packet.getPort();
        System.out.print("\nUsername : "+username+"\nPassword : "+password);
        String status=check(password,username);
        packet=new DatagramPacket(status.getBytes(),status.getBytes().length,ip,port);
        server.send(packet);
    }
    public static String check(String password,String username)
    {
        if(password.equals((String)user_data.get(username)))
        {
            return "success";
        }
        return "failure";
    }
    public static String convertToString(byte[] a)
    {
        if (a == null)
            return null;
        String s = "";
        int i = 0;
        while (a[i] != 0)
        {
            s=s+(char)a[i];
            i++;
        }
        return s;
    }
    public static String decryptPassword(String password)
    {
        char[] tokens = password.toCharArray();
        String decrypted_text="";
        for(char c:tokens)
        {
            decrypted_text=decrypted_text+((char)((int)c-3))+"";
        }
        return decrypted_text;
    }
}