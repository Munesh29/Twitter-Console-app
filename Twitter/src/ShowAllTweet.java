import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class ShowAllTweet extends TweetDetails
{
    int showAllTweet(String userName)
    {
        ArrayList<String> allTweet=new ArrayList<>();
        
        for(int i=0;i<getAllTweetCount();)
        {
            allTweet=getAllTweet();
            String[] tweet=allTweet.get(i).split("!T@#");
            showTweet(tweet[0]);
            String[] splitTweet=tweet[0].split("!L@#");
            boolean check=true;
            while(check)
            {
                check=false;
                if(i!=0){System.out.println("Enter \"1\" for Previous Tweet");}
                if(i!=allTweet.size()-1){System.out.println("Enter \"2\" for Next Tweet");}
                System.out.println("Enter \"3\" for Like\nEnter \"4\" for Retweet\nEnter \"5\" for Comment");
                if(tweet.length>1){System.out.println("Enter \"6\" for Show All Comments");}
                if(splitTweet[0].equals(userName)){System.out.println("Enter \"7\" for delete Tweet");}
                System.out.println("Enter \"8\" for Back\nEnter \"9\" logout\nEnter \"0\" for exit");

                String checkOption=scan.next();
                if(i!=0 && checkOption.equals("1"))
                {
                    i--;
                }
                else if(i!=allTweet.size()-1 && checkOption.equals("2"))
                {
                    i++;
                }
                else if(checkOption.equals("3"))
                {
                    String likeCount=setCounts(allTweet.get(i),0,5);
                    allTweet.set(i, likeCount);
                }
                else if(checkOption.equals("4"))
                {
                    String retweetCount=setCounts(allTweet.get(i),0,6);
                    allTweet.set(i, retweetCount);
                    addRetweet(userName,splitTweet[4],splitTweet[0]+splitTweet[2]+splitTweet[3]);
                    i++;
                }
                else if(checkOption.equals("5"))
                {
                    String commentCount=setCounts(allTweet.get(i),0,7);
                    allTweet.set(i, commentCount);
                    System.out.println("\nEnter your comment : ");
                    scan.nextLine();
                    String newComment=scan.nextLine();
                    addComment(userName,newComment,i,1,splitTweet[0]+"!R@#"+",");
                    System.out.println("\nYour Comment is Successfully Added");
                }
                else if(splitTweet.length>1 && checkOption.equals("6"))
                {
                    ShowComments showComments=new ShowComments();
                    System.out.println("\nComment: ");
                    String[] setComments=showComments.showAllComments(userName,allTweet.get(i),i).split("\\|\\|#\\|\\|");
                    if(Integer.parseInt(setComments[0])>0)
                    {
                        i+=(Integer.parseInt(setComments[0]));
                    }
                    allTweet.set(i,setComments[2]);
                    if(setComments[1].equals("9")){
                        try {
                            setTweetList();
                        } catch (IOException e) { }
                        return 9;
                    }
                    else if(setComments[1].equals("0"))
                    {
                        try {
                            setTweetList();
                        } catch (IOException e) { }
                        return 0;
                    }
                }
                else if(splitTweet[0].equals(userName) && checkOption.equals("7"))
                {
                    if(splitTweet[1].equals("Retweet")){
                        resetRetweetCount(splitTweet[4],splitTweet[8]);
                    }
                    allTweet.remove(i);
                    if(i==(getAllTweetCount())){
                        i--;
                    }
                    System.out.println("\nYour Tweet is Deleted Successfully");
                }
                else if(checkOption.equals("8"))
                {
                    try {
                    setTweetList();
                    } catch (IOException e) { }
                    return 1;
                }
                else if(checkOption.equals("9")){
                    try {
                        setTweetList();
                    } catch (IOException e) { }
                    return 9;
                }
                else if(checkOption.equals("0"))
                {
                    try {
                        setTweetList();
                    } catch (IOException e) { }
                    return 0;
                }
                else
                {
                    System.out.println("Enter a valid number\n");
                    check=true;
                }
            }
        }
        return 5;
    }
    void showTweet(String tweet) 
    {
        String[] strg=tweet.split("!L@#");
            System.out.println("\n-------------------------------------------------------------------------\n");
            System.out.print("("+strg[1]+")"+strg[0]);
            try {
                System.out.println("\t\t*"+getTimeorDate(strg[2],strg[3]));
            } catch (ParseException e) {}
            System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.println("\n"+strg[4]);
            System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.println("\nLikes: "+strg[5]+"\t\tRetweet: "+strg[6]+"\t\tComment: "+strg[7]);
            System.out.println("\n-------------------------------------------------------------------------\n");
    }
}
