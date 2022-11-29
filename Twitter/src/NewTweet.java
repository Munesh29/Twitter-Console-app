import java.text.SimpleDateFormat;
import java.util.Date;

public class NewTweet extends UserLogin
{
    void newTweet(String userName)
    {
        System.out.println("\nEnter your new Tweet : ");
        String enterTweet=scan.nextLine();
        Date date=new Date();
        SimpleDateFormat setDate=new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat setTime=new SimpleDateFormat("hh:mm:ss a");
        String presentDate=setDate.format(date),presentTime=setTime.format(date);
        TwitterFeed newTweet=new TwitterFeed();
        newTweet.addNewTweet(userName,presentDate,presentTime,enterTweet,0,0,0);
    }
}
