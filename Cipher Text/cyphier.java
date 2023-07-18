import java.util.*;
public class cyphier {

    public static void main(String args[])
    {
            Scanner sc=new Scanner(System.in);
        int ch;

            System.out.println("Enter plain text");
            String str=sc.next();
            System.out.println("Enter key");
            int key=sc.nextInt();

        do{
            
            System.out.println("\n\n--------------Cyphier Text---------------");  
            System.out.println("1) Encryption \n 2) Decryption \n 3) Exit");
            System.out.println("Enter Your Choice : ");
            ch = sc.nextInt();

            switch(ch)
            {
                case 1 :    for(int i=0;i<str.length();i++)
                            {
                                char c = str.charAt(i);
                                int a = (int)c + key;
                                char d =  (char) a;
                                System.out.print(d);
                            }
                            break;
                case 2 :    for(int i=0;i<str.length();i++)
                            {
                                char c = str.charAt(i);
                                int a = (int)c - key;
                                char d =  (char) a;
                                System.out.print(d);
                            }
                            break;
                case 3 : System.out.println("Thank You");break;
                default : System.out.println("Invalid Choice");
            }
            
        }while(ch!=3);
            
            
    }
    
}
