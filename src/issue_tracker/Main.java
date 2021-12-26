package issue_tracker;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        int projectID;

        String title;
        String status;
        String description;
        String createdBy;
        String assignee;
        String time;
        String tag;

        int IssueID;
        int IssuePriority;

        int intcommentID;
        int intcount;
        String text;
        String reaction;
        String user;
        String timestampp2;


        try {
            FileReader filereader = new FileReader(".\\json file\\data.json");

            JSONObject rootJSON = (JSONObject) new JSONParser().parse(filereader);
            JSONArray dataList = (JSONArray) rootJSON.get("projects");

            /**
             * For loop to loop through all Projects
             */

            for (Object projectObj : dataList.toArray()) {

                JSONObject project = (JSONObject) projectObj;
                long pjid = (long) project.get("id");
                JSONArray issueList = (JSONArray) project.get("issues");
                projectID = (int) pjid;
                Project p = new Project(projectID);

                ArrayList <Issue> issuesinproject = new ArrayList<>();

                // int ct=0;

                for (Object issueObj : issueList.toArray()) {
                    JSONObject iss = (JSONObject) issueObj;

                    long idd = (long) iss.get("id");
                    title = (String) iss.get("title");
                    long priority = (long) iss.get("priority");
                    status = (String) iss.get("status");

                    //Convert datatype to fit in Iss ArrayList
                    IssueID = (int) idd;
                    IssuePriority = (int) priority;


                    System.out.println("Issues: ");
                    System.out.println("id: " + idd);
                    System.out.println("title: " + title);
                    System.out.println("priority: " + priority);
                    System.out.println("status: " + status);

                    JSONObject issues = (JSONObject) issueObj;
                    JSONArray tagList = (JSONArray) issues.get("tag");
                    for (Object tagObject : tagList.toArray()) {
                        System.out.println("tag: " + tagObject);

                        //Convert datatype to fit in Iss ArrayList
                        String IssueTag= (String) tagObject;
                    }

                    JSONArray isuTag = (JSONArray) issues.get("tag");
                    tag = (String) isuTag.get(0);

                    description = (String) iss.get("descriptionText");
                    createdBy = (String) iss.get("createdBy");
                    assignee = (String) iss.get("assignee");
                    long timestamp = (long) iss.get("timestamp");
                    // convert seconds to milliseconds
                    Date dt = new Date(timestamp * 1000);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                    //timezone reference
                    sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

                    //Convert datatype to fit in Iss ArrayList
                    time = sdf.format(dt);

                    String alignment1 = "%-9s %-5d %-7s %-15s %n";
                    String alignment2 = "%-4s %-10s %-9s %-5d %-11s %-16s %n";
                    String alignment3 = "%-30s %n";
                    String alignment4 = "%-12s %-14s %-11s %-10s %n";
                    System.out.println("");
                    System.out.printf(alignment1, "Issue ID:", idd, "Status:", status);
                    System.out.printf(alignment2, "Tag:", tag, "Priority:", priority, "Created On:", timestamp);
                    System.out.printf(alignment3, title);
                    System.out.printf(alignment4, "Assigned to:", assignee, "Created By:", createdBy);
                    System.out.println("");
                    System.out.println("Issue Description");
                    System.out.println("-----------------");
                    System.out.printf(description);
                    System.out.println("");
                    System.out.println("");

                    System.out.println("Comments");
                    System.out.println("---------");


                    Issue IssuefromJSON = new Issue(IssueID, title, description, createdBy, assignee, tag, IssuePriority,status,time); //Create New Issue with data from JSON file




                    //Read Comments stored as JSON under Issues
                    JSONObject comments = (JSONObject) issueObj;
                    JSONArray commentArray = (JSONArray) comments.get("comments");
                    for (Object commentObject : commentArray.toArray()) {
                        JSONObject commentObj = (JSONObject) commentObject;

                        long commentID = (long) commentObj.get("comment_id");
                        intcommentID = (int) commentID;
                        text = (String) commentObj.get("text");

                        System.out.println("comments: ");
                        System.out.println("comment id: " + commentID);
                        System.out.println("text: " + text);
                        System.out.println("Testing");
                        HashMap<String, Integer> hashMap= new HashMap();



                        JSONObject reactObject = (JSONObject) commentObject;
                        JSONArray reactArray = (JSONArray) reactObject.get("react");
                        for (Object reactObj : reactArray.toArray()) {
                            JSONObject reactionObj = (JSONObject) reactObj;

                            reaction = (String) reactionObj.get("reaction");
                            long count = (long) reactionObj.get("count");
                            intcount = (int) count;

                            System.out.println("react:");
                            System.out.println("reaction: " + reaction);
                            System.out.println("count: " + count);

                            hashMap.put(reaction,intcount);

                        }

                        long timestampp = (long) commentObj.get("timestamp");
                        user = (String) commentObj.get("user");
                        // convert seconds to milliseconds
                        Date dt2 = new Date(timestamp * 1000);
                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                        //timezone reference
                        sdf2.setTimeZone(TimeZone.getTimeZone("GMT"));
                        timestampp2 = sdf.format(dt2);

                        System.out.println("timestamp: " + timestampp);
                        System.out.println("TimeStamp in String" + timestampp2);
                        System.out.println("user: " + user);

                        //   Object[] GeneratedHashMap=Arrays.asList(hashMap).toArray();
                        Comment c = new Comment(projectID,IssueID,text,intcommentID,user,timestampp2, hashMap);
                        IssuefromJSON.issue_commentlist.add(c);



                    }
                    Iss.add(IssuefromJSON); // Add New Issue to ArrayList "Iss"; "Iss" is a static variable that will be overall updated //

                    issuesinproject.add(IssuefromJSON); //To call while creating Project object


                    // ct++;

                }
                p.setListofIssue(issuesinproject);
                Projects.add(p);
                System.out.println();


            }for (int i = 0; i < Projects.size(); i++) {
                System.out.println("\n~~~~~~~~~~");
                Projects.get(i).displayissue();
            }

            for (int i = 0; i < Iss.size(); i++) {
                System.out.println("\n========================");
                System.out.println(Iss.size());
                Iss.get(i).displayComment();
            }


        } catch (ParseException e) {
            //do smth
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    Integer ProjectID;
    public static ArrayList<Project> Projects=new ArrayList<>();
    //ArrayList<Project> cloneProjects=();
    Integer issueno;
    String issuetitle;
    String issuedescriptiontext;
    //String timestamp;
    String issuecreator;
    String issueassignee;
    // public static ArrayList<Comment> issuecommentlist; //Arraylist to store all comments related to that issue
    String issuetag;
    int[] issue_priority_range = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    int issuepriority;
    String issuestatus;

    private String commenttext;
    private int commentid;
    private int projectID;
    private int issueID;
    private String commentuser;
    private String reaction;
    public List<String> reactionrange = new ArrayList<>();

    //HashMap<String, Integer> hashMap= new HashMap();


    public static ArrayList<Issue> Iss = new ArrayList<>();

    Scanner s = new Scanner(System.in);


    public void trackNextStep() {
        System.out.println("Enter 'c' to comment on an Issue, \nEnter 'n' to create new Issue, \nEnter 'e' to end process : ");
        String nextstep;
        nextstep = s.next();
        switch (nextstep) {
            case "c" -> // System.out.println("Enter ID of the Project to add Comment: "); int x=s.nextInt();
                    CreateNewComment();
            case "n" -> CreateNewIssue();
            default -> System.out.println("End of process");
        }
    }

    /**
     * Method to create New Issue
     */
    public void CreateNewIssue() {
       /*System.out.println("Enter the following values: ");
        System.out.println("Project No. : ");
        ProjectID =s.nextInt();
        System.out.print("Issue No. : ");
        issueno = s.nextInt();
        System.out.print("Issue Title : ");
        issuetitle = s.next();
        System.out.print("Issue Description : ");
        issuedescriptiontext = s.next();
        System.out.print("Issue Creator : ");
        issuecreator = s.next();
        System.out.print("Issue Assignee : ");
        createIssueassignee();
        System.out.print("Issue Tag [Frontend, Backend] : ");
        issuetag = s.next();
        System.out.print("Issue priority [1-9] : ");
        checkpriority();
        System.out.println("Issue Status [Resolved (r), Closed(c), Open(p), In Progress(n)] : ");*/
        checkstatus();


        // Call in all variable for Creating issue
        Issue e = new Issue(ProjectID,issueno, issuetitle, issuedescriptiontext, issuecreator, issueassignee, issuetag, issuepriority, issuestatus);
        Projects.get(ProjectID-1).getListofIssue().add(e);
        //  Iss.add(e);
        System.out.println(e.getIssue_no());
        System.out.println(e.getIssue_priority());
        System.out.println(e.getTimestamp());

        //If use array to store issue data
        //
        trackNextStep();
    }

    /**
     * Method to Create New Comment
     */
    public void CreateNewComment() {

        Stack<String> list1=new Stack<>();
        Stack<String> list2=new Stack<>();
        System.out.println("Enter the ID of Project: ");
        int pID=s.nextInt();

        System.out.println("Enter the ID of the issue to add comments: ");
        int issuetoaddcomment = s.nextInt();

        // ArrayList<Comment> commentlist = Iss.get(issuetoaddcomment-1).getIssue_commentlist();

        System.out.print("Enter Comment Text : ");
        commenttext = s.next();
        System.out.println("Enter u if want to undo text, Enter k to continue");
        if (s.next().equals("u")){
            list1.push(commenttext);
            System.out.println("Enter r to redo text. or continue your text");
            if(s.next().equals("r")){
                commenttext=list1.pop();
                s.next();
            }else {
                commenttext=s.nextLine();
            }
        }

        System.out.print("Enter Comment ID : ");
        commentid = s.nextInt();
        System.out.print("Enter Comment User : ");
        commentuser = s.next();
        //System.out.print("Enter Comment Reaction : ");
        /*reaction = s.next();
        checkreaction();*/
        //Comment c = new Comment(projectID, issueID, commenttext, commentid, commentuser, reaction);
        Comment c = new Comment(pID, issuetoaddcomment, commenttext, commentid, commentuser);
        Projects.get(pID-1).getListofIssue().get(issuetoaddcomment-1).getIssue_commentlist().add(c);

        // Iss.get(issuetoaddcomment - 1).getIssue_commentlist().add(c);
        //issuecommentlist.add(c);


        System.out.println(Projects.get(pID-1).getListofIssue().get(issuetoaddcomment-1).getIssue_commentlist().toString());

    }
 /*   public void RedoUndo(){
        System.out.println("Enter u if want to undo text, Enter r if want to redo text, Enter k to continue");
        UndoRedo2<String> ur=new UndoRedo2<>();
        String step=s.next();
        while (step.equals("u")){
            ur.undo();
            break;
        }
        while (step.equals("r")){
            ur.redo();
            break;
        }
        while (!step.equals("u") && !step.equals("r")){
            break;
        }
    }*/
    /*public String RedoUndo(){

    }*/

    public void addReaction(){
        String [] word = new String[3];
        HashMap <String, Integer> hashMap;
        String reactioninrange="";
        System.out.println("Enter project no. that you wish to react: ");
        int pID = s.nextInt();
        System.out.println("Enter issue no. that you wish to react: ");
        int issuetoaddreaction = s.nextInt();
        System.out.println("Enter comment no. that you wish to react");
        int commentId = s.nextInt();
        System.out.print("1- (for angry), 2- (for happy), 3- (for thumbs up): ");
        int reaction = s.nextInt();
        hashMap= Projects.get(pID-1).getListofIssue().get(issuetoaddreaction-1).getIssue_commentlist().get(commentId-1).getHashmap1();

        switch (reaction) {
            case 1: reactioninrange="angry";break;
            case 2: reactioninrange= "happy";break;
            case 3: reactioninrange="thumbsUp";break;
        }

        // Comment c = new Comment(projectId,issueId,commentId,hashMap);
        // c.hashmap1.get(reaction);
        Integer count= hashMap.get(reactioninrange);


        if (count == null){
            if(reactioninrange.equals("thumbsUp") || reactioninrange.equals("angry") || reactioninrange.equals("happy")){
                hashMap.put(reactioninrange, 1);
            }}
        else{
            hashMap.put(reactioninrange, count+1);
        }


        System.out.println("--"+ hashMap);

        //Comment c=new Comment(pID-1,issuetoaddreaction-1,commentId-1,hashMap);
        //Projects.get(pID-1).getListofIssue().get(issuetoaddreaction-1).getIssue_commentlist().set(commentId-1,c);

        System.out.println("Reaction " + reactioninrange +" added");
        Projects.get(pID-1).getListofIssue().get(issuetoaddreaction-1).getIssue_commentlist().get(commentId-1).setHashmap1(hashMap);

        System.out.println(Projects.get(pID-1).getListofIssue().get(issuetoaddreaction-1).getIssue_commentlist().get(commentId-1).getHashmap1());

        System.out.println("==========================");
        System.out.println(Projects.get(pID-1).getListofIssue().get(issuetoaddreaction-1).getIssue_commentlist().get(commentId-1).toString());

        System.out.println(hashMap);
    }

    public void createIssueassignee() {
        issueassignee = s.next();
        if (issueassignee.equalsIgnoreCase("no")) {
            issueassignee = null;
        }
    }

    /**
     * If able to set the if condition to be related to the issue_priority_range declared above will be better
     */
    public void checkpriority() {
        issuepriority = s.nextInt();
        if (issuepriority < 1 || issuepriority > 9) {
            System.out.println("This value is not applicable, please enter a number from 1-9");
            checkpriority();
        }
    }

    public void checkstatus() {
        //Should this method be called by programme not entered by user....
        issuestatus = s.next();
        issuestatus = issuestatus.toLowerCase();
        switch (issuestatus) {
            case ("resolved"), "r" -> System.out.println("Case Resolved");
            case "closed", "c" -> System.out.println("Case Closed");
            case "open", "p" -> System.out.println("Case Opened");
            case "in progress", "n" -> System.out.println("Case In Progress");
            default -> {
                System.out.println("This status is not applicable please enter again");
                checkstatus();
            }
        }

    }


    public void checkreaction() {
        if (reactionrange.contains(reaction)) {
            System.out.println(reaction + " added");
        } else {
            System.out.println("The reaction is not applicable, please enter again " + (Arrays.deepToString(reactionrange.toArray())));
            checkreaction();
        }
    }




}