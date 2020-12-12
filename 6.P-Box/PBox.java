import java.util.Scanner;  

class PBox{
  public String doEncryption(String s)
  {
    byte p[]=new byte[8];
    byte pTemp[]=new byte[8];
    pTemp=s.getBytes();
    p[0]=pTemp[1];
    p[1]=pTemp[7];
    p[2]=pTemp[0];
    p[3]=pTemp[5];
    p[4]=pTemp[6];
    p[5]=pTemp[2];
    p[6]=pTemp[3];
    p[7]=pTemp[4];
    return(new String(p));
  }
  public String doDecryption(String s){
    byte p[]=new byte[8];
    byte pTemp[]=new byte[8];
    pTemp=s.getBytes();
    p[0]=pTemp[2];
    p[1]=pTemp[0];
    p[2]=pTemp[5];
    p[3]=pTemp[6];
    p[4]=pTemp[7];
    p[5]=pTemp[3];
    p[6]=pTemp[4];
    p[7]=pTemp[1];
    return(new String(p));
  }
  public static void main(String args[]){
    Scanner sc=new Scanner(System.in);
    System.out.println("Enter string of 8 characters : ");
    String plaintext=sc.nextLine();
    PBox pbox=new PBox();
    System.out.println("Encrypted Text : " + pbox.doEncryption(plaintext));
    System.out.println("Decrypted Text : " + pbox.doDecryption(pbox.doEncryption(plaintext)));
  }  
}  