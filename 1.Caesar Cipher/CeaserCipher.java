import java.util.*;

class CeaserCipher
{

	public static void main (String args[]) throws Exception
	{	
		Scanner sc=new Scanner(System.in); 
		System.out.print("Enter string to be encrypted : "); 
		String ipstr=sc.nextLine();
		int key=6;
		conversion c=new conversion();
		String encrypt=c.convert((key*-1),ipstr); 
		System.out.println("encrypted string : "+encrypt); 
		String decrypt=c.convert(key,encrypt); 
		System.out.println("decrypted string : "+decrypt);
	}	
}

class conversion
{

	char temp,t; 
	int ascii=0;
	public String convert(int key,String ipstrmsg)
	{
 		String op="";
		int min=0,max=0,flag=0;
		for(int i=0;i<ipstrmsg.length();i++)
		{
			temp=ipstrmsg.charAt(i); 
			ascii=temp+key;
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
				if(ascii>max)
				{
					int rem=ascii-max;
					t=(char)((min-1)+rem);
				}
				else if(ascii<min)
				{
					int rem=min-ascii; 
					t=(char)((max+1)-rem);
				}
				else
				{
					t=(char)(ascii);
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
