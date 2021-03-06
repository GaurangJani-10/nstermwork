import java.net.*;
import java.util.*;
import java.io.*;

class Prog13_Client
{
    public static void main(String args[]) throws IOException
    {
        DatagramSocket client = new DatagramSocket();
        InetAddress ip = InetAddress.getLocalHost();
        byte[] send = new byte[65536];
        byte[] recieve = new byte[65536];
        Scanner in = new Scanner(System.in);
        System.out.print("\nEnter username:");
        String username=in.nextLine();
        System.out.print("\nEnter password:");
        String password = in.nextLine();
        password = encryptPassword(password);
        String data=username+","+password;
        send=data.getBytes();
        DatagramPacket packet = new DatagramPacket(send,send.length,ip,6969);
        client.send(packet);
        packet = new DatagramPacket(recieve,recieve.length);
        client.receive(packet);
        System.out.print("Server:"+convertToString(recieve));
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
    public static String encryptPassword(String password)
    {
        char[] tokens = password.toCharArray();
        String encrypted_text="";
        for(char c:tokens)
        {
            encrypted_text=encrypted_text+((char)((int)c+3))+"";
        }
        return encrypted_text;
    }
}

