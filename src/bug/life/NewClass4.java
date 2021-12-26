
package bug.life;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

public class NewClass4 {
    
        public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String JSON_file = "data.json";
        JSONParser parser = new JSONParser();
        Scanner scan = new Scanner(System.in);

        try {
            FileReader reader = new FileReader(JSON_file);

            //Java object
            Object obj = parser.parse(reader);

            //JSON object
            JSONObject jsonObject = (JSONObject) obj;

            //Project Board
            projectBoard();

            JSONArray array = (JSONArray) jsonObject.get("projects");

            for (int i = 0; i < array.size(); i++) {
                JSONObject project = (JSONObject) array.get(i);
                projectDashboard(project);
            }
            System.out.format("+------+----------------------+----------+%n");

            //sorting
            System.out.print("Sorting(yes/no): ");
            String sort = scan.next();
            int sort1 = 0;
            if (sort.equalsIgnoreCase("yes")) {
                System.out.println("");
                System.out.println("1. Sort Project Name alphabetically");
                System.out.println("2. Sort Project ID numerically in descending ");
                System.out.print("Enter your choice: ");
                sort1 = scan.nextInt();

                //Sort Project Name alphabetically
                if (sort1 == 1) {
                    System.out.println("1. Sort in ascending alphabetical order\n" + "2. Sort in decending alphabetical order ");
                    System.out.print("Enter your choice: ");
                    int sort2 = scan.nextInt();

                    //ascending alphabetical order
                    if (sort2 == 1) {
                        ArrayList<JSONObject> list = new ArrayList<>();
                        ArrayList<JSONObject> sortedJsonArray = new ArrayList<>();
                        System.out.println("");
                        projectBoard();
                        for (int i = 0; i < array.size(); i++) {
                            list.add((JSONObject) array.get(i));
                        }
                        Collections.sort(list, new Comparator<JSONObject>() {
                            //Sort by Project Name
                            String KEY_NAME = "name";

                            @Override
                            public int compare(JSONObject a, JSONObject b) {
                                String valA = new String();
                                String valB = new String();

                                try {
                                    valA = (String) a.get(KEY_NAME);
                                    valB = (String) b.get(KEY_NAME);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                return valA.compareTo(valB);            //ascending order

                            }
                        });

                        for (int i = 0; i < array.size(); i++) {
                            sortedJsonArray.add(list.get(i));
                        }
                        for (JSONObject object : sortedJsonArray) {
                            projectDashboard(object);
                        }
                        System.out.format("+------+----------------------+----------+%n");
                    }

                    //descending alphabetical order
                    if (sort2 == 2) {
                        ArrayList<JSONObject> list = new ArrayList<>();
                        ArrayList<JSONObject> sortedJsonArray = new ArrayList<>();
                        System.out.println("");
                        projectBoard();
                        for (int i = 0; i < array.size(); i++) {
                            list.add((JSONObject) array.get(i));
                        }
                        Collections.sort(list, new Comparator<JSONObject>() {
                            //Sort by Project Name
                            String KEY_NAME = "name";

                            @Override
                            public int compare(JSONObject a, JSONObject b) {
                                String valA = new String();
                                String valB = new String();

                                try {
                                    valA = (String) a.get(KEY_NAME);
                                    valB = (String) b.get(KEY_NAME);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                return -valA.compareTo(valB);       //descending order

                            }
                        });

                        for (int i = 0; i < array.size(); i++) {
                            sortedJsonArray.add(list.get(i));
                        }
                        for (JSONObject object : sortedJsonArray) {
                            projectDashboard(object);
                        }
                        System.out.format("+------+----------------------+----------+%n");
                    }
                }

                //Sort Project ID numerically in descending
                if (sort1 == 2) {
                    ArrayList<JSONObject> list = new ArrayList<>();
                    ArrayList<JSONObject> sortedJsonArray = new ArrayList<>();
                    System.out.println("");
                    projectBoard();
                    for (int i = 0; i < array.size(); i++) {
                        list.add((JSONObject) array.get(i));
                    }
                    Collections.sort(list, new Comparator<JSONObject>() {
                        //sort by ID
                        String KEY_NAME = "id";

                        @Override
                        public int compare(JSONObject a, JSONObject b) {
                            String valA = new String();
                            String valB = new String();

                            try {
                                valA = String.valueOf(a.get(KEY_NAME));
                                valB = String.valueOf(b.get(KEY_NAME));

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            return -valA.compareTo(valB);       //descending order
                        }
                    });

                    for (int i = 0; i < array.size(); i++) {
                        sortedJsonArray.add(list.get(i));
                    }
                    for (JSONObject object : sortedJsonArray) {
                        projectDashboard(object);
                    }
                    System.out.format("+------+----------------------+----------+%n");
                }
            }

            System.out.print("Enter selected issue ID to check project: ");
            int choice = scan.nextInt();

            /*LOOP TO MAKE SURE THE USER DOESN'T ENTER THE WRONG INPUT*/
            while (choice > array.size() || choice == 0) {
                System.out.println("Wrong input.");
                System.out.print("Enter selected issue ID to check project: ");
                choice = scan.nextInt();
            }

            //Issue Board
            issueBoard();

            //Issue Dashboard
            issueDashboard(choice, array);
            
            //Issue LifeCycle
            issueLifeCycle();
            
        } catch (Exception e) {
            e.printStackTrace();    //shows neither the line number where the error occurred nor the functions that were executed
        }
    }

    public static void projectDashboard(JSONObject object) {
        String allignment = "| %4d | %-20s | %8s |%n";
        long project_ID = (long) object.get("id");
        String project_name = (String) object.get("name");
        JSONArray array1 = (JSONArray) object.get("issues");

        for (int j = 0; j < array1.size(); j++) {
            JSONObject issues = (JSONObject) array1.get(j);
        }

        System.out.format(allignment, project_ID, project_name, array1.size());
    }

    public static void issueDashboard(JSONObject object) {
        String allignment = "| %4d | %-34s | %-13s | %-17s |  %7s | %-17s | %-8s | %-9s |%n";
        long id = (long) object.get("id");
        String title = (String) object.get("title");
        String status = (String) object.get("status");
        long priority = (long) object.get("priority");
        String assignee = (String) object.get("assignee");
        String createdBy = (String) object.get("createdBy");
        long timestamp = (long) object.get("timestamp");
        // convert seconds to milliseconds
        Date dt = new Date(timestamp * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        //timezone reference 
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT"));
        String time = sdf.format(dt);

        JSONArray Tag = (JSONArray) object.get("tag");
        for (int k = 0; k < Tag.size(); k++) {

            String tag = (String) Tag.get(k);

            System.out.format(allignment, id, title, status, tag, priority, time, assignee, createdBy);
        }
    }

    public static void projectBoard() {
        System.out.println("Project Board");
        System.out.println("--------------");
        System.out.format("+------+----------------------+----------+%n");
        System.out.format("|  ID  |     Project Name     |  Issues  |%n");
        System.out.format("+------|----------------------+----------+%n");
    }

    public static void issueBoard() {
        System.out.println("");
        System.out.println("Issue Board");
        System.out.println("--------------");
        System.out.format("+------+------------------------------------+---------------+-------------------+"
                + "----------+-------------------+----------+-----------+%n");
        System.out.format("|  ID  |                Title               |    Status     |        Tag        |"
                + " Priority |        Time       | Assignee | CreatedBy |%n");
        System.out.format("+------+------------------------------------+---------------+-------------------+"
                + "----------+-------------------+----------+-----------+%n");
    }

    public static void issueDashboard(int num, JSONArray array) {
        JSONObject project = (JSONObject) array.get(num - 1);
        JSONArray issue = (JSONArray) project.get("issues");
        int sort1 = 0;
        for (int j = 0; j < issue.size(); j++) {
            JSONObject issues = (JSONObject) issue.get(j);
            issueDashboard(issues); 
        }
        System.out.format("+------+------------------------------------+---------------+-------------------+"
                + "----------+-------------------+----------+-----------+%n");

        System.out.print("Enter selected issue ID to check issue \n" + "or 's' to search \n" + "or 'q' to sort \n"
                + "or 'f' to filter \n");

        String search = sc.nextLine();

        //sorting
        if (search.equalsIgnoreCase("q")) {
            System.out.println("");
            System.out.println("SORTING");
            System.out.println("1. Sort by priority\n" + "2. Sort by timestamp ");
            System.out.println("");
            System.out.print("Enter your choice: ");
            sort1 = sc.nextInt();

            issueBoard();

            //sort by priority
            if (sort1 == 1) {
                ArrayList<JSONObject> list = new ArrayList<>();
                ArrayList<JSONObject> sortedJsonArray = new ArrayList<>();

                for (int i = 0; i < issue.size(); i++) {
                    list.add((JSONObject) issue.get(i));
                }

                Collections.sort(list, new Comparator<JSONObject>() {
                    //sort by priority
                    String KEY_NAME = "priority";

                    @Override
                    public int compare(JSONObject a, JSONObject b) {
                        String valA = new String();
                        String valB = new String();

                        try {
                            valA = String.valueOf(a.get(KEY_NAME));
                            valB = String.valueOf(b.get(KEY_NAME));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        return -valA.compareTo(valB);   // higher priority to lower priority
                    }
                });

                for (int i = 0; i < issue.size(); i++) {
                    sortedJsonArray.add(list.get(i));
                }

                for (JSONObject object : sortedJsonArray) {
                    issueDashboard(object);
                }
                System.out.format("+------+------------------------------------+---------------+-------------------+"
                        + "----------+-------------------+----------+-----------+%n");
            }

            //Sort by timestamp(newest to oldest)
            if (sort1 == 2) {
                ArrayList<JSONObject> list = new ArrayList<>();
                ArrayList<JSONObject> sortedJsonArray = new ArrayList<>();

                for (int i = 0; i < issue.size(); i++) {
                    list.add((JSONObject) issue.get(i));
                }

                Collections.sort(list, new Comparator<JSONObject>() {
                    //sort by timestamp
                    String KEY_NAME = "timestamp";

                    @Override
                    public int compare(JSONObject a, JSONObject b) {
                        String valA = new String();
                        String valB = new String();

                        try {
                            valA = String.valueOf(a.get(KEY_NAME));
                            valB = String.valueOf(b.get(KEY_NAME));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        return -valA.compareTo(valB); //descending order
                    }
                });

                for (int i = 0; i < issue.size(); i++) {
                    sortedJsonArray.add(list.get(i));
                }

                for (JSONObject object : sortedJsonArray) {
                    issueDashboard(object);
                }
                System.out.format("+------+------------------------------------+---------------+-------------------+"
                        + "----------+-------------------+----------+-----------+%n");
            }
        }

        //filter
        if (search.equalsIgnoreCase("f")) {
            System.out.println("");
            System.out.println("FILTER");
            System.out.println("1. Filter by status\n" + "2. Filter by tag ");
            System.out.println("");
            System.out.print("Enter your choice: ");
            int filter1 = sc.nextInt();

            //filter by status
            if (filter1 == 1) {
                System.out.println("1.Filter by status: “Resolved”");
                System.out.println("2.Filter by status: “Closed”");
                System.out.println("3.Filter by status: “Open”");
                System.out.println("4.Filter by status: “In Progress”");
                System.out.println("");
                System.out.print("Enter your choice: ");
                int input = sc.nextInt();

                issueBoard();

                //status: Resolved
                if (input == 1) {
                    for (int j = 0; j < issue.size(); j++) {
                        JSONObject issues = (JSONObject) issue.get(j);
                        String status = (String) issues.get("status");
                        if (status.equals("Resolved")) {
                            issueDashboard(issues);
                        }
                    }
                    System.out.format("+------+------------------------------------+---------------+-------------------+"
                            + "----------+-------------------+----------+-----------+%n");
                }

                //status: Closed
                if (input == 2) {
                    for (int j = 0; j < issue.size(); j++) {
                        JSONObject issues = (JSONObject) issue.get(j);
                        String status = (String) issues.get("status");
                        if (status.equals("Closed")) {
                            issueDashboard(issues);
                        }
                    }
                    System.out.format("+------+------------------------------------+---------------+-------------------+"
                            + "----------+-------------------+----------+-----------+%n");
                }

                //status: Open
                if (input == 3) {
                    for (int j = 0; j < issue.size(); j++) {
                        JSONObject issues = (JSONObject) issue.get(j);
                        String status = (String) issues.get("status");
                        if (status.equals("Open")) {
                            issueDashboard(issues);
                        }
                    }
                    System.out.format("+------+------------------------------------+---------------+-------------------+"
                            + "----------+-------------------+----------+-----------+%n");
                }

                //status: In Progress
                if (input == 4) {
                    for (int j = 0; j < issue.size(); j++) {
                        JSONObject issues = (JSONObject) issue.get(j);
                        String status = (String) issues.get("status");
                        if (status.equals("In Progress")) {
                            issueDashboard(issues);
                        }
                    }
                    System.out.format("+------+------------------------------------+---------------+-------------------+"
                            + "----------+-------------------+----------+-----------+%n");
                }
            }

            //Filter by tag
            if (filter1 == 2) {
                System.out.print("Filter by tag: ");
                String input2 = sc.next();

                issueBoard();

                for (int j = 0; j < issue.size(); j++) {
                    JSONObject issues = (JSONObject) issue.get(j);
                    JSONArray Tag = (JSONArray) issues.get("tag");
                    for (int k = 0; k < Tag.size(); k++) {

                        String tag = (String) Tag.get(k);

                        if (tag.toLowerCase().contains(input2.toLowerCase())) {
                            issueDashboard(issues);
                        }
                    }
                }
                System.out.format("+------+------------------------------------+---------------+-------------------+"
                        + "----------+-------------------+----------+-----------+%n");
            }
        }

        //search the issue by title
        if (search.equalsIgnoreCase("s")) {
            System.out.println("");
            System.out.println("SEARCHING");
            System.out.print("Search the issue by title: ");            //search look into title, descriptive text & comments //search by keyword?
            String issueTitle = sc.nextLine();

            issueBoard();

            for (int j = 0; j < issue.size(); j++) {
                JSONObject issues = (JSONObject) issue.get(j);
                String title = (String) issues.get("title");
                String desText = (String) issues.get("descriptionText");
                
                if (title.toLowerCase().contains(issueTitle.toLowerCase())) {
                    issueDashboard(issues);
                }
                
                if (desText.toLowerCase().contains(issueTitle.toLowerCase())){
                    issueDashboard(issues);
                }

                JSONArray comments = (JSONArray) issues.get("comments");
                for (int k = 0; k < comments.size(); k++) {
                    JSONObject comment = (JSONObject) comments.get(k);
                    String text = (String) comment.get("text");

                    if (text.toLowerCase().contains(issueTitle.toLowerCase())) {
                        issueDashboard(issues);
                    }
                }
            }
            System.out.format("+------+------------------------------------+---------------+-------------------+"
                    + "----------+-------------------+----------+-----------+%n");
        }

        System.out.print("Enter issue id: ");
        int issueID = sc.nextInt();
        
        /*LOOP TO MAKE SURE THE USER DOESN'T ENTER THE WRONG INPUT*/
        while (issueID > issue.size() || issueID == 0) {
            System.out.println("Wrong input.");
            System.out.print("Enter selected issue ID to check project: ");
            issueID = sc.nextInt();
        }
        
        //Issue Page
        issuePage(issueID, issue);
    }
    
    public static void issuePage(int num1, JSONArray array){
        JSONObject issue = (JSONObject) array.get(num1 - 1);
        
        for (int j = 0; j < issue.size(); j++) {
            JSONObject issues = (JSONObject) issue.get(j);
            
            JSONArray tag = (JSONArray) issue.get("tag");
            for (int k = 0; k < tag.size(); k++) {
                JSONObject Tag = (JSONObject) tag.get(k);
            }
            JSONArray comment = (JSONArray) issue.get("comments");
            for (int n = 0; n < comment.size(); n++) {
                JSONObject Comment = (JSONObject) comment.get(n);
                
                JSONArray react = (JSONArray) comment.get(j);

                for (int m = 0; m < react.size(); m++) {
                    JSONObject React = (JSONObject) react.get(j);

                }
            }
            issuePage(issues); 
        }
    }
    
    public static void issuePage(JSONObject object){
        String alignment1 = "%-9s %-5d %-7s %-15s %n";
        String alignment2 = "%-4s %-10s %-9s %-5d %-11s %-16s %n";
        String alignment3 = "%-30s %n";
        String alignment4 = "%-12s %-14s %-11s %-10s %n";
        long id = (long) object.get("id");
        String title = (String) object.get("title");
        String status = (String) object.get("status");
        int priority = (int) object.get("priority");
        String assignee = (String) object.get("assignee");
        String createdBy = (String) object.get("createdBy");
        String descriptionText = (String) object.get("descriptionText");
        long timestamp1 = (long) object.get("timestamp");
        // convert seconds to milliseconds
        Date dt = new Date(timestamp1 * 1000);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        //timezone reference 
        sdf1.setTimeZone(java.util.TimeZone.getTimeZone("GMT"));
        String time1 = sdf1.format(dt);

        JSONArray Tag = (JSONArray) object.get("tag");
        for (int i = 0; i < Tag.size(); i++) {

            String tag = (String) Tag.get(i);

            System.out.format(alignment1, "Issue ID:", id, "Status:", status);
            System.out.format(alignment2, "Tag:", tag, "Priority:", priority, "Created On:", time1);
            System.out.format(alignment3, title);
            System.out.format(alignment4, "Assigned to:", assignee, "Created By:", createdBy);            
            System.out.println("");
            System.out.println("Issue Description");
            System.out.println("-----------------");
            System.out.format(descriptionText);
            System.out.println("");
        }
        
        String alignment5 = "%1s%-6d %-11s %-19s %-3s %-10s %n";
        String alignment6 = "%-2s %-60s %n";
        
        JSONArray comment = (JSONArray) object.get("comments");
        
        for (int j = 0; j < comment.size(); j++) {
            long comment_id = (long) object.get("comment_id");
            String text = (String) object.get("text");
            String user = (String) object.get("user");
            long timestamp2 = (long) object.get("timestamp");
            // convert seconds to milliseconds
            Date dt2 = new Date(timestamp2 * 1000);
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            //timezone reference 
            sdf2.setTimeZone(java.util.TimeZone.getTimeZone("GMT"));
            String time2 = sdf2.format(dt);
        
            System.out.println("Comments");
            System.out.println("---------");
            System.out.format(alignment5, "#", comment_id, "Created on:", time2, "By:", user);
            System.out.println(text);
                
            JSONArray React = (JSONArray) object.get("react");
            for (int k = 0; k < React.size(); k++) {

                String reaction = (String) object.get("reaction");
                long count = (long) object.get("count");
                
                if(count > 0){
                    System.out.format(alignment6, "$$", count, "people react with", reaction);
                }else{
                    System.out.print("");
                }
            }
            System.out.println("");
        }
        System.out.println("Enter /n'r' to react /nor'c' to comment /nor 'help' for more commands");
    }
    
    public static void issueLifeCycle(){
        //try{
            
        }
    }
//}

