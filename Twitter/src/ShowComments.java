import java.text.ParseException;

public class ShowComments extends ShowAllTweet
{
    String showAllComments(String userName,String allComments,int index)
    {
        String[] splitAllComment=allComments.split("!T@#");
        String checkRetweet="0";
        for(int i=1;i<splitAllComment.length && i!=0;)
        {
            splitAllComment=allComments.split("!T@#");
            String[] splitComment=splitAllComment[i].split("!L@#");
            showComment(splitAllComment[i]);
            boolean check=true;
            while(check)
            {
                check=false;
                if(i>1){
                    System.out.println("Enter \"1\" for Previous Comment");
                }
                if(i<splitAllComment.length-1){
                    System.out.println("Enter \"2\" for Next Comment");
                }
                System.out.println("Enter \"3\" for Like\nEnter \"4\" for Retweet\nEnter \"5\" for Reply");
                if(userName.equals(splitComment[0])){
                    System.out.println("Enter \"6\" for Delete Comment");
                }
                System.out.println("Enter \"8\" for Back\nEnter \"9\" for Logout\nEnter \"0\" for Exit");

                String checkOption=scan.next();
                if(i>1 && checkOption.equals("1"))
                {
                    i--;
                }
                else if(i<splitAllComment.length-1 && checkOption.equals("2"))
                {
                    i++;
                }
                else if(checkOption.equals("3")){
                    String likeCount=setCounts(allComments, i, 5);
                    allComments=likeCount;
                }
                else if(checkOption.equals("4")){
                    String retweetCount=setCounts(allComments, i, 6);
                    allComments=retweetCount;
                    addRetweet(userName,splitComment[4],splitComment[0]+splitComment[2]+splitComment[3]);
                    checkRetweet=String.valueOf(Integer.parseInt(checkRetweet)+1);
                }
                else if(checkOption.equals("5")){
                    String replyCount=setCounts(allComments, i, 7);
                    allComments=replyCount;
                    NewReply newReply=new NewReply();
                    allComments=newReply.setNewComment(userName,allComments,i,splitComment[0]+"!R@#"+splitComment[4]+splitComment[2]+splitComment[3]);
                    System.out.println("\nYour Reply is Successfully Added");
                }
                else if(checkOption.equals("6") && userName.equals(splitComment[0]))
                {
                    allComments=resetCommentOrReplyCount(allComments,i);
                    
                    if(i==(splitAllComment.length - (1+Integer.parseInt(splitComment[7])))){
                        i--;
                    }
                    System.out.println("\nYour Comment or Reply is Deleted Successfully");
                }
                else if(checkOption.equals("8"))
                {
                    return checkRetweet+"||#||"+"8||#||"+allComments;
                }
                else if(checkOption.equals("9"))
                {
                    return checkRetweet+"||#||"+"9||#||"+allComments;
                }
                else if(checkOption.equals("0"))
                {
                    return checkRetweet+"||#||"+"0||#||"+allComments;
                }
                else{
                    System.out.println("\nEnter a valid number\n");
                    check=true;
                }
            }

        }
        return checkRetweet+"||#||"+"1||#||"+allComments;
    }
    void showComment(String tweet) 
    {
        String[] strg=tweet.split("!L@#");
        String[] reply=strg[8].split("!R@#");
        System.out.println("\n\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n");
            try {
                System.out.println(strg[0]+"\t\t*"+getTimeorDate(strg[2],strg[3]));
            } catch (ParseException e) {}
            System.out.println(strg[1]+reply[0]+"\n");
            System.out.println("\n"+strg[4]);
            System.out.println("\n\nLikes: "+strg[5]+"\t\tRetweet: "+strg[6]+"\t\tReply: "+strg[7]);
            System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n");   
    }
}
