import java.text.ParseException;
import java.util.Scanner;

public class TweetDetails extends TwitterFeed
{
    Scanner scan=new Scanner(System.in);
    String setCounts(String tweetdetail,int tweetIndex,int count)
    {
        String alterCount="";
        String[] splitAllTweetOrComment=tweetdetail.split("!T@#");
        for (int i=0;i<splitAllTweetOrComment.length;i++) {
            if(i==tweetIndex){
                String[] splitTweetOrComment=splitAllTweetOrComment[i].split("!L@#");
                for(int j=0;j<splitTweetOrComment.length;j++)
                {
                    if(j==count && j==splitTweetOrComment.length-1){
                        alterCount+=String.valueOf(Integer.parseInt(splitTweetOrComment[j])+1);
                    }
                    else if(j==count){
                        alterCount+=(String.valueOf(Integer.parseInt(splitTweetOrComment[j])+1)+"!L@#");
                    }
                    else if(j==splitTweetOrComment.length-1){
                        alterCount+=splitTweetOrComment[j];
                    }
                    else{
                        alterCount+=(splitTweetOrComment[j]+"!L@#");
                    }
                }
                alterCount+="!T@#";
            }
            else{
                alterCount+=(splitAllTweetOrComment[i]+"!T@#");
            }
        }
        return alterCount;
    }
    String resetCommentOrReplyCount(String allComment,int index)
    {
        String[] splitAllCommentOrReply=allComment.split("!T@#");
        String[] splitCommentOrReply=splitAllCommentOrReply[index].split("!L@#");
        if(splitCommentOrReply[1].equals("Comments for "))
        {
            String[] str=splitAllCommentOrReply[0].split("!L@#");
            str[7]=String.valueOf((Integer.parseInt(str[7])-1));
            splitAllCommentOrReply[0]=str[0]+"!L@#"+str[1]+"!L@#"+str[2]+"!L@#"+str[3]+"!L@#"+str[4]+"!L@#"+str[5]+"!L@#"+str[6]+"!L@#"+str[7]+"!L@#"+str[8];
        }
        allComment=splitAllCommentOrReply[0];
        for(int j=1;j<splitAllCommentOrReply.length;)
        {
            String[] str=splitAllCommentOrReply[j].split("!L@#");
            String[] reply=splitCommentOrReply[8].split("!R@#");
            String commentDateAndTime=str[4]+str[2]+str[3];
            if(str[0].equals(reply[0]) && commentDateAndTime.equals(reply[1]))
            {
                String[] strg=splitAllCommentOrReply[j].split("!L@#");
                strg[7]=String.valueOf((Integer.parseInt(strg[7])-1));
                splitAllCommentOrReply[j]=strg[0]+"!L@#"+strg[1]+"!L@#"+strg[2]+"!L@#"+strg[3]+"!L@#"+strg[4]+"!L@#"+strg[5]+"!L@#"+strg[6]+"!L@#"+strg[7]+"!L@#"+strg[8];
            }
            if(j!=index){
                allComment+=("!T@#"+splitAllCommentOrReply[j]);
                j++;
            }
            if(j==index )
            {
                j+=Integer.parseInt(splitCommentOrReply[7])+1;
            }
        }
        return allComment;
    }
    String getTimeorDate(String tweetDate,String tweetTime) throws ParseException 
    {
        String timeOrDate="";
        String[] strDate=tweetDate.split("-");
        String[] splitPresDate=getPresentDate().split("-");
        if(strDate[2].equals(splitPresDate[2]))
        {    
            timeOrDate=strDate[1]+" "+strDate[0]+"\t@ "+tweetTime;
        }
        else if(!(strDate[2].equals(splitPresDate[2])))
        {    
            timeOrDate=strDate[0]+" "+strDate[1]+" "+strDate[2];
        }
        return timeOrDate;
    }
}
