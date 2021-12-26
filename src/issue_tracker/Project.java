package issue_tracker;

import java.util.ArrayList;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Project {


    private Integer project_ID;
    private ArrayList<Issue> ListofIssue;
    private ArrayList<Comment>CommentInsideIssue;

    public Project(Integer project_ID) {
        // ListofIssue = listofIssue;
        this.project_ID = project_ID;
    }

    public Project(Integer project_ID, ArrayList<Issue> listofIssue) {
        this.project_ID = project_ID;
        ListofIssue = listofIssue;
    }

    public Project(Integer project_ID, ArrayList<Issue> listofIssue, ArrayList<Comment> commentInsideIssue) {
        this.project_ID = project_ID;
        ListofIssue = listofIssue;
        CommentInsideIssue = commentInsideIssue;
    }

    public ArrayList<Issue> getListofIssue() {
        return ListofIssue;
    }

    public void setListofIssue(ArrayList<Issue> listofIssue) {
        ListofIssue = listofIssue;
    }

    public Integer getProject_ID() {
        return project_ID;
    }

    public void setProject_ID(Integer project_ID) {
        this.project_ID = project_ID;
    }

    public ArrayList<Comment> getCommentInsideIssue() {
        return CommentInsideIssue;
    }

    public void setCommentInsideIssue(ArrayList<Comment> commentInsideIssue) {
        CommentInsideIssue = commentInsideIssue;
    }


    public void displayissue(){
        System.out.println(getProject_ID());
        for(int i=0; i<ListofIssue.size(); i++) {
            // title=title+"\n"+ListofIssue.get(i).getIssue_title();
            System.out.println(ListofIssue.get(i).toString());
        }

    }

    @Override
    public String toString() {
        String title="";

        return "Project{"
                + "\\n" +
                "project_ID=" + project_ID;
        //"//, ListofIssue=" +"\\n"+ title;
                /*", CommentInsideIssue=" + (Arrays.deepToString(new String[]{CommentInsideIssue.toString()})) +
                '}'*/

    }

}