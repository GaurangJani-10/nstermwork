import java.net.*;

public class PortScanner
{   
    public static void main(String args[])
    {
        for(int i=1; i<=65535; i++)
        {
            try
            {
                Socket s=new Socket();
                s.connect(new InetSocketAddress("localhost",i),1000);
                s.close();
                System.out.println("Port "+i+" is open!");
            }
            catch(Exception e)
            { }
        }
    }
}

/*
E:\Gaurang\Study\MCA Sem 5\NS>javac PortScanner.java

E:\Gaurang\Study\MCA Sem 5\NS>java PortScanner
Port 25 is open!
Port 80 is open!
Port 110 is open!
Port 119 is open!
Port 135 is open!
Port 143 is open!
*/