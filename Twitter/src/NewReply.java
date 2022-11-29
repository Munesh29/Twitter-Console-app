public class NewReply extends ShowComments
{
    String setNewComment(String userName,String allComment,int index,String replyFor)
    {
        System.out.println("\nEnter Your Reply : ");
        String newReply=scan.nextLine();
        String[] separateAllComment=allComment.split("!T@#");
        allComment=separateAllComment[0];
        for(int i=1;i<separateAllComment.length;i++)
        {
            allComment+=("!T@#"+separateAllComment[i]);
            if(i==index)
            {
                allComment+="!T@#"+userName+"!L@#"+"Replying to "+"!L@#"+getPresentDate()+"!L@#"+getPresentTime()+"!L@#"+newReply+"!L@#"+"0"+"!L@#"+"0"+"!L@#"+"0"+"!L@#"+replyFor;
            }
        }
        return allComment;
    }
}
