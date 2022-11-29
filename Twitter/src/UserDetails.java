import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserDetails extends Files
{
    Scanner scan=new Scanner(System.in);
    private ArrayList<String> userList=getUserDetails();
    public static String userName;
    String password,emailOrPhoneNo;
    void insertLoginDetails()
    {
        boolean check=true;
        System.out.println("\nEnter your User name : ");
        while(check){
            userName=scan.next();
            int flag=0;
            for(int i=0;i<userList.size();i++){
                String[] str=userList.get(i).split("-\\|\\|\\*\\|\\|-");
                if(userName.equals(str[0])){
                    System.out.println("\nEntered User Name is already exits\nPlease Enter another user name : ");
                    flag=1;
                    break;
                }
            }
            if(flag==0){break;}
        }   
        System.out.println("\nEnter your password : ");
        password=scan.next();
        System.out.println("\nEnter your emailID or Phone number");
        while(true){
            emailOrPhoneNo=scan.next();
            if(Pattern.matches("[a-z]+[0-9]*[@]{1}[a-z]+[.]{1}[a-z]+", emailOrPhoneNo)){
                if(emailOrPhoneNo.contains("gmail")&& (emailOrPhoneNo.contains("com")||emailOrPhoneNo.contains("in"))){
                    break;
                }
            }
            else if(Pattern.matches("[6789][0-9]{9}", emailOrPhoneNo)){
                break;
            }
            System.out.println("\nEntered emailId or Phone number is Invalid\nExamples emailId: xyz@gmail.com or xyz@gmail.in or xyz123@gmail.com or xyz123@gmail.in");
            System.out.println("Phone Number must start with [6 or 7 or 8 or 9] and must contain 10 digit");
            System.out.println("\nEnter a valid email Id or Phone number");
        }
            try {
                setUserlist(userName,password,emailOrPhoneNo);
            } catch (Exception e) {}
    }
    int checkLoginDetails()
    {
        System.out.println("\nEnter your User Name");
        boolean checkUsername=true;
        int userindex=0;
        while(checkUsername)
        {
            userName=scan.next();
            int flag=0;
            for(int i=0;i<userList.size();i++){
                String[] str=userList.get(i).split("-\\|\\|\\*\\|\\|-");
                if(userName.equals(str[0])){
                    userindex=i;
                    flag=1;
                    break;
                }
            }
            if(flag==0){
                System.out.println("\nEntered User Name is invalid");
                System.out.println("\nEnter \"1\" for re-enter User name \nEnter \"2\" for create a new UserAccount\nEnter \"0\" for exit");
                String check=scan.next();
                if(check.equals("2")){return 1;}
                else if(check.equals("0")){return 2;}
                else{System.out.println("\nEnter a valid user name : ");}
            }
            else{
                String[] str=userList.get(userindex).split("-\\|\\|\\*\\|\\|-");
                Boolean checkpassword=true;
                while(checkpassword)
                {
                    System.out.println("\nEnter your password");
                    password=scan.next();
                    if(str[1].equals(password)){
                    return 0;
                    }
                    else
                    {
                        System.out.println("\nEntered Password is incorrect");
                        System.out.println("\nEnter \"1\" for Re-Enter your password \nEnter \"2\" for Forget Password\nEnter \"0\" for Exit");
                        String checkOption=scan.next();
                        if(checkOption.equals("2")){
                            boolean checkemail=true;
                            while(checkemail){
                                System.out.println("Enter your emailId or Phone number");
                                emailOrPhoneNo=scan.next();
                                if(str[2].equals(emailOrPhoneNo)){
                                    System.out.println("\nYour Password is : "+str[1]);
                                    checkemail=false;checkpassword=false;
                                    System.out.println("\nEnter your User Name");
                                }
                                else{
                                    System.out.println("\nEntered emailId or Phone Number is Invalid\n");
                                    System.out.println("Enter \"1\" for Re-enter your emailId or phone number \nEnter \"2\" for create a new UserAccount\nEnter \"0\" for exit");
                                    String check=scan.next();
                                    if(check.equals("2")){return 1;}
                                    else if(check.equals("0")){return 2;}
                                }
                            }
                        }
                        else if(checkOption.equals("0")){return 2;}
                    }
                }
            }
        }
        return 0;
    } 
    String getOption(){
        String option=scan.next();
        return option;
    }
    String getUserName(){return userName;}
}
