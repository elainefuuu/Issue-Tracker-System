package issue_tracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Scanner;

public class Issue  {


    private Integer issue_no;
    private String issue_title;
    private String issue_description_text;
    private String timestamp=null;
    private String issue_creator;
    private String issue_assignee;
    ArrayList<Comment> issue_commentlist=new ArrayList<>(); //Arraylist to store all comments related to that issue
    private String issue_tag = "";
    private int issue_priority ;
    private String issue_status;

    private Comment newComment;
    private Integer projectID;



    public Issue() {

    }

    //This constructor will generate REAL TIME Timestamp (For creating new Issues)
    public Issue( Integer projectID,Integer issue_no, String issue_title, String issue_description_text, String issue_creator, String issue_assignee, String issue_tag, int issue_priority, String issue_status) {
        this.projectID=projectID;
        this.issue_no = issue_no;
        this.issue_title = issue_title;
        this.issue_description_text = issue_description_text;
        create_issue_timestamp();           //this is the way i found to set timestamp which is by calling a method, doesnt need to be input by user
        this.issue_creator = issue_creator;
        this.issue_assignee = issue_assignee;
        this.issue_tag = issue_tag;
        this.issue_priority = issue_priority;
        this.issue_status = issue_status;
    }

    //This constructor includes default timestamp (For importing JSON Issues)
    public Issue(Integer issue_no, String issue_title, String issue_description_text, String issue_creator, String issue_assignee, String issue_tag, int issue_priority, String issue_status, String timestamp) {
        this.issue_no = issue_no;
        checkissueno();
        this.issue_title = issue_title;
        this.issue_description_text = issue_description_text;
        this.timestamp=timestamp;
        this.issue_creator = issue_creator;
        this.issue_assignee = issue_assignee;
        this.issue_tag = issue_tag;
        this.issue_priority = issue_priority;
        this.issue_status = issue_status;



    }


    /**
     * This method gets the time the issue is created and update the value to the variable 'timestamp'
     */
    public void create_issue_timestamp(){
        LocalDateTime now= LocalDateTime.now();
        DateTimeFormatter dtf= DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        timestamp= dtf.format(now);
    }

    /**
     *
     * @return
     */
    public void checkissueno(){
        if (issue_no < Main.Iss.size()){
            System.out.println("Issue No. not applicable, please enter again");
        }
    }


    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getIssue_no() { return issue_no;    }

    public void setIssue_no(Integer issue_no) { this.issue_no = issue_no; }

    public String getIssue_title() {   return issue_title; }

    public void setIssue_title(String issue_title) {   this.issue_title = issue_title; }

    public String getIssue_description_text() {    return issue_description_text; }

    public void setIssue_description_text(String issue_description_text) {    this.issue_description_text = issue_description_text; }

    public String getIssue_creator() {    return issue_creator; }

    public void setIssue_creator(String issue_creator) {     this.issue_creator = issue_creator; }

    public String getIssue_assignee() {    return issue_assignee; }

    public void setIssue_assignee(String issue_assignee) {    this.issue_assignee = issue_assignee; }

    public ArrayList<Comment> getIssue_commentlist() {    return issue_commentlist; }

    public void setIssue_commentlist(ArrayList<Comment> issue_commentlist) {    this.issue_commentlist = issue_commentlist; }

    public String getIssue_tag() {     return issue_tag; }

    public void setIssue_tag(String issue_tag) {    this.issue_tag = issue_tag; }

    public int getIssue_priority() {    return issue_priority; }

    public void setIssue_priority(int issue_priority) {     this.issue_priority = issue_priority; }

    public String getIssue_status() {    return issue_status;  }

    public void setIssue_status(String issue_status) {     this.issue_status = issue_status; }

    public Comment getNewComment() {
        return newComment;
    }

    public void setNewComment(Comment newComment) {
        this.newComment = newComment;
        issue_commentlist.add(newComment);
    }

    public void displayComment(){
        System.out.println(getIssue_no());System.out.println(
                "Issue number: " + getIssue_no()+ "\n" +
                        "Issue title: " + getIssue_title()+ "\n" +
                        "Priority: " + getIssue_priority()+ "\n" +
                        "Status: " + getIssue_status()+ "\n" +
                        "Tag: " + getIssue_tag()+ "\n" +
                        "Description test: " + getIssue_description_text()+ "\n" +
                        "Created by: " + getIssue_creator() + "\n" +
                        "Assignee: " + getIssue_assignee()+ "\n");
        for(int i=0; i<getIssue_commentlist().size(); i++) {

            // title=title+"\n"+ListofIssue.get(i).getIssue_title();

            System.out.println(issue_commentlist.get(i).toString());
        }

    }
    @Override
    public String toString() {
        return "\n\nIssue{" +
                "issue_no=" + issue_no +
                "\nissue_title='" + issue_title  +
                "\nissue_description_text='" + issue_description_text +
                "\ntimestamp='" + timestamp +
                "\nissue_creator='" + issue_creator  +
                "\nissue_assignee='" + issue_assignee  +
                "\nissue_commentlist=" + (Arrays.deepToString(new String[]{issue_commentlist.toString()})) +
                "\nissue_tag='" + issue_tag +
                "\nissue_priority=" + issue_priority +
                "\nissue_status='" + issue_status  +
                '}';
    }
}

