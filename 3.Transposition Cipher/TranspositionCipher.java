import java.util.*;  

class TranspositionCipher  

{  
           public static void main(String args[])throws Exception  
           {  
                       Scanner sc=new Scanner(System.in);  
                       System.out.println("enter key of unique alphabets : ");  
                       String k=sc.nextLine();  
                       char[] key=k.toCharArray();  
                       char[] temp_key=new char[key.length];  
                       System.arraycopy(key,0,temp_key,0,key.length);  
                       Arrays.sort(temp_key);  
                       System.out.println("\nenter string :");  
                       String t=sc.nextLine();  
                       char[] str=t.toCharArray();  
                       for(int i=0;i<str.length;i++)  
                       {  
                                  if(str[i]==' ')  
                                              str[i]='$';  

                       }  
                       int index=0,row;  
                       if(((str.length)%(key.length))==0)  
                                  row=((str.length)/(key.length));  
                       else  
                                  row=((str.length)/(key.length))+1;
                      
                       char[] cipher=new char[(row*(key.length))];
                       int ci=0;
                       while(ci<(row*(key.length)))
                       {
                        for(int i=0;i<key.length;i++)
                        {
                         index=0;
                         for(int j=0;j<key.length;j++)
                         {
                          if(temp_key[i]==key[j])
                          {
                            index=j;
                            int l=0;
                            while(l<row)
                            {
                              if(index<str.length)
                              {
                                cipher[ci]=str[index];
                                ci++;
                                l++;
                                index=index+(key.length);
                              }
                              else
                              {
                                cipher[ci]='!';
                                ci++;
                                l++;
                              }
                            }
                            break;
                          }
                        }
                      }
                    }
                    System.out.println("Cipher text : ");
                    for(int i=0;i<cipher.length;i++)
                    { 
                     System.out.print(cipher[i]);
                    }
                    char[] decipher=new char[cipher.length];
                    int di=0;
                    int l=0;
                    while(di<cipher.length)
                    {
                     for(int i=0;i<key.length;i++)
                     {
                      index=0;
                      for(int j=0;j<key.length;j++)
                      {
                       if(key[i]==temp_key[j])
                       {
                        index=((j)*row)+l;
                        decipher[di]=cipher[index];
                        if(decipher[di]=='$')
                          decipher[di]=' ';
                        if(decipher[di]=='!')
                          decipher[di]='\0';
                        di++;
                        break;
                       }
                     }
                   }
                   l++;
                 }
                 System.out.println("\nDecipher text : ");
                 for(int i=0;i<cipher.length;i++)
                  {
                    System.out.print(decipher[i]);
                  }
            }
}  
