import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Files
{
    private static ArrayList<String> userList=new ArrayList<>();
    private static ArrayList<String> tweetList=new ArrayList<>();
    File userdata=new File("UserData.txt");
    File tweetdata=new File("TwitterFeed.txt");
    void getFile() throws Exception{
        Scanner scan1=new Scanner(userdata);
        Scanner scan2=new Scanner(tweetdata);
        while(scan1.hasNextLine())
        {
            userList.add(scan1.nextLine());
        }
        while(scan2.hasNextLine())
        {
            tweetList.add(scan2.nextLine());
        }
        scan1.close();
        scan2.close();
    }
    ArrayList<String> getUserDetails(){
        return userList;
    }
    ArrayList<String> getTwitterFeed(){
        return tweetList;
    }
    void setUserlist(String username,String password,String emailOrPhoneNo)throws Exception
    {
        BufferedWriter buf=new BufferedWriter(new FileWriter(userdata,true)); 
        buf.write(username+"-||*||-");
        buf.write(password+"-||*||-");
        buf.write(emailOrPhoneNo);
        buf.newLine();
        buf.close();
    }
    void setTweetList() throws IOException{
        tweetdata.delete();
        tweetdata.createNewFile();
        BufferedWriter buf=new BufferedWriter(new FileWriter(tweetdata)); 
        for(int i=0;i<tweetList.size();i++)
        {
            buf.write(tweetList.get(i));
            buf.newLine();
        }
        buf.close();
    }
}
