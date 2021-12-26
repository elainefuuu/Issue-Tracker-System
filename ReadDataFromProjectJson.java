package javaa;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadDataFromProjectJson {
    public static void main(String[] args) throws IOException, ParseException {
        //JSONParser jsonparser = new JSONParser();

        FileReader reader = new FileReader(".\\jsonfiles\\project.json");
       /* Object obj = jsonparser.parse(reader);
        JSONObject projectJsonObj = (JSONObject)obj;

        JSONArray array = (JSONArray) projectJsonObj.get("projects");

        for (int i = 0; i<array.size() ; i++){
            JSONObject projects = (JSONObject)array.get(i);

            long id = (long) projects.get("id");
            String name = (String)projects.get("name");

            System.out.println("id: " + id);
            System.out.println("name: " + name);

            JSONObject project = (JSONObject)projects;
            //JSONArray issueList = (JSONArray) project.get("issues");
            JSONArray issues = (JSONArray) projectJsonObj.get("issues");

            for (int j = 0 ; j<issues.size() ; j++){
                JSONObject iss = (JSONObject)issues.get(j);

                int idd = (int)iss.get("id");
                String title = (String)iss.get("title");
                int priority = (int)iss.get("priority");
                String status = (String)iss.get("status");

                System.out.println("Issues: ");
                System.out.println("id: " + idd);
                System.out.println("title: " + title);
                System.out.println("priority: " + priority);
                System.out.println("status: " + status);

            }

        }*/

        try {
            JSONObject rootJSON = (JSONObject) new JSONParser().parse(reader);
            JSONArray dataList = (JSONArray) rootJSON.get("projects");

            for(Object projectObj: dataList.toArray()){
                JSONObject projects = (JSONObject) projectObj;

                long id = (long) projects.get("id");
                String name = (String)projects.get("name");

                System.out.println("Projects: ");
                System.out.println("id: " + id);
                System.out.println("name: " + name);

                JSONObject project = (JSONObject)projectObj;
                JSONArray issueList = (JSONArray) project.get("issues");

                for(Object issueObj: issueList.toArray()){
                    JSONObject iss = (JSONObject) issueObj;

                    long idd = (long)iss.get("id");
                    String title = (String)iss.get("title");
                    long priority = (long)iss.get("priority");
                    String status = (String)iss.get("status");

                    System.out.println("Issues: ");
                    System.out.println("id: " + idd);
                    System.out.println("title: " + title);
                    System.out.println("priority: " + priority);
                    System.out.println("status: " + status);

                    JSONObject issues = (JSONObject)issueObj;
                    JSONArray tagList = (JSONArray) issues.get("tag");

                    for (Object tagObject : tagList.toArray()){
                        System.out.println("tag: " + tagObject);
                    }

                    String description = (String)iss.get("descriptionText");
                    String createdBy = (String)iss.get("createdBy");
                    String assignee = (String)iss.get("assignee");
                    long timestamp = (long)iss.get("timestamp");

                    System.out.println("description text: " + description);
                    System.out.println("created by: " + createdBy);
                    System.out.println("assignee: " + assignee);
                    System.out.println("timestamp: " + timestamp);

                    JSONObject comments = (JSONObject)issueObj;
                    JSONArray commentArray = (JSONArray) comments.get("comments");
                    for (Object commentObject : commentArray.toArray()){
                        JSONObject commentObj = (JSONObject)commentObject;

                        long commentId = (long)commentObj.get("comment_id");
                        String text = (String)commentObj.get("text");

                        System.out.println("comments: ");
                        System.out.println("comment id: " + commentId);
                        System.out.println("text: " + text);


                        JSONObject reactObject = (JSONObject)commentObject;
                        JSONArray reactArray = (JSONArray)reactObject.get("react");
                        for (Object reactObj : reactArray.toArray()){
                            JSONObject reactionObj = (JSONObject)reactObj;

                            String reaction = (String)reactionObj.get("reaction");
                            long count = (long)reactionObj.get("count");

                            System.out.println("react:");
                            System.out.println("reaction: " + reaction);
                            System.out.println("count: " + count);
                        }

                        long timestampp = (long)commentObj.get("timestamp");
                        String user = (String)commentObj.get("user");

                        System.out.println("timestamp: " + timestampp);
                        System.out.println("user: " + user);

                    }
                }
            }
            //Date dt = new Date (timestamp * 1000);
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

            JSONArray userList = (JSONArray) rootJSON.get("users");
            for(Object userObject: userList.toArray()) {
                JSONObject userPro = (JSONObject) userObject;

                long userid = (long) userPro.get("userid");
                String username = (String)userPro.get("username");
                String password = (String)userPro.get("password");

                System.out.println();
                System.out.println("user ID: " + userid);
                System.out.println("username: " + username);
                System.out.println("password: " + password);
            }

        } catch (ParseException e) {
            //do smth
            e.printStackTrace();
        }

    }
}
