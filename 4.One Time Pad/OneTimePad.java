import java.util.*;

class OneTimePad
{
  public static void main(String args[])throws Exception
  {
    Scanner sc=new Scanner(System.in);
    System.out.println("enter string : ");
    String str1=sc.nextLine();
    char[] pad=new char[str1.length()];
    Random rand = new Random();
    for(int i=0;i<str1.length();i++)
      {
        int ascii=rand.nextInt(123);
        if(ascii<=127)
          {
            pad[i]=(char)ascii;
          }
        else
          i--;
      }
    System.out.println(pad);
    char[] msg=str1.toCharArray();
    char[] str2=new char[msg.length];
    for(int i=0;i<msg.length;i++)
      {
        str2[i]=(char)(msg[i]^pad[i]);
      }
    System.out.print("cipher text:");
    System.out.print(str2);
    char[] str3=new char[msg.length];
    for(int i=0;i<msg.length;i++)
      {
        str3[i]=(char)(pad[i]^str2[i]);
      }
    System.out.print("\noriginal text:");
    System.out.print(str3);
  }
}  
