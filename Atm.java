import java.util.*;
class userdetails{
   
     String username;
     String pass;
     String balance;
     public userdetails(String username,String pass,String balance)
     {
         this.username=username;
         this.pass=pass;
         this.balance=balance;
     }
    }
class admindetails
{
    String adminname;
    String pin;
    public admindetails(String adminname,String pin)
    {
        this.adminname=adminname;
        this.pin=pin;
    }
}

public class Atm {
    static userdetails user1=new userdetails("Admin","1234","1000000");
    static userdetails user2=new userdetails("ABC","5678","100000");
    static userdetails user3=new userdetails("DEF","5678","200000");
    static admindetails admindetails=new admindetails("bank","0000");
    static int notesname[]={2000,500,200,100};
    static int demo[]={0,0,0,0};
    static int attempt=0;
    
    static int atmmoneysum=0;
    static Scanner sc=new Scanner(System.in);
  public static void Admin()
    { 
        System.out.println("Admin logged in successfully"); 
        
    while(true)
    {
    System.out.println("-------"); 
    System.out.println("1.LOAD");
    System.out.println("2.SHOW");
    System.out.println("3.EXIT");
    System.out.print("Enter choice : "); 
    int adminchoice=sc.nextInt();
    
    if(adminchoice==1)
     {
        for(int i=0;i<demo.length;i++)
        {
            System.out.println("enter no of notes to be added :"+notesname[i]);
            int add=sc.nextInt();
            demo[i]=demo[i]+add;

        }
        System.out.println("Amount Loaded successfully!");;
    }

    else if(adminchoice==2)
    {
      for(int i=0;i<demo.length;i++)
      {
          System.out.println(notesname[i]+"-->"+demo[i]);
      }
    }
    else if(adminchoice==3)
    {
        break;
    }
    else
    {
        System.out.println("Invalid input");
        break;
    }
}

}



 
    public static void user()
    {
    System.out.println("User logged in successfully"); 
       
    while(true)
    {
    
    System.out.println("-------");
    System.out.println("Enter admin choice");
    System.out.println("1.Withdraw");
    System.out.println("2.Checkbalance");
    System.out.println("3.Pin Change");
    System.out.println("4.Deposit");
    System.out.println("5.Exit");
    System.out.print("Enter choice : "); 
    int userchoice=sc.nextInt();
    if(userchoice==1)
    {
        System.out.println("Enter the amount to be withdrawn: ");
        int enteramount=sc.nextInt();
        int temp=enteramount;
        int withdrawnotes[]={0,0,0,0};
        int count=0;
         if(enteramount<=Integer.parseInt(user1.balance) && enteramount<=atmmoneysum)
         {
            for(int i=0;i<demo.length;i++)
            {
                withdrawnotes[i]=temp/notesname[i];
                temp=temp%notesname[i];
            }
            for(int i=0;i<demo.length;i++)
            {
            if(withdrawnotes[i]<=demo[i])
            {
                    count++;
            }
            } 
                if(count==4)
                {
                  int updated=Integer.parseInt(user1.balance)-enteramount;
                    user1.balance=Integer.toString(updated);
                    for(int i=0;i<4;i++)
                    {
                        demo[i]=demo[i]-withdrawnotes[i];
                    }
                    System.out.print("Amount Withdraw Successfully");
                    System.out.println();
                    for(int i:withdrawnotes)
                    {
                        System.out.print(i+" ");
                    }
                    System.out.println();
     
                }
                else
                {
                    for(int i=0;i<demo.length;i++)
                    {
                        System.out.println(notesname[i]+"-->"+demo[i]);
                    }
                }  
            }
            
        
    }
    else if(userchoice ==2)
    {
        System.out.println("Balance Amount: "+user1.balance);
    }
    else if(userchoice==3)
    {
        System.out.println("Enter Old pin");
        String prevpin=sc.next();
        if(prevpin.equals(user1.pass))
        {
            System.out.println("Enter New pin");
            String newpin=sc.next();
            user1.pass=newpin;
            System.out.println("Pin Changed Successfully!");
        }
    }
    else if(userchoice==4)
    {
        System.out.println("Enter deposit amount");
        int depositamount=sc.nextInt();
        int temp=depositamount;
        int depositnotes[]={0,0,0,0};
       
        for(int i=0;i<demo.length;i++)
            {
                depositnotes[i]=temp/notesname[i];
                temp=temp%notesname[i];
            }
        for(int i=0;i<notesname.length;i++)
        {
            demo[i]=demo[i]+depositnotes[i];
        }
        int updated=Integer.parseInt(user1.balance)+depositamount;
        user1.balance=Integer.toString(updated);
        System.out.println("Amount Deposited Successfully!");
    }
    else if(userchoice==5)
    {
        break;
    }
    else
    {
        System.out.println("Invalid choice");
        break;
    }
    }
}
    public static void main(String[] args) {
        sc=new Scanner(System.in);
        
        while(true)
        {
        System.out.println("ATM APPLICATION");
        System.out.println("1.Admin");
        System.out.println("2.User");
        System.out.println("3.Exit");
        System.out.print("Enter your choice : ");
        int a=sc.nextInt();
        if(a==1)
        {
            System.out.print("Enter username :");
            System.out.println();
            String giveadminuser=sc.next();
            System.out.println("Enter password : ");
            String giveadminpass=sc.next();
            System.out.println(admindetails.adminname+" "+admindetails.pin);
            if(giveadminuser.equals(admindetails.adminname) && giveadminpass.equals(admindetails.pin))
            {
                Admin();
            }
            else
            {
                System.out.println("Invalid Admin");
            }
        }
        else if(a==2)
        {
            System.out.println("Enter username : ");
            String giveuser=sc.next();
           
            
            if(giveuser.equals(user1.username))
            {
                while(attempt<3)
                {
                    System.out.println("Enter user password");
                    String givepass=sc.next();
                if(givepass.equals(user1.pass))
                {
                    user();
                    break;
                }
                else
                {
                    attempt++;
                    
                    System.out.println("Wrong password");
                }
            }
            if(attempt>=3)
            {
                System.out.println("Temporarily blocked");
            }
            }
            else
            {
                System.out.println("Invalid User");
            }

        }
        else if(a==3)
        {

            break;
        }
        else
        {
            System.out.println("Invalid input");
        }
        }
    }
}

