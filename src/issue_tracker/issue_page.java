package issue_tracker;

import java.awt.Color;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class issue_page extends javax.swing.JFrame {

    String JSON_file = "data.json";
    JSONParser parser = new JSONParser();

    /**
     * Creates new form IssuePage
     */
    public issue_page() {
        initComponents();
        //center the form
        this.setLocationRelativeTo(null);
        jTextArea_descriptionText.setEditable(false);
        jTextArea_comments.setEditable(false);

    }

    public void setLabelText(String txt1) {
        jLabel_userName.setText(txt1);
    }

    class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {

        public MyHighlightPainter(Color color) {
            super(color);
        }
    }

    Highlighter.HighlightPainter highlighter = new MyHighlightPainter(Color.YELLOW);

    //method to highlight the word
    public void highlight(JTextComponent textComp, String pattern) {

        //remove every highlight first before searching
        removeHighlight(textComp);

        try {
            Highlighter hilite = textComp.getHighlighter();
            Document doc = textComp.getDocument();
            String text = doc.getText(0, doc.getLength());
            int position = 0;
            while ((position = text.toLowerCase().indexOf(pattern.toLowerCase(), position)) >= 0) {
                hilite.addHighlight(position, position + pattern.length(), highlighter);
                position += pattern.length();

            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    //method to remove highlight of the word
    public void removeHighlight(JTextComponent textComp) {
        Highlighter hilite = textComp.getHighlighter();
        Highlighter.Highlight[] hightlights = hilite.getHighlights();

        for (int i = 0; i < hightlights.length; i++) {

            if (hightlights[i].getPainter() instanceof MyHighlightPainter) {
                hilite.removeHighlight(hightlights[i]);
            }

        }
    }
    int projectID, issueID;

    public void setTextFieldText(String txt1, String txt2) {
        jLabel_projectID.setText(txt1);
        projectID = Integer.valueOf(txt1);
        issueID = Integer.valueOf(txt2);
        try {
            FileReader reader = new FileReader(JSON_file);

            //Java object
            Object obj = parser.parse(reader);

            //JSON object
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray array = (JSONArray) jsonObject.get("projects");

            if (projectID == Integer.valueOf(txt1) && projectID != 0) {
                JSONObject project = (JSONObject) array.get(projectID - 1);
                JSONArray issue = (JSONArray) project.get("issues");
                if (issueID == Integer.valueOf(txt2)) {
                    JSONObject issues = (JSONObject) issue.get(issueID - 1);
                    long id = (long) issues.get("id");
                    String title = (String) issues.get("title");
                    String status = (String) issues.get("status");
                    long priority = (long) issues.get("priority");
                    String assignee = (String) issues.get("assignee");
                    String createdBy = (String) issues.get("createdBy");
                    String descriptionText = (String) issues.get("descriptionText");

                    long timestamp = (long) issues.get("timestamp");
                    // convert seconds to milliseconds
                    Date dt = new Date(timestamp * 1000);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                    //timezone reference 
                    sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT"));
                    String time = sdf.format(dt);

                    JSONArray Tag = (JSONArray) issues.get("tag");
                    String tag;
                    for (int k = 0; k < Tag.size(); k++) {

                        tag = (String) Tag.get(k);

                        jLabel_issueID.setText(String.valueOf(id));
                        jLabel_status.setText(status);
                        jLabel_tag.setText(tag);
                        jLabel_priority1.setText(String.valueOf(priority));
                        jLabel_time.setText(time);
                        jLabel_title.setText(title);
                        jLabel_assignee.setText(assignee);
                        jLabel_createdBy.setText(createdBy);
                        jTextArea_descriptionText.setText(descriptionText);
                    }

                    JSONArray comments = (JSONArray) issues.get("comments");
                    if (comments != null) {
                        for (int g = 0; g < comments.size(); g++) {
                            JSONObject comment = (JSONObject) comments.get(g);
                            long comment_id = (long) comment.get("comment_id");
                            String text = (String) comment.get("text");
                            String user = (String) comment.get("user");
                            long timestamp2 = (long) comment.get("timestamp");
                            // convert seconds to milliseconds
                            Date dt2 = new Date(timestamp2 * 1000);
                            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                            //timezone reference 
                            sdf2.setTimeZone(java.util.TimeZone.getTimeZone("GMT"));
                            String time2 = sdf2.format(dt);

                            String s = ("#" + String.valueOf(comment_id) + "\n" + "Created on :" + time2 + "         "
                                    + "By: " + user + "\n" + text);

                            jTextArea_comments.setText(jTextArea_comments.getText() + "\n " + s);

                            JSONArray React = (JSONArray) comment.get("react");
                            if (React != null) {
                                for (int m = 0; m < React.size(); m++) {
                                    JSONObject react = (JSONObject) React.get(m);
                                    String reaction = (String) react.get("reaction");
                                    long count = (long) react.get("count");

                                    String str = ("$$" + String.valueOf(count) + "people react with " + reaction);
                                    jTextArea_comments.setText(jTextArea_comments.getText() + "\n " + str);
                                }
                            }
                        }
                    }

                }
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();    //shows neither the line number where the error occurred nor the functions that were executed
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel_issueID = new javax.swing.JLabel();
        jLabel_status = new javax.swing.JLabel();
        jLabel_tag = new javax.swing.JLabel();
        jLabel_priority1 = new javax.swing.JLabel();
        jLabel_time = new javax.swing.JLabel();
        jLabel_title = new javax.swing.JLabel();
        jLabel_assignee = new javax.swing.JLabel();
        jLabel_createdBy = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel_userName = new javax.swing.JLabel();
        jComboBox_updateStatus = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel_projectID = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_descriptionText = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea_comments = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel_react = new javax.swing.JLabel();
        jTextField_commentNo = new javax.swing.JTextField();
        jButton_angry = new javax.swing.JButton();
        jButton_happy = new javax.swing.JButton();
        jLabel_COMMENT = new javax.swing.JLabel();
        jTextField_comment = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Issue ID: ");

        jLabel2.setText("       Tag: ");

        jLabel3.setText("Issue Title: ");

        jLabel4.setText("Status: ");

        jLabel5.setText("Priority: ");

        jLabel6.setText("Created On: ");

        jLabel7.setText("Assigned to:");

        jLabel8.setText("Created By:");

        jLabel9.setText("Issue Description: ");

        jLabel10.setText("Comments: ");

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel11.setText("ISSUE PAGE");

        jButton1.setText("Dashboard");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jLabel_userName.setForeground(new java.awt.Color(255, 255, 255));

        jComboBox_updateStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Status", "Resolved", "Closed", "In Progress", "Reopened" }));
        jComboBox_updateStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_updateStatusActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Update status :");

        jLabel14.setText("Project ID :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_userName, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_projectID, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox_updateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel_userName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel_projectID, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox_updateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jTextArea_descriptionText.setColumns(20);
        jTextArea_descriptionText.setRows(5);
        jScrollPane1.setViewportView(jTextArea_descriptionText);

        jTextArea_comments.setColumns(20);
        jTextArea_comments.setRows(5);
        jScrollPane2.setViewportView(jTextArea_comments);

        jButton2.setText("Search");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel_react.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_react.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_react.setForeground(new java.awt.Color(204, 0, 0));
        jLabel_react.setText("react");

        jTextField_commentNo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jTextField_commentNo.setText("Comment ID");
        jTextField_commentNo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTextField_commentNo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField_commentNoMouseClicked(evt);
            }
        });

        jButton_angry.setBackground(new java.awt.Color(255, 204, 204));
        jButton_angry.setForeground(new java.awt.Color(255, 51, 51));
        jButton_angry.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Downloads\\angryly.png")); // NOI18N
        jButton_angry.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_angry.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_angryMouseClicked(evt);
            }
        });

        jButton_happy.setBackground(new java.awt.Color(255, 204, 204));
        jButton_happy.setForeground(new java.awt.Color(255, 153, 51));
        jButton_happy.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Downloads\\happily.png")); // NOI18N
        jButton_happy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_happy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_happyMouseClicked(evt);
            }
        });

        jLabel_COMMENT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_COMMENT.setText("comment");

        jButton3.setText("GO");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(10, 10, 10))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_issueID, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_tag, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_status, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel_priority1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel_time, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(17, 17, 17)
                                .addComponent(jLabel_title, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_assignee, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(141, 141, 141)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_createdBy, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 11, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_COMMENT)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField_comment, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_react, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_commentNo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_happy, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_angry, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel_issueID, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel_status, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_priority1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_tag, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel_time, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel_title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_createdBy, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel_assignee, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_react, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_commentNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_happy, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_angry, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_COMMENT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jTextField_comment, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton3))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        //show checkIssues page
        checkIssues sl = new checkIssues();
        //show the window
        sl.setVisible(true);
        //Causes the window to fit the preferred size
        sl.pack();
        //place the window in the center of the screen
        sl.setLocationRelativeTo(null);
        //pass data to checkIssues page
        sl.setLabelText(jLabel_userName.getText());
        //close the window
        this.dispose();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        highlight(jTextArea_descriptionText, jTextField1.getText());
        highlight(jTextArea_comments, jTextField1.getText());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField_commentNoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_commentNoMouseClicked
        if (jTextField_commentNo.getText().equals("Comment ID")) {
            jTextField_commentNo.setText("");
        }
    }//GEN-LAST:event_jTextField_commentNoMouseClicked

    private void jComboBox_updateStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_updateStatusActionPerformed
        try {
            FileReader reader = new FileReader(JSON_file);

            //Java object
            Object obj = parser.parse(reader);

            //JSON object
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray array = (JSONArray) jsonObject.get("projects");

            JSONObject project = (JSONObject) array.get(Integer.valueOf(jLabel_projectID.getText()) - 1);
            JSONArray issue = (JSONArray) project.get("issues");

            String s = jLabel_issueID.getText();
            JSONObject issues1 = (JSONObject) issue.get(Integer.valueOf(s) - 1);

            if (jComboBox_updateStatus.getSelectedIndex() == 0) {
                jLabel_status.setText(jLabel_status.getText());
            }
            if (jComboBox_updateStatus.getSelectedIndex() == 1) {
                issues1.put("status", "Resolved");
                jLabel_status.setText("Resolved");
            }
            if (jComboBox_updateStatus.getSelectedIndex() == 2) {
                issues1.put("status", "Closed");
                jLabel_status.setText("Closed");
            }
            if (jComboBox_updateStatus.getSelectedIndex() == 3) {
                issues1.put("status", "In Progress");
                jLabel_status.setText("In Progress");
            }
            if (jComboBox_updateStatus.getSelectedIndex() == 4) {
                issues1.put("status", "Open");
                jLabel_status.setText("Open");
            }
            try (FileWriter writer = new FileWriter("data.json")) {

                writer.write(jsonObject.toJSONString());
                writer.flush();
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();    //shows neither the line number where the error occurred nor the functions that were executed
        }
    }//GEN-LAST:event_jComboBox_updateStatusActionPerformed

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        if (jTextField_comment.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No comments added ");
        } else {
            JSONObject object = null;
            try {
                object = (JSONObject) parser.parse(new FileReader(JSON_file));
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Error!!");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            JSONArray projects = (JSONArray) object.get("projects");
            int s = Integer.valueOf(jLabel_projectID.getText());
            JSONObject project = ((JSONObject) projects.get(s - 1));
            JSONArray issues = (JSONArray) project.get("issues");

            String str = jLabel_issueID.getText();
            JSONObject issues1 = (JSONObject) issues.get(Integer.valueOf(str) - 1);

            JSONArray comments = (JSONArray) issues1.get("comments");
            Map<String, Object> newComment = new LinkedHashMap<>();

            for (int i = 0; i < comments.size(); i++) {
                JSONObject comment = ((JSONObject) comments.get(i));
                long issueID = (long) comment.get("comment_id");
            }
            long issueNO = comments.size();
            newComment.put("comment_id", ++issueNO);
            newComment.put("text", jTextField_comment.getText());
            long epoch = System.currentTimeMillis() / 1000;
            newComment.put("timestamp", epoch);
            newComment.put("user", jLabel_userName.getText());
            comments.add(newComment);

            Date dt = new Date(epoch * 1000);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            String time = sdf.format(dt);

            String string = ("#" + String.valueOf(issueNO) + "\n" + "Created on :" + time + "         "
                    + "By: " + jLabel_userName.getText() + "\n" + jTextField_comment.getText());

            jTextArea_comments.setText(jTextArea_comments.getText() + "\n " + string);

            try (FileWriter file = new FileWriter("data.json")) {
                file.write(JSONValue.toJSONString(object));

            } catch (IOException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Comment added");
        }
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton_happyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_happyMouseClicked
        jTextArea_comments.setText(null);

        try {
            FileReader reader = new FileReader(JSON_file);

            //Java object
            Object obj = parser.parse(reader);

            //JSON object
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray array = (JSONArray) jsonObject.get("projects");

            JSONObject project = (JSONObject) array.get(Integer.valueOf(jLabel_projectID.getText()) - 1);
            JSONArray issue = (JSONArray) project.get("issues");

            String s = jLabel_issueID.getText();
            JSONObject issues = (JSONObject) issue.get(Integer.valueOf(s) - 1);
            JSONArray comments = (JSONArray) issues.get("comments");
            JSONObject comment = (JSONObject) comments.get(Integer.valueOf(jTextField_commentNo.getText()) - 1);

            JSONArray React = (JSONArray) comment.get("react");

            if (React.isEmpty()) {
                JSONObject object = null;

                try {

                    object = (JSONObject) parser.parse(new FileReader(JSON_file));

                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Error!!");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                JSONArray projects = (JSONArray) object.get("projects");
                int projID = Integer.valueOf(jLabel_projectID.getText());
                JSONObject proj = ((JSONObject) projects.get(projID - 1));
                JSONArray iss = (JSONArray) proj.get("issues");
                int issID = Integer.valueOf(jLabel_issueID.getText());
                JSONObject issobj = ((JSONObject) iss.get(issID - 1));
                JSONArray cmmt = (JSONArray) issues.get("comments");
                JSONObject commt = (JSONObject) cmmt.get(Integer.valueOf(jTextField_commentNo.getText()) - 1);
                JSONArray reactList = new JSONArray();
                
                Map<String, Object> newReaction = new LinkedHashMap<>();
                
                JSONArray ReactList = new JSONArray();
                newReaction.put("angry", 0);
                newReaction.put("happy", 1);
                ReactList.add(newReaction);
                newReaction.put("react", reactList);                

                try (FileWriter file = new FileWriter("data.json")) {
                    file.write(JSONValue.toJSONString(object));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            } else {
                JSONObject react = (JSONObject) React.get(1);
                long count = (long) react.get("count");
                react.put("count", ++count);

                try (FileWriter writer = new FileWriter("data.json")) {
                    writer.write(jsonObject.toJSONString());
                    writer.flush();
                    writer.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();    //shows neither the line number where the error occurred nor the functions that were executed
        }
        try {
            FileReader reader = new FileReader(JSON_file);

            //Java object
            Object obj = parser.parse(reader);

            //JSON object
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray array = (JSONArray) jsonObject.get("projects");
            int projectID = Integer.valueOf(jLabel_projectID.getText());
            JSONObject project = (JSONObject) array.get(projectID - 1);
            JSONArray issue = (JSONArray) project.get("issues");
            int issueID = Integer.valueOf(jLabel_issueID.getText());
            JSONObject issues = (JSONObject) issue.get(issueID - 1);
            long id = (long) issues.get("id");
            String title = (String) issues.get("title");
            String status = (String) issues.get("status");
            long priority = (long) issues.get("priority");
            String assignee = (String) issues.get("assignee");
            String createdBy = (String) issues.get("createdBy");
            String descriptionText = (String) issues.get("descriptionText");

            long timestamp = (long) issues.get("timestamp");
            // convert seconds to milliseconds
            Date dt = new Date(timestamp * 1000);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            //timezone reference 
            sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT"));
            String time = sdf.format(dt);

            JSONArray Tag = (JSONArray) issues.get("tag");
            String tag;
            for (int k = 0; k < Tag.size(); k++) {

                tag = (String) Tag.get(k);

                jLabel_issueID.setText(String.valueOf(id));
                jLabel_status.setText(status);
                jLabel_tag.setText(tag);
                jLabel_priority1.setText(String.valueOf(priority));
                jLabel_time.setText(time);
                jLabel_title.setText(title);
                jLabel_assignee.setText(assignee);
                jLabel_createdBy.setText(createdBy);
                jTextArea_descriptionText.setText(descriptionText);
            }

            JSONArray comments = (JSONArray) issues.get("comments");
            if (comments != null) {
                for (int g = 0; g < comments.size(); g++) {
                    JSONObject comment = (JSONObject) comments.get(g);
                    long comment_id = (long) comment.get("comment_id");
                    String text = (String) comment.get("text");
                    String user = (String) comment.get("user");
                    long timestamp2 = (long) comment.get("timestamp");
                    // convert seconds to milliseconds
                    Date dt2 = new Date(timestamp2 * 1000);
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                    //timezone reference 
                    sdf2.setTimeZone(java.util.TimeZone.getTimeZone("GMT"));
                    String time2 = sdf2.format(dt);

                    String s = ("#" + String.valueOf(comment_id) + "\n" + "Created on :" + time2 + "         "
                            + "By: " + user + "\n" + text);

                    jTextArea_comments.setText(jTextArea_comments.getText() + "\n " + s);

                    JSONArray React = (JSONArray) comment.get("react");
                    if (React != null) {
                        for (int m = 0; m < React.size(); m++) {
                            JSONObject react = (JSONObject) React.get(m);
                            String reaction = (String) react.get("reaction");
                            long count = (long) react.get("count");

                            String str = ("$$" + String.valueOf(count) + "people react with " + reaction);
                            jTextArea_comments.setText(jTextArea_comments.getText() + "\n " + str);
                        }
                    }
                }
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();    //shows neither the line number where the error occurred nor the functions that were executed
        }
    }//GEN-LAST:event_jButton_happyMouseClicked

    private void jButton_angryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_angryMouseClicked
        jTextArea_comments.setText(null);

        try {
            FileReader reader = new FileReader(JSON_file);

            //Java object
            Object obj = parser.parse(reader);

            //JSON object
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray array = (JSONArray) jsonObject.get("projects");

            JSONObject project = (JSONObject) array.get(Integer.valueOf(jLabel_projectID.getText()) - 1);
            JSONArray issue = (JSONArray) project.get("issues");

            String s = jLabel_issueID.getText();
            JSONObject issues = (JSONObject) issue.get(Integer.valueOf(s) - 1);
            JSONArray comments = (JSONArray) issues.get("comments");
            JSONObject comment = (JSONObject) comments.get(Integer.valueOf(jTextField_commentNo.getText()) - 1);
            JSONArray React = (JSONArray) comment.get("react");
            JSONObject react = (JSONObject) React.get(0);
            long count = (long) react.get("count");
            react.put("count", ++count);

            try (FileWriter writer = new FileWriter("data.json")) {
                writer.write(jsonObject.toJSONString());
                writer.flush();
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();    //shows neither the line number where the error occurred nor the functions that were executed

        }

        try {
            FileReader reader = new FileReader(JSON_file);

            //Java object
            Object obj = parser.parse(reader);

            //JSON object
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray array = (JSONArray) jsonObject.get("projects");
            int projectID = Integer.valueOf(jLabel_projectID.getText());
            JSONObject project = (JSONObject) array.get(projectID - 1);
            JSONArray issue = (JSONArray) project.get("issues");
            int issueID = Integer.valueOf(jLabel_issueID.getText());
            JSONObject issues = (JSONObject) issue.get(issueID - 1);
            long id = (long) issues.get("id");
            String title = (String) issues.get("title");
            String status = (String) issues.get("status");
            long priority = (long) issues.get("priority");
            String assignee = (String) issues.get("assignee");
            String createdBy = (String) issues.get("createdBy");
            String descriptionText = (String) issues.get("descriptionText");

            long timestamp = (long) issues.get("timestamp");
            // convert seconds to milliseconds
            Date dt = new Date(timestamp * 1000);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            //timezone reference 
            sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT"));
            String time = sdf.format(dt);

            JSONArray Tag = (JSONArray) issues.get("tag");
            String tag;
            for (int k = 0; k < Tag.size(); k++) {

                tag = (String) Tag.get(k);

                jLabel_issueID.setText(String.valueOf(id));
                jLabel_status.setText(status);
                jLabel_tag.setText(tag);
                jLabel_priority1.setText(String.valueOf(priority));
                jLabel_time.setText(time);
                jLabel_title.setText(title);
                jLabel_assignee.setText(assignee);
                jLabel_createdBy.setText(createdBy);
                jTextArea_descriptionText.setText(descriptionText);
            }

            JSONArray comments = (JSONArray) issues.get("comments");
            if (comments != null) {
                for (int g = 0; g < comments.size(); g++) {
                    JSONObject comment = (JSONObject) comments.get(g);
                    long comment_id = (long) comment.get("comment_id");
                    String text = (String) comment.get("text");
                    String user = (String) comment.get("user");
                    long timestamp2 = (long) comment.get("timestamp");
                    // convert seconds to milliseconds
                    Date dt2 = new Date(timestamp2 * 1000);
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                    //timezone reference 
                    sdf2.setTimeZone(java.util.TimeZone.getTimeZone("GMT"));
                    String time2 = sdf2.format(dt);

                    String s = ("#" + String.valueOf(comment_id) + "\n" + "Created on :" + time2 + "         "
                            + "By: " + user + "\n" + text);

                    jTextArea_comments.setText(jTextArea_comments.getText() + "\n " + s);

                    JSONArray React = (JSONArray) comment.get("react");
                    if (React != null) {
                        for (int m = 0; m < React.size(); m++) {
                            JSONObject react = (JSONObject) React.get(m);
                            String reaction = (String) react.get("reaction");
                            long count = (long) react.get("count");

                            String str = ("$$" + String.valueOf(count) + "people react with " + reaction);
                            jTextArea_comments.setText(jTextArea_comments.getText() + "\n " + str);
                        }
                    }
                }
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();    //shows neither the line number where the error occurred nor the functions that were executed
        }
    }//GEN-LAST:event_jButton_angryMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(issue_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(issue_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(issue_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(issue_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new issue_page().setVisible(true);

            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton_angry;
    private javax.swing.JButton jButton_happy;
    private javax.swing.JComboBox<String> jComboBox_updateStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_COMMENT;
    private javax.swing.JLabel jLabel_assignee;
    private javax.swing.JLabel jLabel_createdBy;
    private javax.swing.JLabel jLabel_issueID;
    private javax.swing.JLabel jLabel_priority1;
    private javax.swing.JLabel jLabel_projectID;
    private javax.swing.JLabel jLabel_react;
    private javax.swing.JLabel jLabel_status;
    private javax.swing.JLabel jLabel_tag;
    private javax.swing.JLabel jLabel_time;
    private javax.swing.JLabel jLabel_title;
    private javax.swing.JLabel jLabel_userName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea_comments;
    private javax.swing.JTextArea jTextArea_descriptionText;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField_comment;
    private javax.swing.JTextField jTextField_commentNo;
    // End of variables declaration//GEN-END:variables
}
