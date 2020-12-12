import java.util.*;

class SBox{
  char key[][];
  Random rand;
  SBox()
  {
    rand=new Random();
    int add=rand.nextInt(5);
    key=new char[52][2];
    char temp='A',ch;
    for(int i=0;i<key.length;i++,temp++)
    {
      if(temp<='Z' && temp>='A')
      {
        ch=(char)(temp+add);
        if(ch>'Z')
        {
          ch=(char)(ch-'Z'+'A'-1);
        }
        key[i][0]=(char)temp;
        key[i][1]=(char)(ch);
        if(temp=='Z')
        {
          temp=(char)('a'-1);
        }
      }
      else if(temp<='z' && temp>='a')
      {
        ch=(char)(temp+add);
        if(ch>'z')
        {
          ch=(char)(ch-'z'+'a'-1);
        }
        key[i][0]=(char)temp;
        key[i][1]=(char)(ch);
      }
    }
  }
  public String doEncryption(String s)
  {
    String cipherText="";
    for(int i=0;i<s.length();i++)
    {
      for(int j=0;j<key.length;j++)
      {
        if(s.charAt(i)==key[j][0])
        {
          cipherText+=key[j][1];
        }
      }
    }
    return cipherText;
  }
  public void doDecryption(String s)
  {
    String plainText="";
    for(int i=0;i<s.length();i++)
    {
      for(int j=0;j<key.length;j++)
      {
        if(s.charAt(i)==key[j][1])
        {
          plainText+=key[j][0];
        }
      }
    }
    System.out.println("Decrypted Text : " + plainText);
  }
  public static void main(String args[])
  {
    SBox s=new SBox();
    Scanner sc=new Scanner(System.in);
    System.out.println("Enter Message : ");
    String plaintext=sc.nextLine();
    String encrypted = s.doEncryption(plaintext);
    System.out.println("Encrypted Text : " + encrypted);
    s.doDecryption(encrypted);
  }
}  