import java.util.Scanner;

public class UserLogin extends UserDetails{
    public static void main(String args[]) throws Exception
    {
        Scanner scan=new Scanner(System.in);
        Files file=new Files();
        UserDetails userData=new UserDetails();
        String isNewUser;
        boolean check=true;
        System.out.println("\nWelcome to Twitter!");
        file.getFile();
        while(check)
        {
            System.out.println("\nEnter \"1\" for Create New User Account\nEnter \"2\" for Login\nEnter \"0\" for Exit");
            isNewUser=scan.next();
            if(isNewUser.equals("1"))
            {
                userData.insertLoginDetails();
                int check1=tweet();
                if(check1==0){
                    break;
                }
            }
            else if(isNewUser.equals("2"))
            {
                int valid=userData.checkLoginDetails();
                if(valid==1)
                {
                    userData.insertLoginDetails();
                    int check1=tweet();
                    if(check1==0){
                        break;
                    }
                }
                else if(valid==2){
                    break;
                }
                else{
                    int check1=tweet();
                    if(check1==0){
                        break;
                    }
                }
            }
            else if(isNewUser.equals("0"))
            {
                check=false;
            }
            else
            {
                System.out.println("\nEnter a valid number");
            }
        }
        System.out.println("\nThank you");
        scan.close();
    }
    private static int tweet() 
    {
            UserDetails userData=new UserDetails();
            String userName=userData.getUserName();
            boolean check=true;
            while(check)
            {
                System.out.println("\nEnter \"1\" for create a new Tweet \nEnter \"2\" for Show All Tweet\nEnter \"9\" for Logout\nEnter \"0\" for Exit");
                String checkOption=userData.getOption();
                if(checkOption.equals("1"))
                {
                    NewTweet newTweet=new NewTweet();
                    newTweet.newTweet(userName);
                    System.out.println("\nNew Tweet is Successfully Added");
                }
                else if(checkOption.equals("2"))
                {
                    ShowAllTweet showAllTweet=new ShowAllTweet();
                    int islogout;
                    islogout = showAllTweet.showAllTweet(userName);
                    if(islogout==9){return 9;}
                    else if(islogout==0){return 0;}
                }
                else if(checkOption.equals("9")){return 9;}
                else if(checkOption.equals("0")){return 0;}
                else
                {
                    System.out.println("\nEnter a valid number");
                }
            }
        return 9;
    }
}
