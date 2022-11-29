import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TwitterFeed extends Files
{
    private ArrayList<String> allTweetList=getTwitterFeed();
    void addNewTweet(String userName,String date1,String time,String tweet,int like,int retweet,int comment)
    {
        String strg=userName+"!L@#"+"tweet"+"!L@#"+date1+"!L@#"+time+"!L@#"+tweet+"!L@#"+String.valueOf(like)+"!L@#"+String.valueOf(retweet)+"!L@#"+String.valueOf(comment)+"!L@#"+",";
        allTweetList.add(0,strg);
    }
    ArrayList<String> getAllTweet()
    {
        return allTweetList;
    }
    int getAllTweetCount()
    {
        return allTweetList.size();
    }
    void addRetweet(String userName,String tweet,String retweetFrom)
    {
        String strg=userName+"!L@#"+"Retweet"+"!L@#"+getPresentDate()+"!L@#"+getPresentTime()+"!L@#"+tweet+"!L@#"+"0"+"!L@#"+"0"+"!L@#"+"0"+"!L@#"+retweetFrom;
        allTweetList.add(0,strg);
    }
    void addComment(String userName,String comment,int index,int commentIndex,String replyFor)
    {
        String[] str=allTweetList.get(index).split("!T@#");
        ArrayList<String> allComment=new ArrayList<>();
        for (String string : str) {
            allComment.add(string);
        }
        String commentList=allComment.get(0);
        for(int i=1;i<(commentIndex==allComment.size()?allComment.size()+1:allComment.size());i++)
        {
            if(i==commentIndex){
                String strg=userName+"!L@#"+"Comments for "+"!L@#"+getPresentDate()+"!L@#"+getPresentTime()+"!L@#"+comment+"!L@#"+"0"+"!L@#"+"0"+"!L@#"+"0"+"!L@#"+replyFor;
                allComment.add(i,strg);
            }
            commentList+=("!T@#"+allComment.get(i));
        }
        allTweetList.set(index,commentList);
    }
    void resetRetweetCount(String tweet,String userName)
    {
        for(int i=0;i<allTweetList.size();i++)
        {
            String[] splitAllTweet=allTweetList.get(i).split("!T@#");
            String setTweet="";
            int flag=0;
            for(int j=0;j<splitAllTweet.length;j++)
            {
                String[] splitTweet=splitAllTweet[j].split("!L@#");
                String tweetDateAndTime=splitTweet[0]+splitTweet[2]+splitTweet[3];
                if(tweetDateAndTime.equals(userName) && splitTweet[4].equals(tweet)){
                    flag=1;
                    splitTweet[6]=String.valueOf(Integer.parseInt(splitTweet[6])-1);
                    splitAllTweet[j]=splitTweet[0]+"!L@#"+splitTweet[1]+"!L@#"+splitTweet[2]+"!L@#"+splitTweet[3]+"!L@#"+splitTweet[4]+"!L@#"+splitTweet[5]+"!L@#"+splitTweet[6]+"!L@#"+splitTweet[7]+"!L@#"+splitTweet[8];
                }
                if(j==0)
                {
                    setTweet+=splitAllTweet[j];
                }
                else{
                    setTweet+="!T@#"+splitAllTweet[j];
                }
            }
            if(flag==1)
            {
                allTweetList.set(i, setTweet);
            }
        }
    }
    String getPresentDate(){
        Date date=new Date();
        SimpleDateFormat setDate=new SimpleDateFormat("dd-MMM-yyyy");
        return setDate.format(date);
    }
    String getPresentTime(){
        Date date=new Date();
        SimpleDateFormat setTime=new SimpleDateFormat("hh:mm:ss a");
        return setTime.format(date);
    }
}
